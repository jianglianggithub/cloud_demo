package com;

import org.jboss.netty.handler.codec.base64.Base64;
import org.jboss.netty.handler.codec.base64.Base64Decoder;
import org.springframework.util.Base64Utils;

public class App {
    public static void main(String[] args) {
        byte[] bytes = Base64Utils.decodeFromString("RIcluWliVzL12y0nV2O1rx6dKLg=");
        System.out.println(new String(bytes));
    }
}
