package edu.miracosta.cs113;

public class EncryptedEMail extends EMail implements Comparable<EMail>{
	
	protected String encMsg;
	
	public EncryptedEMail(){
		super();
		encMsg = "";
	}
	
	public void setEncMsg(String encMsg){
		this.encMsg = encMsg;
	}
	
	public String getEncMsg(){
		return encMsg;
	}
	
	public void encrypt(){
		//TODO: wait Encrypter
	}
	
	public void decrypt(){
		//TODO: wait Encrypter
	}
	
	@Override
	public int compareTo(EMail other) {
		return this.sender.compareTo(other.sender);
	}

}
