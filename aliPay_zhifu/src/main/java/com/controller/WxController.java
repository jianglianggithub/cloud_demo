package com.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by aa on 2019/11/9.
 */

@RestController
@RequestMapping("/qqqqqq")
public class WxController {


    @RequestMapping("/aa")
    public String aa(HttpServletRequest request) throws IOException {
        String characterEncoding = request.getCharacterEncoding();
        System.out.println(characterEncoding);
        ServletInputStream inputStream = request.getInputStream();
        String s = org.apache.commons.io.IOUtils.toString(inputStream, characterEncoding);


        System.out.println(s);
        return s;
    }

    @RequestMapping(value = "/sssss", produces = "text/html;charset=UTF-8")
    public String test(HttpServletRequest request) throws IOException {
        String xmlResult = org.apache.commons.io.IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
        return xmlResult;
    }

}
