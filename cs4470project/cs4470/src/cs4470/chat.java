package cs4470;

import java.util.Scanner;

public class chat {

	public static void main(String[] args) {
		int myPort = 4545;
		int choice = 8;
		Scanner scanner = new Scanner(System.in);
		
			do{ 
				
			String input = scanner.nextLine(); 
			if(input.equals("HELP")) {
				System.out.println("myip: display IP address");
				System.out.println("myport: my port number");
				System.out.println("connect: connect to another peer");
				System.out.println("send: send messages to peers");
				System.out.println("exit: exit the program");
			}
			if(input.equals("exit")) {
				choice=0;
			}
		}while(choice != 0);
		System.out.print("Thanks for using <Golden eagle Chat>,Good Bye!");

	}

}
