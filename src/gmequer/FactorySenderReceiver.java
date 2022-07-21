package gmequer;

public class FactorySenderReceiver {

	private static FactorySenderReceiver singleton = null;
	private PropertiesCollection props = null;
	public static FactorySenderReceiver getInstance() {
		if (singleton == null)
			singleton = new FactorySenderReceiver();
		return singleton;
	}
	
	public FactorySenderReceiver() {
		props = PropertiesCollection.getSingleton();
	}
	public int getNumberSender() {
		return Integer.parseInt(props.getProperty("numThreadsSender").trim());
	}
	public int getNumberReceiver() {
		return Integer.parseInt(props.getProperty("numThreadsReceiver").trim());
	}
	public int getNumberRunners() {
		return getNumberSender() + getNumberReceiver();
	}
	
	public OneThreadMQ create(int i) {
		if (i < getNumberSender()) {
			return new OneSender();
		} else {
			return new OneReceiver();
		}
	}
	
}
