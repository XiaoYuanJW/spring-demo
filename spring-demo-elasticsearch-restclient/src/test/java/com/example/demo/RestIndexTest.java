package com.example.demo;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * ElasticSearchRestClient索引库操作测试类
 * Created by YuanJW on 2022/11/7.
 */
@Slf4j
public class RestIndexTest {
    private RestHighLevelClient restHighLevelClient;

    String PRODUCT_TEMPLATE;

    @BeforeEach
    void setUp() {
        // 初始化RestHighLevelClient
        this.restHighLevelClient = new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://1.117.34.49:9200")
        ));
        // 加载资源访问类的json文件
        ClassPathResource classPathResource = new ClassPathResource("template/product.json");
        this.PRODUCT_TEMPLATE = IoUtil.read(classPathResource.getStream(), Charset.forName("UTF-8"));
    }

    @AfterEach
    void tearDown() throws IOException {
        // 关闭客户端资源
        this.restHighLevelClient.close();
    }

    @Test
    void testInit() {
        System.out.println(this.restHighLevelClient);
    }

    @Test
    void createProductIndex() throws IOException {
        // 创建CreateIndexRequest对象
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("product");
        // 设置请求参数：PRODUCT_TEMPLATE-DSL语句 XContentType.JSON
        createIndexRequest.source(PRODUCT_TEMPLATE, XContentType.JSON);
        // 发送请求
        this.restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
    }

    @Test
    void deleteProductIndex() throws IOException {
        // 创建DeleteIndexRequest对象
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("product");
        // 发送请求
        this.restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
    }

    @Test
    void existsProductIndex() throws IOException {
        // 创建GetIndexRequest对象
        GetIndexRequest getIndexRequest = new GetIndexRequest("product");
        // 发送请求，接收结果
        boolean exists = this.restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        System.out.println(exists ? "索引库存在" : "索引库不存在");
    }

}
