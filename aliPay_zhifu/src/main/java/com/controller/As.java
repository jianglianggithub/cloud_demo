package com.controller;


import org.assertj.core.api.UrlAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping("/aaasd/dd")
public class As {


    public static void main(String[] args) {
        new As();
        new As();

    }

    {
        System.out.println("{  }");
        test();
    }

    public void test() {
        System.out.println("!11");
    }

    public As() {
        System.out.println("As");
    }

    @RequestMapping("/aa")//RedirectAttributes
    public String csa(HttpServletResponse response, HttpServletRequest request) throws IOException {
        System.out.println(request.getServletPath());
        response.getWriter().write("aaaa");
        return "aa";
    }

    @RequestMapping("/asdasd")//RedirectAttributes
    @ResponseBody
    public String asdasd(HttpServletResponse response, HttpServletRequest request) throws IOException {
        Cookie aa = new Cookie("qwer", "11");
        aa.setDomain("yasdas1231es.com");
        aa.setPath("/");
        aa.setMaxAge(-1);
        response.addCookie(aa);
        return "aa";
    }
    @RequestMapping("/asdasd2")//RedirectAttributes
    @ResponseBody
    public String asdasd2(HttpServletResponse response, HttpServletRequest request) throws IOException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                System.out.println(cookies[i].getName()+"  "+cookies[i].getValue());
                System.out.println(cookies[i].getPath()+"  "+cookies[i].getDomain());
            }
        }

        return "aa";
    }
    @RequestMapping("/asd")
    public void asd(HttpServletResponse response) {
        long l = System.currentTimeMillis();
        File file = new File("D:\\img\\1.png");
        BufferedInputStream stream = null;
        try {
            stream = new BufferedInputStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("file not exisst");
        }
        byte[] bytes = new byte[1024];
        int length;

        String fileName = "1.jpg";
        response.setContentLength((int) file.length());
        response.setContentType("image/jpeg");
        //response.addHeader("Content-Disposition", "attachment;filename=" + "1.png");
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            while ((length = stream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, length);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(System.currentTimeMillis() - l);

    }

    @RequestMapping("/asd1")
    public void asd1(HttpServletResponse response) throws IOException {
        long l = System.currentTimeMillis();


        File file = new File("D:/img/1.png");
        RandomAccessFile rw = null;
        try {
            rw = new RandomAccessFile(file, "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        response.setContentLength((int) file.length());
        response.setContentType("image/jpeg");
        //response.addHeader("Content-Disposition", "attachment;filename=" + "1.png");

        FileChannel channel = rw.getChannel();
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, file.length());
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int length;
        while ((length = channel.read(buffer)) > 0) {
            byte[] array = buffer.array();
            buffer.flip();
            response.getOutputStream().write(array, 0, length);
        }

        channel.close();
        rw.close();

        System.out.println(System.currentTimeMillis() - l);
    }

    @RequestMapping("/testabc")//RedirectAttributes
    public String aaaa(@RequestParam("a") String a, ModelMap attributes) {
        System.out.println(a);
        attributes.addAttribute("a", a);
        return "redirect:/aaasd/dd/aaa";
    }

    @RequestMapping("/aaa")
    @ResponseBody
    public Object asd(@RequestParam Map map) {
        System.out.println(map);
        return map;
    }

    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;

    @RequestMapping("/sss")
    public Object sss(@RequestParam Map map, MultipartHttpServletRequest request) throws IOException {
        Map<String, MultipartFile> fileMap = request.getFileMap();
        System.out.println(fileMap);
        System.out.println(map);
        String realPath = request.getServletContext().getRealPath("");
        System.out.println(realPath);
//        file.transferTo(new File(realPath+"1.jpg"));
        return "";
    }

    @RequestMapping("/qqqq")
    public Object bb(@RequestParam Map<String, String> pram) throws IOException {
        System.out.println();
        response.getWriter().append("<H1>没有权限</H1>");
        return "redirect:/aaasd/dd/qq";
    }

    @RequestMapping("/qq")
    @ResponseBody
    public Object basdasdb(@RequestParam Map<String, String> pram) throws IOException {
        System.out.println();
        return pram;
    }


    @RequestMapping("/zxc")
    public Object download(HttpServletRequest response) {

        //打成jar包 要获取文件得话 要 用 newClasspathResource 打成jar写不了 jar里面得路径
        return response.getServletContext().getRealPath("");
    }

    @RequestMapping("/sa")
    public void test(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        BufferedInputStream buff = new BufferedInputStream(inputStream);
        byte[] bytes = new byte[1024];

        FileOutputStream fileOutputStream = new FileOutputStream(new File("d:/sp/test.mp4"));

        int length = 0;
        while ((length = buff.read(bytes)) != -1) {
            fileOutputStream.write(bytes, 0, length);
        }
        buff.close();
        fileOutputStream.close();
    }

    public static void main0(String[] args) throws Exception {

        ExecutorService executor = new ThreadPoolExecutor(
                3,
                3,
                0,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());

        URL url = new URL("http://127.0.0.1:8889/aaa.m");

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        String headerField = urlConnection.getHeaderField("Content-Length");
        long fileSize = Long.parseLong(headerField);
        int chunk = 1024 * 1024;
        int number = (int) Math.ceil(fileSize * 1.0 / chunk);
        CompletableFuture<?>[] futures = new CompletableFuture<?>[number];
        AtomicInteger atomicInteger = new AtomicInteger(0);
        int start = 0;
        int end = chunk;


        for (int i = 0; i < number; i++) {
            if (i == number - 1) {
                start = end + 1;
                end = (int) (fileSize - 1);


            } else if (i != 0) {
                start = end + 1;
                end = start + chunk;
            }

            int finalStart = start;
            int finalEnd = end;
            int finalI = i;
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    URL url1 = new URL("http://127.0.0.1:8889/aaa.m");
                    HttpURLConnection urlConnection1 = (HttpURLConnection) url1.openConnection();

                    urlConnection1.setRequestProperty("Range", "bytes=" + finalStart + "-" + finalEnd);
                    InputStream inputStream = urlConnection1.getInputStream();
                    byte[] bytes = new byte[1024];
                    int length;
                    FileOutputStream fileOutputStream = new FileOutputStream(new File("d:/sp/chunk/" + finalI));
                    while ((length = inputStream.read(bytes)) != -1) {
                        fileOutputStream.write(bytes, 0, length);
                    }
                    fileOutputStream.close();
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }, executor);
            futures[i] = future;
        }

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(futures);

        voidCompletableFuture.join();

        System.out.println(atomicInteger.get());

        executor.shutdown();


        RandomAccessFile rw = new RandomAccessFile(new File("d:/sp/bb.mp4"), "rw");
        FileChannel channel = rw.getChannel();

        File[] files = new File("d:/sp/chunk").listFiles();
        Arrays.sort(files, (sss, ccc) -> {
            return Integer.parseInt(sss.getName()) - Integer.parseInt(ccc.getName());
        });

        System.out.println("===============");
        for (File file : files) {
            FileChannel rw1 = new RandomAccessFile(file, "rw").getChannel();
            rw1.transferTo(0, file.length(), channel);
            System.out.println(channel.position());
            rw1.close();
        }

        channel.close();
        rw.close();
    }
}
