package test;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MyServer extends Thread {

    private int port;
    private ClientHandler ch;
    private volatile boolean stop;

    public MyServer(int port, ClientHandler ch) {
        this.port = port;
        this.ch = ch;
        this.stop = false;
    }

    private void runServer() throws Exception {
        ServerSocket server = new ServerSocket(port);
        server.setSoTimeout(1000);

        while (!stop) {
            try {
                Socket aClient = server.accept();
                InputStream in = aClient.getInputStream();
                OutputStream out = aClient.getOutputStream();
                try {
                    ch.handleClient(in, out);
                } finally {
                    in.close();
                    out.close();
                    aClient.close();
                }
            } catch (SocketTimeoutException e) {
                // System.out.println("Exception : " + e.getMessage());
            } catch (Exception e) {
                // System.out.println("Exception : " + e.getMessage());
                e.printStackTrace();
            }
        }
    
        server.close();
    }
    

    public void start() {
            
        new Thread(()->{
            try {
                this.runServer();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }).start();
    }

    public void close() {
        
        try {
            stop = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

	
}
