package com.wxl.system.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.wxl.system.entity.*;
import com.wxl.system.utils.ESconst;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional

@Slf4j
public class ElasticSearchInitImpl implements ElasticSearchInit {

    @Autowired
    private RestHighLevelClient client;
    @Autowired
    private ElasticSearchService elasticSearchService;


    //创建索引
    @Override
    public void CreateIndex() throws IOException {
        //创建索引请求
        CreateIndexRequest request = new CreateIndexRequest(ESconst.student_index);
        CreateIndexRequest request1 = new CreateIndexRequest(ESconst.manager_index);
        CreateIndexRequest request2 = new CreateIndexRequest(ESconst.optional_major_index);
        CreateIndexRequest request3 = new CreateIndexRequest(ESconst.sendinfo_role_index);
        CreateIndexRequest request4 = new CreateIndexRequest(ESconst.tc_user_index);
        CreateIndexRequest request5 = new CreateIndexRequest(ESconst.teacher_sc_index);

        //客户端执行请求，请求后获得响应
        client.indices().create(request, RequestOptions.DEFAULT);
        client.indices().create(request1, RequestOptions.DEFAULT);
        client.indices().create(request2, RequestOptions.DEFAULT);
        client.indices().create(request3, RequestOptions.DEFAULT);
        client.indices().create(request4, RequestOptions.DEFAULT);
        client.indices().create(request5, RequestOptions.DEFAULT);

    }

