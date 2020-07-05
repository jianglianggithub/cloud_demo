package com.testPrICE;

public class A {
    public static void main(String[] args) {
        String a= "{\n" +
                "\t\"1\": {\n" +
                "\t\t\"lockerDoorType\": 1,\n" +
                "\t\t\"chargingStandard\": 22\n" +
                "\t}\n" +
                "}";
        System.out.println(a.trim().replace("\n","").replace("\t",""));
    }
}
