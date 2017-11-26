package cs4470;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread {
	List<Peer> listeningList;

	ServerSocket listener;



	Server(int listeningPortNumber){

		try {

			listener = new ServerSocket(listeningPortNumber);

		} catch (IOException e) {

			e.printStackTrace();

		}

		listeningList = new ArrayList<Peer>();

	}



	public void run(){

		try {

			while(true){

				// Accepting new connections

				Socket connection = listener.accept();
				int port = connection.getPort();
				Peer peer = new Peer(connection, port);

				chat.setList(peer);
				Client client = new Client(new Peer(connection));

				client.start();

				



			}

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}