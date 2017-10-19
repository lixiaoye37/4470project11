package cs4470;

import java.util.Scanner;
import java.net.*;

public class chat {

    public static void main(String[] args) {
        int port = readPort(args);
        int choice = 8;
        Scanner scanner = new Scanner(System.in);

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
            } else if (input.equals("myip")) {
                System.out.println(myIP());
            } else if (input.equals("myport")) {
                System.out.println(myPort(port));
            } else if (input.startsWith("connect")) {

            } else if (input.equals("exit")) {
                choice = 0;
            }

        } while (choice != 0);
        System.out.print("Thanks for using <Golden eagle Chat>, Good Bye!");

    }

    public static void connect(String arguments) {

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
