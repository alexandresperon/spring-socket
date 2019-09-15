package br.com.socket.controller.message;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import br.com.socket.dto.MessageDTO;
import br.com.socket.dto.OutputMessageDTO;

@Controller
@MessageMapping("/chat")
public class ChatController {

	@MessageMapping("/public")
	@SendTo("/topic/messages")
	public OutputMessageDTO send(final MessageDTO message) throws Exception {
		final String time = new SimpleDateFormat("HH:mm").format(new Date());
		return new OutputMessageDTO(message.getFrom(), message.getText(), time);
	}

	@MessageMapping("/private")
	@SendToUser("/topic/reply")
	public OutputMessageDTO processMessageFromClient(@Payload MessageDTO message) throws Exception {
		final String time = new SimpleDateFormat("HH:mm").format(new Date());
		return new OutputMessageDTO(message.getFrom(), message.getText(), time);
	}
}
