package gmequer;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.ibm.msg.client.jms.JmsConnectionFactory;
import com.ibm.msg.client.jms.JmsFactoryFactory;
import com.ibm.msg.client.wmq.WMQConstants;

public class ConnectionInfo {

	public JmsFactoryFactory factory;
	public JmsConnectionFactory jmsFactory;
	public javax.jms.Connection connection;
	public Session session;
	public Queue queue;
	public TextMessage textMessage;
	public MessageProducer producer;
	public MessageConsumer consumer;
	public boolean isProducer = false;
	public boolean connected = false;
	public int messageSizing = 0;
	public String pattern;
	public PropertiesCollection props = PropertiesCollection.getSingleton();
	
	
	public MessageConsumer getConsumer() {
		return consumer;
	}

	public void setProducer(MessageProducer producer) {
		this.producer = producer;
	}



	public ConnectionInfo(boolean isProducer) {
		this.isProducer = isProducer;
		messageSizing = props.getIntProperty("messageSizing");
		pattern = props.getStringProperty("messagePattern");
		try {
			factory = JmsFactoryFactory.getInstance(WMQConstants.WMQ_PROVIDER);
			jmsFactory = factory.createConnectionFactory();
			jmsFactory.setStringProperty(WMQConstants.WMQ_HOST_NAME, props.getStringProperty("hostNameSender"));
			jmsFactory.setStringProperty(WMQConstants.WMQ_PORT, props.getStringProperty("hostPortSender"));
			jmsFactory.setStringProperty(WMQConstants.WMQ_CHANNEL, props.getStringProperty("channelNameSender"));
			jmsFactory.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT);
			jmsFactory.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER,
					props.getStringProperty("queueManagerNameSender"));
			connection = jmsFactory.createConnection(props.getStringProperty("usernameSender"),
					props.getStringProperty("passwordSender"));
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			queue = session.createQueue(props.getStringProperty("queueNameSender"));
			
			if (isProducer) {
				producer = session.createProducer(queue);
				setPersistency();
			}	
			else
				consumer = session.createConsumer(queue);
			
			
			connection.start();
			connected = true;
		} catch (JMSException e) {
			ErrorsCollection.getSingleton().addError(e);
		}
	}


	public void terminate() {
		try {
			producer.close();
			session.close();
			connection.close();
			connected = false;
		} catch (Exception e) {
			ErrorsCollection.getSingleton().addError(e);
		}
	}
	
	public void processMessage() {
		OneResult oneres = new OneResult();
		oneres.isProducer = isProducer;
		try {
			if (isProducer) {
				String message = createMessage();
				oneres.messageSize = message.length();
				textMessage = session.createTextMessage(message);
				oneres.timestamp = System.currentTimeMillis();
				producer.send(textMessage);				
				oneres.processTime = System.currentTimeMillis() - oneres.timestamp;
			}else {
				oneres.timestamp = System.currentTimeMillis();
				Message m = consumer.receive();
			    
				oneres.messageSize = ((String)m.getBody(String.class)).length();
				oneres.processTime = System.currentTimeMillis() - oneres.timestamp;
			}
			ResultsCollection.getSingleton().addResult(oneres);

		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

    private void setPersistency() {
		try {
			if (props.getBooleanProperty("persistent"))
			    producer.setDeliveryMode(DeliveryMode.PERSISTENT);
			else
			    producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		} catch (JMSException e) {
			ErrorsCollection.getSingleton().addError(e);
		}
    }
    
	private String createMessage() {
		int numrep = messageSizing / pattern.length();
		String result = "";
		for (int i = 0; i < numrep; i++) {
			result += pattern;
		}
		return result;
	}
    	
}
