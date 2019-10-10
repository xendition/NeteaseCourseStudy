package com.michael.demo.spring.event.base;

import org.springframework.context.ApplicationEvent;

/**
 * 类功能描述:
 * <pre>
 *   xxxx
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/9/16 18:36
 */
public abstract class AbstractEvent extends ApplicationEvent {

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public AbstractEvent(Object source) {
        super(source);
    }
}
