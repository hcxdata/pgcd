package com.jetyun.pgcd.service.backend.receivedata;

import java.util.List;

public class LdaEntity {

	private List<String> all_Entitys;
	
	private List<String> locs;
	
	private List<String> orgs;
	
	private List<Integer> pers;

	public List<String> getAll_Entitys() {
		return all_Entitys;
	}

	public void setAll_Entitys(List<String> all_Entitys) {
		this.all_Entitys = all_Entitys;
	}



	public List<String> getLocs() {
		return locs;
	}

	public void setLocs(List<String> locs) {
		this.locs = locs;
	}

	public List<String> getOrgs() {
		return orgs;
	}

	public void setOrgs(List<String> orgs) {
		this.orgs = orgs;
	}

	public List<Integer> getPers() {
		return pers;
	}

	public void setPers(List<Integer> pers) {
		this.pers = pers;
	}


	
}
