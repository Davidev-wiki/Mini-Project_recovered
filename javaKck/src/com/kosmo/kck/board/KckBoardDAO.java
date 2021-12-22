package com.kosmo.kck.board;

import java.util.ArrayList;

public interface KckBoardDAO {
	
	// 전체 조회 함수
	public ArrayList<KckBoardVO> kboardSelectAll();

	// 일반 조회 함수
	public ArrayList<KckBoardVO> kboardSelect(KckBoardVO kvo);

	// 게시판 등록 함수
	public int kboardInsert(KckBoardVO kvo);

	// 게시판 수정 함수
	public int kboardUpdate(KckBoardVO kvo);

	// 게시판 삭제 함수
	public int kboardDelete(KckBoardVO kvo);

	
}
