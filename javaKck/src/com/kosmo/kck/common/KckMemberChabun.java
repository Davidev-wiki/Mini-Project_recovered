package com.kosmo.kck.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kosmo.kck.member.KckMemberSqlMap;

public class KckMemberChabun {

	// ��� ȸ���ĺ����� "M"
	public static final String BIZ_GUBUN_M = "M";

	// �������

	// ������

	// �Լ�
	// ymdNum() : ��¥������ ���� ȸ�� ��ȣ�� �޾ƿ´�.
	// ���� ���� (DataSouce : ConnProperty)��
	// ������ ������ ������ �̿��� �����ͺ��̽��� ����.
	// DAOImpl���� ������ �� ���´� ������ ���е� ��.
	public static String ymdNum() {
		
		System.out.println("KckMemberChabun.ymdNum() ����");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rsRs = null;
		String commNO = "";
		
		try {
			
			conn = KckConnProperty.getConnection();
			pstmt = conn.prepareStatement(KckMemberSqlMap.getKckMemberChabunQuery());
			rsRs = pstmt.executeQuery();
			System.out.println("������ �������� DB�� ���޵Ǿ����ϴ�. : " + KckMemberSqlMap.getKckMemberChabunQuery());
			
			if(rsRs != null) {
				while (rsRs.next()) {
					commNO = rsRs.getString("COMMNO");
				}
			} else {
				System.out.println("ȸ�� ��ȣ�� �������� ���߽��ϴ�.");
			}
			
			// DB���� ������ �� MAX��
			System.out.println("������ commNO : " + commNO);
			
			// DB���� ������ �� MAX�� '0'�� ä�� 4�ڸ� �����
			commNO = CodeUtil.numPad(commNO);
			
			// ���� ��¥ ���̱�
			commNO = DateUtil.yyyymmdd().concat(commNO);
			
			// 'M' ���̱�
			commNO = KckMemberChabun.BIZ_GUBUN_M.concat(commNO);
			
			System.out.println("������ ��ȣ commNO : " + commNO);
			
			// ���� ����
			KckConnProperty.conClose(conn, pstmt, rsRs);
			
		}catch(Exception e) {
			System.out.println("��ȣ ���� �� ������ �߻��߽��ϴ�. : " + e.getMessage());
		} finally {
			KckConnProperty.conClose(conn, pstmt, rsRs);
		}
		
		return commNO;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("KckMemberChabun.main() ����");
	}

}
