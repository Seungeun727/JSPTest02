package com.test.phonebook.txt;

public class PersonInfo {
	private int num;
	private String name;
	private String hp;
	private String tel;

	public PersonInfo(String name, String hp, String tel) {
		this.name = name;
		this.hp = hp;
		this.tel = tel;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCellphone() {
		return hp;
	}

	public void setCellphone(String hp) {
		this.hp = hp;
	}

	public String getHomephone() {
		return tel;
	}

	public void setHomephone(String tel) {
		this.tel = tel;
	}

	public void showInfo() {
		System.out.printf("%d. %s\t%s\t%s%n", num, name, hp, tel);
	}

	@Override
	public String toString() {
		String result = String.format("%d. %s\t%s\t%s", num, name, hp, tel);
		return result;
	}
}