package com.journaldev.spring.di.consumer;

import com.journaldev.spring.di.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author min
 */
@Component
public class MyApplication {

  //field-based dependency injection
  //@Autowired
  private MessageService service;

//	constructor-based dependency injection	
//	@Autowired
//	public MyApplication(MessageService svc){
//		this.service=svc;
//	}

  @Autowired
  public void setService(MessageService messageService) {
    this.service = messageService;
  }

  public boolean processMessage(String msg, String rec) {
    //some magic like validation, logging etc
    return this.service.sendMessage(msg, rec);
  }
}
