package cs4470;

public class Client extends Thread{
	private Peer peer;



	Client(Peer peer){

		this.peer = peer;

	}



	public void run(){

		peer.printMessage();

	}
}