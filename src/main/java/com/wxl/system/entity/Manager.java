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

public class Manager {
    private String worktime;
    private String tel;
    private String pol;
    private String edurecord;
    private String position;
    private int msex;
    private String mname;
    private String mno;
}
