package com.jetyun.pgcd.service.backend.receivedata;

import java.util.List;

public class LdaRelte {

	private List<LdaRelteRelLocTe> relLocTe;
	
	private List<LdaRelteRelOrgTe> relOrgTe;
	
	private List<LdaRelteRelOrgTe> relPerTe;

	public List<LdaRelteRelOrgTe> getRelPerTe() {
		return relPerTe;
	}

	public void setRelPerTe(List<LdaRelteRelOrgTe> relPerTe) {
		this.relPerTe = relPerTe;
	}

	public List<LdaRelteRelOrgTe> getRelOrgTe() {
		return relOrgTe;
	}

	public void setRelOrgTe(List<LdaRelteRelOrgTe> relOrgTe) {
		this.relOrgTe = relOrgTe;
	}

	public List<LdaRelteRelLocTe> getRelLocTe() {
		return relLocTe;
	}

	public void setRelLocTe(List<LdaRelteRelLocTe> relLocTe) {
		this.relLocTe = relLocTe;
	}
	
	
}
