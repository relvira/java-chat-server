package JavaChatServer.server;

import JavaChatServer.RandomUtility;

import java.io.IOException;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.Random;

public abstract class BasicServer implements Runnable {
	private static final int PORT = 8000;
	
	private ServerSocket serverSocket;
	private Socket socket;
	
	protected BasicServer() {
        this.onCreateServer();
		this.initServer();
		this.acceptClients();
	}

	/**
	 * Método encargado de inicializar el socket del servidor.
	 */
	private void initServer() {
		try {
			this.serverSocket = new ServerSocket(PORT, 100);
			this.onStartServer();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	/**
	 * Método encargado de crear hilos que acepten nuevos clientes.
	 */
	private void acceptClients() {
		final Thread t = new Thread(this, "NewClient");
		t.start();
	}

	/**
	 * Método llamado en el hilo de aceptación de clientes que acepta el socket y manda
	 * el evento onAcceptedClient() a la clase hija.
	 */
	@Override
	public void run() {
		while (true) {
			try {
				this.socket = serverSocket.accept();
				this.onAcceptedClient(RandomUtility.nextId(), RandomUtility.randomString());
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}

	/**
	 * Es necesario sobreescribir el método para asegurarnos la eliminación de la conexión
	 * de los sockets.
	 * @throws Throwable
	 */
	@Override
	protected void finalize() throws Throwable {
		this.onDisposeServer();
		super.finalize();
		this.serverSocket.close();
		this.socket.close();
	}

    protected abstract void onCreateServer();
	protected abstract void onStartServer();
	protected abstract void onDisposeServer();
	protected abstract void onAcceptedClient(final String id, final String nick);

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public Socket getSocket() {
		return socket;
	}
}
