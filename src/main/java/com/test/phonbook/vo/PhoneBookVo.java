package com.test.phonbook.vo;

public class PhoneBookVo {
	private Long no;
	private String name;
	private String tel;
	private String hp;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	
	
	@Override
	public String toString() {
		return "PhoneBookVo [no=" + no + ", name=" + name + ", tel=" + tel + ", hp=" + hp + "]";
	}
	
	 
}
