package com.ruoyi.web.controller.tool.ai;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.service.impl.AIChatServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: 高雪峰
 * @date: 2024/6/27
 * @description:
 **/
@Api("AI聊天")
@RestController
@RequestMapping("/AI")
public class AiChatController {

    @Resource
    private AIChatServiceImpl aiChatService;

    @ApiOperation("http json 接口")
    @GetMapping("/get")
    public R<String> get(String content) {
        return R.ok(aiChatService.chat(content));
    }

    @ApiOperation("sse")
    @GetMapping("/sse")
    public R<String> sse(String content) {
        return R.ok(aiChatService.chat(content));
    }
}
