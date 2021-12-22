package com.kosmo.kck.board;

import java.util.ArrayList;
import java.util.Scanner;

import com.kosmo.kck.common.KckBoardChabun;

public class KckBoardScr {

	// �Խ��� ���� ��
	public static final String[] insert_label = { "������", "�ۼ���", "�۳���", "��й�ȣ" };
	public static final String[] update_label = { "�۹�ȣ", "������", "�۳���" };

	// ��ü ��ȸ == 1
	public static void kboardSelectAll() {
		System.out.println("KckBoardScr.kboardSelectAll()�Լ� ����");

		try {
			KckBoardService kbs = new KckBoardServiceImpl();

			ArrayList<KckBoardVO> aList = kbs.kboardSelectAll();

			if (aList != null && aList.size() > 0) {
				for (int i = 0; i < aList.size(); i++) {
					KckBoardVO kvo = aList.get(i);
					KckBoardVO.printKckBoardVO(kvo);
				}
			} else {
				System.out.println("aList�� ����ֳ׿�..");
			}
		} catch (Exception e1) {
			System.out.println("��ü ��ȸ�� ������ �߻��߾�� : " + e1.getMessage());
		}
	}

	// ���� ��ȸ == 2 || �� ��ȣ
	public static void kboardSelect(String sVal[]) {
		System.out.println("KckBoardScr.kboardSelect()�Լ� ����");
		System.out.println("ȸ������ ��ȸ : " + sVal[0]);

		try {
			KckBoardService kbs = new KckBoardServiceImpl();
			KckBoardVO kvo = new KckBoardVO();
			kvo.setBnum(sVal[0]);

			ArrayList<KckBoardVO> aList = kbs.kboardSelect(kvo);

			if (aList != null && aList.size() > 0) {
				for (int i = 0; i < aList.size(); i++) {
					KckBoardVO _kvo = aList.get(i);
					// �ϳ��� ����̹Ƿ� println���� ����
					KckBoardVO.printlnKckBoardVO(_kvo);
				}
			} else {
				System.out.println("aList�� ����ֳ׿�..");
			}
		} catch (Exception e) {
			System.out.println("�Խù� ���� ��ȸ�� ������ �߻��߾�� : " + e.getMessage());
		}

	}

	// �� ��� == 3 || �� ��ȣ, ����, �۾���, ��й�ȣ, �� ����
	public static void kboardInsert(String sVal[]) {
		System.out.println("KckBoardScr.kboardInsert()�Լ� ����");
		System.out.println("ȸ�� ���� �Է� : " + sVal.length);

		for (int i = 0; i < sVal.length; i++) {
			System.out.println(insert_label[i] + " : " + sVal);
		}

		String bnum = KckBoardChabun.gubunNum();
		String bsubject = sVal[0];
		String bwriter = sVal[1];
		String bpw = sVal[2];
		String bcontents = sVal[3];

		try {
			KckBoardService kbs = new KckBoardServiceImpl();
			KckBoardVO kvo = new KckBoardVO();

			kvo.setBnum(bnum);
			kvo.setBsubject(bsubject);
			kvo.setBwriter(bwriter);
			kvo.setBpw(bpw);
			kvo.setBcontents(bcontents);

			int nCnt = kbs.kboardInsert(kvo);

			if (nCnt > 0) {
				System.out.println("�Խù� " + nCnt + "���� ��ϵǾ����ϴ�!");
			} else {
				System.out.println("�Խù� ��� ����!");
			}

		} catch (Exception e) {
			System.out.println("�Խù� ����� ������ �߻��߾�� : " + e.getMessage());
		}

	}

	// �� ���� == 4 || �� ��ȣ, �� ����, �� ����
	public static void kboardUpdate(String sVal[]) {
		System.out.println("KckBoardScr.kboardUpdate()�Լ� ����");
		System.out.println("ȸ������ ���� : " + sVal.length);

		for (int i = 0; i < sVal.length; i++) {
			System.out.println(update_label[i] + " >>> : " + sVal[i]);
		}

		String bnum = sVal[0];
		String bsubject = sVal[1];
		String bcontents = sVal[2];

		try {
			// ���� ȣ��
			KckBoardService kbs = new KckBoardServiceImpl();
			KckBoardVO kvo = null;
			kvo = new KckBoardVO();

			kvo.setBnum(bnum);
			kvo.setBsubject(bsubject);
			kvo.setBcontents(bcontents);

			int nCnt = kbs.kboardUpdate(kvo);

			if (nCnt > 0) {
				System.out.println("�Խñ� " + nCnt + " �� ���� �Ǿ����ϴ�.");
			} else {
				System.out.println("�Խñ� ���� ����.");
			}
		} catch (Exception e) {
			System.out.println("���� �� ������ >>> : " + e.getMessage());
		}
	}

