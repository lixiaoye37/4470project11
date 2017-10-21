package cs4470;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class chat {
	static List<Peer> peerList = new ArrayList<>();
	static int myPort=8;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		
		int choice = 8;
		String input1;
		
		System.out.println("Enter a port Number");
		input1 = scanner.nextLine(); 
		myPort = Integer.parseInt(input1);
		Server serverThread = new Server(myPort);
		serverThread.start();
		String myIp="";
		myIp = getIpAddress();
	
		
		
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
				connect(words[1],Integer.parseInt(words[2]));
				
			}
			else if(words[0].equals("list")) {
				connectionList();
				
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
	public static void connect(String ip, int portNumber) {
		try {

			Socket socket = new Socket(ip, portNumber);

			Peer peer = new Peer(socket, portNumber);

			peer.sendMessage("You have connected to " + socket.getLocalAddress().toString());

			System.out.println("You have connected to " + ip);

			peerList.add(peer);

		} catch (Exception e) {

			System.out.println("Can not connect to " + ip + ". Error: " + e.toString());

		}
	}
	public static void connectionList() {

		System.out.println("id: IPaddress   PortNo.");

		for ( Peer peer : peerList) {

			System.out.println(peer.toString());

		}

	}

}

