package com.wxl.system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain=true)
@Component
public class ESrole_perm {
    @Field(type = FieldType.Text,searchAnalyzer = "ik_smart",analyzer = "ik_max_word")
    private String role_id;
    @Field(type = FieldType.Text,searchAnalyzer = "ik_smart",analyzer = "ik_max_word")
    private String perm_id;
}
