package com.oracle.oBootMybatis03.handler;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SocketHandler extends TextWebSocketHandler {
	HashMap<String, WebSocketSession> sessionMap = new HashMap<>(); //웹소켓 세션을 담아둘 맵

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		//메시지 발송
		String msg = message.getPayload();
		System.out.println("SocketHandler handleTextMessage msg->"+msg);
		JSONObject jsonObj = jsonToObjectParser(msg);
		for(String key : sessionMap.keySet()) {
			WebSocketSession wss = sessionMap.get(key);
			try {
				wss.sendMessage(new TextMessage(jsonObj.toJSONString()));
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	//  @SuppressWarning
	//  이건 컴파일러가 일반적으로 경고하는 내용 중	"이건 하지마"하고 제외시킬 때 쓰임
	//	따라서 어떤 경고를 제외시킬지 옵션
	//	1. all : 모든 경고를 억제
	//	2. cast : 캐스트 연산자 관련 경고 억제
	//	3. dep-ann : 사용하지 말아야 할 주석 관련 경고 억제
	//	4. deprecation : 사용하지 말아야 할 메소드 관련 경고 억제
	//	5. fallthrough : switch문에서의 break 누락 관련 경고 억제
	//	6. finally : 반환하지 않는 finally 블럭 관련 경고 억제
	//	7. null : null 분석 관련 경고 억제
	//	8. rawtypes : 제네릭을 사용하는 클래스 매개 변수가 불특정일 때의 경고 억제
	//	9. unchecked : 검증되지 않은 연산자 관련 경고 억제
	//	10. unused : 사용하지 않는 코드 관련 경고 억제

	@SuppressWarnings("unchecked")
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("SocketHandler afterConnectionEstablished start...");
		// 웹소켓  연결이 되면 동작
		super.afterConnectionEstablished(session);
		sessionMap.put(session.getId(), session);
		System.out.println("session -> "+session);
		System.out.println("session.getId() -> "+session.getId());
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("type", "getId");
		jsonObj.put("sessionId", session.getId());
		session.sendMessage(new TextMessage(jsonObj.toJSONString()));
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("SocketHandler afterConnectionEstablished start...");
		// 웹소켓 종료
		sessionMap.remove(session.getId());
		super.afterConnectionClosed(session, status);
	}
	
	// handleTextMessage 메소드 에 JSON파일이 들어오면 파싱해주는 함수를 추가
	private static JSONObject jsonToObjectParser(String jsonStr) {
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = null;
		try {
			jsonObj = (JSONObject) parser.parse(jsonStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return jsonObj;
	}
}
