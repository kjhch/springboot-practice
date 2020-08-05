package com.hch.listener;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

/**
 * 用于和META-INF/spring.factories配合测试spring的spi机制
 * <p></p>
 *
 * @author hch
 * @since 2020/7/20
 */
public class MyListener implements ApplicationListener<ApplicationStartingEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        System.out.println("************application starting************");
    }
}
