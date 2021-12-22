package com.kosmo.kck.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kosmo.kck.common.KckConnProperty;
import com.kosmo.kck.member.KckMemberVO;

public class KckLoginDAOImpl implements KckLoginDAO {

	@Override
	public int kLoginCheck(KckMemberVO kvo) {
		// TODO Auto-generated method stub
		System.out.println("KckLoginDAOImpl kLoginCheck() 함수 진입 >>> : ");

		// 사용할 객체를 지역변수로 선언하고 초기화 하기
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rsRs = null;
		int nCnt = 0;

		try {
			conn = KckConnProperty.getConnection();
			pstmt = conn.prepareStatement(KckLoginSqlMap.gethLoginCheckQuery());
			System.out.println("로그인 체크  : " + KckLoginSqlMap.gethLoginCheckQuery());

			pstmt.clearParameters();
			pstmt.setString(1, kvo.getKid());
			pstmt.setString(2, kvo.getKpw());
			rsRs = pstmt.executeQuery();

			if (rsRs != null) {
				while (rsRs.next()) {
					nCnt = rsRs.getInt(1);
				}
			}

			KckConnProperty.conClose(conn, pstmt, rsRs);

		} catch (Exception e) {
			System.out.println(" 로그인 체크하는데 에러가 발생했어요! " + e.getMessage());

		} finally {
			KckConnProperty.conClose(conn, pstmt, rsRs);
		}

		return nCnt;
	}

}
