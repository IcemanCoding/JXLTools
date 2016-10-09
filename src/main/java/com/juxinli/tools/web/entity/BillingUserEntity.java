package com.juxinli.tools.web.entity;


public class BillingUserEntity {
	
	private String org;
	private String idcard;
	private String name;
	
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals( Object obj ) {
		
		if ( ! ( obj instanceof BillingUserEntity ) ) {
			return false;
		}
		BillingUserEntity billingUserEntity = ( BillingUserEntity ) obj;
		return this.name.equals( billingUserEntity.name ) && this.idcard.equals( billingUserEntity.idcard );
		
	}

}
