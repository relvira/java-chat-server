package JavaChatServer;

import JavaChatServer.server.BasicServer;

public class Protocol {
	private static final String EXIT = "exit";
	private static final String USERS_ON = "conected_users";

	private BasicServer server;

	public Protocol(final BasicServer server) {
		this.server = server;
	}

	public String processInput(String clientMsg) {
		String serverOutput;

		switch (clientMsg) {
			case EXIT:
				serverOutput = EXIT;
				break;
			case USERS_ON:
				serverOutput = EXIT;
				break;
			default:
				serverOutput = clientMsg;
				break;
		}

		return serverOutput;
	}
}
