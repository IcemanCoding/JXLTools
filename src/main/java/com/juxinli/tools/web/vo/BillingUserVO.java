package com.juxinli.tools.web.vo;

public class BillingUserVO {

	private String org;
	private String env;
	private String rpt_version;
	private String st;
	private String et;
	
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getEnv() {
		return env;
	}
	public void setEnv(String env) {
		this.env = env;
	}
	public String getRpt_version() {
		return rpt_version;
	}
	public void setRpt_version(String rpt_version) {
		this.rpt_version = rpt_version;
	}
	public String getSt() {
		return st;
//		return st + " 00:00:00";
	}
	public void setSt(String st) {
		this.st = st;
	}
	public String getEt() {
		return et;
//		return et + " 23:59:59";
	}
	public void setEt(String et) {
		this.et = et;
	}
	
	@Override
	public String toString() {
		return "org=" + this.org + "&env=" + this.env + "&rpt_version=" + this.rpt_version + 
				"&st=" + this.st + "&et=" + this.et;
	}
	
}
