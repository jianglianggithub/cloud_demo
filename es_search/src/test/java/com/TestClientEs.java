package com;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.MaxAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ValueCount;
import org.elasticsearch.search.aggregations.metrics.ValueCountAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.*;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

@RunWith(SpringRunner.class)
@SpringBootTest

public class TestClientEs {

    public static void main(String[] args) {
        System.out.println("discovery.seed_hosts: \n" +
                "    - es-master\n" +
                "    - node2\n" +
                "    - node3");
    }

    @Autowired
    private RestHighLevelClient restClient;
    @Autowired

    private RestClient client;

    @Test
    public void test1() throws IOException {
        for (int i = 100; i < 200; i++) {
            Random random = new Random();
            IndexRequest type = new IndexRequest("aaa").id(i+"");
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("title","a b c "+i);
            hashMap.put("name",(char)(random.nextInt(3)+97) +i%3);
            hashMap.put("name1",(char)(random.nextInt(3)+97) +i%3+"name2");

            hashMap.put("created",new Date().getTime());
            type.source(hashMap);
            IndexResponse index = restClient.index(type, RequestOptions.DEFAULT);
            System.out.println(index.status().getStatus());
        }


    }

    @Test
    public void test12() throws IOException {
        SearchRequest searchRequest = new SearchRequest("index1").types("doc");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = restClient.search(searchRequest,RequestOptions.DEFAULT);
        System.out.println(search.getHits().getHits().length);
    }

    @Test
    public void test11(){

    }

    @Test
    public void test121() throws IOException {
        GetRequest getRequest = new GetRequest(
                "index22",
                "_doc","1");
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");
        boolean exists = restClient.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    /**
     *  scroll api 类似于 指针。一次取多少个 如果直接用seach 每次都是把前面的io出来在 查询 就会查很多重复的数据
     * @throws IOException
     */
    @Test
    public void test1221() throws IOException {
        final Scroll scroll = new Scroll(TimeValue.timeValueMinutes(1L));
        SearchRequest searchRequest = new SearchRequest("index22");
        searchRequest.searchType(SearchType.QUERY_THEN_FETCH);

        searchRequest.scroll(scroll);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(1);
        SearchResponse searchResponse = restClient.search(searchRequest, RequestOptions.DEFAULT);
        String scrollId = searchResponse.getScrollId();
        SearchHit[] searchHits = searchResponse.getHits().getHits();

        while (searchHits != null && searchHits.length > 0) {

            SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
            scrollRequest.scroll(scroll);
            searchResponse = restClient.scroll(scrollRequest, RequestOptions.DEFAULT);
            scrollId = searchResponse.getScrollId();
            searchHits = searchResponse.getHits().getHits();
            for (SearchHit searchHit : searchHits) {
                System.out.print(searchHit.getSourceAsMap());
            }
            System.out.println("=======================");
        }

        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
        clearScrollRequest.addScrollId(scrollId);

        ClearScrollResponse clearScrollResponse = restClient.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
        boolean succeeded = clearScrollResponse.isSucceeded();
    }


    @Test
    public void taest11() throws IOException {

        SearchRequest searchRequest = new SearchRequest("index22");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = restClient.search(searchRequest, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
        System.out.println(response.getHits().getHits().length);
    }

    /**
     *
     *
     * 在ES 中。 如果对一个 text 字段【会分词 建立倒排索引】 那么使用聚合的时候会报错。
     *  terms 是给一个 filed 进行分组
     *
     *   为什么不能呢 因为 带有text 的会分词  分词后在次 分组的话本来就有问题了。。。有的doc 在每个词都有 这样计数不准确的。
     *   一般采用 keyworld 来进行分组 才有意义。 默认text 分组其实就是把 这个feild 所有的分词 的doc 拿过来了。
     *      这个肯定不准确的。
     *
     *
     *
     *
     *      es 里面是支持 多层 嵌套的。
     * @throws IOException
     */
    @Test
    public void tesasdt11() throws IOException {

        SearchRequest request = new SearchRequest("aaa");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        TermsAggregationBuilder r1 = AggregationBuilders.terms("r1").field("name");
        TermsAggregationBuilder r2 = AggregationBuilders.terms("r2").field("name1");
        ValueCountAggregationBuilder e3 = AggregationBuilders.count("r3").field("name");
        r1.subAggregation(r2);
        r2.subAggregation(e3);
        //aggregation.subAggregation(AggregationBuilders.avg("啊啊").field("name"));
        searchSourceBuilder.aggregation(r1);
        //searchSourceBuilder.aggregation(e3);
        request.source(searchSourceBuilder);
        SearchResponse search = restClient.search(request,RequestOptions.DEFAULT);
        Aggregations aggregations = search.getAggregations();
        ParsedStringTerms groups = aggregations.get("r1");
        Iterator<? extends Terms.Bucket> iterator = groups.getBuckets().iterator();
        while (iterator.hasNext()) {
            Terms.Bucket next = iterator.next();

            Terms terms = next.getAggregations().get("r2");
            for (Terms.Bucket bucket : terms.getBuckets()) {
                System.out.print(next.getKeyAsString()+"  ");
                System.out.print(next.getDocCount()+"  ");
                System.out.print(bucket.getKeyAsString()+"  ");
                System.out.print(bucket.getDocCount()+"  ");
                ValueCount r3 = bucket.getAggregations().get("r3");
                System.out.print("个数"+r3.getValue());
                System.out.println();
            }
            System.out.println("========================");
        }
    }
}
