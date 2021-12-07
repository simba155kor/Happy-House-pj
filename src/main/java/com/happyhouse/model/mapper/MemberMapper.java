package com.happyhouse.model.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.happyhouse.model.dto.member.MemberDto;
import com.happyhouse.model.dto.member.MemberFindDto;
import com.happyhouse.model.dto.member.MemberInfoDto;
import com.happyhouse.model.dto.member.MemberLoginHashDto;

@Mapper
public interface MemberMapper {
	MemberInfoDto memberSearch(String id) throws Exception;
	ArrayList<MemberDto> memberSearchAll() throws Exception;
	int memberUpdate(MemberDto memberDto) throws Exception;
	int memberRemove(String id) throws Exception;
	int memberCreate(MemberDto memberDto) throws Exception;
	MemberInfoDto memberFindPassword(MemberFindDto memberFindDto) throws Exception;
	String login(MemberLoginHashDto memberLoginHashDto) throws Exception;
}
