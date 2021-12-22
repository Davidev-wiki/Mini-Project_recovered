package com.kosmo.kck.board;

import java.util.ArrayList;
import java.util.Scanner;

import com.kosmo.kck.common.KckBoardChabun;

public class KckBoardScr {

	// 게시판 정보 라벨
	public static final String[] insert_label = { "글제목", "작성자", "글내용", "비밀번호" };
	public static final String[] update_label = { "글번호", "글제목", "글내용" };

	// 전체 조회 == 1
	public static void kboardSelectAll() {
		System.out.println("KckBoardScr.kboardSelectAll()함수 진입");

		try {
			KckBoardService kbs = new KckBoardServiceImpl();

			ArrayList<KckBoardVO> aList = kbs.kboardSelectAll();

			if (aList != null && aList.size() > 0) {
				for (int i = 0; i < aList.size(); i++) {
					KckBoardVO kvo = aList.get(i);
					KckBoardVO.printKckBoardVO(kvo);
				}
			} else {
				System.out.println("aList가 비어있네요..");
			}
		} catch (Exception e1) {
			System.out.println("전체 조회중 에러가 발생했어요 : " + e1.getMessage());
		}
	}

	// 조건 조회 == 2 || 글 번호
	public static void kboardSelect(String sVal[]) {
		System.out.println("KckBoardScr.kboardSelect()함수 진입");
		System.out.println("회원정보 조회 : " + sVal[0]);

		try {
			KckBoardService kbs = new KckBoardServiceImpl();
			KckBoardVO kvo = new KckBoardVO();
			kvo.setBnum(sVal[0]);

			ArrayList<KckBoardVO> aList = kbs.kboardSelect(kvo);

			if (aList != null && aList.size() > 0) {
				for (int i = 0; i < aList.size(); i++) {
					KckBoardVO _kvo = aList.get(i);
					// 하나의 결과이므로 println으로 보기
					KckBoardVO.printlnKckBoardVO(_kvo);
				}
			} else {
				System.out.println("aList가 비어있네요..");
			}
		} catch (Exception e) {
			System.out.println("게시물 정보 조회중 에러가 발생했어요 : " + e.getMessage());
		}

	}

