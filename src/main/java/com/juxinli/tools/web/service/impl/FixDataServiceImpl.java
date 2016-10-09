package com.juxinli.tools.web.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.juxinli.tools.web.bo.BillingUserBO;
import com.juxinli.tools.web.entity.BillingUserEntity;
import com.juxinli.tools.web.service.FixDataService;
import com.juxinli.tools.web.utils.DateUtils;
import com.juxinli.tools.web.utils.HttpClientUtils;
import com.juxinli.tools.web.utils.OracleUtils;
import com.juxinli.tools.web.utils.TransforUtils;
import com.juxinli.tools.web.vo.BillingUserVO;

@Service
public class FixDataServiceImpl implements FixDataService {

	private static final String HTTP_URL = "";
	private static final String BILLING_USER_METHOD = "";
	
	@Override
	public BillingUserBO compareBillingUserData( BillingUserVO billingUserVo ) throws Exception {
		
		// http请求接口，获取数据
		String resStr = HttpClientUtils.sendGet( HTTP_URL, BILLING_USER_METHOD, billingUserVo.toString() );
		
		// nullcheck, if null return, else trans result to list
		if ( resStr == null || "".equals( resStr ) ) {
			return null;
		}
		List<BillingUserEntity> apiData = TransforUtils.transStrToList( resStr );
		if ( apiData == null || apiData.size() == 0 ) {
			return null;
		}
		
		// query db data
		List<BillingUserEntity> dbData = OracleUtils.getBillingUserByCondition( billingUserVo );
		
		// nullcheck, if null return, else compare db's data and api's data
		if ( dbData == null || dbData.size() == 0 ) {
			return null;
		}
		
		// return compare data
		List<BillingUserEntity> dbDataTemp = dbData;
		List<BillingUserEntity> apiDataTemp = apiData;
		apiDataTemp.removeAll( dbData );
		dbDataTemp.removeAll( apiData );
		BillingUserBO billingUserBo = new BillingUserBO();
		billingUserBo.setLackData( dbDataTemp );
		billingUserBo.setSurplusData( apiDataTemp );
		billingUserBo.setUpdateTime( DateUtils.transDateToString( new Date(), DateUtils.PATTERN1 ) );
		
		return billingUserBo;
		
	}

}
