package com.kosmo.kck.member;

import java.util.ArrayList;

public class KckMemberServiceImpl implements KckMemberService {

	@Override
	public ArrayList<KckMemberVO> kmemselectAll() {
		// TODO Auto-generated method stub
		System.out.println("KckMemberServiceImpl.kmemselectAll() 진입");

		KckMemberDAO kdao = new KckMemberDAOImpl();
		ArrayList<KckMemberVO> aList = kdao.kmemselectAll();
		return aList;
	}

	@Override
	public ArrayList<KckMemberVO> kmemselect(KckMemberVO kvo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean kmemInsert(KckMemberVO kvo) {
		// TODO Auto-generated method stub
		System.out.println("KckMemberServiceImpl.kmemInsert() 진입");

		KckMemberDAO kdao = new KckMemberDAOImpl();
		boolean bool = kdao.kmemInsert(kvo);
		return bool;
	}

	@Override
	public boolean kmemUpdate(KckMemberVO kvo) {
		// TODO Auto-generated method stub
		System.out.println("KckMemberServiceImpl.kmemUpdate() 진입");

		KckMemberDAO kdao = new KckMemberDAOImpl();
		boolean bool = kdao.kmemUpdate(kvo);
		return bool;
	}

	@Override
	public boolean kmemDelete(KckMemberVO kvo) {
		// TODO Auto-generated method stub
		System.out.println("KckMemberServiceImpl.kmemDelete() 진입");

		KckMemberDAO kdao = new KckMemberDAOImpl();
		boolean bool = kdao.kmemDelete(kvo);
		return bool;
	}

	@Override
	public ArrayList<KckMemberVO> kmemSelectName(KckMemberVO kvo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<KckMemberVO> kmemSelectId(KckMemberVO kvo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean kmemIdCheck(KckMemberVO kvo) {
		// TODO Auto-generated method stub
		return false;
	}

}
