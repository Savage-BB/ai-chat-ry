package com.ruoyi.system.service.impl;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
    public void sseTest()throws Exception{
        URL url = new URL("http://your-server-url");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Accept", "text/event-stream");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {  // 空行表示事件之间的分隔符
                // 处理事件的逻辑
                System.out.println("Received event: " + line);
            }
        }

        reader.close();
        connection.disconnect();
    }
    public void sseSDK(){
//        ClientV4 client = new ClientV4.Builder(key).build();
//        List<ChatMessage> messages = new ArrayList<>();
//        ChatMessage chatMessage = new ChatMessage(ChatMessageRole.USER.value(), "作为一名营销专家，请为智谱开放平台创作一个吸引人的slogan");
//        messages.add(chatMessage);
////        String requestId = String.format(requestIdTemplate, System.currentTimeMillis());
//        String requestId = String.format("%d", System.currentTimeMillis());
//
//        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
//                .model(Constants.ModelChatGLM4)
//                .stream(Boolean.FALSE)
//                .invokeMethod(Constants.invokeMethod)
//                .messages(messages)
//                .requestId(requestId)
//                .build();
//        ModelApiResponse invokeModelApiResp = client.invokeModelApi(chatCompletionRequest);
//        //            System.out.println("model output:" + mapper.writeValueAsString(invokeModelApiResp));
//        System.out.println("model output:" + JSONUtil.toJsonStr(invokeModelApiResp));
    }
}
