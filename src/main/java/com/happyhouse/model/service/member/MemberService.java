package com.happyhouse.model.service.member;

import java.util.List;
import java.util.Map;

import com.happyhouse.model.dto.member.MemberDto;
import com.happyhouse.model.dto.member.MemberFindDto;
import com.happyhouse.model.dto.member.MemberInfoDto;
import com.happyhouse.model.dto.member.MemberLoginDto;
import com.happyhouse.model.dto.member.MemberPwDto;

public interface MemberService {
	String login(MemberLoginDto memberLoginDto) throws Exception;
	MemberInfoDto search(String id) throws Exception;
	List<MemberDto> searchAll() throws Exception;
	boolean memberFindPassword(MemberFindDto memberFindDto) throws Exception;
	boolean remvoe(String id) throws Exception;
	boolean update(MemberPwDto memberDto) throws Exception;
	void sendEmail(MemberInfoDto memberDto) throws Exception;
	boolean create(MemberPwDto memberDto) throws Exception;
}
