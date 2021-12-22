package com.kosmo.kck.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kosmo.kck.board.KckBoardSqlMap;

public class KckBoardChabun {

	// 상수
	public static final String BIZ_GUBUN_M = "M"; // 회원
	public static final String BIZ_GUBUN_B = "B"; // 게시판

	// 멤버 변수

	// 생성자

	// 함수
	public static String gubunNum() {
		System.out.println("KckBoardChabun.gubunNum()함수 진입");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rsRs = null;
		String commNO = null;

		try {
			conn = KckConnProperty.getConnection();
			pstmt = conn.prepareStatement(KckBoardSqlMap.getKboardChabunQueryMap());
			rsRs = pstmt.executeQuery();

			if (rsRs != null) {
				while (rsRs.next()) {
					commNO = rsRs.getString("COMMNO");
					System.out.println("채번 성공했어요 : " + commNO);
				}
			} else {
				System.out.println("채번에 실패했어요..");
			}

			// DB에서 가지고 온 MAX 값
			System.out.println(commNO);

			// DB에서 가지고 온 MAX 값을 4자리로 만들고 빈칸에 0 채우기
			commNO = CodeUtil.numPad(commNO);

			// 업무구분코드 붙이기.
			commNO = KckBoardChabun.BIZ_GUBUN_B.concat(commNO);
			System.out.println("만들어진 번호 : " + commNO);

			KckConnProperty.conClose(conn, pstmt, rsRs);
		} catch (Exception e) {
			System.out.println("채번 과정에서 에러가 발생했어요!" + e.getMessage());
		} finally {
			KckConnProperty.conClose(conn, pstmt, rsRs);
		}

		return commNO;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String commNO = KckBoardChabun.gubunNum();
		System.out.println("main :: commNO >>> : " + commNO);
	}

}
