package com.test.phonebook.dao;

import java.util.List;

import com.test.phonbook.vo.PhoneBookVo;

public interface PhoneBookDao {
	public List<PhoneBookVo> getList();
	public List<PhoneBookVo> search(PhoneBookVo vo);
	public int correct(PhoneBookVo vo);
	public int insert(PhoneBookVo vo);
	public int delete(Long pk);
	
}
