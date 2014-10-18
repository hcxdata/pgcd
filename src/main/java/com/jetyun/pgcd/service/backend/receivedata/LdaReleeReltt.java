package com.jetyun.pgcd.service.backend.receivedata;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class LdaReleeReltt {

	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private Integer destEntityID;

	private Integer orginEntityID;

	private Integer topicID;

	private String value;

	

	public Integer getDestEntityID() {
		return destEntityID;
	}

	public void setDestEntityID(Integer destEntityID) {
		this.destEntityID = destEntityID;
	}

	public Integer getOrginEntityID() {
		return orginEntityID;
	}

	public void setOrginEntityID(Integer orginEntityID) {
		this.orginEntityID = orginEntityID;
	}

	public Integer getTopicID() {
		return topicID;
	}

	public void setTopicID(Integer topicID) {
		this.topicID = topicID;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<LdaReleeReltt> temps = new ArrayList<LdaReleeReltt>();
		for(int i=0;i<5;i++){
		LdaReleeReltt reltt = new LdaReleeReltt();
		reltt.setDestEntityID(100);
		//reltt.setID(100);
		reltt.setOrginEntityID(100);
		reltt.setTopicID(100);
		reltt.setValue("0.123123");
		temps.add(reltt);
		}
		String json = mapper.writeValueAsString(temps);
		System.out.println(json);
		
		//System.out.println(mapper.readValue(json, LdaReleeReltt.class).getID());
		
		
//		List<LdaReleeReltt> reltts = mapper.readValue(FileUtils.readFileToString(new File("E:\\test3.txt"), "utf-8"),new TypeReference<ArrayList<LdaReleeReltt>>() {
//		});
//		System.out.println(reltts.size());
	}

}
