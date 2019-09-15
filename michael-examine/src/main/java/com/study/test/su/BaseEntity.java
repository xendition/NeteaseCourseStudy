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
@ToString(callSuper = true)
@Accessors(chain = true)
public class BaseEntity<T extends BaseEntity> extends SuperEntity<Long,T> {

    private Long pageNo;

    public T setPageNo(Long pageNo) {
        this.pageNo = pageNo;
        return (T) this;
    }
}
