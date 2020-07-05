package com.zhenghe;

public class AA {
    private static Object object=new Object();
    public static void main(String[] args) {
        AA aa = new AA();

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    aa.aa();
                }
            }).start();
        }

    }

    public static class BB{

    }

    int i = 1;
    public synchronized void  aa() {

        synchronized (this) {

        }
    }
}
