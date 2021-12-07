package com.happyhouse.model.dto.member;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MemberLoginHashDto {
	private String memberId;
	private String memberHash;
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberHash() {
		return memberHash;
	}
	public void setMemberHash(String memberHash) {
		this.memberHash = getHashCode(memberHash);
	}
	
	public String getHashCode(String pw) {
		int hash = 17;  //nonzero constant
	    hash = 31 * hash + pw.hashCode();
	    String returnString = "";
	    try {
			MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
			sha512.reset();
			String tmp = Integer.toString(hash);
			sha512.update(tmp.getBytes("utf8"));
			returnString = String.format("%0128x", new BigInteger(1, sha512.digest()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	    return returnString;
	}
}
