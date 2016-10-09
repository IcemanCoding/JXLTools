package com.juxinli.tools.web.service.impl;

import java.util.ArrayList;
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

	private static final String HTTP_URL = "http://172.16.1.15:81/service";
	private static final String BILLING_USER_METHOD = "/billing/user";
	
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
		List<BillingUserEntity> dbDataTemp = new ArrayList<BillingUserEntity>( dbData.size() );
		dbDataTemp.addAll( dbData );
		List<BillingUserEntity> apiDataTemp = new ArrayList<BillingUserEntity>( apiData.size() );
		apiDataTemp.addAll( apiData );
		apiDataTemp.removeAll( dbData );
		dbDataTemp.removeAll( apiData );
		BillingUserBO billingUserBo = new BillingUserBO();
		billingUserBo.setLackData( dbDataTemp );
		billingUserBo.setSurplusData( apiDataTemp );
		billingUserBo.setUpdateTime( DateUtils.transDateToString( new Date(), DateUtils.PATTERN2 ) );
		billingUserBo.setApiDataCnt( apiData.size() );
		billingUserBo.setDbDataCnt( dbData.size() );
		billingUserBo.setLackCnt( dbDataTemp.size() );
		billingUserBo.setSurplusCnt( apiDataTemp.size() );
		
		return billingUserBo;
		
	}

}
