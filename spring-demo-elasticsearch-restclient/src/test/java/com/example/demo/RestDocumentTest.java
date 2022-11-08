package com.example.demo;

import cn.hutool.json.JSONUtil;
import com.example.demo.entity.PmsProduct;
import com.example.demo.nosql.elasticsearch.document.EsProduct;
import com.example.demo.service.PmsProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * ElasticSearchRestClient文档操作测试类
 * Created by YuanJW on 2022/11/8.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RestDocumentTest {
    @Resource
    private PmsProductService pmsProductService;

    private RestHighLevelClient restHighLevelClient;

    @Before
    public void setUp() {
        // 初始化RestHighLevelClient
        this.restHighLevelClient = new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://1.117.34.49:9200")
        ));
    }

    @After
    public void tearDown() throws IOException {
        // 关闭客户端资源
        this.restHighLevelClient.close();
    }

    @Test
    public void testInit() {
        System.out.println(this.restHighLevelClient);
    }

    @Test
    public void postIndexDocument() throws IOException {
        // 获取Json对象
        PmsProduct pmsProduct = pmsProductService.getPmsProductById(1L);
        EsProduct esProduct = new EsProduct();
        // 拷贝为es商品信息实体
        BeanUtils.copyProperties(pmsProduct, esProduct);
        // 序列化为JSON
        String source = JSONUtil.toJsonStr(esProduct);
        // 创建IndexRequest对象
        IndexRequest indexRequest = new IndexRequest("product").id(String.valueOf(esProduct.getId()));
        // 设置请求参数
        indexRequest.source(source, XContentType.JSON);
        // 发送请求
        this.restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    }

    @Test
    public void getIndexDocument() throws IOException{
        // 创建GetRequest对象
        GetRequest getRequest = new GetRequest("product", "1");
        // 发送请求
        GetResponse getResponse = this.restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        // 获取source
        String sourceAsString = getResponse.getSourceAsString();
        // 将json放序列化为对象
        EsProduct esProduct = JSONUtil.toBean(sourceAsString, EsProduct.class);
        System.out.println(esProduct);
    }

    @Test
    public void deleteIndexDocument() throws IOException {
        // 创建DeleteRequest对象
        DeleteRequest deleteRequest = new DeleteRequest("product", "1");
        // 发送请求
        this.restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
    }

    @Test
    public void updateIndexDocument() throws IOException {
        // 创建UpdateRequest对象
        UpdateRequest updateRequest = new UpdateRequest("product", "1");
        // 准备参数
        updateRequest.doc("price","128", "sale", "10");
        // 发送请求
        this.restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
    }

    @Test
    public void bulkIndexDocument() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        List<PmsProduct> pmsProducts = pmsProductService.getPmsProducts(new PmsProduct());
        pmsProducts.forEach(pmsProduct -> {
            // 对象赋值
            EsProduct esProduct = new EsProduct(pmsProduct);
            // 遍历添加准备参数
            bulkRequest.add(new IndexRequest("product")
                    .id(String.valueOf(esProduct.getId()))
                    .source(JSONUtil.toJsonStr(esProduct), XContentType.JSON));
        });
        // 发送请求
        this.restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
    }
}

