

package gmequer;

import java.io.IOException;
import java.util.Scanner;

public class Main { 	
	
	public static void main(String[] args) {
		PropertiesCollection prop = PropertiesCollection.getSingleton();
		FactorySenderReceiver factory = FactorySenderReceiver.getInstance();
    	Scanner keyboard = new Scanner(System.in);
    	OneThreadMQ threads[];
		
		System.out.println("GMEQUER 1.0");

		threads = new OneThreadMQ[factory.getNumberRunners()];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = factory.create(i);
		}	
		
		Scripts.getSingleton().initialization();	

    	System.out.println("Type help for all available commands ");

        while (true) {  
            try { Thread.sleep(Long.parseLong(prop.getProperty("statisticTime")) * 1000); } catch (Exception e) {}
        	try {
				if (System.in.available() != 0) {
				    String input = keyboard.nextLine();
				    if(input != null) {
				        if ("exit".equals(input)) {
				            finalize(threads);
				            break;
				        } else {
				        	Scripts.getSingleton().executeCommand(input);
				        }
				    } 
				}
				Scripts.getSingleton().loop();
			} catch (IOException e) {
				ErrorsCollection.getSingleton().addError(e);
			}
        } 

	}

	private static void finalize(OneThreadMQ threads[]) {
		for (int i = 0; i < threads.length; i++) {
			threads[i].terminate();
		}
		Scripts.getSingleton().finalization();
		System.exit(0);
	}

}
