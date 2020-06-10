package com.wxl.system.service;


import com.wxl.system.entity.EScourse;
import com.wxl.system.utils.ESconst;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class SeachServiceImpl implements SeachService {

    @Autowired
    private RestHighLevelClient client;


      /*  //分页
        sourceBuilder.from((pageNo-1)*pageSize);
        sourceBuilder.size(pageSize);*/

    //获取数据实现搜索功能
    @Override
    public List<Map<String,Object>> searchPage1(String keyword) throws IOException {

        //条件搜索（先查询学生索引）
        SearchRequest searchRequest = new SearchRequest(ESconst.student_index);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders
                .multiMatchQuery(keyword,"dept", "college","cname","sname","cno")
                .minimumShouldMatch("70%");

        sourceBuilder.query(multiMatchQueryBuilder);

        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        //执行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        //解析结果
        ArrayList<Map<String,Object>> list = new ArrayList<>();

        for (SearchHit documentFields : searchResponse.getHits().getHits()){
            list.add(documentFields.getSourceAsMap());
        }
        return list;
    }

    //获取数据实现搜索功能
    @Override
    public List<Map<String,Object>> searchPage2(String keyword) throws IOException {

        //条件搜索（先查询学生索引）
        SearchRequest searchRequest = new SearchRequest(ESconst.manager_index);

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders
                .multiMatchQuery(keyword,"mno", "mname","name","tel","position")
                .minimumShouldMatch("70%");

        sourceBuilder.query(multiMatchQueryBuilder);

        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        //执行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        //解析结果
        ArrayList<Map<String,Object>> list = new ArrayList<>();

        for (SearchHit documentFields : searchResponse.getHits().getHits()){
            list.add(documentFields.getSourceAsMap());
        }
        return list;
    }


    //获取数据实现搜索功能
    @Override
    public List<Map<String,Object>> searchPage3(String keyword) throws IOException {

        //条件搜索（先查询学生索引）
        SearchRequest searchRequest = new SearchRequest(ESconst.optional_major_index);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders
                .multiMatchQuery(keyword,"major", "tname","cname","term","cno")
                .minimumShouldMatch("70%");

        sourceBuilder.query(multiMatchQueryBuilder);

        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        //执行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        //解析结果
        ArrayList<Map<String,Object>> list = new ArrayList<>();

        for (SearchHit documentFields : searchResponse.getHits().getHits()){
            list.add(documentFields.getSourceAsMap());
        }
        return list;
    }


    //获取数据实现搜索功能
    @Override
    public List<Map<String,Object>> searchPage4(String keyword) throws IOException {

        //条件搜索（先查询学生索引）
        SearchRequest searchRequest = new SearchRequest(ESconst.sendinfo_role_index);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders
                .multiMatchQuery(keyword,"title.keyword", "content.keyword","role_id.keyword","name.keyword","code.keyword")
                .minimumShouldMatch("70%");

        sourceBuilder.query(multiMatchQueryBuilder);

        sourceBuilder.timeout(new TimeValue(120, TimeUnit.SECONDS));
        //执行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        //解析结果
        ArrayList<Map<String,Object>> list = new ArrayList<>();

        for (SearchHit documentFields : searchResponse.getHits().getHits()){
            list.add(documentFields.getSourceAsMap());
        }
        return list;
    }


    //获取数据实现搜索功能
    @Override
    public List<Map<String,Object>> searchPage5(String keyword) throws IOException {

        //条件搜索（先查询学生索引）
        SearchRequest searchRequest = new SearchRequest(ESconst.tc_user_index);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders
                .multiMatchQuery(keyword,"account.keyword", "tno.keyword","place.keyword","grade.keyword","cno.keyword")
                .minimumShouldMatch("70%");

        sourceBuilder.query(multiMatchQueryBuilder);

        sourceBuilder.timeout(new TimeValue(120, TimeUnit.SECONDS));
        //执行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        //解析结果
        ArrayList<Map<String,Object>> list = new ArrayList<>();

        for (SearchHit documentFields : searchResponse.getHits().getHits()){
            list.add(documentFields.getSourceAsMap());
        }
        return list;
    }

    //获取数据实现搜索功能
    @Override
    public List<Map<String,Object>> searchPage6(String keyword) throws IOException {

        //条件搜索（先查询学生索引）
        SearchRequest searchRequest = new SearchRequest(ESconst.teacher_sc_index);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders
                .multiMatchQuery(keyword,"tno", "tname","term","type","tel")
                .minimumShouldMatch("70%");

        sourceBuilder.query(multiMatchQueryBuilder);

        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        //执行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        //解析结果
        ArrayList<Map<String,Object>> list = new ArrayList<>();

        for (SearchHit documentFields : searchResponse.getHits().getHits()){
            list.add(documentFields.getSourceAsMap());
        }
        return list;
    }

    //获取数据实现搜索功能
    @Override
    public List<Map<String,Object>> searchPage7(String keyword) throws IOException {

        //条件搜索（先查询日志索引）
        SearchRequest searchRequest = new SearchRequest(ESconst.log);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        //"message", "logger_name","level_string"
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders
                .multiMatchQuery(keyword,"message", "logger_name","level_string")
                .minimumShouldMatch("70%");

        sourceBuilder.query(multiMatchQueryBuilder);

        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        //执行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        //解析结果
        ArrayList<Map<String,Object>> list = new ArrayList<>();

        for (SearchHit documentFields : searchResponse.getHits().getHits()){
            list.add(documentFields.getSourceAsMap());
        }
        return list;
    }

    //获取日志的获取功能
    @Override
    public List<Map<String,Object>> searchPage8(String keyword) throws IOException, ParseException {


        //条件搜索（先查询日志索引）
        SearchRequest searchRequest = new SearchRequest(ESconst.log);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        //WildcardQueryBuilder wildcardQuery = QueryBuilders.wildcardQuery("created_time", keyword);

        //FuzzyQueryBuilder fuzzyQuery = QueryBuilders.fuzzyQuery("create_time", keyword);

        //PrefixQueryBuilder prefixQuery = QueryBuilders.prefixQuery("created_time", keyword);

        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders
                .multiMatchQuery(keyword,"created_time")
                .minimumShouldMatch("50%");


        sourceBuilder.query(multiMatchQueryBuilder);

        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        //执行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        //解析结果
        ArrayList<Map<String,Object>> list = new ArrayList<>();

        for (SearchHit documentFields : searchResponse.getHits().getHits()){
            list.add(documentFields.getSourceAsMap());
        }
        return list;
    }


    public List<Map<String,Object>> searchPage9(String keyword)
    {
        ArrayList<Map<String,Object>> list = new ArrayList<>();


        return list;
    }

}
