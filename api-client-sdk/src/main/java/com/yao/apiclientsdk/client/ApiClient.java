package com.yao.apiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.yao.apiclientsdk.model.*;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.yao.apiclientsdk.utils.SignUtils.genSign;

/**
 * 调用第三方接口的客户端
 *

 */
public class ApiClient {

    private static final String GATEWAY_HOST = "http://1.14.155.37:8090";

    private String accessKey;

    private String secretKey;

    public ApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getNameByGet(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result = HttpUtil.get(GATEWAY_HOST + "/api/name/", paramMap);
        System.out.println(result);
        return result;
    }

    public String getNameByPost(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result = HttpUtil.post(GATEWAY_HOST + "/api/name/", paramMap);
        System.out.println(result);
        return result;
    }
    //添加头部信息（ak，加密后的sk
    private Map<String, String> getHeaderMap(String body) {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("accessKey", accessKey);
        // 一定不能直接发送
//        hashMap.put("secretKey", secretKey);
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        hashMap.put("body", body);
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        hashMap.put("sign", genSign(hashMap.get("nonce"),hashMap.get("timestamp"), secretKey));
        return hashMap;
    }

    public String getUsernameByPost(User user) {
        Gson gson = new Gson();
        String json = gson.toJson(user);
        HttpResponse execute = HttpRequest.post(GATEWAY_HOST + "/api/name/user")
                .addHeaders(getHeaderMap(json))
                .body(json).execute();
        String result = execute.body();
        System.out.println(result);
        return result;
    }

    public String passwordEncode(PasswordEncode password) {
        Gson gson = new Gson();
        String json = gson.toJson(password);
        HttpResponse execute = HttpRequest.get(GATEWAY_HOST + "/api/password/encode")
                .addHeaders(getHeaderMap(json))
                .body(json).execute();
        String result = execute.body();
        System.out.println(result);
        return result;
    }

    public String passwordMatches(PasswordMatch password) {
        Gson gson = new Gson();
        String json = gson.toJson(password);
        HttpResponse execute = HttpRequest.get(GATEWAY_HOST + "/api/password/matches")
                .addHeaders(getHeaderMap(json))
                .body(json).execute();
        String result = execute.body();
        System.out.println(result);
        return result;
    }
    public String nextID(){
        String json="";
        HttpResponse execute = HttpRequest.get(GATEWAY_HOST + "/api/idWorker/next")
                .addHeaders(getHeaderMap(json))
                .body(json).execute();
        String result = execute.body();
        System.out.println(result);
        return result;
    }
    public String dm_comment(){
        String json="";
        HttpResponse execute = HttpRequest.get("https://api.qqsuu.cn/api/dm-comment?format=json&apiKey=a457b57a469db39c843ebf3982555dc0")
                .addHeaders(getHeaderMap(json))
                .body(json).execute();
        String result = execute.body();
        System.out.println(result);
        return result;
    }

    public String BinaryCode(CodeText codeText){
        String text = codeText.getText();
        String url = "https://api.qqsuu.cn/api/dm-qrcode?frame=1&e=L&size=100&text="+text+"&apiKey=24f82c30ada2225bd862d0a34e690e13";
        String json = "";
        HttpResponse execute = HttpRequest.get(url)
                .addHeaders(getHeaderMap(json))
                .body(json).execute();
        String result = execute.body();
        System.out.println(result);
        return result;
    }
    public String dm_everyday(){
        String json="";
        HttpResponse execute = HttpRequest.get("https://api.qqsuu.cn/api/dm-everyday&apiKey=0b4b58c19c3017c227b025647e2be42d")
                .addHeaders(getHeaderMap(json))
                .body(json).execute();
        String result = execute.body();
        System.out.println(result);
        return result;
    }

    public String getFood(Food food){
        String text = food.getName();
        Integer num = food.getNum();
        String url = "https://api.qqsuu.cn/api/dm-caipu?word="+text+"&num="+num+"&apiKey=4b1171958ddd87e1527528fefa72a864";
        String json = "";
        HttpResponse execute = HttpRequest.get(url)
                .addHeaders(getHeaderMap(json))
                .body(json).execute();
        String result = execute.body();
        System.out.println(result);
        return result;
    }
    private static String formatKeyValue(String input) {

        // 移除JSON字符串中的{}、双引号、逗号和换行符
        input= input.replaceAll("[\\{\\}\\s\"\\,\\n]", "");

        String regex = "([\\w]+)\\s*:\\s*([\\w\\s]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            result.append(matcher.group(1)).append(": ").append(matcher.group(2)).append("<br>");
        }
        return result.toString().trim();
    }
}
