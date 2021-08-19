package com.test.phonebook.txt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBookEx {
	static final String rootPath = System.getProperty("user.dir") + "\\files\\";
	static final String address = rootPath + "AddressBook.txt";
	
	
	public static void main(String[] args) {
		
		List<PersonInfo> person = new ArrayList<>();
		
		readTxt(person); // txt파일 읽기
		
		run(person);
		
	}
	private static void run(List<PersonInfo> person) {
		boolean runx = true;
		int num = 0;
		String serchstr = "";
		System.out.println("***************************************");
		System.out.println("*          전화번호 관리 프로그램           *");
		System.out.println("***************************************");
		while (runx) {
			Scanner sc = new Scanner(System.in);
			System.out.println("1.리스트   2.등록   3.삭제   4.검색   5.종료");
			System.out.println("---------------------------------------");
			System.out.print("메뉴번호 >> ");
			num = sc.nextInt();

			switch (num) {
			case 1:
				System.out.println();
				System.out.println("<1. 리스트>");
				showinfo(person);
				System.out.println();
				break;
			case 2:
				System.out.println();
				System.out.println("<2. 등록>");
				add(sc, person);
				break;
			case 3:
				System.out.println();
				System.out.println("<3. 삭제>");
				System.out.print("번호 >> ");
				delete(sc, person);
				break;
			case 4:
				System.out.println();
				System.out.println("<4. 검색>");
				System.out.print("이름 >> ");
				serchstr = sc.next();
				search(person, serchstr);
				System.out.println();
				break;
			case 5:
				System.out.println();
				System.out.println("***************************************");
				System.out.println("*              감사합니다                *");
				System.out.println("***************************************");
				sc.close();
				runx = false;
				break;
			default:
				System.out.println("[다시 입력해 주세요]");
				System.out.println();

			}
		}
	}
	// 이름에서 단어를 포함하는 리스트 검색
	
	private static void search(List<PersonInfo> person, String str) {
		for (int i = 0; i < person.size(); i++) {
			PersonInfo serchperson = (PersonInfo) person.get(i);
			if (serchperson.getName().contains(str)) {
				System.out.println(serchperson.toString());
				}
			}

	}
	
	// 선택한 번호 삭제	
	private static void delete(Scanner sc, List<PersonInfo> person) {
		int del = sc.nextInt();
		person.remove(del - 1);
		update(person);
		System.out.println();
		wirteTxt(person);
		System.out.println("[삭제되었습니다.]");
	}
	
	// 리스트 출력
	private static void showinfo(List<PersonInfo> person) {
		for (int i = 0; i < person.size(); i++) {
			System.out.println(person.get(i).toString());
		}
	}

	// 리스트에 추가
	private static void add(Scanner sc, List<PersonInfo> person) {
		String name;
		String hp;
		String tel;
		System.out.print(">이름    : ");
		name = sc.next();
		System.out.print(">휴대전화 : ");
		hp = sc.next();
		System.out.print(">집전화   : ");
		tel = sc.next();
		person.add(new PersonInfo(name, hp, tel));
		update(person);
		System.out.println();
		wirteTxt(person);
		System.out.println("[등록되었습니다.]");
	}
	
	// 번호 순차대로 다시 넘버링
	private static void update(List<PersonInfo> person) {
		for (int i = 0; i < person.size(); i++) {
			PersonInfo n = (PersonInfo) person.get(i);
			n.setNum(i + 1);
		}
	}

	// 텍스트 읽어오기
	private static List<PersonInfo> readTxt(List<PersonInfo> person) {
		Reader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(address);
			br = new BufferedReader(fr);
			String line = "";
			String[] words = new String[3];
			while ((line = br.readLine()) != null) {
				words = line.split(",");
				person.add(new PersonInfo(words[0], words[1], words[2]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		update(person);
		return person;
	}
	
	private static void wirteTxt(List<PersonInfo> person) {
		Writer fw = null;
		BufferedWriter bw = null;

		try {
			// 주스트림
			fw = new FileWriter(address);

			// 메인스트림
			bw = new BufferedWriter(fw);

			for (int i = 0; i < person.size(); i++) {
				PersonInfo writeperson = (PersonInfo) person.get(i);
				bw.write(writeperson.getName() + ",");
				bw.write(writeperson.getCellphone() + ",");
				bw.write(writeperson.getHomephone());
				bw.write("\r\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
