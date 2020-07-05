package com.controller;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/fre")
public class TestController {



    @RequestMapping("/a")
    public ModelAndView test(Model model)throws Exception{
        ModelAndView modelAndView = new ModelAndView();

        return modelAndView;
    }


    public static void test() throws IOException, TemplateException {
        // 创建一个模板文件
        // 创建一个Configuration对象
        Configuration configuration = new Configuration(Configuration.getVersion());
        // 设置模板文件保存的目录
        configuration.setDirectoryForTemplateLoading(new File("D:\\java\\code\\cloud_demo\\fremaker\\src\\main\\resources\\templates"));

        // 设置文件的编码格式，一般是utf-8
        configuration.setDefaultEncoding("utf-8");
        // 加载一个模板文件，创建一个模板对象
        Template template = configuration.getTemplate("in.ftl");
        // 创建一个穆数据集，可以是pojo也可以是map，推荐使用map
        Map<String, String> data = new HashMap<>();
        data.put("test", "hello freemarker!");
        // 创建一个Writer对象，指定输出文件的路径以及文件名
        Writer out = new FileWriter(new File("/templates"));
        // 生成静态页面
        template.process(data, out);
        // 关闭流
        out.close();


    }

    public static void main(String[] args) throws IOException, TemplateException {
        test();
    }

}
