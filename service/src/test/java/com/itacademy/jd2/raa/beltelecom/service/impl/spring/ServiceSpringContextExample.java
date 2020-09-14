package com.itacademy.jd2.raa.beltelecom.service.impl.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itacademy.jd2.raa.telecom.service.IAttributeService;
import com.itacademy.jd2.raa.telecom.service.IUserAccountService;

public class ServiceSpringContextExample {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceSpringContextExample.class);

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("service-context.xml");
		LOGGER.info(context.getBean(IUserAccountService.class).toString());

		LOGGER.info(context.getBean(IAttributeService.class).toString());

		// TODO show multiple candidates with Qualifier

	}
}
