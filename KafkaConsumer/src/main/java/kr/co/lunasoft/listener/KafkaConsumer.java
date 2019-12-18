package kr.co.lunasoft.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaConsumer {

	@KafkaListener(topics = "elastic-call")
	public void receiveElasticCallTopic(String payload) {
		log.info("[elastic-call] received payload='{}'", payload);
	}

}
