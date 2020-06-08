package com.wxl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.wxl.system.System01Application;
import com.wxl.system.entity.*;
import lombok.SneakyThrows;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.naming.directory.SearchResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = System01Application.class)
@RunWith(SpringRunner.class)
public class TestES {

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    //测试索引的创建 Request PUT wuxiaoli_index
    @Test
    public void testCreateIndex() throws IOException {
        //创建索引请求
        CreateIndexRequest request = new CreateIndexRequest("test2");
        //客户端执行请求，请求后获得响应
        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);

        System.out.println(createIndexResponse);
    }


    //测试获取索引,判断其是否存在
    @Test
    public void testExistIndex() throws Exception{
        GetIndexRequest request = new GetIndexRequest("wuxiaoli_index");
        boolean exists = client.indices().exists(request,RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    //测试删除索引
    public void testDeleteIndex() throws Exception{
        DeleteIndexRequest request = new DeleteIndexRequest("wuxiaoli_index");
        //删除
        AcknowledgedResponse delete = client.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());
    }

    //测试添加文档
    @Test
    public void testAddDocument() throws IOException {
        //创建对象
       // Role_Perm role_perm = new Role_Perm(9, 8);

       // Perm perm = new Perm(1, "吴小莉", "wxl");

        Role role = new Role(1,"吴美丽","吴小莉","wxl");

        //创建请求
        IndexRequest request = new IndexRequest("wuxiaoli_index");
        //规则 PUT /wuxiaoli_index/_doc/1
        request.id("1");
        request.timeout(TimeValue.timeValueSeconds(1));
        request.timeout("1s");

        //将数据放入请求 JSON
        //request.source(JSON.toJSONString(role_perm), XContentType.JSON);

        request.source(JSON.toJSONString(role), XContentType.JSON);

        //客户端发送请求,获取相应结果
        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);

       System.out.println(indexResponse.toString());
       System.out.println(indexResponse.status());//对应我们命令返回的状态（create等）
    }

    //获取文档，判断是否存在 get /index/doc/1
    @Test
    public void testIsExists() throws IOException {
        GetRequest getRequest = new GetRequest("wuxiaoli_index", "1");
        //不获取返回的 _source的上下文
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");

        boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);

        System.out.println(exists);

    }

    //获取文档的信息
    @Test
    public void testGetDocument() throws IOException {
        GetRequest getRequest = new GetRequest("wuxiaoli_index", "56iPg3IBJYhcbPafxa7u");

        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);

        System.out.println(getResponse.getSourceAsString());//打印文档内容

        System.out.println(getResponse);
    }

    //更新文档的信息
    @Test
    public void testUpdateDocument() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("wuxiaoli_index", "56iPg3IBJYhcbPafxa7u");
        updateRequest.timeout("1s");

        Role_Perm role_perm = new Role_Perm(8, 8);

        updateRequest.doc(JSON.toJSONString(role_perm),XContentType.JSON);

        UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);

        System.out.println(updateResponse.status());
    }

    //删除文档信息
    @Test
    public void testDeleteRequest() throws Exception{
        DeleteRequest request = new DeleteRequest("student_index", "14");
        request.timeout("1s");

        DeleteResponse deleteResponse = client.delete(request, RequestOptions.DEFAULT);

        System.out.println(deleteResponse.status());
    }

    //特殊的，真的项目一般会批量插入数据
    @Test
    public void testBulkRequest() throws Exception{

        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");

        ArrayList<Role>  roles = new ArrayList<>();
        roles.add(new Role(1,"吴小莉","吴小莉","吴小莉"));
        roles.add(new Role(2,"代美华","代美华","代美华"));
        roles.add(new Role(3,"苏一清","苏一清","苏一清"));

        ArrayList<Permission> permissions = new ArrayList<>();
        permissions.add(new Permission(1,"吴小莉","吴小莉","吴小莉"));
        permissions.add(new Permission(2,"代美华","代美华","代美华"));
        permissions.add(new Permission(3,"苏一清","苏一清","苏一清"));


        //批处理请求
        //批量跟新、删除都在这里操作，修改对应的请求

        for (int i=0;i<roles.size();i++){
            bulkRequest.add(
                    new IndexRequest("wuxiaoli")
                            .id(""+(i+1))
                            .source(JSON.toJSONString(roles.get(i)),XContentType.JSON));
        }

        for (int i=0;i<permissions.size();i++){
            bulkRequest.add(
                    new IndexRequest("wuxiaoli")
                            .id(""+(i+1+roles.size()))
                            .source(JSON.toJSONString(permissions.get(i)),XContentType.JSON));
        }

        BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);

        System.out.println(bulkResponse.hasFailures());//操作是否失败，返回false代表成功

    }


    //查询
    @Test
    public void testSearch() throws IOException {
        SearchRequest searchRequest = new SearchRequest("student_index");

        //构建搜索条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        //构建查询
        //QueryBuilders.termQuery精确匹配
        //QueryBuilders.matchAllQuery() 匹配所有
        //TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("role_id", "2");

        MatchPhraseQueryBuilder termQueryBuilder = QueryBuilders.matchPhraseQuery("cno","000001");

        sourceBuilder.query(termQueryBuilder);
        //分页
        /*sourceBuilder.from();
        sourceBuilder.size();*/
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println(JSON.toJSONString(searchResponse.getHits()));
        System.out.println("===========================================");
        for (SearchHit documentFields : searchResponse.getHits().getHits()){
            System.out.println(documentFields.getSourceAsMap());
        }

    }


}
