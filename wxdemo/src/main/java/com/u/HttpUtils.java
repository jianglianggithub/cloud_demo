package com.u;

import org.apache.tomcat.util.net.jsse.JSSEUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class HttpUtils {

    @Autowired
    private RestTemplate restTemplate;
    private static String appId= "wx745e3621242c1dc2";
    private static String appSecret="5dcbdd4901399c85cc18451b7df9fec3";



    public String uploadimg(String filePath,String token,String type,String url_) {

        String accessToken = token;
        if (accessToken != null) {
            String url = url_+accessToken;
            if (!StringUtils.isEmpty(type)) {
                url += "&type=" + type;
            }
            //设置请求体，注意是LinkedMultiValueMap
            MultiValueMap<String, Object> data = new LinkedMultiValueMap<>();

            //设置上传文件
            FileSystemResource fileSystemResource = new FileSystemResource(filePath);
            data.add("media", fileSystemResource);
            if (url.contains("add_material") && type.equals("video")) {
                Map p =new HashMap();
                p.put("title","asdasdasd");
                p.put("introduction","qweqwewqeq");
                data.add("description",p);

            }
            //上传文件,设置请求头
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
            httpHeaders.setContentLength(fileSystemResource.getFile().length());

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(data,
                    httpHeaders);
            try{
                //这里RestTemplate请求返回的字符串直接转换成JSONObject会报异常,后续深入找一下原因
//                ResponseEntity<JSONObject> resultEntity = restTemplate.exchange(url,
//                        HttpMethod.POST, requestEntity, JSONObject.class);
                String resultJSON;


                    resultJSON = restTemplate.postForObject(url, requestEntity, String.class);

                    resultJSON = restTemplate.postForObject(url, requestEntity, String.class);




                return resultJSON;
            }catch (Exception e){
                System.out.println(e);
            }
        }
        return null;

    }

    public String getAccessToken() {
        Map rs = restTemplate.getForObject(
                "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={APPID}&secret={APPSECRET}"
                , Map.class, appId, appSecret);
        return (String) rs.get("access_token");
    }


}
