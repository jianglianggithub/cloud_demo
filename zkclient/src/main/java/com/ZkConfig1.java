package com;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

public class ZkConfig1 {

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZkClient zkClient = new ZkClient("192.168.0.103:2181",
                5000,//session timeout = reqeust = > respones 的超时。请求
                5000,
                new SerializableSerializer());

        zkClient.subscribeDataChanges("/dubbo1", new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("节点数据发生修改   " + o);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println(s + " 节点被删除 ");
            }
        });

        zkClient.subscribeChildChanges("/dubbo1", new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                System.out.println("subscribeChildChanges  " + list + s);
            }
        });

        zkClient.subscribeStateChanges(new IZkStateListener() {
            @Override
            public void handleStateChanged(Watcher.Event.KeeperState keeperState) throws Exception {
                System.out.println("1");
            }

            @Override
            public void handleNewSession() throws Exception {
                System.out.println("2");
            }

            @Override
            public void handleSessionEstablishmentError(Throwable throwable) throws Exception {
                System.out.println("3");
            }
        });
        System.out.println(zkClient.getChildren("/dubbo1"));
        LockSupport.park();
    }
}
