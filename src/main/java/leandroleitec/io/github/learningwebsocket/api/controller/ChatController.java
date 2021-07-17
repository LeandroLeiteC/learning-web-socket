package leandroleitec.io.github.learningwebsocket.api.controller;

import leandroleitec.io.github.learningwebsocket.model.entity.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Random;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat.sendMessage/{room}")
    @SendTo("/topic/public/{room}")
    public ChatMessage send(@DestinationVariable String room, @Payload ChatMessage chatMessage) {
        log.info(String.format("Receveid a new message of: %s", chatMessage.getSender()));
        return chatMessage;
    }

    @MessageMapping("/chat.addUser/{room}")
    @SendTo("/topic/public/{room}")
    public ChatMessage addUser(@DestinationVariable String room, @Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        log.info(String.format("New user called: %s on room %s", chatMessage.getSender(), room));
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        headerAccessor.getSessionAttributes().put("room", chatMessage.getRoom());
        return chatMessage;
    }
}
