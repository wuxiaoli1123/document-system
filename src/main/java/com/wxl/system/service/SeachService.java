package com.wxl.system.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public interface SeachService {

    //获取数据实现搜索功能
    List<Map<String,Object>> searchPage1(String keyword) throws IOException;

    List<Map<String,Object>> searchPage2(String keyword) throws IOException;

    List<Map<String,Object>> searchPage3(String keyword) throws IOException;

    List<Map<String,Object>> searchPage4(String keyword) throws IOException;

    List<Map<String,Object>> searchPage5(String keyword) throws IOException;

    List<Map<String,Object>> searchPage6(String keyword) throws IOException;

    List<Map<String,Object>> searchPage7(String keyword) throws IOException;

    //实现搜索高亮功能

}
