package com.michael.demo.mybatis.simple;
import lombok.Data;

import java.io.Serializable;

/**
 * 类功能描述:
 * <pre>
 *   xxxx
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2020/7/1 9:21
 */
@Data
public class Employee implements Serializable {

    int id;
    String name;
}
