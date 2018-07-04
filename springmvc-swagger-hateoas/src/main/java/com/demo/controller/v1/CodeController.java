package com.demo.controller.v1;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dao.v1.CodeDao;
import com.demo.model.v1.Code;


@RestController
public class CodeController {

	@Autowired
	CodeDao codeDao;

	private final Logger LOGGER = LoggerFactory.getLogger(CodeController.class);
	
	//-------------------Retrieve All code--------------------------------------------------------
	@CrossOrigin
	@RequestMapping(value = "/v1/code/", method = RequestMethod.GET)
	public ResponseEntity<List<Code>> listAllCode() {
	
		LOGGER.info("info logging");
        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("debug logging");
        }        
		
		List<Code> codeLst = codeDao.getCode();
		if(codeLst!=null && !codeLst.isEmpty()){
			LOGGER.info("codeLst ==========> "+codeLst);
			
			return new ResponseEntity<List<Code>>(codeLst, HttpStatus.OK);
			
		} else {
			return new ResponseEntity<List<Code>>(HttpStatus.NO_CONTENT);
		}
		
	}

}
