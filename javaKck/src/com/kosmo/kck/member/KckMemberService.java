package com.kosmo.kck.member;

import java.util.ArrayList;

public interface KckMemberService {

	// ��ü ��ȸ�ϱ�
	public ArrayList<KckMemberVO> kmemselectAll();

	// �Ϲ� ��ȸ�ϱ�(VO��ü�� �Էµ� ������ �´� ������)
	public ArrayList<KckMemberVO> kmemselect(KckMemberVO kvo);

	// ȸ�� ����ϱ�
	public boolean kmemInsert(KckMemberVO kvo);

	// ȸ�� �����ϱ�
	public boolean kmemUpdate(KckMemberVO kvo);

	// ȸ�� �����ϱ�
	public boolean kmemDelete(KckMemberVO kvo);

	// ȸ�� '�̸�'���� �˻��ϴ� ���
	public ArrayList<KckMemberVO> kmemSelectName(KckMemberVO kvo);

	// ȸ�� '���̵�'�� �˻��ϴ� ���
	public ArrayList<KckMemberVO> kmemSelectId(KckMemberVO kvo);

	// ȸ�� ���̵� '�ߺ�üũ'�ϴ� ���
	public boolean kmemIdCheck(KckMemberVO kvo);

}
