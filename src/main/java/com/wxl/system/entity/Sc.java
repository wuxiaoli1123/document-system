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
public class Sc {
    private String cno;
    private Integer credit;
    private Double grade;
    private String classno;
    private String sno;
    private String type;
}
