package gmequer;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Iterator;

public class PropertiesCollection extends java.util.Properties{
	
	     private static PropertiesCollection props = null;
	     public static PropertiesCollection getSingleton() { 
	    	 if (props == null) 
	    		  props = new PropertiesCollection();
	    	 return props;
	     }

	     public PropertiesCollection() { 
	    	 super();
	    	 initialize();
	     }
	     
	     public String getStringProperty(String prop) {
	    	 return super.getProperty(prop).trim();
	     }
	     
	     public int getIntProperty(String prop) {
	    	 return Integer.parseInt(super.getProperty(prop));
	     }
	     
	     public long getLongProperty(String prop) {
	    	 return Long.parseLong(super.getProperty(prop));
	     }
	     
	     public boolean getBooleanProperty(String prop) {
	    	 return Boolean.parseBoolean(super.getProperty(prop));
	     }
	     
	 	private void initialize() {
			InputStream reader = null;
			try {
				reader = new FileInputStream("gmequer.properties");
				this.load(reader);
			} catch (Exception e1) {
				System.out.println("Necessário o arquivo gmequer.properties"); 
				System.exit(0);
			}
		}
        public OneProperty[] getArray() {
        	OneProperty[] values = new OneProperty[this.size()];
        	Enumeration<Object> keys = keys();
        	int count = 0;
        	while (keys.hasMoreElements()) {
        		String key = keys.nextElement().toString();
				OneProperty prop = new OneProperty(key, this.getStringProperty(key));
				values[count++] = prop;
			}
        	return values;
        }
        public void clear() { 
        }
	     
}
