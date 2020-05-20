package com.wxl.system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SpcResult {
    private Boolean state = true;
    private String msg;

    private String username;

    private Boolean isfull = true;

    private Boolean ischoose = true;

    private String role;
    //登录成功之后，返回给前台
    private String user_account;
}
