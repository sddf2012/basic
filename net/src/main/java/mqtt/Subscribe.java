package mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author liu peng bo
 * date: 2021/3/6 16:57
 */
public class Subscribe {
    public static void main(String[] args) {

        final String broker = "tcp://localhost:1883";
        final String acessKey = "emqx_test1";
        final String secretKey = "emqx_test_password";
        final String topic = "$share/im/send";
        final String clientId = "client101";
        String sign;
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            final MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            final MqttConnectOptions connOpts = new MqttConnectOptions();
            System.out.println("Connecting to broker: " + broker);

            final String[] topicFilters = new String[]{topic};
            final int[] qos = {1};
            connOpts.setUserName(acessKey);
            connOpts.setServerURIs(new String[]{broker});
            connOpts.setPassword(secretKey.toCharArray());
            connOpts.setCleanSession(false);//设置确定是否继续接受离线消息
            connOpts.setKeepAliveInterval(90);
            connOpts.setAutomaticReconnect(true);
            final ExecutorService executorService = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>());
            sampleClient.setCallback(new MqttCallbackExtended() {
                @Override
                public void connectComplete(boolean reconnect, String serverURI) {
                    System.out.println("connect success");
                    //连接成功，需要上传客户端所有的订阅关系
                    executorService.submit(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                sampleClient.subscribe(topicFilters, qos);
                            } catch (Exception me) {
                                me.printStackTrace();
                            }
                        }
                    });
                }

                @Override
                public void connectionLost(Throwable throwable) {
                    System.out.println("mqtt connection lost");
                }

                @Override
                public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                    System.out.println("messageArrived:" + topic + "------" + new String(mqttMessage.getPayload()));
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                    System.out.println("deliveryComplete:" + iMqttDeliveryToken.getMessageId());
                }
            });
            //客户端每次上线都必须上传自己所有涉及的订阅关系，否则可能会导致消息接收延迟
            sampleClient.connect(connOpts);
            //每个客户端最多允许存在30个订阅关系，超出限制可能会丢弃导致收不到部分消息
            sampleClient.subscribe(topicFilters, qos);
            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception me) {
            me.printStackTrace();
        }
    }
}
