package com.javacodegeeks.jms;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;

public class JmsMultipleCustomersMessageListenerExample {
	public static void main(String[] args) throws URISyntaxException, Exception {
	/*	BrokerService broker = BrokerFactory.createBroker(new URI(
				"broker:(tcp://localhost:61616)"));*/
		//broker.start();
		Connection connection = null;
		try {
			// Producer
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
					"tcp://localhost:61616");
			connection = connectionFactory.createConnection();
			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			Queue queue1 = session.createQueue("customerQueue1");
			Queue queue2 = session.createQueue("customerQueue2");
			Queue queue3 = session.createQueue("customerQueue3");
			Queue queue4 = session.createQueue("customerQueue4");
			Queue queue5 = session.createQueue("customerQueue5");
			Queue queue6 = session.createQueue("customerQueue6");
			Queue queue7 = session.createQueue("customerQueue7");
			Queue queue8 = session.createQueue("customerQueue8");
			Queue queue9 = session.createQueue("customerQueue9");

			List<Queue>listaColas=new ArrayList<Queue>();
			listaColas.add(queue1);
			listaColas.add(queue2);
			listaColas.add(queue3);
			listaColas.add(queue4);
			listaColas.add(queue5);
			listaColas.add(queue6);
			listaColas.add(queue7);
			listaColas.add(queue8);
			listaColas.add(queue9);


			// Consumer
			for (int i = 0; i < 9; i++) {
				MessageConsumer consumer = session.createConsumer(listaColas.get(i));
				consumer.setMessageListener(new ConsumerMessageListener(
						"Consumer " + i));
			}
			connection.start();

			String basePayload = "Important Task";

		/*	for (int i = 0; i < 10; i++) {
				MessageProducer producer = session.createProducer(listaColas.get(i));
				String payload = basePayload + i;
				Message msg = session.createTextMessage(payload);
				System.out.println("Sending text '" + payload + "'");
				producer.send(msg);
			}*/

			Thread.sleep(1000);
			session.close();
		} finally {
			if (connection != null) {
				connection.close();
			}
			//broker.stop();
		}
	}

}
