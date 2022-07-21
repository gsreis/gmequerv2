package gmequer;

import java.util.Vector;

public class ResultsCollection {

	private static ResultsCollection instance = null;
	Vector<OneResult> collec;	
	private boolean paused = false;
	
	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public static ResultsCollection getSingleton() {
		if (instance == null)
			instance = new ResultsCollection();
		return instance;
	}
	
	public ResultsCollection() {
		collec = new Vector<OneResult>();
		paused = false;
	}
	
	public OneResult[] getArray() {
		if (paused) {
			OneResult result[] = new OneResult[0];
			return result;
		} else {
			OneResult result[] = new OneResult[collec.size()];
			for (int i = 0; i < result.length; i++) {
				result[i] = collec.elementAt(i);
			}	
			return result;
		}	
	}
    public synchronized void addResult(OneResult result) {
    	collec.add(result);
	}
    
    public synchronized void clear() {
    	collec.clear();
    }
    
}
