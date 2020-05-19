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
//Tc表实体类
public class Tc {
    private Integer id;
    private String cno;
    private Integer grade;
    private String classno;
    private String term;
    private String tno;
    private String time;
    private Integer hour;
    private String place;
}
