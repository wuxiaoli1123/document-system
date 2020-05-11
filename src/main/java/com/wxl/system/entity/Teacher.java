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
public class Teacher {
    private String tno;
    private String tname;
    private Integer sex;//0:女；1：男
    private String worktime;
    private String pol;
    private String edurecord;
    private String position;
    private String dept;
    private String tel;
}
