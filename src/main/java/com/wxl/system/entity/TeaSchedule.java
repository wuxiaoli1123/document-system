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

//用于教师查看课表的实体类
public class TeaSchedule {
    private String cno;
    private String classno;
    private String term;
    private Integer hour;
    private String place;
    private String time;
    private Integer grade;
    private String cname;
}
