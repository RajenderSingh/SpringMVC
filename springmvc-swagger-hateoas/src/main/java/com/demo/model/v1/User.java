package com.demo.model.v1;

import java.io.Serializable;

import org.springframework.hateoas.ResourceSupport;

import io.swagger.annotations.ApiModelProperty;

public class User extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = -889976693182180703L;
	
    @ApiModelProperty(value="Id of the User")
	private long uid;
	
    @ApiModelProperty(value="Name of the User")
	private String name;
	
    @ApiModelProperty(value="Age of the User")
	private int age;
	
    @ApiModelProperty(value="Salary of the User")
	private double salary;

	public User(){
		uid=0;
	}
	
	public User(long uid, String name, int age, double salary){
		this.uid = uid;
		this.name = name;
		this.age = age;
		this.salary = salary;
	}
	
	public long getUId() {
		return uid;
	}

	public void setUId(long uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (uid ^ (uid >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (uid != other.uid)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + uid + ", name=" + name + ", age=" + age
				+ ", salary=" + salary + "]";
	}


}
