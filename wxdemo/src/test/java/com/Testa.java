package com;
import com.alibaba.fastjson.JSON;
import com.u.HttpUtils;
import org.apache.http.entity.mime.content.FileBody;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class Testa {

    @Autowired
    private HttpUtils httpUtils;
    @Autowired
    RestTemplate restTemplate;

    private String tokne = "35_dR6CrJpWRU_A19lEclF2S9goDNeIubP1UMxnJw9DV6WfOdICsF8gmikYI0wIPrAoT0jQjYddwUl-tyDCZsIaMWSBevuSuAiGNLwTBMsDhYPlOdqgVIuulRL3RExnHKF-iKteCHFPMIvUo3uxWKReAHASGN";

    @Test
    public void testToken() {
        String accessToken = httpUtils.getAccessToken();
        System.out.println(accessToken);
    }

    // 上传微信图文可用 图片。
    @Test
    public void testUpload() {
        String uploadimg = httpUtils.uploadimg("E:\\1.png", tokne,null,
                "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=");
        System.out.println(uploadimg);
    }


    //媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
    // 新增永久素材
    @Test
    public void testAddsc() {
        String uploadimg = httpUtils.uploadimg("D:\\222.mp4", tokne,"video",
                "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=");
        System.out.println(uploadimg);
    }

    // 新增临时素材
    @Test
    public void testLSAddsc() {
        String uploadimg = httpUtils.uploadimg("D:\\222.mp4", tokne,"video",
                "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=");
        System.out.println(uploadimg);
    }


    // 获取临时素材
    @Test
    public void testget() throws IOException {
        HttpHeaders headers = new HttpHeaders();


            List list = new ArrayList<>();
            list.add(MediaType.valueOf("voice/speex"));
            //headers.setAccept(list);

        ResponseEntity<byte[]> rs = restTemplate.exchange(
                "https://api.weixin.qq.com/cgi-bin/media/get?access_token={ACCESS_TOKEN}&media_id={MEDIA_ID}",
                HttpMethod.GET,
                new HttpEntity<byte[]>(headers),
                byte[].class,
                tokne,
                "4w8s0l1LFw5jWZk-gWN7cy3gzJV_eV1E2618Vv878U0_RI9kKZnonCE5w5yOcob9");
        System.out.println(new String(rs.getBody()));
        System.out.println(rs.getBody().length);

        RandomAccessFile rw = new RandomAccessFile("d:/ww.mp3", "rw");
        rw.write(rs.getBody());
        rw.close();
    }

    // 获取视频永久素材
    @Test
    public void getVi(){
        Map m = new HashMap();
        m.put("media_id","FFJucLz66TGNRyy8AH9u-m3z4owePOoGafiD2seFDgQ");
        String forObject = restTemplate.postForObject(
                "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token={ACCESS_TOKEN}",
                m , String.class, tokne);
        System.out.println(forObject);
    }

    // 新增图文永久素材
    @Test
    public void testAddyjsc() {


        Map<String,Object> p = new HashMap<>();
        List<Object> chi = new ArrayList<>();
        Map<String,Object> p1= new HashMap<>();
        p1.put("title","ww");
        p1.put("thumb_media_id","FFJucLz66TGNRyy8AH9u-pPjd0yXbnAdNmx4IVFUOpk");
        p1.put("show_cover_pic",1);
        p1.put("content","<section class=\"video-ng\">\n" +
                "    <p style=\"font-size:14px;color:#888;text-align:center;\">\n" +
                "        视频名称\n" +
                "    </p><iframe class=\"video_iframe\" width=\"100%\" height=\"277.5\" style=\"z-index:1;\" frameborder=\"0\" src=\"https://v.qq.com/iframe/preview.html?vid=l3123mwkk32&auto=0\" allowfullscreen=\"\"></iframe>\n" +
                "</section>\n" +
                "<p>\n" +
                "    <br/>\n" +
                "</p>");
//        p1.put("content","<span>44</span>\n" +
//                "<video src=\"http://203.205.137.71/vweixinp.tc.qq.com/1007_0884ade6476e4d76aebd7f6c8a25192e.f10.mp4?vkey=4686039752DA7EE31E450ABAADC991C12A5BFBDF5A136513137B586E0D536580ECAAC18BC3F9B941891440CE6061D4E395DF9DDC399426FCD1A515B9F16DC07419DF6BF6BE6E898514A47B1F95470CB3FC48736B780C10DE&sha=0&save=1\"   controls=\"controls\" autoplay  width=\"320\" height=\"240\" controls>\n" +
//                "     <source src=\"http://203.205.137.71/vweixinp.tc.qq.com/1007_0884ade6476e4d76aebd7f6c8a25192e.f10.mp4?vkey=4686039752DA7EE31E450ABAADC991C12A5BFBDF5A136513137B586E0D536580ECAAC18BC3F9B941891440CE6061D4E395DF9DDC399426FCD1A515B9F16DC07419DF6BF6BE6E898514A47B1F95470CB3FC48736B780C10DE&sha=0&save=1\"   type=\"video/mp4\">\n" +
//                "   </video>");
        p1.put("content_source_url","http://www.qq.com");
        chi.add(p1);
        p.put("articles",chi);
        String s = restTemplate.postForObject("https://api.weixin.qq.com/cgi-bin/material/add_news?access_token={ACCESS_TOKEN}"
                , p, String.class, tokne);
        System.out.println(s);
    }

    //获取素材列表
    @Test
    public void getAll() {

        String map  = "{\n" +
                "    \"type\":\"video\",\n" +
                "    \"offset\":0,\n" +
                "    \"count\":20\n" +
                "}";
        String forObject = restTemplate.postForObject(
                "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token={ACCESS_TOKEN}",
                map , String.class, tokne);

        System.out.println(forObject);
    }


    // 发布预览接口
    @Test
    public void fbxx() {
        String id = "{\n" +
                "    \"touser\":\"oG0RFt28Mcv-cB8TzWUBkwadmywQ\",\n" +
                "    \"mpnews\":{  \"media_id\":\"FFJucLz66TGNRyy8AH9u-rsF9onKPYb9034a_SkCjuQ\",   \n" +
                "   },\n" +
                "    \"msgtype\":\"mpnews\" \n" +
                "}";

        Map map = restTemplate.postForObject("https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token={ACCESS_TOKEN}",
                JSON.parseObject(id), Map.class, tokne);
        System.out.println(map);
    }

    // 发发送客服消息接口
    @Test
    public void asdq() {
        String id = "{\n" +
                "    \"touser\":\"oG0RFt28Mcv-cB8TzWUBkwadmywQ\",\n" +
                "    \"msgtype\":\"news\",\n" +
                "    \"news\":{\n" +
                "        \"articles\": [\n" +
                "         {\n" +
                "             \"title\":\"Happy Day\",\n" +
                "             \"description\":\"Is Really A Happy Day\",\n" +
                "             \"url\":\"https://www.baidu.com\",\n" +
                "             \"picurl\":\"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1596119212360&di=bc0ca7437badb0985c374d8befac740d&imgtype=0&src=http%3A%2F%2Fa2.att.hudong.com%2F36%2F48%2F19300001357258133412489354717.jpg\"\n" +
                "         },\n" +
                " {\n" +
                "             \"title\":\"Happy Day\",\n" +
                "             \"description\":\"Is Really A Happy Day\",\n" +
                "             \"url\":\"https://www.baidu.com\",\n" +
                "             \"picurl\":\"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1596119212360&di=92123a83ddd32b818aed97a85b7e2b8f&imgtype=0&src=http%3A%2F%2Fa3.att.hudong.com%2F14%2F75%2F01300000164186121366756803686.jpg\"\n" +
                "         }\n" +
                "\n" +
                "         ]\n" +
                "    }\n" +
                "}";

        Map map = restTemplate.postForObject("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token={ACCESS_TOKEN}",
                JSON.parseObject(id), Map.class, tokne);
        System.out.println(map);
    }

    }



