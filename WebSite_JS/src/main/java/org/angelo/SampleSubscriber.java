package org.angelo;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class SampleSubscriber  {

	public static void main(String[] args) throws Exception {
		String brokerAddress = "tcp://127.0.0.1:1883";	
		MqttClient subscriber = new MqttClient(brokerAddress, "sub1");
		MqttConnectOptions options = new MqttConnectOptions();

		//creo una connessione permanente
		options.setCleanSession(false);
		subscriber.connect(options);//se togliamo option diventa pulita, non permane

		subscriber.subscribe("/nodes/+/sensors/+/samples", 2, new MyListener());
	}
}

class MyListener implements IMqttMessageListener {
	public void messageArrived(String topic, MqttMessage msg) {
		System.out.println("A message is arrived");
		try {
			System.out.println(new String(msg.getPayload()));
		} catch(Exception e) {System.err.println(e);}   
	}
}