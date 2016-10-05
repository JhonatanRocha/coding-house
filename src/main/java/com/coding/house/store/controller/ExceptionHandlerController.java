package com.coding.house.store.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler(Exception.class)
    public ModelAndView handleGenericException(Exception exception) {
		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("exception", exception);
		exception.printStackTrace();
		return modelAndView;
    }
	
	/*@ExceptionHandler(Exception.class)
    public ModelAndView handleGenericException() {
		ModelAndView modelAndView = new ModelAndView("error");
		return modelAndView;
    } */
}
