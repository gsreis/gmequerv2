package gmequer;

public class OneReceiver extends Thread implements OneThreadMQ{

	ConnectionInfo connection = null;
	PropertiesCollection props = null;
	
	private boolean running = true;
	private long sleepTime = 0;


	public OneReceiver() {
		
		props = PropertiesCollection.getSingleton();
		sleepTime = props.getLongProperty("sleepTimeSender");

		
		connection = new ConnectionInfo(false);
		ConnectionsCollection.getSingleton().add(connection);
	}
	
	public void run() {
		while (running) {
			if (!connection.connected)
				continue;
			connection.processMessage();
			try { Thread.sleep( sleepTime);  } catch(Exception e ) {}
		}
	}
	
	public void terminate()
	{
		running = false;
		connection.terminate();		
	}
}