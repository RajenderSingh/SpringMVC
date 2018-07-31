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
public class UserController {

	private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@CrossOrigin
	@RequestMapping(value = "/v1/getUserList", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<JSONObject>> getUserList() {
		
		List<JSONObject> userList = new ArrayList<JSONObject>();
		
		JSONObject jsonObject = new JSONObject();
		try {
			
				jsonObject.put("message", "User List found.");
				userList.add(jsonObject);
			
		} catch (Exception e) {
			jsonObject.put("message", "No User List found.");
			userList.add(jsonObject);
			return new ResponseEntity<List<JSONObject>>(userList, HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<JSONObject>>(userList, HttpStatus.OK);

	}
	
		
}
