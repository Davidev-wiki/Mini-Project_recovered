package com.kosmo.kck.member;

import java.util.ArrayList;

public interface KckMemberService {

	// 전체 조회하기
	public ArrayList<KckMemberVO> kmemselectAll();

	// 일반 조회하기(VO객체에 입력된 정보에 맞는 데이터)
	public ArrayList<KckMemberVO> kmemselect(KckMemberVO kvo);

	// 회원 등록하기
	public boolean kmemInsert(KckMemberVO kvo);

	// 회원 수정하기
	public boolean kmemUpdate(KckMemberVO kvo);

	// 회원 삭제하기
	public boolean kmemDelete(KckMemberVO kvo);

	// 회원 '이름'으로 검색하는 기능
	public ArrayList<KckMemberVO> kmemSelectName(KckMemberVO kvo);

	// 회원 '아이디'로 검색하는 기능
	public ArrayList<KckMemberVO> kmemSelectId(KckMemberVO kvo);

	// 회원 아이디 '중복체크'하는 기능
	public boolean kmemIdCheck(KckMemberVO kvo);

}
