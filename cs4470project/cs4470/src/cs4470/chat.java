package cs4470;

//import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.net.*;

public class chat {

    public static void main(String[] args) {
        int port = readPort(args);
        int id = 1;
        String ip = myIP();
        int choice = 8;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Client> clients = new ArrayList<Client>();

        //server has to start before client
        ServerWrapper s;
        try {
            s = new ServerWrapper(port);
            s.start();
        } catch (Exception e) {
            System.out.println();
        }

        Client c = new Client(ip, port, id);
        clients.add(c);

        do {
            System.out.print(">> ");
            String input = scanner.nextLine();
            if (input.equals("help")) {
                System.out.println("myip: display my IP address");
                System.out.println("myport: my listening port number");
                System.out.println("connect <destination> <port no>: connect to IP address at port");
                System.out.println("list: show all connections");
                System.out.println("terminate <connection id>: end connection with specific id");
                System.out.println("send <connection id> <message>: send message to id");
                System.out.println("exit: close all connections and exit the program");
            }
            else if(input.equals("myip")) {
                System.out.println(myIP());
            }
            else if(input.equals("myport")) {
                System.out.println(myPort(port));
            }
            else if(input.startsWith("connect")) {
                id++;
                connect(input, clients, id);
            }
            else if(input.startsWith("terminate")) {
                terminate(input, clients);
            }
            else if(input.equals("list")) {
                listClients(clients);
            }
            else if(input.startsWith("send")) {
                send(input, clients);
            }
            else if(input.equals("exit")) {
                choice = 0;
                try {
                    for(Client cli: clients) {
                        c.stopConnection();
                    }
                    System.out.println("Closed all client connections");
                } catch(Exception e) {
                    System.out.println("Error closing client connections");
                }
            }
            else {
                System.out.println("invalid command");
            }

        } while(choice != 0);
        System.out.print("Thanks for using <Golden Eagle Chat>, Good Bye!");

    }

    public static void send(String arguments, ArrayList<Client> clients) {
        arguments = arguments.replace("send ", ""); //replace "send " with ""
        int id = Character.getNumericValue(arguments.charAt(0));
        arguments = arguments.substring(1); //remove the id from the message string

        if(clients != null) {
            Boolean found = false;
            for(Client c: clients) {
                if(c.getId() == id) {
                    found = true;
                    try {
                        System.out.println("Response: " + c.sendMessage(arguments));
                        break;
                    } catch(Exception e) {
                        System.out.println("Error closing connections");
                    }
                }
            }
            if(!found) {
                System.out.println("Unable to find matching connection id to send message to");
            }
        }
        else{
            System.out.println("No connection to send message to");
        }
    }

    public static void terminate(String argument, ArrayList<Client> clients) {
        String[] splitter = argument.split(" ");
        int id = Integer.parseInt(splitter[1]);

        if(clients != null) {
            Boolean found = false;
            for(Client c: clients) {
                if(c.getId() == id) {
                    found = true;
                    try {
                        c.stopConnection();
                        System.out.println("Connection id: " + id + " closed successfully!");
                        break;
                    } catch(Exception e) {
                        System.out.println("Error closing connections");
                    }
                }
            }
            if(!found) {
                System.out.println("Unable to find matching connection id to terminate");
            }
        }
        else{
            System.out.println("No connections to terminate");
        }

    }


    public static void connect(String arguments, ArrayList<Client> clients, int id) {
        String[] splitter = arguments.split(" ");
        String ip = splitter[1]; //holds ip at index 1
        int port = Integer.parseInt(splitter[2]); //holds port at index 2

        Client c = new Client(ip, port, id);
        clients.add(c);

        System.out.println("Successfully connected to IP: " + ip + " on port: " + port);
    }

    public static void listClients(ArrayList<Client> clients) {
        if(clients != null) {
            System.out.println("id:\t\tIP Address\t\tPort No.");
            for(Client c: clients) {
                System.out.println(c.getId() + ":\t\t" + c.getIp() + "\t" + c.getPort());
            }
        }
        else {
            System.out.println("There are currently no connections.");
        }

    }


    public static String myIP() {
        try {
            InetAddress IP = InetAddress.getLocalHost();
            return IP.getHostAddress();
        } catch (Exception e) {
            System.out.println("Uh oh. Something happened.");
        }
        return ""; //this happens if something breaks
    }

    public static int myPort(int port) {
        return port;
    }

    public static int readPort(String args[]) {
        int port = 0;
        try {
            if(args.length == 1) { //do we have one command line arguments?
                port = Integer.parseInt(args[0]);
            }
            else { //we have too few arguments
                port = 4040; //default port if less than one argument
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong number");
            port = 4040; //default port if bad input
        }
        return port;

    }
}
