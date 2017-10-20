package cs4470;

import java.net.*;
import java.io.*;

//adapted from: http://www.baeldung.com/a-guide-to-java-sockets

public class Client{
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public Client(String ip, int port) {
        try {
            startConnection(ip, port);
        } catch(Exception e) {
            System.out.println("Client constructor bug");
        }
    }

    //this connects to an ip and port of our choice
    public void startConnection(String ip, int port) throws Exception{
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String msg) throws Exception{
        out.println(msg);
        String response = in.readLine();
        return response;
    }

    public void stopConnection() throws Exception{
        in.close();
        out.close();
        clientSocket.close();
    }

}
