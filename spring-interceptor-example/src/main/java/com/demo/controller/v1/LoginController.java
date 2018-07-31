package com.demo.controller.v1;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {

	private final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	static int counter = 0;

	@CrossOrigin
	@RequestMapping(value = "/v1/login", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<JSONObject>> login() {
		
		List<JSONObject> resultList = new ArrayList<JSONObject>();
		counter++;
		JSONObject jsonObject = new JSONObject();
		
		if (counter % 2 == 0) {
			jsonObject.put("meesage", "Login successful.");
			resultList.add(jsonObject);
			return new ResponseEntity<List<JSONObject>>(resultList, HttpStatus.NO_CONTENT);
		} else {
			jsonObject.put("meesage", "Login failed");
			resultList.add(jsonObject);
			return new ResponseEntity<List<JSONObject>>(resultList, HttpStatus.OK);
		}
			
	}
		
}
