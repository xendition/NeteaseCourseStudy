package com.michael.demos.springboot.jacksontest;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 类功能描述:
 * <pre>
 *   xxxx
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2020/4/10 11:20
 */
@Data
@Accessors(chain = true)
public class C {

    private String name;
    private Integer age;

    private BigDecimal price;
}
