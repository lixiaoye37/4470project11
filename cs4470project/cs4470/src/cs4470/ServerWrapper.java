package cs4470;

import java.net.*;
import java.io.*;
import java.util.HashMap;

//adapted from: http://www.baeldung.com/a-guide-to-java-sockets

public class ServerWrapper extends Thread{
    private ServerSocket serverSocket;
    private static HashMap<String, PrintWriter> users;

    public ServerWrapper(int port) throws Exception{
        this.serverSocket = new ServerSocket(port);
        this.users = new HashMap<String, PrintWriter>();
    }

    public void run() {
        //accept blocks until connection made
        try {
//            Server s = new Server(serverSocket.accept());
//            s.start();
            while(true) { //wait for additional clients
                new Server(serverSocket.accept()).start();
            }
        } catch(Exception e) {
            System.out.println("Error in ServerWrapper run");
            System.out.println(e);
        }
    }

    private class Server extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public Server(Socket sock) throws Exception {
            this.clientSocket = sock;
        }

        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String name = clientSocket.getInetAddress().getHostAddress();
                if(!users.containsKey(name)) {
                    users.put(name, out); // <ip_string, printwriter>
                    System.out.println("User connected from IP: " + name);
                }
                else {
                    System.out.println("That name already exists in the users list");
                }
                System.out.print(">> "); //a little hack to clean up the console-like input
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
//                    System.out.println(inputLine);
//                    if (".".equals(inputLine)) {
//                        out.println("good bye");
//                        break;
//                    }
                    for(PrintWriter writer : users.values()) {
                        out.println(inputLine);
                    }
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

}
