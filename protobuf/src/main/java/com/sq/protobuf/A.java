package com.sq.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.InvalidProtocolBufferException;
import org.springframework.http.codec.protobuf.ProtobufDecoder;

public class A {
    public static void main(String[] args) throws InvalidProtocolBufferException {
//        byte[] bytes = Taaaa.Person.newBuilder().setId(1).setEmail("aa").setName("asd").build().toByteArray();
//        System.out.println(bytes.length);
//        Taaaa.Person person = Taaaa.Person.parseFrom(bytes);
//        System.out.println(person.getId());
//        Taaaa.Person.newBuilder().setTe(
//                Taaaa1.Person1.newBuilder().setId(1).build()
//        );

        Taaaa2.SampleMessage.Builder builder = Taaaa2.SampleMessage.newBuilder();
        builder.setName("1");
        builder.setSubMessage("12");
        builder.setName1("22");
        Taaaa2.SampleMessage build = builder.build();
        Descriptors.FileDescriptor descriptor = Taaaa2.getDescriptor();
        System.out.println(build.getName());
        System.out.println(build.getName1());
        System.out.println(build.getSubMessage());
    }
}