	// 글 등록 == 3 || 글 번호, 제목, 글쓴이, 비밀번호, 글 내용
	public static void kboardInsert(String sVal[]) {
		System.out.println("KckBoardScr.kboardInsert()함수 진입");
		System.out.println("회원 정보 입력 : " + sVal.length);

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
				System.out.println("게시물 " + nCnt + "건이 등록되었습니다!");
			} else {
				System.out.println("게시물 등록 실패!");
			}

		} catch (Exception e) {
			System.out.println("게시물 등록중 에러가 발생했어요 : " + e.getMessage());
		}

	}

	// 글 수정 == 4 || 글 번호, 글 제목, 글 내용
	public static void kboardUpdate(String sVal[]) {
		System.out.println("KckBoardScr.kboardUpdate()함수 진입");
		System.out.println("회원정보 수정 : " + sVal.length);

		for (int i = 0; i < sVal.length; i++) {
			System.out.println(update_label[i] + " >>> : " + sVal[i]);
		}

		String bnum = sVal[0];
		String bsubject = sVal[1];
		String bcontents = sVal[2];

		try {
			// 서비스 호출
			KckBoardService kbs = new KckBoardServiceImpl();
			KckBoardVO kvo = null;
			kvo = new KckBoardVO();

			kvo.setBnum(bnum);
			kvo.setBsubject(bsubject);
			kvo.setBcontents(bcontents);

			int nCnt = kbs.kboardUpdate(kvo);

			if (nCnt > 0) {
				System.out.println("게시글 " + nCnt + " 건 수정 되었습니다.");
			} else {
				System.out.println("게시글 수정 실패.");
			}
		} catch (Exception e) {
			System.out.println("수정 중 에러가 >>> : " + e.getMessage());
		}
	}

	// 글 삭제 == 5 || 글 번호
	public static void kboardDelete(String sVal[]) {
		System.out.println("KckBoardScr.kboardDelete()함수 진입");
		System.out.println("회원정보 삭제 : " + sVal[0]);

		try {
			// 서비스 호출
			KckBoardService kbs = new KckBoardServiceImpl();
			KckBoardVO kvo = null;
			kvo = new KckBoardVO();

			kvo.setBnum(sVal[0]);

			int nCnt = kbs.kboardDelete(kvo);

			if (nCnt > 0) {
				System.out.println("게시글 " + nCnt + " 건 삭제 되었습니다.");
			} else {
				System.out.println("게시글 삭제 실패.");
			}
		} catch (Exception e) {
			System.out.println("삭제 중 에러가 >>> : " + e.getMessage());
		}

	}

	// 파라미터 세팅 : 사용자 텍스트 입력받아 저장하는 함수
	// 게시판 Insert, Update 정보 입력하기
	public static String[] getParameter(int iVal) {

		String sVal[] = null;

		// 조건 조회 || 삭제하기
		if (2 == iVal || 5 == iVal) {
			sVal = new String[1];

			System.out.println("조회 또는 삭제할 게시판의 글 번호를 입력해주세요!");

			Scanner sc25 = new Scanner(System.in);

			sVal[0] = sc25.next();

			System.out.println("글 번호 " + sVal[0] + " 을 선택하셨습니다!");

		}
		// 등록하기
		if (3 == iVal) {

			sVal = new String[insert_label.length];
			Scanner sc3 = new Scanner(System.in);

			System.out.println("등록할 게시판 정보를 입력해주세요!" + insert_label.length);
			System.out.println("등록할 게시판 배열의 길이 : " + sVal.length);

			for (int i = 0; i < insert_label.length; i++) {
				System.out.println(insert_label[i] + "을(를) 입력하세요!");
				sVal[i] = sc3.next();
				System.out.println(insert_label[i] + " : " + sVal[i]);

			}

		}
		// 수정하기
		if (4 == iVal) {

			sVal = new String[update_label.length];
			Scanner sc4 = new Scanner(System.in);

			System.out.println("수정할 게시판 정보를 입력해주세요!" + update_label.length);
			System.out.println("수정할 게시판 배열의 길이 : " + sVal.length);

			for (int i = 0; i < update_label.length; i++) {
				System.out.println(update_label[i] + "의 값을 입력하세요!");
				sVal[i] = sc4.next();
				System.out.println(update_label[i] + " : " + sVal[i]);
			}

		}
		return sVal;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("게시판 CRUD :: ISUD 테스트 ");
		Scanner sc = new Scanner(System.in);

		while (true) {

			System.out.println("\n 게시판정보 [전체조회 : 1], [조건조회 : 2], [등록 : 3], [수정 : 4], [삭제 : 5], [종료 : 0]");

			int isud = sc.nextInt();
			if (0 == isud) {
				System.out.println("-프로그램이 종료됩니다.-");
				break;
			}

			switch (isud) {
			case 1:
				try {
					KckBoardScr.kboardSelectAll();
				} catch (Exception e) {
					System.out.println("전체조회 실행 중 에러가 발생했습니다!" + e.getMessage());
				}
				break;
			case 2:
				try {
					String sVal2[] = KckBoardScr.getParameter(2);
					KckBoardScr.kboardSelect(sVal2);
				} catch (Exception e) {
					System.out.println("조건조회 실행 중 에러가 발생했습니다!" + e.getMessage());
				}
				break;
			case 3:
				try {
					String sVal3[] = KckBoardScr.getParameter(3);
					KckBoardScr.kboardInsert(sVal3);
				} catch (Exception e) {
					System.out.println("등록 실행 중 에러가 발생했습니다!" + e.getMessage());
				}
				break;
			case 4:
				try {
					String sVal4[] = KckBoardScr.getParameter(4);
					KckBoardScr.kboardUpdate(sVal4);
				} catch (Exception e) {
					System.out.println("수정 실행 중 에러가 발생했습니다!" + e.getMessage());
				}
				break;
			case 5:
				try {
					String sVal5[] = KckBoardScr.getParameter(5);
					KckBoardScr.kboardDelete(sVal5);
				} catch (Exception e) {
					System.out.println("삭제 실행 중 에러가 발생했습니다!" + e.getMessage());
				}
				break;

			}
		}
	}
}
