package com.study.test.su;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 类功能描述:
 * <pre>
 *   xxxx
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/9/6 10:59
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class SuperEntity<PK extends Serializable,T extends SuperEntity> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID 主键，默认使用ID_WORKER生成
     */
    protected PK id;

    public T setId(PK id) {
        this.id = id;
        return (T) this;
    }
}