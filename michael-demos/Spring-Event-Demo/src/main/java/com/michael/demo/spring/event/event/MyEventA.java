package com.michael.demo.spring.event.event;

import com.michael.demo.spring.event.base.AbstractEvent;

/**
 * 类功能描述:
 * <pre>
 *   xxxx
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/9/16 18:38
 */
public class MyEventA extends AbstractEvent {

    private int i;

    public MyEventA(Object source, int i) {
        super(source);
        this.i = i;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}
