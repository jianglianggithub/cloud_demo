package com.c;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.util.Date;
import java.util.function.Function;

@RestController
public class TController {

    @RequestMapping("/upload")
    public String upload(MultipartFile file) throws Exception{
        String contentType = file.getContentType();
        byte[] bytes = file.getBytes();
        String name = file.getName();
        String originalFilename = file.getOriginalFilename();

        System.out.println(contentType+" ");
        System.out.println(bytes+" ");
        System.out.println(name+" ");
        System.out.println(originalFilename+" ");
        return "1";
    }

    public static void main(String[] args) {
//        System.out.println(1073741824 & 0x3FFFFFFF);
//        System.out.println(0x3FFFFFFF);

//        System.out.println(0x3FFFFFFF);
        System.out.println(1<<6);

    }



}
