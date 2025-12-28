package com.umer.revision_spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class RevisionSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(RevisionSpringBootApplication.class, args);
	}

	/**
	 * RestController is needed in order to represent a restful resource.
	 */
	@RestController
	class Resource {

		@RequestMapping(
			method = RequestMethod.GET
		)
		Message getMessage(){
			return new Message("Hello World");
		}
	}

	/**
	 * POJO Class
	 */
	class Message{
		private final String message;
		
		public Message(String message){
			this.message=message;
		}

		public String getMessage(){
			return this.message;
		}

		@Override
		public String toString(){
			return "Message{"+
			"message="+message+"\'"+"}";
		}
	}

}
