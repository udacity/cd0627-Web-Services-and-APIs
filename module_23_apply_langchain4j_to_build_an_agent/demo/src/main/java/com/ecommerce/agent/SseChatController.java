package com.ecommerce.agent;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.output.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@RestController
public class SseChatController {

    private static final Logger log = LoggerFactory.getLogger(SseChatController.class);
    private final StreamingChatLanguageModel streamingChatLanguageModel;

    public SseChatController(StreamingChatLanguageModel streamingChatLanguageModel) {
        this.streamingChatLanguageModel = streamingChatLanguageModel;
    }

    @GetMapping("/api/chat/stream")
    public SseEmitter streamChat(@RequestParam(defaultValue = "Tell me a short story about a robot.") String message) {
        SseEmitter emitter = new SseEmitter(60000L); // 60 seconds timeout

        streamingChatLanguageModel.generate(message, new StreamingResponseHandler<AiMessage>() {
            @Override
            public void onNext(String token) {
                try {
                    emitter.send(token);
                } catch (IOException e) {
                    log.error("Error sending token: ", e);
                    emitter.completeWithError(e);
                }
            }

            @Override
            public void onComplete(Response<AiMessage> response) {
                emitter.complete();
            }

            @Override
            public void onError(Throwable error) {
                log.error("Error generating streaming response: ", error);
                emitter.completeWithError(error);
            }
        });

        return emitter;
    }
}