    //批量添加文档
    public boolean BulkRequest() throws IOException {

        BulkRequest bulkRequest = new BulkRequest();

        bulkRequest.timeout("1000s");

        List<EScollege> eScolleges = elasticSearchService.findESCollege();
        List<EScourse> eScourses = elasticSearchService.findESCourse();
        List<ESmajor> eSmajors = elasticSearchService.findESMajor();
        List<ESmanager> eSmanagers = elasticSearchService.findESManager();
        List<ESoptional> eSoptionals = elasticSearchService.findESOptional();
        List<ESpermission> eSpermissions = elasticSearchService.findESPermission();
        List<ESrole> eSroles = elasticSearchService.findESRole();
        List<ESrole_perm> eSrole_perms = elasticSearchService.findESRole_perm();
        List<ESsc> eSscs = elasticSearchService.findESSc();
        List<ESsendinfo> eSsendinfos = elasticSearchService.findESSendinfo();
        List<ESstudent> eSstudents = elasticSearchService.findESStudent();
        List<EStc> eStcs = elasticSearchService.findESTc();
        List<ESteacher> eSteachers = elasticSearchService.findESTeacher();
        List<ESuser> eSusers = elasticSearchService.findESUser();


        //批处理请求
        //批量跟新、删除都在这里操作，修改对应的请求

        //==================================================student_index=============================================================//
        //批量将学院信息，批量插入学生索引中
        for (int i=0;i<eScolleges.size();i++){
            bulkRequest.add(
                    new IndexRequest(ESconst.student_index)
                            .id(""+(i+1))
                            .source(JSON.toJSONString(eScolleges.get(i)),XContentType.JSON));
        }

        //将课程信息，批量插入学生索引中
        for (int i=0;i<eScourses.size();i++){
            bulkRequest.add(
                    new IndexRequest(ESconst.student_index)
                            .id(""+(i+1+eScolleges.size()))
                            .source(JSON.toJSONString(eScourses.get(i)),XContentType.JSON));
        }


        //将学生信息，批量插入学生索引
        for (int i=0;i<eSstudents.size();i++){
            bulkRequest.add(
                    new IndexRequest(ESconst.student_index)
                            .id(""+(i+1+eScolleges.size()+eScourses.size()))
                            .source(JSON.toJSONString(eSstudents.get(i)),XContentType.JSON));
        }

        //==================================================optional_major_index=============================================================//
        //将专业信息，批量插入学生索引中
        for (int i=0;i<eSmajors.size();i++){
            bulkRequest.add(
                    new IndexRequest(ESconst.optional_major_index)
                            .id(""+(i+1))
                            .source(JSON.toJSONString(eSmajors.get(i)),XContentType.JSON));
        }

        //将选课信息，批量插入学生索引中
        for (int i=0;i<eSoptionals.size();i++){
            bulkRequest.add(
                    new IndexRequest(ESconst.optional_major_index)
                            .id(""+(i+1+eSmajors.size()))
                            .source(JSON.toJSONString(eSoptionals.get(i)),XContentType.JSON));
        }


        //==================================================manager_index=============================================================//
        //将管理员信息，批量插入管理员索引
        for (int i=0;i<eSmanagers.size();i++){
            bulkRequest.add(
                    new IndexRequest(ESconst.manager_index)
                            .id(""+(i+1))
                            .source(JSON.toJSONString(eSmanagers.get(i)),XContentType.JSON));
        }


        //将权限信息，批量插入管理员索引
        for (int i=0;i<eSpermissions.size();i++){
            bulkRequest.add(
                    new IndexRequest(ESconst.manager_index)
                            .id(""+(i+1+eSmanagers.size()))
                            .source(JSON.toJSONString(eSpermissions.get(i)),XContentType.JSON));
        }


        //将角色-权限信息，批量插入管理员索引
        for (int i=0;i<eSrole_perms.size();i++){
            bulkRequest.add(
                    new IndexRequest(ESconst.manager_index)
                            .id(""+(i+1+eSmanagers.size()+eSpermissions.size()))
                            .source(JSON.toJSONString(eSrole_perms.get(i)),XContentType.JSON));
        }

        //==================================================sendinfo_role_index=============================================================//
        //将角色信息，批量插入管理员索引
        for (int i=0;i<eSroles.size();i++){
            bulkRequest.add(
                    new IndexRequest(ESconst.sendinfo_role_index)
                            .id(""+(i+1))
                            .source(JSON.toJSONString(eSroles.get(i)),XContentType.JSON));
        }


        //将通知信息，批量插入管理员索引
        for (int i=0;i<eSsendinfos.size();i++){
            bulkRequest.add(
                    new IndexRequest(ESconst.sendinfo_role_index)
                            .id(""+(i+1+eSroles.size()))
                            .source(JSON.toJSONString(eSsendinfos.get(i)),XContentType.JSON));
        }

        //==================================================teacher_sc_index=============================================================//

        //将学生选课信息，批量插入学生索引
        for (int i=0;i<eSscs.size();i++){
            bulkRequest.add(
                    new IndexRequest(ESconst.teacher_sc_index)
                            .id(""+(i+1))
                            .source(JSON.toJSONString(eSscs.get(i)),XContentType.JSON));
        }

        //将教师信息，批量插入教师索引
        for (int i=0;i<eSteachers.size();i++){
            bulkRequest.add(
                    new IndexRequest(ESconst.teacher_sc_index)
                            .id(""+(i+1+eSscs.size()))
                            .source(JSON.toJSONString(eSteachers.get(i)),XContentType.JSON));
        }


        //==================================================tc_user_index=============================================================//

        //将通知信息，批量插入管理员索引
        for (int i=0;i<eSusers.size();i++){
            bulkRequest.add(
                    new IndexRequest(ESconst.tc_user_index)
                            .id(""+(i+1))
                            .source(JSON.toJSONString(eSusers.get(i)),XContentType.JSON));
        }

        //将授课信息，批量插入教师索引
        for (int i=0;i<eStcs.size();i++){
            bulkRequest.add(
                    new IndexRequest(ESconst.tc_user_index)
                            .id(""+(i+1+eSusers.size()))
                            .source(JSON.toJSONString(eStcs.get(i)),XContentType.JSON));
        }
        BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);

        return !bulkResponse.hasFailures();
    }

}
