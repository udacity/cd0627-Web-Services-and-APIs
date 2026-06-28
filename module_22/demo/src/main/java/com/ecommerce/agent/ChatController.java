package com.ecommerce.agent;

import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {

    private final TranslatorAgent translatorAgent;
    private final StreamingChatLanguageModel streamingChatLanguageModel;

    public ChatController(TranslatorAgent translatorAgent, StreamingChatLanguageModel streamingChatLanguageModel) {
        this.translatorAgent = translatorAgent;
        this.streamingChatLanguageModel = streamingChatLanguageModel;
    }

    @GetMapping("/chat/sync")
    public String syncChat(@RequestParam String text) {
        return translatorAgent.translate(text);
    }

    @GetMapping(value = "/chat/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamChat(@RequestParam String text) {
        return Flux.create(sink -> {
            streamingChatLanguageModel.generate(text, new dev.langchain4j.model.StreamingResponseHandler<dev.langchain4j.data.message.AiMessage>() {
                @Override
                public void onNext(String token) {
                    sink.next(token);
                }

                @Override
                public void onComplete(dev.langchain4j.model.output.Response<dev.langchain4j.data.message.AiMessage> response) {
                    sink.complete();
                }

                @Override
                public void onError(Throwable error) {
                    sink.error(error);
                }
            });
        });
    }
}
