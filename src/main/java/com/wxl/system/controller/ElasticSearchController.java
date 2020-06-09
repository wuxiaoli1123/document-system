package com.wxl.system.controller;

import com.wxl.system.entity.*;
import com.wxl.system.service.ElasticSearchInit;
import com.wxl.system.service.ElasticSearchService;
import com.wxl.system.service.SeachService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("elasticsearch")
@Slf4j

public class ElasticSearchController {

    @Autowired
    private ElasticSearchInit elasticSearchInit;

    @Autowired
    private SeachService seachService;

    @Autowired
    private ElasticSearchService elasticSearchService;

    @GetMapping("init")
    public void CreateIndex() throws IOException {
        elasticSearchInit.CreateIndex();
    }

    @GetMapping("bulkrequest")
    public Boolean BulkRequest() throws IOException {
        return elasticSearchInit.BulkRequest();
    }


    @GetMapping("search/{keyword}")
    public List<Map<String,Object>> searchPage(@PathVariable("keyword") String keyword) throws IOException {

        ArrayList<Map<String,Object>> list = new ArrayList<>();

        List<Map<String, Object>> list1 = seachService.searchPage1(keyword);
        List<Map<String, Object>> list2 = seachService.searchPage2(keyword);
        List<Map<String, Object>> list3 = seachService.searchPage3(keyword);
        List<Map<String, Object>> list4 = seachService.searchPage4(keyword);
        List<Map<String, Object>> list5 = seachService.searchPage5(keyword);
        List<Map<String, Object>> list6 = seachService.searchPage6(keyword);
        List<Map<String, Object>> list7 = seachService.searchPage7(keyword);
        if(list1.size()!=0){
            for (int i=0;i<list1.size();i++){
                Map<String, Object> temp = list1.get(i);
                list.add(temp);
            }
        }

        if (list2.size()!=0){
            for (int i=0;i<list2.size();i++){
                Map<String, Object> temp = list2.get(i);
                list.add(temp);
            }
        }

        if(list3.size()!=0){
            for (int i=0;i<list3.size();i++){
                Map<String, Object> temp = list3.get(i);
                list.add(temp);
            }
        }
        if (list4.size()!=0){
            for (int i=0;i<list4.size();i++){
                Map<String, Object> temp = list4.get(i);
                list.add(temp);
            }
        }
        if (list5.size()!=0){
            for (int i=0;i<list5.size();i++){
                Map<String, Object> temp = list5.get(i);
                list.add(temp);
            }
        }
        if (list6.size()!=0){
            for (int i=0;i<list6.size();i++){
                Map<String, Object> temp = list6.get(i);
                list.add(temp);
            }

        }
        if (list7.size()!=0){
            for (int i=0;i<list7.size();i++){
                Map<String, Object> temp = list7.get(i);
                list.add(temp);
            }

        }

        return list;
    }




}
