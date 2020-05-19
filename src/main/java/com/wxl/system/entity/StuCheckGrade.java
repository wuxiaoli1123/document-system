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
//学生查成绩
public class StuCheckGrade {
    private String cno;
    private Integer credit;
    private Double grade;
    private String classno;
    private String type;
    private String term;
    private String cname;

}
