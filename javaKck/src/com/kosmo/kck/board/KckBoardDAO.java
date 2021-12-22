package com.kosmo.kck.board;

import java.util.ArrayList;

public interface KckBoardDAO {
	
	// ��ü ��ȸ �Լ�
	public ArrayList<KckBoardVO> kboardSelectAll();

	// �Ϲ� ��ȸ �Լ�
	public ArrayList<KckBoardVO> kboardSelect(KckBoardVO kvo);

	// �Խ��� ��� �Լ�
	public int kboardInsert(KckBoardVO kvo);

	// �Խ��� ���� �Լ�
	public int kboardUpdate(KckBoardVO kvo);

	// �Խ��� ���� �Լ�
	public int kboardDelete(KckBoardVO kvo);

	
}
