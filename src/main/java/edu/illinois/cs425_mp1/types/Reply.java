package edu.illinois.cs425_mp1.types;

import java.io.Serializable;

abstract public class Reply extends Message implements Serializable {
	
	static final long serialVersionUID = 1L;
	
	private long id;
	
	protected String replierAddress;
	
	public Reply(String body, long id, String replierAddress) {
		super(body);
		this.id = id;
		this.replierAddress = replierAddress;
	}
	
	public String getReplierAddress() {
		return this.replierAddress;
	}
}
