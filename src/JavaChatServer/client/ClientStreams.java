package JavaChatServer.client;

import JavaChatServer.server.BasicServer;
import netscape.javascript.JSObject;

import java.io.*;

public abstract class ClientStreams implements Runnable {
	private BufferedReader INPUT;
	private PrintWriter OUTPUT;
	private String m_Id;

    /**
     * En el constructor establecemos que el buffer de escritura haga flush() automáticamente.
     * @param id Usado para darle nombre al Thread.
     */
	public ClientStreams(final BasicServer server, final String id) {
		try {
			this.INPUT = new BufferedReader(new InputStreamReader(server.getSocket().getInputStream()));
			this.OUTPUT = new PrintWriter(server.getSocket().getOutputStream(), true);
			this.m_Id = id;

			final Thread chatThread = new Thread(this, id);
			chatThread.start();
			this.onConnected();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que evaluará constantemente las peticiones que reciba del cliente.
	 * Se ejecutará mientras que el cliente no se haya desconectado, en cuyo caso el valor boolean se tornará false.
	 */
	@Override
	public void run() {
		ActionRequest actionRequest;
		Boolean notDisconnected = true;

		do {
			try {
				//actionRequest = (ActionRequest) this.INPUT.readObject();
			} catch (Exception e) {
				actionRequest = new ActionRequest(this.m_Id, ActionRequest.Action.DISCONNECT);
			}

//			switch (actionRequest.getAction()) {
//				case SEND_MESSAGE:
//					this.onSendingMessage(actionRequest);
//					break;
//				case GET_FRIENDS:
//					this.onRequestFriends(actionRequest.getId());
//					break;
//				case DISCONNECT:
//					this.onDisconnected();
//					this.disposeBuffers();
//					notDisconnected = false;
//					break;
//				default:
//					this.onDisconnected();
//					this.disposeBuffers();
//					notDisconnected = false;
//					System.out.println("Handled, but something went unexpected... =(");
//					break;
//			}
		} while (notDisconnected);
	}

	private void disposeBuffers() {
		try {
			this.INPUT.close();
			this.OUTPUT.close();
		} catch (IOException e) {
			e.printStackTrace();
			this.onDisconnected();
		}
	}

	/**
	 * Método encargado de devolver el objeto recibido al servidor de nuevo al cliente emisor.
	 * @param actionRequest Objecto a enviar.
	 */
	protected void returnRequest(final ActionRequest actionRequest) {
//		try {
//			//this.getOUTPUT().writeObject(actionRequest);
//			this.getOUTPUT().flush();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	protected abstract void onConnected();

	protected abstract void onSendingMessage(final ActionRequest actionRequest);

	protected abstract void onRequestFriends(final String id);

	protected abstract void  onDisconnected();

	public BufferedReader getINPUT() {
		return INPUT;
	}

	public PrintWriter getOUTPUT() {
		return OUTPUT;
	}
}
