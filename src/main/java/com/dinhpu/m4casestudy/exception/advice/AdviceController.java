package com.dinhpu.m4casestudy.exception.advice;

import com.dinhpu.m4casestudy.exception.RealEstateException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler
    public ModelAndView realEstateNotFoundException(RealEstateException exception){
        String message=exception.getMessage();
        ModelAndView modelAndView=new ModelAndView("real-estate-exception");
        modelAndView.addObject("message",message);
        return modelAndView;
    }

}
