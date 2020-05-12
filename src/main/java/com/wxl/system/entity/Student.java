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
public class Student {
    private String sno;
    private String sname;
    private Integer sex;//0:女；1：男
    private String birth;
    private String gs;
    private String pol;
    private String classno;
    private String major;
    private String dept;
    private String grade;
}
