package cs4470;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class chat {
	 static List<Peer> peerList = new ArrayList<>();
	 public static void setList(Peer peer) {
		 peerList.add(peer);
	 }
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
				System.out.println("terminate: close the connection with connection ID");
				System.out.println("exit: exit the program");
			}
			else if(input.equals("myip")) {
				System.out.println(myIp);
			}
			else if(input.equals("myport")) {
				System.out.println(myPort);
			}
			else if(words[0].equals("connect")) {
				System.out.println(words[1]);
				System.out.println(words[2]);
				connect(words[1],Integer.parseInt(words[2]));
				
			}
			else if(words[0].equals("list")) {
				connectionList();
				
			}
			else if(words[0].equals("send")) {
				String temp="";
				for(int i = 2;i<words.length;i++) {
					temp = temp +words[i]+" ";
				}
				send(Integer.parseInt(words[1]),temp);
			}
			else if(words[0].equals("terminate")) {
				send(Integer.parseInt(words[1]),"connection closed with:"+myIp);
				terminate(Integer.parseInt(words[1]));
				
				
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
		InetAddress ip;

		try {

			ip = Inet4Address.getLocalHost();

			return ip.getHostAddress();

		} catch (Exception e) {

			System.out.println("Can not get ip address:" + e.toString());

		}

		return "";
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
	public static void terminate(int connectionID) {

		for (int i = 0; i < peerList.size(); i++) {

			if (peerList.get(i).getId() == connectionID) {

				peerList.get(i).terminate();

				peerList.remove(i);

				return;

			}

		}

	}
	public static void send(int connectionID, String message) {

		for ( Peer peer : peerList) {

			if (peer.getId() == connectionID) {

				peer.sendMessage(message);

				return;

			}

		}

	}



	// REQUIEMENT # 8: exit

	public static void exit() {

		for ( Peer peer : peerList) {

			peer.terminate();

		}

	

	}
	
	

}

//class Server extends Thread {
//	List<Peer> listeningList;
//
//	ServerSocket listener;
//
//
//
//	Server(int listeningPortNumber){
//
//		try {
//
//			listener = new ServerSocket(listeningPortNumber);
//
//		} catch (IOException e) {
//
//			e.printStackTrace();
//
//		}
//
//		listeningList = new ArrayList<Peer>();
//
//	}
//
//
//
//	public void run(){
//
//		try {
//
//			while(true){
//
//				// Accepting new connections
//
//				Socket connection = listener.accept();
////				int   port = connection.getPort();
////				
////				Peer peer = new Peer (connection,port);
////				peerList.add(peer);
//
//				Client client = new Client(new Peer(connection));
//
//				client.start();
//
//				
//
//
//
//			}
//
//		} catch (IOException e) {
//
//			e.printStackTrace();
//
//		}
//
//	}
//
//}
