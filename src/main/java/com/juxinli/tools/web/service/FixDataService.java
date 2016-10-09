package com.juxinli.tools.web.service;

import com.juxinli.tools.web.bo.BillingUserBO;
import com.juxinli.tools.web.vo.BillingUserVO;

public interface FixDataService {
	
	BillingUserBO compareBillingUserData( BillingUserVO billingUserVo ) throws Exception;

}
