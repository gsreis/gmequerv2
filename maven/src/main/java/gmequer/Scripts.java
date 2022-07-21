package gmequer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class Scripts {
	
	private static Scripts instance = null;
	
	public static Scripts getSingleton() {
		if (instance == null)
			instance = new Scripts();
		return instance;
	}
	
	public static void initialization() { 
		internalExecuteScript(PropertiesCollection.getSingleton().getStringProperty("scripts") + "/initialization.js", "execute");	
	}
	
	public static void finalization() { 
		internalExecuteScript(PropertiesCollection.getSingleton().getStringProperty("scripts") + "/finalization.js", "execute");
		
	}
	
	public static void loop() { 
		internalExecuteScript(PropertiesCollection.getSingleton().getStringProperty("scripts") + "/loop.js", "execute");
		
	}
	
	public void executeCommand(String input) {		
		if (input.equals("help"))
			showAllHelps();
		else
		    internalExecuteScript("commands/"+ input + ".js", "execute");
	}
	
	private void showAllHelps() {
		File files[] = new File("commands").listFiles();
		for (int i = 0; i < files.length; i++) {
			try {
				internalExecuteScript("commands/" + files[i].getName(), "help" );
			} catch (Exception e) {
				ErrorsCollection.getSingleton().addError(e);
			}
		}
	}

	private static void internalExecuteScript(String script, String function) { 
	    ScriptEngineManager manager = new ScriptEngineManager();
	    ScriptEngine engine = manager.getEngineByName("JavaScript");
	     
	    try {
	    	String script1 = new String(Files.readAllBytes(Paths.get(script)));
			engine.eval(script1);
		    Invocable inv = (Invocable) engine;
		    if (!function.equals("help")) {
		        inv.invokeFunction(function, ConnectionsCollection.getSingleton(), 
		    		                     ErrorsCollection.getSingleton(), 
		    		                     ResultsCollection.getSingleton(), 
		    		                     PropertiesCollection.getSingleton());

		    }
		    else {
		    	inv.invokeFunction(function);
		    }
		    	
		} catch (Exception e) {
			ErrorsCollection.getSingleton().addError(e);
		}
 
	}
}
