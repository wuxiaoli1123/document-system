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
@Accessors(chain=true)
public class Action {
    private Integer id;
    private String userID;
    private String type;
    private String path;
    private String efID;
    private String className;
    private String sfTime;
    private String efTime;
    private String val;
    private String action;
}
