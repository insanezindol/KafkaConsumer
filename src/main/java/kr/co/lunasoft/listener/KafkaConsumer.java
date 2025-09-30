package kr.co.lunasoft.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import kr.co.lunasoft.model.MessageInfo;
import kr.co.lunasoft.model.NoticeInfo;
import kr.co.lunasoft.service.ElasticsearchService;
import kr.co.lunasoft.service.MysqlService;
import kr.co.lunasoft.util.DateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaConsumer {

	@Autowired
	ElasticsearchService elasticsearchService;
	
	@Autowired
	MysqlService mysqlService;

	@KafkaListener(topics = "elastic-call")
	public void receiveElasticCallTopic(String payload) {
		log.info("[CONSUMER] [elastic-call] payload = '{}'", payload);
		MessageInfo messageInfo = new Gson().fromJson(payload, MessageInfo.class);
		messageInfo.setRequestTime(DateUtil.getNowDatetime());
		log.info("[CONSUMER] [elastic-call] messageInfo = '{}'", messageInfo);
		elasticsearchService.callElasticsearch("POST", "lunasoft-message/message", messageInfo);
	}
	
	@KafkaListener(topics = "mysql-call")
	public void receiveMysqlCallTopic(String payload) {
		log.info("[CONSUMER] [mysql-call] payload = '{}'", payload);
		NoticeInfo noticeInfo = new Gson().fromJson(payload, NoticeInfo.class);
		int resultCnt = mysqlService.addNotice(noticeInfo);
		log.info("[CONSUMER] [mysql-call] noticeInfo = '{}' , '{}'", noticeInfo, resultCnt);
	}
	
	@KafkaListener(topics = "test")
	public void receiveTestTopic(String payload) {
		log.info("[CONSUMER] [TEST] message = '{}'", payload);
	}
	
	@KafkaListener(topics = "dean")
	public void receiveDeanTopic(String payload) {
		log.info("[CONSUMER] [DEAN] message = '{}'", payload);
	}

}
