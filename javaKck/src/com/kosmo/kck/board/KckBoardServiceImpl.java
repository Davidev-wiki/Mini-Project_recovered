package com.kosmo.kck.board;

import java.util.ArrayList;

public class KckBoardServiceImpl implements KckBoardService {

	// 전체 조회 함수
	@Override
	public ArrayList<KckBoardVO> kboardSelectAll() {
		// TODO Auto-generated method stub
		System.out.println("KckBoardServiceImpl.kboardSelectAll()함수 진입");

		KckBoardDAO kdao = new KckBoardDAOImpl();
		return kdao.kboardSelectAll();
	}

	// 일반 조회 함수
	@Override
	public ArrayList<KckBoardVO> kboardSelect(KckBoardVO kvo) {
		// TODO Auto-generated method stub
		System.out.println("KckBoardServiceImpl.kboardSelect()함수 진입");

		KckBoardDAO kdao = new KckBoardDAOImpl();
		return kdao.kboardSelect(kvo);

	}

	// 게시물 등록 함수
	@Override
	public int kboardInsert(KckBoardVO kvo) {
		// TODO Auto-generated method stub
		System.out.println("KckBoardServiceImpl.kboardInsert()함수 진입");

		KckBoardDAO kdao = new KckBoardDAOImpl();
		return kdao.kboardInsert(kvo);
	}

	// 게시물 수정 함수
	@Override
	public int kboardUpdate(KckBoardVO kvo) {
		// TODO Auto-generated method stub
		System.out.println("KckBoardServiceImpl.kboardUpdate()함수 진입");

		KckBoardDAO kdao = new KckBoardDAOImpl();
		return kdao.kboardUpdate(kvo);
	}

	// 게시물 삭제 함수
	@Override
	public int kboardDelete(KckBoardVO kvo) {
		// TODO Auto-generated method stub
		System.out.println("KckBoardServiceImpl.kboardDelete()함수 진입");

		KckBoardDAO kdao = new KckBoardDAOImpl();
		return kdao.kboardDelete(kvo);
	}

}
