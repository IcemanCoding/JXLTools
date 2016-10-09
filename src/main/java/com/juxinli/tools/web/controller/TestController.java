package com.juxinli.tools.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping( "/tttt" )
public class TestController {
	
	@RequestMapping ( value = "/bbb", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> authenUser( HttpServletRequest request ) {
		
		Map<String, Object> res = new HashMap<String, Object>();
		
		res.put( "flag", 1 );
		res.put( "msg", "success" );
		
		return res;
		
	}

}
