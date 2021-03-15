package com.recommender.app.model.forms;

public class SignUpForm extends QuestionaryForm {
	private String username;
	private String password1;
	private String password2;
	public SignUpForm() {
		super();
		username ="";
		password1 = "";
		password2 = "";
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword1() {
		return password1;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	public boolean isValid() {
		return !username.equals("")&&password1.equals(password2)&&password1.length()>=8&&username.length()>=8&&!password1.equals("")&&super.isValid();
	}
}
