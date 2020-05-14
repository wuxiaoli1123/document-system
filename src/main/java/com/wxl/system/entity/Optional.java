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
public class Optional {
    private String cno;
    private Integer credit;
    private String cname;
    private String tname;
    private String place;
    private Integer number;
    private Integer max;

}
