package bit.com.a.websock;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocket extends TextWebSocketHandler {
	
	private Map<String, WebSocketSession> users = new ConcurrentHashMap<String, WebSocketSession>();
	
	public WebSocket() {
		System.out.println("EchoHandler " + new Date());
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// 연결된 후에 실행
		System.out.println("연결 됌 " + new Date());
		
		users.put(session.getId(), session);	// 접속된 사람을 추가 , 일반세션과 다르다 WebSocket Session 이다
		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// 연결 종료
		
		System.out.println("연결 종료 " + new Date());

		users.remove(session.getId());
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// 메시지를 수신했을 때 이 부분이 실행된다,제일 중심
		System.out.println("메세지 수신 " + new Date());
		
		for(WebSocketSession s : users.values()) {
			s.sendMessage(message);
			
			System.out.println(s.getId() + "의 메시지 발송");
		}
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		// Exception 예외 발생
		System.out.println("handleTransportError " + new Date());
		

		System.out.println(session.getId()+"에서 문제 발생");
	}

}
