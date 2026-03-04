package org.angelo;

import org.eclipse.paho.client.mqttv3.MqttClient;


public class SamplePublisher {
	
	public static void main(String[] args) throws Exception {
		String brokerAddress = "tcp://127.0.0.1:1883";
		try (MqttClient publisher = new MqttClient(brokerAddress, "pub")) {
			publisher.connect();
			while (true) {
				 try { Thread.sleep(1000); } catch (InterruptedException e) { }
			     publisher.publish("/nodes/10/sensors/10/samples", new String("{ \"attribute\" : \"temperature\", \"simpleValue\" : \" " + (Math.random()*2+20) + "\"  }").getBytes(), 2, true); //false indica no retraition
			}
		}  
	}
}



