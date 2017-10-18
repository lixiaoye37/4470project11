package cs4470;


import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class chat {

	public static void main(String[] args) {
		int myPort = 1818;
		int choice = 8;
		String myIp="";
		myIp = getIpAddress();
	
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Welcome to <Golden eagle Chat> program, You can start now!");
			do{ 
				
			String input = scanner.nextLine(); 
			String[] words=input.split(" ");
			if(input.equals("HELP")||input.equals("help")) {
				System.out.println("myip: display IP address");
				System.out.println("myport: my port number");
				System.out.println("connect: connect to another peer");
				System.out.println("send: send messages to peers");
				System.out.println("exit: exit the program");
			}
			else if(input.equals("myip")) {
				System.out.println(myIp);
			}
			else if(input.equals("myport")) {
				System.out.println(myPort);
			}
			else if(words[0].equals("connect")) {
				System.out.println("connect");
				
			}
			else if(words[0].equals("list")) {
				System.out.println("list");
				
			}
			else if(words[0].equals("send")) {
				System.out.println("send");
				
			}
			else if(input.equals("exit")) {
				choice=0;
			}
			else {
				System.out.println("Please Enter a Correct Command or Enter 'HELP' to check Commands!" );
			}
		}while(choice != 0);
		System.out.print("Thanks for using <Golden eagle Chat>,Good Bye!");

	}
	public static String getIpAddress() 
	{ 
	        URL myIP;
	        try {
	            myIP = new URL("http://api.externalip.net/ip/");

	            BufferedReader in = new BufferedReader(
	                    new InputStreamReader(myIP.openStream())
	                    );
	            return in.readLine();
	        } catch (Exception e) 
	        {
	            try 
	            {
	                myIP = new URL("http://myip.dnsomatic.com/");

	                BufferedReader in = new BufferedReader(
	                        new InputStreamReader(myIP.openStream())
	                        );
	                return in.readLine();
	            } catch (Exception e1) 
	            {
	                try {
	                    myIP = new URL("http://icanhazip.com/");

	                    BufferedReader in = new BufferedReader(
	                            new InputStreamReader(myIP.openStream())
	                            );
	                    return in.readLine();
	                } catch (Exception e2) {
	                    e2.printStackTrace(); 
	                }
	            }
	        }

	    return null;
	}

}
