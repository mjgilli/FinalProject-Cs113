package edu.miracosta.cs113;

public class EncryptedEMail extends EMail implements Comparable<EMail>{

	@Override
	public int compareTo(EMail other) {
		return this.sender.compareTo(other.sender);
	}

}
