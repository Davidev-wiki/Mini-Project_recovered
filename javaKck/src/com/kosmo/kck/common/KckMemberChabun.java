package com.kosmo.kck.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kosmo.kck.member.KckMemberSqlMap;

public class KckMemberChabun {

	// 상수 회원식별문자 "M"
	public static final String BIZ_GUBUN_M = "M";

	// 멤버변수

	// 생성자

	// 함수
	// ymdNum() : 날짜형식을 붙인 회원 번호를 받아온다.
	// 연결 정보 (DataSouce : ConnProperty)와
	// 전달할 쿼리문 정보를 이용해 데이터베이스에 전달.
	// DAOImpl에서 보내는 것 형태는 같지만 구분된 것.
	public static String ymdNum() {
		
		System.out.println("KckMemberChabun.ymdNum() 진입");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rsRs = null;
		String commNO = "";
		
		try {
			
			conn = KckConnProperty.getConnection();
			pstmt = conn.prepareStatement(KckMemberSqlMap.getKckMemberChabunQuery());
			rsRs = pstmt.executeQuery();
			System.out.println("다음의 쿼리문이 DB에 전달되었습니다. : " + KckMemberSqlMap.getKckMemberChabunQuery());
			
			if(rsRs != null) {
				while (rsRs.next()) {
					commNO = rsRs.getString("COMMNO");
				}
			} else {
				System.out.println("회원 번호를 가져오지 못했습니다.");
			}
			
			// DB에서 가지고 온 MAX값
			System.out.println("가져온 commNO : " + commNO);
			
			// DB에서 가지고 온 MAX값 '0'을 채워 4자리 만들기
			commNO = CodeUtil.numPad(commNO);
			
			// 현재 날짜 붙이기
			commNO = DateUtil.yyyymmdd().concat(commNO);
			
			// 'M' 붙이기
			commNO = KckMemberChabun.BIZ_GUBUN_M.concat(commNO);
			
			System.out.println("생성된 번호 commNO : " + commNO);
			
			// 연결 종료
			KckConnProperty.conClose(conn, pstmt, rsRs);
			
		}catch(Exception e) {
			System.out.println("번호 생성 중 에러가 발생했습니다. : " + e.getMessage());
		} finally {
			KckConnProperty.conClose(conn, pstmt, rsRs);
		}
		
		return commNO;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("KckMemberChabun.main() 진입");
	}

}
