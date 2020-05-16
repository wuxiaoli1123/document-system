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

//用于学生查看课表的实体类
public class StuSchedule {
    private String cname;
    private String classno;
    private String tname;
    private String place;
    private String hour;
    private String time;
}
