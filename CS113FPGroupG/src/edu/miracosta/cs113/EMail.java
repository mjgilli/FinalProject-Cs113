package edu.miracosta.cs113;

import java.util.Date;

public class EMail implements Comparable<EMail>{
	protected String sender;
	protected String subject;
	//TODO: consider Calendar
	protected Date date;
	protected String msg;
	protected String saveFileName;
	
	public EMail(){
		sender = "";
		subject = "";
		date = null;
		msg = "";
		saveFileName = "encrypted"+subject+".txt";
	}
	
	public void setSender(String sender){
		this.sender = sender;
	}
	
	public String getSender(){
		return sender;
	}
	
	public void setSubject(String subject){
		this.subject = subject;
	}
	
	public String getSubject(){
		return subject;
	}
	
	public void setDate(Date date){
		this.date = date;
	}
	
	public Date getDate(){
		return date;
	}
	
	public void setMsg(String msg){
		this.msg = msg;
	}
	
	public String getMsg(){
		return msg;
	}
	
	public void setSaveFileName(String saveFileName){
		this.saveFileName = saveFileName;
	}
	
	public String getSaveFileName(){
		return saveFileName;
	}

	@Override
	public int compareTo(EMail other) {
		return this.sender.compareTo(other.sender);
	}
}