	// �� ���� == 5 || �� ��ȣ
	public static void kboardDelete(String sVal[]) {
		System.out.println("KckBoardScr.kboardDelete()�Լ� ����");
		System.out.println("ȸ������ ���� : " + sVal[0]);

		try {
			// ���� ȣ��
			KckBoardService kbs = new KckBoardServiceImpl();
			KckBoardVO kvo = null;
			kvo = new KckBoardVO();

			kvo.setBnum(sVal[0]);

			int nCnt = kbs.kboardDelete(kvo);

			if (nCnt > 0) {
				System.out.println("�Խñ� " + nCnt + " �� ���� �Ǿ����ϴ�.");
			} else {
				System.out.println("�Խñ� ���� ����.");
			}
		} catch (Exception e) {
			System.out.println("���� �� ������ >>> : " + e.getMessage());
		}

	}

	// �Ķ���� ���� : ����� �ؽ�Ʈ �Է¹޾� �����ϴ� �Լ�
	// �Խ��� Insert, Update ���� �Է��ϱ�
	public static String[] getParameter(int iVal) {

		String sVal[] = null;

		// ���� ��ȸ || �����ϱ�
		if (2 == iVal || 5 == iVal) {
			sVal = new String[1];

			System.out.println("��ȸ �Ǵ� ������ �Խ����� �� ��ȣ�� �Է����ּ���!");

			Scanner sc25 = new Scanner(System.in);

			sVal[0] = sc25.next();

			System.out.println("�� ��ȣ " + sVal[0] + " �� �����ϼ̽��ϴ�!");

		}
		// ����ϱ�
		if (3 == iVal) {

			sVal = new String[insert_label.length];
			Scanner sc3 = new Scanner(System.in);

			System.out.println("����� �Խ��� ������ �Է����ּ���!" + insert_label.length);
			System.out.println("����� �Խ��� �迭�� ���� : " + sVal.length);

			for (int i = 0; i < insert_label.length; i++) {
				System.out.println(insert_label[i] + "��(��) �Է��ϼ���!");
				sVal[i] = sc3.next();
				System.out.println(insert_label[i] + " : " + sVal[i]);

			}

		}
		// �����ϱ�
		if (4 == iVal) {

			sVal = new String[update_label.length];
			Scanner sc4 = new Scanner(System.in);

			System.out.println("������ �Խ��� ������ �Է����ּ���!" + update_label.length);
			System.out.println("������ �Խ��� �迭�� ���� : " + sVal.length);

			for (int i = 0; i < update_label.length; i++) {
				System.out.println(update_label[i] + "�� ���� �Է��ϼ���!");
				sVal[i] = sc4.next();
				System.out.println(update_label[i] + " : " + sVal[i]);
			}

		}
		return sVal;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("�Խ��� CRUD :: ISUD �׽�Ʈ ");
		Scanner sc = new Scanner(System.in);

		while (true) {

			System.out.println("\n �Խ������� [��ü��ȸ : 1], [������ȸ : 2], [��� : 3], [���� : 4], [���� : 5], [���� : 0]");

			int isud = sc.nextInt();
			if (0 == isud) {
				System.out.println("-���α׷��� ����˴ϴ�.-");
				break;
			}

			switch (isud) {
			case 1:
				try {
					KckBoardScr.kboardSelectAll();
				} catch (Exception e) {
					System.out.println("��ü��ȸ ���� �� ������ �߻��߽��ϴ�!" + e.getMessage());
				}
				break;
			case 2:
				try {
					String sVal2[] = KckBoardScr.getParameter(2);
					KckBoardScr.kboardSelect(sVal2);
				} catch (Exception e) {
					System.out.println("������ȸ ���� �� ������ �߻��߽��ϴ�!" + e.getMessage());
				}
				break;
			case 3:
				try {
					String sVal3[] = KckBoardScr.getParameter(3);
					KckBoardScr.kboardInsert(sVal3);
				} catch (Exception e) {
					System.out.println("��� ���� �� ������ �߻��߽��ϴ�!" + e.getMessage());
				}
				break;
			case 4:
				try {
					String sVal4[] = KckBoardScr.getParameter(4);
					KckBoardScr.kboardUpdate(sVal4);
				} catch (Exception e) {
					System.out.println("���� ���� �� ������ �߻��߽��ϴ�!" + e.getMessage());
				}
				break;
			case 5:
				try {
					String sVal5[] = KckBoardScr.getParameter(5);
					KckBoardScr.kboardDelete(sVal5);
				} catch (Exception e) {
					System.out.println("���� ���� �� ������ �߻��߽��ϴ�!" + e.getMessage());
				}
				break;

			}
		}
	}
}
