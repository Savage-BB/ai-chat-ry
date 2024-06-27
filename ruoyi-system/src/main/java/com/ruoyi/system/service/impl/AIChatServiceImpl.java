package com.ruoyi.system.service.impl;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @author: 高雪峰
 * @date: 2024/6/27
 * @description:
 **/
@Service
public class AIChatServiceImpl {
    public static String key = "c3eb1f018ed8f1e1f5b96e65ccdf9744.VLY51p68vKkjvHc4";
    public static String content = "";
    public static String body = "{\n" +
            "    \"model\": \"glm-4\",\n" +
            "    \"messages\": [\n" +
            "        {\n" +
            "            \"role\": \"user\",\n" +
            "            \"content\": \"你好\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";
    public static String model = "glm-4";
    public JSONObject createMessage(String content) {
        JSONObject body = new JSONObject();
        body.put("model", model);

        JSONObject roleAndUser = new JSONObject();
        roleAndUser.put("role", "user");
        roleAndUser.put("content", content);
        JSONArray message = new JSONArray();
        message.add(roleAndUser);
        body.put("messages", message);
        return body;
    }
    public String chat(String content){
        String response = HttpRequest.post("https://open.bigmodel.cn/api/paas/v4/chat/completions")
                .header(Header.AUTHORIZATION, "Bearer " + key)
                .header(Header.CONTENT_TYPE, "application/json")
                .body(createMessage(content).toString())
                .execute()
                .body();
        return response;
    }
}
