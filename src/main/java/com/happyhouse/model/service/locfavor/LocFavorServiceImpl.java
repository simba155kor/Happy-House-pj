package com.happyhouse.model.service.locfavor;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.websocket.Session;

import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happyhouse.Excption.MemberExcption;
import com.happyhouse.model.Trade;
import com.happyhouse.model.dto.locfavor.LocFavorDto;
import com.happyhouse.model.dto.member.MemberDto;
import com.happyhouse.model.dto.member.MemberInfoDto;
import com.happyhouse.model.dto.member.MemberLoginDto;
import com.happyhouse.model.dto.member.MemberLoginHashDto;
import com.happyhouse.model.dto.member.MemberPwDto;
import com.happyhouse.model.mapper.LocFavorMapper;
import com.happyhouse.model.mapper.MemberMapper;

@Service
public class LocFavorServiceImpl implements LocFavorService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<Trade> search(String memberId) throws Exception {
		return sqlSession.getMapper(LocFavorMapper.class).search(memberId);
	}

	@Override
	public boolean remvoe(LocFavorDto locfavorDto) throws Exception {
		return sqlSession.getMapper(LocFavorMapper.class).remove(locfavorDto)==1;
	}

	@Override
	public boolean create(LocFavorDto locfavorDto) throws Exception {
		return sqlSession.getMapper(LocFavorMapper.class).create(locfavorDto)==1; 
	}
}
