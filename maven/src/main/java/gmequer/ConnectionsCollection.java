package gmequer;

import java.util.Vector;

public class ConnectionsCollection {

	private static ConnectionsCollection instance = null;
	private Vector<ConnectionInfo> collec;
	private boolean paused = false;
	
	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public static ConnectionsCollection getSingleton() {
		if (instance == null)
			instance = new ConnectionsCollection();
		return instance;
	}
	
	public ConnectionsCollection() {
		collec = new Vector<ConnectionInfo>();
	}
	
	public ConnectionInfo[] getArray() {
		if (!paused) {
			ConnectionInfo result[] = new ConnectionInfo[collec.size()];
			for (int i = 0; i < result.length; i++) {
				result[i] = collec.elementAt(i);
			}
			return result;			
		} else {
			ConnectionInfo result[] = new ConnectionInfo[0];
			return result;
		}

	}
	public void clear() {
	}
}
