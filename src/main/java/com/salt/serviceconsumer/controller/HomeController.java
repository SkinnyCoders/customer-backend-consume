package com.salt.serviceconsumer.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.salt.serviceconsumer.Model.KonsumentForm;
import com.salt.serviceconsumer.service.HomeService;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;

@Controller
public class HomeController {

    @Autowired
    HomeService homeService;

    @RequestMapping("/")
    public String index(Model model) throws JsonMappingException, JsonProcessingException, ParseException{
        model.addAttribute("datas", homeService.getData());
        model.addAttribute("message", null);
        return "index";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("konsumenForm", new KonsumentForm());
        return "add";
    }

    @PostMapping("/register")
	public String registerUser(@ModelAttribute KonsumentForm konsumentForm, Model model) throws JsonMappingException, JsonProcessingException, ParseException {

        //logic insert data 
        String result = homeService.insertData(konsumentForm);

        if(result.equals("SUCCESS")){
            model.addAttribute("message", result);
		
            model.addAttribute("datas", homeService.getData());
            return "index";
        }

        model.addAttribute("message", result);
		model.addAttribute("konsumenForm", new KonsumentForm());
		return "add";
	}

    @GetMapping("/delete")
    public String deleteData(@RequestParam(value = "id") Integer id, Model model) throws JsonMappingException, JsonProcessingException, UniformInterfaceException, ClientHandlerException, ParseException{
        //logic insert data 
        String result = homeService.deleteData(id);

        model.addAttribute("message", result);
        model.addAttribute("datas", homeService.getData());

        return "index";
    }

    
}
