package com.michael.demos.springboot.tt;

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
 * @date 2019/11/26 16:20
 */
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private int pageNo;
    private int pageSize;
}
