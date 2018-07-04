package com.demo.controller.v1;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.demo.model.v1.User;
import com.demo.service.v1.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
@Api(value = "UserController", description = "REST APIs related to User")
public class UserController {

	@Autowired
	UserService userService;

	private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	//-------------------Retrieve All Users--------------------------------------------------------
	@CrossOrigin
	@RequestMapping(value = "/v1/user/", method = RequestMethod.GET)
	@ApiOperation(value="List of Users",notes="Get list of all Users",response=UserController.class)
	public ResponseEntity<List<User>> listAllUsers() {
	
		LOGGER.info( "write log1" );
		LOGGER.info( "write log2" );
		LOGGER.info( "write log3" );
        
		
		List<User> users = userService.findAllUsers();
		if(users.isEmpty()){
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}


	//-------------------Retrieve Single User--------------------------------------------------------
	@CrossOrigin
	@RequestMapping(value = "/v1/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Get User",notes="Get User by a particular id",response=UserController.class)
	public ResponseEntity<User> getUser(@PathVariable("id") long id) {
		System.out.println("Fetching User with id " + id);
		User user = addLinks(userService.findById(id));
		if (user == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	private static User addLinks(User user) {
		Link selfLink = linkTo(methodOn(UserController.class).getUser(user.getUId())).withSelfRel();
		//Link invoiceLink = linkTo(methodOn(InvoiceController.class).getInvoiceByUserId(userr.getUId())).withRel("invoice");
		 
		user.add(selfLink);
		//user.add(invoiceLink);
		 
		return user;
		 
		}
	
	//-------------------Create a User--------------------------------------------------------    
	@CrossOrigin
	@RequestMapping(value = "/v1/user/", method = RequestMethod.POST)
	@ApiOperation(value="Create User",notes="Creation of a new User",response=UserController.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User Created successfully"),
            @ApiResponse(code = 401, message = "You are Not authorized to create User"),
            @ApiResponse(code = 403, message = "Create User is forbidden"),
            @ApiResponse(code = 404, message = "Resource Not found")
    })
	public ResponseEntity<Void> createUser(@RequestBody User user, 	UriComponentsBuilder ucBuilder) {
		System.out.println("Creating User " + user.getName());

		if (userService.isUserExist(user)) {
			System.out.println("A User with name " + user.getName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		userService.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	
	//------------------- Update a User --------------------------------------------------------
	@CrossOrigin
	@RequestMapping(value = "/v1/user/{id}", method = RequestMethod.PUT)
	@ApiOperation(value="Update User",notes="Update a particular User",response=UserController.class)
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		System.out.println("Updating User " + id);
		
		User currentUser = userService.findById(id);
		
		if (currentUser==null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		currentUser.setName(user.getName());
		currentUser.setAge(user.getAge());
		currentUser.setSalary(user.getSalary());
		
		userService.updateUser(currentUser);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}

	//------------------- Delete a User --------------------------------------------------------
	@CrossOrigin
	@RequestMapping(value = "/v1/user/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value="Delete User",notes="Delete a particular User",response=UserController.class)
	public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting User with id " + id);

		User user = userService.findById(id);
		if (user == null) {
			System.out.println("Unable to delete. User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		userService.deleteUserById(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	
	//------------------- Delete All User --------------------------------------------------------
	@CrossOrigin
	@RequestMapping(value = "/v1/user/", method = RequestMethod.DELETE)
	@ApiOperation(value="Delete all Users",notes="Delete all Users",response=UserController.class)
	public ResponseEntity<User> deleteAllUsers() {
		System.out.println("Deleting All Users");

		userService.deleteAllUsers();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

}
