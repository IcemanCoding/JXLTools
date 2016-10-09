package com.juxinli.tools.web.bo;

import java.util.List;

import com.juxinli.tools.web.entity.BillingUserEntity;

public class BillingUserBO {
	
	private String status;
	private String updateTime;
	private Object requestArgs;
	private List<BillingUserEntity> lackData;
	private List<BillingUserEntity> surplusData;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the lackData
	 */
	public List<BillingUserEntity> getLackData() {
		return lackData;
	}
	/**
	 * @param lackData the lackData to set
	 */
	public void setLackData(List<BillingUserEntity> lackData) {
		this.lackData = lackData;
	}
	/**
	 * @return the surplusData
	 */
	public List<BillingUserEntity> getSurplusData() {
		return surplusData;
	}
	/**
	 * @param surplusData the surplusData to set
	 */
	public void setSurplusData(List<BillingUserEntity> surplusData) {
		this.surplusData = surplusData;
	}
	/**
	 * @return the updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}
	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * @return the requestArgs
	 */
	public Object getRequestArgs() {
		return requestArgs;
	}
	/**
	 * @param requestArgs the requestArgs to set
	 */
	public void setRequestArgs(Object requestArgs) {
		this.requestArgs = requestArgs;
	}

}
