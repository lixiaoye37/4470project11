package cs4470;

import java.net.*;
import java.io.*;

//adapted from: http://www.baeldung.com/a-guide-to-java-sockets

public class Client{
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private String ip;
    private int port;
    private int id;

    public Client(String ip, int port, int id) {
        try {
            this.ip = ip;
            this.port = port;
            this.id = id;
            startConnection();
        } catch(Exception e) {
            System.out.println("Client constructor bug");
        }
    }

    public String getIp() {
        return this.ip;
    }

    public int getPort() {
        return this.port;
    }

    public int getId() {
        return this.id;
    }

    //this connects to an ip and port of our choice
    public void startConnection() throws Exception{
        clientSocket = new Socket(ip, port);
//        System.out.println(ip);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String msg) throws Exception{
        out.println(msg); //send message to server
        String response = in.readLine(); //see response from server (for testing)
        return response;
    }

    //close all connections upon exit command
    public void stopConnection() throws Exception{
        in.close();
        out.close();
        clientSocket.close();
    }

}
