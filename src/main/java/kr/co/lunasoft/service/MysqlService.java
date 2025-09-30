package kr.co.lunasoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.lunasoft.mapper.MysqlMapper;
import kr.co.lunasoft.model.NoticeInfo;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MysqlService {
	
	@Autowired
	private MysqlMapper mysqlMapper;
	
	@Transactional
	public int addNotice(NoticeInfo param) {
		return mysqlMapper.insertNotice(param);
	}

}
