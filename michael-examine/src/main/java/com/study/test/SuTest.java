package com.study.test;

import com.study.test.su.SubEntity;
import com.study.test.su.SuperEntity;

/**
 * 类功能描述:
 * <pre>
 *   xxxx
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/9/6 11:05
 */
public class SuTest {

    public static void main(String[] args) {
        SuperEntity abc = new SubEntity().setName("abc").setPageNo(1L).setId(11L).setName("def").setPageNo(1111L);
        System.out.println(abc.toString());
    }
}
