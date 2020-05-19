package com.wxl.system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class User {
    private String account;
    private String password;
    private Integer role_id;
    private String username;
    private String private_salt;
    private String user_status;
}
