package com.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;

@Configuration
public class EsConfig {

    @Value("${elasticsearch.cluster-nodes}")
    private String clusterNodes;

    @Value("${elasticsearch.cluster-name}")
    private String clusterName;


    public EsConfig() {
    }

    @Bean
    public RestClientBuilder builder(){
        // 如果有多个从节点可以持续在内部new多个HttpHost，参数1是ip,参数2是HTTP端口，参数3是通信协议
        String[] split = clusterNodes.split(",");
        HttpHost[] httpHosts= new HttpHost[split.length];

        for (int i = 0; i < httpHosts.length; i++) {
            httpHosts[i] = new HttpHost(split[i],9200,"http");
        }
        return RestClient.builder(httpHosts);
    }

    @Bean
    public RestClient client(){

        return builder().build();
    }

    @Bean
    public RestHighLevelClient  highLevelClient() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        RestHighLevelClient client = new RestHighLevelClient(builder());


        return client;
    }

}
