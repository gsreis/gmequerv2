package gmequer;

import java.util.Vector;

public class ConnectionsCollection {

	private static ConnectionsCollection instance = null;
	private Vector<ConnectionInfo> collec;

	public static ConnectionsCollection getSingleton() {
		if (instance == null)
			instance = new ConnectionsCollection();
		return instance;
	}
	
	public ConnectionsCollection() {
		collec = new Vector<ConnectionInfo>();
	}
	
	public ConnectionInfo[] getArray() {
			ConnectionInfo result[] = new ConnectionInfo[collec.size()];
			for (int i = 0; i < result.length; i++) {
				result[i] = collec.elementAt(i);
			}
			return result;
	}
	public void clear() {
	}
	public synchronized void add(ConnectionInfo info) {
		collec.add(info);
	}
}
