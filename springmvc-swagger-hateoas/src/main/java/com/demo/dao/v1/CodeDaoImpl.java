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
	
	@Cacheable(value="codeCache") //It is mapped in ehcache.xml file
	public List<Code> getCode() {
		logger.info("CodeDaoImpl is called to fetch data...");
		List<Code> codeArrList = new ArrayList<Code>();
		Code code1 = new Code(1,"CODE1");
		Code code2 = new Code(2,"CODE2");
		Code code3 = new Code(3,"CODE3");
		codeArrList.add(code1);
		codeArrList.add(code2);
		codeArrList.add(code3);
		return codeArrList;
	}
	
}