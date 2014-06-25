package JavaChatServer.client;

import java.io.Serializable;

public class ActionRequest implements Serializable {
	public enum Action {
		SEND_MESSAGE,
		DISCONNECT,
		GET_FRIENDS
	}

	private String m_id;
	private String m_message;
	private Action m_action;

	public ActionRequest(String id, Action action) {
		this(id, action, "");
	}

	public ActionRequest(String id, Action action, String msg) {
		this.m_id = id;
		this.m_action = action;
		this.m_message = msg;
	}

	public String getId() {
		return m_id;
	}

	public String getMessage() {
		return m_message;
	}

	public Action getAction() {
		return m_action;
	}
}
