package com.juxinli.tools.web.utils;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.juxinli.tools.web.entity.BillingUserEntity;

public class TransforUtils {
	
	public static List<BillingUserEntity> transStrToList( String data ) {
		
		JSONObject jsonData = JSONObject.parseObject( data );
		JSONObject jsonResult = jsonData.getJSONObject( "result" );
		JSONArray dataList = jsonResult.getJSONArray( "details" );
		return JSONArray.parseArray( dataList.toString(), BillingUserEntity.class );
		
	}

}
