package com.Nio.ServerN;

public class Test {


    public static void main(String[] args) {
        Test test = new Test();
        new Test().aaa(test::ss);
        new Thread(test::ss);
    }

    public void aaa(Aaa a){

    }

    public String ss(){
     return "!";
    }
}

interface Aaa{
    String asd();
}
