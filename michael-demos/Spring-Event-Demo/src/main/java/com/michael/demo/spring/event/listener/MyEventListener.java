package com.michael.demo.spring.event.listener;

import com.michael.demo.spring.event.base.AbstractEvent;
import com.michael.demo.spring.event.constant.EventTypeConstnts;
import com.michael.demo.spring.event.entity.Entity;
import com.michael.demo.spring.event.event.MyEventA;
import com.michael.demo.spring.event.event.MyEventB;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 类功能描述:
 * <pre>
 *   xxxx
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/9/16 18:39
 */
@Component
public class MyEventListener {

    // @Async
    // @EventListener
    public void handleAbstractEvent(AbstractEvent object) {
        String simpleName = object.getClass().getSimpleName();
        switch (simpleName) {
            case EventTypeConstnts.A:

                MyEventA eventA = (MyEventA) object.getSource();
                int i = eventA.getI();
                if (i%5 == 1) {
                    i = i/0;
                }
                System.out.println("处理业务A");
                break;
            case EventTypeConstnts.B:

                MyEventB eventB = (MyEventB) object.getSource();
                i = eventB.getI();
                if (i%6 == 1) {
                    i = i/0;
                }
                System.out.println("处理业务B");
                break;
            default:
                return;
        }
        Entity entity = (Entity) object.getSource();

        System.out.println(simpleName + "->" + entity.getName());
    }

    @Async
    @EventListener
    // @TransactionalEventListener
    public void handleMyEventA(MyEventA eventA) {
        int i = eventA.getI();
        if (i%5 == 1) {
            i = i/0;
        }
        System.out.println("处理业务A -> " + i);
        // Entity entity = (Entity) object.getSource();
        // System.out.println("handleMyEventA() -> 处理业务A" + entity.getName());
    }
    @Async
    @EventListener
    public void handleMyEventB(MyEventB eventB) {
        int i = eventB.getI();
        if (i%6 == 1) {
            i = i/0;
        }
        System.out.println("处理业务B -> " + i);
        // Entity entity = (Entity) object.getSource();
        // System.out.println("handleMyEventA() -> 处理业务A" + entity.getName());
    }
}
