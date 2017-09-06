package com.tripayweb.controller.mobile.api.travel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

public class CabController implements MessageSourceAware {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	private MessageSource messageSource;

	@Override
	public void setMessageSource(MessageSource arg0) {
		this.messageSource = messageSource;
	}
	

	String consumerKey = "59285FC07292811D72785AC287ACE3E8";
	String consumerSecret = "04DAF1E2C80A758DCC6BCEBE436D25BC";
	
	
}
