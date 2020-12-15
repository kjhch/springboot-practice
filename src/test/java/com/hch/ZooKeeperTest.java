package com.hch;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author hch
 * @since 2020/8/12
 */
@Slf4j
public class ZooKeeperTest {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        try {
            log.info("***begin");
            ZooKeeper zk = new ZooKeeper("localhost:2181", 1, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println("***connection: " + watchedEvent);
                    latch.countDown();
                }
            });
            zk.exists("/lock", false);
        } catch (KeeperException e) {
            log.error("***KeeperException", e);
        } catch (Exception e) {
            log.error("***Exception", e);
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
