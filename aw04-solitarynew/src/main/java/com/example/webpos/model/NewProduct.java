package com.example.webpos.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.webpos.model.mapper.ListToStringHandler;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

import static com.baomidou.mybatisplus.annotation.FieldStrategy.NOT_NULL;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@TableName(value = "product", autoResultMap = true)
public class NewProduct {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField
    private String main_cat;

    @TableField
    private String title;

    @TableField
    private String asin;

    @TableField
    private String price;

    @TableField(jdbcType = JdbcType.VARCHAR, insertStrategy = NOT_NULL, typeHandler = ListToStringHandler.class)
    private List<String> category;

    @TableField(jdbcType = JdbcType.VARCHAR, insertStrategy = NOT_NULL, typeHandler = ListToStringHandler.class, value = "image_url_high_res")
    private List<String> imageURLHighRes;
}
