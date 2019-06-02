package com.michael.demos.elasticsearch.model;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * 类功能描述:
 * <pre>
 *
 *   PS:
 *      indexName索引名称 可以理解为数据库名 必须为小写 不然会报 org.elasticsearch.indices.InvalidIndexNameException 异常
 *      type类型 可以理解为表名
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/05/25 16:27
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "testgoods", type = "goods")
public class GoodsInfo implements Serializable {

    private String id;
    private String name;
    private String description;

}
