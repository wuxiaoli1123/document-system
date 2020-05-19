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

public class Notice {
    private Integer snum;
    private String title;
    private String day;
    private String content;
    private Integer role_id;
}
