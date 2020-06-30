package com.michael.demos.springboot.jacksontest;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 类功能描述:
 * <pre>
 *   xxxx
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2020/4/10 11:19
 */
@Data
@Accessors(chain = true)
public class P {

    private Integer id;
    private String account;

    private List<C> children;
}
