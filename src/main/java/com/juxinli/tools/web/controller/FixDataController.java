package com.juxinli.tools.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.juxinli.tools.web.bo.BillingUserBO;
import com.juxinli.tools.web.service.FixDataService;
import com.juxinli.tools.web.vo.BillingUserVO;

@Controller
@RequestMapping( "/fixData" )
public class FixDataController {
	
	@Autowired
	private FixDataService fixDataService;
	
	@RequestMapping( value = "/billingUser", method = RequestMethod.GET )
	@ResponseBody
	public Object billingUser( BillingUserVO billingUserVo ) {
		
		BillingUserBO billingUserBo = new BillingUserBO();
		try {
			billingUserBo = fixDataService.compareBillingUserData( billingUserVo );
			if ( billingUserBo == null ) {
				billingUserBo = new BillingUserBO();
			}
			billingUserBo.setStatus( "success" );
			billingUserBo.setRequestArgs( billingUserVo );
		} catch ( Exception e ) {
			billingUserBo.setStatus( "fail" );
			billingUserBo.setRequestArgs( billingUserVo );
		}
		return billingUserBo;
		
	}

}
