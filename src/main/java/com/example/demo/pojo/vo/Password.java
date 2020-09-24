package com.example.demo.pojo.vo;

public class Password {
	private String oldPasswd;
	private String newPasswd;
	private String verify;
	public Password() {
		super();
	}
	public Password(String oldPasswd, String newPasswd, String verify) {
		super();
		this.oldPasswd = oldPasswd;
		this.newPasswd = newPasswd;
		this.verify = verify;
	}
	public String getOldPasswd() {
		return oldPasswd;
	}
	public void setOldPasswd(String oldPasswd) {
		this.oldPasswd = oldPasswd;
	}
	public String getNewPasswd() {
		return newPasswd;
	}
	public void setNewPasswd(String newPasswd) {
		this.newPasswd = newPasswd;
	}
	public String getVerify() {
		return verify;
	}
	public void setVerify(String verify) {
		this.verify = verify;
	}
	@Override
	public String toString() {
		return "Password [oldPasswd=" + oldPasswd + ", newPasswd=" + newPasswd + ", verify=" + verify + "]";
	}
}
