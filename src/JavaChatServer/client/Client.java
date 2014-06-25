package JavaChatServer.client;

import JavaChatServer.server.BasicServer;

public class Client extends ClientStreams {
	private final String nick;
	private final String id;

	public Client(final BasicServer server, final String id, final String nick) {
		super(server, id);
		this.nick = nick;
		this.id = id;
	}

	@Override
	public void onConnected() {
		System.out.println("User: " + this.nick + " conected.");
	}

	@Override
	protected void onSendingMessage(ActionRequest actionRequest) {
		System.out.println(actionRequest.getMessage());
		this.returnRequest(actionRequest);
	}

	@Override
	protected void onRequestFriends(String id) {
		System.out.println("Aquí tendría que devolverte los amigos.");
	}

	@Override
	public void onDisconnected() {
		System.out.println("User: " + this.nick + " disconected");
	}

	public String getNick() {
		return nick;
	}

	public String getId() {
		return id;
	}
}
