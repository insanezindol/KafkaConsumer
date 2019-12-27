package kr.co.lunasoft.mapper;

import org.springframework.stereotype.Repository;

import kr.co.lunasoft.model.NoticeInfo;

@Repository
public interface MysqlMapper {
	
	int insertNotice(NoticeInfo param);

}
