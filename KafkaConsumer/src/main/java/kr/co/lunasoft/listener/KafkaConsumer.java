package kr.co.lunasoft.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import kr.co.lunasoft.model.MessageInfo;
import kr.co.lunasoft.service.ElasticsearchService;
import kr.co.lunasoft.util.DateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaConsumer {

	@Autowired
	ElasticsearchService elasticsearchService;

	@KafkaListener(topics = "elastic-call")
	public void receiveElasticCallTopic(String payload) {
		log.info("[CONSUMER] [elastic-call] payload = '{}'", payload);

		MessageInfo messageInfo = new Gson().fromJson(payload, MessageInfo.class);
		messageInfo.setRequestTime(DateUtil.getNowDatetime());

		log.info("[CONSUMER] [elastic-call] messageInfo = '{}'", messageInfo);
		elasticsearchService.callElasticsearch("POST", "lunasoft-message/message", messageInfo);
	}

}
