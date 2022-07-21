package gmequer;

public class OneSender extends Thread implements OneThreadMQ{


	ConnectionInfo connection = null;
	PropertiesCollection props = null;
	
	private boolean running = true;
	private long sleepTime = 0;


	public OneSender() {
		
		props = PropertiesCollection.getSingleton();
		sleepTime = props.getLongProperty("sleepTimeSender");

		
		connection = new ConnectionInfo(true);
		ConnectionsCollection.getSingleton().add(connection);
		this.start();
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