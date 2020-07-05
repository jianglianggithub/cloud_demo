import com.App;

import com.entity.TestCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.*;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class RedisGeoTest {


    @Autowired
    MongoTemplate template;


    @Test
    public void test(){
        TestCollection testCollection = new TestCollection();
        //testCollection.setId("1");
        testCollection.setInfo("asd");
        testCollection.setCreateTime(LocalDateTime.now());
        template.save(testCollection);
    }

    @Test
    public void tes1(){
        List<TestCollection> all = template.findAll(TestCollection.class);
        System.out.println(all);
        //Query.query(Criteria.where().)
    }

    public String updateBook() {
        TestCollection testCollection = new TestCollection();
        Query query = new Query(Criteria.where("_id").is(testCollection.getId()));
        Update update = new Update().set("publish", testCollection.getPublish()).set("info", testCollection.getInfo()).set("updateTime",
                new Date());
        // updateFirst 更新查询返回结果集的第一条
        template.updateFirst(query, update, TestCollection.class);
        // updateMulti 更新查询返回结果集的全部
        // mongoTemplate.updateMulti(query,update,Book.class);
        // upsert 更新对象不存在则去添加
        // mongoTemplate.upsert(query,update,Book.class);
        return "success";
    }

}
