package com.wxl.system.service;

import java.io.IOException;

public interface ElasticSearchInit {

    //创建索引
    void CreateIndex() throws IOException;

    //批量添加文档
    boolean BulkRequest() throws IOException;

}
