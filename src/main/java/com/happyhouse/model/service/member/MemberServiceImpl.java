package com.happyhouse.model.service.member;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.websocket.Session;

import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happyhouse.Excption.MemberExcption;
import com.happyhouse.model.dto.member.MemberDto;
import com.happyhouse.model.dto.member.MemberFindDto;
import com.happyhouse.model.dto.member.MemberInfoDto;
import com.happyhouse.model.dto.member.MemberLoginDto;
import com.happyhouse.model.dto.member.MemberLoginHashDto;
import com.happyhouse.model.dto.member.MemberPwDto;
import com.happyhouse.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	@Transactional
	public MemberInfoDto search(String memberId) throws Exception {
		MemberInfoDto memberNoneHashDto= sqlSession.getMapper(MemberMapper.class).memberSearch(memberId);
		if(memberNoneHashDto == null)
			throw new MemberExcption(memberId+" 회원정보를 조회하던 중 오류가 발생하였습니다.");
		
		return memberNoneHashDto;
	}

	@Override
	@Transactional
	public List<MemberDto> searchAll() throws Exception {
		return sqlSession.getMapper(MemberMapper.class).memberSearchAll();
	}

	@Override
	@Transactional
	public boolean remvoe(String id) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).memberRemove(id) == 1;
	}

	@Override
	@Transactional
	public boolean update(MemberPwDto memberPwDto) throws Exception {
		MemberDto memberDto = new MemberDto();
		memberDto.setMemberId(memberPwDto.getMemberId());
		memberDto.setMemberName(memberPwDto.getMemberName());
		memberDto.setMemberHash(memberPwDto.getMemberPw());
		memberDto.setMemberEmail(memberPwDto.getMemberEmail());
		
		return sqlSession.getMapper(MemberMapper.class).memberUpdate(memberDto) == 1;
	}

	@Override
	@Transactional
	public boolean create(MemberPwDto memberPwDto) throws Exception {
		MemberDto memberDto = new MemberDto();
		memberDto.setMemberId(memberPwDto.getMemberId());
		memberDto.setMemberName(memberPwDto.getMemberName());
		memberDto.setMemberHash(memberPwDto.getMemberPw());
		memberDto.setMemberEmail(memberPwDto.getMemberEmail());
		
		if(memberDto.getMemberId() == null) {
			throw new MemberExcption("회원가입중 오류 발생. : "+ memberPwDto);
		}
		
		return sqlSession.getMapper(MemberMapper.class).memberCreate(memberDto) == 1;
	}
	
	@Override
	@Transactional
	public String login(MemberLoginDto memberLoginDto) throws Exception {
		MemberLoginHashDto memberLoginHashDto = new MemberLoginHashDto();
		memberLoginHashDto.setMemberId(memberLoginDto.getMemberId());
		memberLoginHashDto.setMemberHash(memberLoginDto.getMemberPw());
		return sqlSession.getMapper(MemberMapper.class).login(memberLoginHashDto);
	}
	
	@Override //Exception 어떻게 처리할지 고민
	@Transactional
	public boolean memberFindPassword(MemberFindDto memberFindDto) throws Exception {
		MemberInfoDto memberLoginDto = sqlSession.getMapper(MemberMapper.class).memberFindPassword(memberFindDto);
		
		if(memberLoginDto == null) {
			throw new MemberExcption("일치하는 정보가 없습니다. 다시확인해주세요.");
		}else {
			sendEmail(memberLoginDto);
			return true;
		}
	}

	
	@Override
	@Transactional
	public void sendEmail(MemberInfoDto memberDto) throws Exception {
		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		
		//임시 비밀번호 생성
		int nance = (int)(Math.random() * 10000000); // seed값을 배정하여 생성
        String newPasswd = new String(Integer.toString(nance));         // 난수값 출력
				
		MemberPwDto updateMemberDto = new MemberPwDto();
		updateMemberDto.setMemberId(memberDto.getMemberId());
		updateMemberDto.setMemberName(memberDto.getMemberName());
		updateMemberDto.setMemberEmail(memberDto.getMemberEmail());
		updateMemberDto.setMemberPw(newPasswd);
		
		update(updateMemberDto);
		
		simpleMessage.setSubject("새로운 임시 비밀번호 입니다.");
		simpleMessage.setText("새로운 임시 비밀번호는 : " + newPasswd + " 입니다.");
		
		simpleMessage.setTo(memberDto.getMemberEmail());
		javaMailSender.send(simpleMessage);
	}

}
