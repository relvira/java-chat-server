package com.heydude.server;

import java.io.IOException;
import java.net.*;

/**
 * Clase encargada de crear una instancia estática de servidor.
 * Esta clase no puede ser instanciada ni heredada.
 * Consta de tres variables privadas, las cuales contienen el puerto de conexión,
 * la IP y la instancia de la clase.
 */
public final class ServerConnection {
    private static final int PORT = 8000;
    private static final String IP_ADDRESS = "162.243.214.24";
    private static final ServerConnection INSTANCE = new ServerConnection(PORT, IP_ADDRESS);

    private ServerSocket mServerSocket;

    private ServerConnection()  {}

    /**
     * Constructor que inicializa el socket del servidor en el puerto e IP especificada.
     * @param pPort Puerto de escucha del servidor.
     * @param pIpAddress IP del servidor.
     * @throws java.io.IOException Lanza una excepci&oacute;n IO si ocurre alg&uacute;n problema al iniciar el socket.
     */
    private ServerConnection(int pPort, String pIpAddress) {
        try {
            this.mServerSocket = new ServerSocket(pPort, 50, InetAddress.getByName(pIpAddress));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Sobreescribimos el metodo finalize() para eliminar explícitamente el socket.
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        this.mServerSocket.close();
    }

    /**
     * Método que permite ir añadiendo sockets de cliente al socket del servidor.
     * @return Retorna el socket al cual se puede conectar el cliente.
     */
    public static Socket accept() throws IOException {
        return INSTANCE.mServerSocket.accept();
    }

    public static ServerConnection getInstance() {
        return INSTANCE;
    }
}
