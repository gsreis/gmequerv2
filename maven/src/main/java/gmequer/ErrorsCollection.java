package gmequer;

import java.util.Vector;

public class ErrorsCollection {
    private Vector<Exception> errors = new Vector<Exception>();
   
	private static ErrorsCollection instance = null;
	
	public static ErrorsCollection getSingleton() {
		if (instance == null)
			instance = new ErrorsCollection();
		return instance;
	}
   
    public ErrorsCollection() {
    	errors = new Vector<Exception>();
    }
	
	public synchronized void addError(Exception e) { 
       errors.add(e);
	}
	
	public void clear() { 
		errors.clear();
	}
	
	public Exception[] getArray() {
		Exception result[] = new Exception[errors.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = errors.elementAt(i);
		}
		return result;
	}
}
