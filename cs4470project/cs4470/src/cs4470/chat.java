package cs4470;

import java.util.Scanner;

public class chat {

    public static void main(String[] args) {
        int myPort = 4545;
        int choice = 8;
        Scanner scanner = new Scanner(System.in);

        do{
            System.out.print(">> ");
            String input = scanner.nextLine();
            if(input.equals("HELP")) {
                System.out.println("myip: display my IP address");
                System.out.println("myport: my listening port number");
                System.out.println("connect <destination> <port no>: connect to IP address at port");
                System.out.println("list: show all connections");
                System.out.println("terminate <connection id>: end connection with specific id");
                System.out.println("send <connection id> <message>: send message to id");
                System.out.println("exit: close all connections and exit the program");
            }
            if(input.equals("exit")) {
                choice=0;
            }

        }while(choice != 0);
        System.out.print("Thanks for using <Golden eagle Chat>, Good Bye!");

    }

}
