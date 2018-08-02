package com.demo.dao.v1;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.demo.model.v1.Code;

@Repository("codeDao")
public class CodeDaoImpl implements CodeDao {

	private final Logger logger = LoggerFactory.getLogger(CodeDaoImpl.class);
	
	@Cacheable(value="codeCache") //mapped with ehcache.xml
	public List<Code> getCode() {
		logger.info("CodeDaoImpl is invoked to retrieve data");
		
		List<Code> codeList = new ArrayList<Code>();
		
		Code code1 = new Code(1,"Data code 1");
		Code code2 = new Code(2,"Data code 2");
		Code code3 = new Code(3,"Data code 3");
		Code code4 = new Code(3,"Data code 4");
		
		codeList.add(code1);
		codeList.add(code2);
		codeList.add(code3);
		codeList.add(code4);
		
		return codeList;
	}
	
}