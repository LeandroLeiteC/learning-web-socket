package leandroleitec.io.github.learningwebsocket.model.entity;

import leandroleitec.io.github.learningwebsocket.enums.MessageType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatMessage {

    private MessageType type;
    private String content;
    private String sender;
}
