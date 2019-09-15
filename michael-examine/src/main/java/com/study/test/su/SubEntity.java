package com.study.test.su;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 类功能描述:
 * <pre>
 *   xxxx
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/9/6 11:00
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class SubEntity extends BaseEntity<SubEntity> {

    private String name;

}

