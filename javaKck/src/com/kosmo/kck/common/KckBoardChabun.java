package com.kosmo.kck.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kosmo.kck.board.KckBoardSqlMap;

public class KckBoardChabun {

	// ���
	public static final String BIZ_GUBUN_M = "M"; // ȸ��
	public static final String BIZ_GUBUN_B = "B"; // �Խ���

	// ��� ����

	// ������

	// �Լ�
	public static String gubunNum() {
		System.out.println("KckBoardChabun.gubunNum()�Լ� ����");

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
					System.out.println("ä�� �����߾�� : " + commNO);
				}
			} else {
				System.out.println("ä���� �����߾��..");
			}

			// DB���� ������ �� MAX ��
			System.out.println(commNO);

			// DB���� ������ �� MAX ���� 4�ڸ��� ����� ��ĭ�� 0 ä���
			commNO = CodeUtil.numPad(commNO);

			// ���������ڵ� ���̱�.
			commNO = KckBoardChabun.BIZ_GUBUN_B.concat(commNO);
			System.out.println("������� ��ȣ : " + commNO);

			KckConnProperty.conClose(conn, pstmt, rsRs);
		} catch (Exception e) {
			System.out.println("ä�� �������� ������ �߻��߾��!" + e.getMessage());
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
