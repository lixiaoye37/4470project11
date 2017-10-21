package cs4470;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Peer extends Object{
	private static int connectedByCounter = 1;

	private static int connectedToCounter = 1;

	Socket socket;

	int id;

	int port;



	public Peer(Socket socket) {

		this.id = connectedByCounter++;

		this.socket = socket;

		this.port = socket.getPort();

	}



	public Peer(Socket socket, int portNumber) {

		this.id = connectedToCounter++;

		this.socket = socket;

		this.port = portNumber;

	}



	public int getId() {

		return id;

	}



	@Override

	public String toString() {

		String ip = socket.getInetAddress().getHostAddress();

		return id + ": " + ip + "    " + port;

	}



	public void terminate() {

		try {

			socket.close();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}



	public void sendMessage(String message) {

		PrintWriter out;

		try {

			out = new PrintWriter(socket.getOutputStream(), true);

			out.println(message);

		} catch (IOException e) {

			e.printStackTrace();

		}

	}



	public void printMessage() {

		if (socket.isOutputShutdown()) {

			return;

		}

		try {

			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String response;

	        while ((response = reader.readLine()) != null)

	        {

	        	System.out.println("Message received from " + socket.getInetAddress());

				System.out.println("Sender's Port :  <The port no." + port

						+ " of the sender>");

				System.out.println("Message:  " + "<\"" + response + "\">");

	        }

		} catch (IOException e) {

			e.printStackTrace();

		}

	}
}
