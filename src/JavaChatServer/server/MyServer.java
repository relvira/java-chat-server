package JavaChatServer.server;

import JavaChatServer.client.Client;

import java.util.ArrayList;

public class MyServer extends BasicServer {
	public ArrayList<Client> users;

    @Override
    protected void onCreateServer() {
        this.users = new ArrayList<>();
    }

    @Override
	public void onStartServer() {
		System.out.println("Waiting on port: 8000");
	}

	@Override
	public void onAcceptedClient(String id, String nick) {
		this.users.add(new Client(this, id, nick));
		System.out.println("Welcome " + nick);
	}

	@Override
	public void onDisposeServer() {
		System.out.println("Closing server. Bye!");
	}
}
