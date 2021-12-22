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
		System.out.println("KckLoginDAOImpl kLoginCheck() �Լ� ���� >>> : ");

		// ����� ��ü�� ���������� �����ϰ� �ʱ�ȭ �ϱ�
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rsRs = null;
		int nCnt = 0;

		try {
			conn = KckConnProperty.getConnection();
			pstmt = conn.prepareStatement(KckLoginSqlMap.gethLoginCheckQuery());
			System.out.println("�α��� üũ  : " + KckLoginSqlMap.gethLoginCheckQuery());

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
			System.out.println(" �α��� üũ�ϴµ� ������ �߻��߾��! " + e.getMessage());

		} finally {
			KckConnProperty.conClose(conn, pstmt, rsRs);
		}

		return nCnt;
	}

}
