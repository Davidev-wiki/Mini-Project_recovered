package com.kosmo.kck.board;

import java.util.ArrayList;

public class KckBoardServiceImpl implements KckBoardService {

	// ��ü ��ȸ �Լ�
	@Override
	public ArrayList<KckBoardVO> kboardSelectAll() {
		// TODO Auto-generated method stub
		System.out.println("KckBoardServiceImpl.kboardSelectAll()�Լ� ����");

		KckBoardDAO kdao = new KckBoardDAOImpl();
		return kdao.kboardSelectAll();
	}

	// �Ϲ� ��ȸ �Լ�
	@Override
	public ArrayList<KckBoardVO> kboardSelect(KckBoardVO kvo) {
		// TODO Auto-generated method stub
		System.out.println("KckBoardServiceImpl.kboardSelect()�Լ� ����");

		KckBoardDAO kdao = new KckBoardDAOImpl();
		return kdao.kboardSelect(kvo);

	}

	// �Խù� ��� �Լ�
	@Override
	public int kboardInsert(KckBoardVO kvo) {
		// TODO Auto-generated method stub
		System.out.println("KckBoardServiceImpl.kboardInsert()�Լ� ����");

		KckBoardDAO kdao = new KckBoardDAOImpl();
		return kdao.kboardInsert(kvo);
	}

	// �Խù� ���� �Լ�
	@Override
	public int kboardUpdate(KckBoardVO kvo) {
		// TODO Auto-generated method stub
		System.out.println("KckBoardServiceImpl.kboardUpdate()�Լ� ����");

		KckBoardDAO kdao = new KckBoardDAOImpl();
		return kdao.kboardUpdate(kvo);
	}

	// �Խù� ���� �Լ�
	@Override
	public int kboardDelete(KckBoardVO kvo) {
		// TODO Auto-generated method stub
		System.out.println("KckBoardServiceImpl.kboardDelete()�Լ� ����");

		KckBoardDAO kdao = new KckBoardDAOImpl();
		return kdao.kboardDelete(kvo);
	}

}
