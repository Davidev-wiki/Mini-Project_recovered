package com.kosmo.kck.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kosmo.kck.common.KckConnProperty;

public class KckMemberDAOImpl implements KckMemberDAO {

	@Override
	public ArrayList<KckMemberVO> kmemselectAll() {
		// TODO Auto-generated method stub
		System.out.println("KckMemberDAOImpl.kmemselectAll() 진입");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rsRs = null;

		ArrayList<KckMemberVO> aList = null;

		try {
			conn = KckConnProperty.getConnection();
			pstmt = conn.prepareStatement(KckMemberSqlMap.getKckMemberSelectAllQuery());
			rsRs = pstmt.executeQuery();

			if (rsRs != null) {

				aList = new ArrayList<KckMemberVO>();

				while (rsRs.next()) {

					KckMemberVO kvo = new KckMemberVO();

					kvo.setKnum(rsRs.getString(1));
					kvo.setKname(rsRs.getString(2));
					kvo.setKid(rsRs.getString(3));
					kvo.setKpw(rsRs.getString(4));
					kvo.setKbirth(rsRs.getString(5));
					kvo.setKgender(rsRs.getString(6));
					kvo.setKtel(rsRs.getString(7));
					kvo.setKhp(rsRs.getString(8));
					kvo.setKemail(rsRs.getString(9));
					kvo.setKaddr(rsRs.getString(10));
					kvo.setKhobby(rsRs.getString(11));
					kvo.setKphoto(rsRs.getString(12));
					kvo.setKskill(rsRs.getString(13));
					kvo.setKjob(rsRs.getString(14));
					kvo.setDeleteyn(rsRs.getString(15));
					kvo.setInsertdate(rsRs.getString(16));
					kvo.setUpdatedate(rsRs.getString(17));

					aList.add(kvo);
				}
			}
			KckConnProperty.conClose(conn, pstmt, rsRs);

		} catch (Exception e) {
			System.out.println("DB에서 전체 조회중 에러가 발생했어요! : " + e.getMessage());
		} finally {
			KckConnProperty.conClose(conn, pstmt, rsRs);
		}
		return aList;
	}

	@Override
	public ArrayList<KckMemberVO> kmemselect(KckMemberVO kvo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean kmemInsert(KckMemberVO kvo) {
		// TODO Auto-generated method stub
		System.out.println("KckMemberDAOImpl.kmemInsert() 진입");

		// 사용할 객체를 지역변수로 선언하고 초기화 하기
		Connection conn = null;
		PreparedStatement pstmt = null;
		int nCnt = 0;
		boolean bool = false;

		try {

			conn = KckConnProperty.getConnection();
			System.out.println("conn.getAutoCommit() >>> : " + conn.getAutoCommit());

			pstmt = conn.prepareStatement(KckMemberSqlMap.getKckMemberInsertQuery());
			System.out.println("입력하기 >>> : \n" + KckMemberSqlMap.getKckMemberInsertQuery());

			// 파라미터 클리어 꼭 하기
			pstmt.clearParameters();

			pstmt.setString(1, kvo.getKnum());
			pstmt.setString(2, kvo.getKname());
			pstmt.setString(3, kvo.getKid());
			pstmt.setString(4, kvo.getKpw());
			pstmt.setString(5, kvo.getKbirth());
			pstmt.setString(6, kvo.getKgender());
			pstmt.setString(7, kvo.getKtel());
			pstmt.setString(8, kvo.getKhp());
			pstmt.setString(9, kvo.getKemail());
			pstmt.setString(10, kvo.getKaddr());
			pstmt.setString(11, kvo.getKhobby());
			pstmt.setString(12, kvo.getKphoto());
			pstmt.setString(13, kvo.getKskill());
			pstmt.setString(14, kvo.getKjob());

			nCnt = pstmt.executeUpdate();
			if (!conn.getAutoCommit())
				conn.commit();

			// 왜 nCnt가 1 증가했는가??
			System.out.println("nCnt : " + nCnt + " 건 등록 완료 되었습니다. ");

			if (nCnt > 0) {
				bool = true;
			}

			KckConnProperty.conClose(conn, pstmt);
		} catch (Exception e) {
			System.out.println("입력 디비연동에 문제가 생겼습니다.  : " + e);
		} finally {
			try {
				KckConnProperty.conClose(conn, pstmt);
			} catch (Exception e2) {
			}
		}

		return bool;
	}

	@Override
	public boolean kmemUpdate(KckMemberVO kvo) {
		// TODO Auto-generated method stub
		System.out.println("KckMemberDAOImpl.kmemUpdate() 진입");

		// 사용할 객체를 지역변수로 선언하고 초기화 하기
		Connection conn = null;
		PreparedStatement pstmt = null;
		int nCnt = 0;
		boolean bool = false;

		try {

			conn = KckConnProperty.getConnection();
			System.out.println("conn.getAutoCommit() >>> : " + conn.getAutoCommit());

			pstmt = conn.prepareStatement(KckMemberSqlMap.getKckMemberUpdateQuery());
			System.out.println("입력하기 >>> : \n" + KckMemberSqlMap.getKckMemberUpdateQuery());

			// 파라미터 클리어 꼭 하기
			pstmt.clearParameters();

			/*
			 * StringBuffer sb = new StringBuffer();
			 * sb.append("	UPDATE  							\n");
			 * sb.append("		   	 KCK_MEMBER 			    \n");
			 * sb.append("	SET  								\n");
			 * sb.append("			 KNAME  		= ?			\n"); // placeholder 1
			 * sb.append("			,KID			= ?			\n"); // placeholder 2
			 * sb.append("			,KPW 			= ? 		\n"); // placeholder 3
			 * sb.append("			,KBIRTH   		= ?			\n"); // placeholder 4
			 * sb.append("			,KGENDER   		= ?			\n"); // placeholder 5
			 * sb.append("			,KTEL   		= ?			\n"); // placeholder 6
			 * sb.append("			,KHP  			= ?			\n"); // placeholder 7
			 * sb.append("			,KEMAIL   		= ?			\n"); // placeholder 8
			 * sb.append("			,KADDR   		= ?			\n"); // placeholder 9
			 * sb.append("			,KHOBBY   		= ?			\n"); // placeholder 10
			 * sb.append("			,KJOB   		= ?			\n"); // placeholder 11
			 * sb.append("		  	,UPDATEDATE 	= SYSDATE	\n");
			 * sb.append("	WHERE  	 KNUM 			= ?			\n"); // placeholder 12
			 * sb.append("	AND    	 DELETEYN 		= 'Y'  		\n");
			 */
			pstmt.setString(1, kvo.getKname());
			pstmt.setString(2, kvo.getKid());
			pstmt.setString(3, kvo.getKpw());
			pstmt.setString(4, kvo.getKbirth());
			pstmt.setString(5, kvo.getKgender());
			pstmt.setString(6, kvo.getKtel());
			pstmt.setString(7, kvo.getKhp());
			pstmt.setString(8, kvo.getKemail());
			pstmt.setString(9, kvo.getKaddr());
			pstmt.setString(10, kvo.getKhobby());
			pstmt.setString(11, kvo.getKjob());
			pstmt.setString(12, kvo.getKid());

			nCnt = pstmt.executeUpdate();
			if (!conn.getAutoCommit())
				conn.commit();

			// 왜 nCnt가 1 증가했는가??
			System.out.println("nCnt : " + nCnt + " 건  수정 완료 되었습니다. ");

			if (nCnt > 0) {
				bool = true;
			}

			KckConnProperty.conClose(conn, pstmt);
		} catch (Exception e) {
			System.out.println("입력 디비연동에 문제가 생겼습니다.  : " + e);
		} finally {
			try {
				KckConnProperty.conClose(conn, pstmt);
			} catch (Exception e2) {
			}
		}

		return bool;
	}

	@Override
	public boolean kmemDelete(KckMemberVO kvo) {
		// TODO Auto-generated method stub
		System.out.println("KckMemberDAOImpl.kmemDelete() 진입");

		// 사용할 객체를 지역변수로 선언하고 초기화 하기
		Connection conn = null;
		PreparedStatement pstmt = null;
		int nCnt = 0;
		boolean bool = false;

		try {

			conn = KckConnProperty.getConnection();
			System.out.println("conn.getAutoCommit() >>> : " + conn.getAutoCommit());

			pstmt = conn.prepareStatement(KckMemberSqlMap.getKckMemberDeleteQuery());
			System.out.println("입력하기 >>> : \n" + KckMemberSqlMap.getKckMemberDeleteQuery());

			// 파라미터 클리어 꼭 하기
			pstmt.clearParameters();
			pstmt.setString(1, kvo.getKid());

			nCnt = pstmt.executeUpdate();
			if (!conn.getAutoCommit())
				conn.commit();

			// 왜 nCnt가 1 증가했는가??
			System.out.println("nCnt : " + nCnt + " 건 삭제 완료 되었습니다. ");

			if (nCnt > 0) {
				bool = true;
			}

			KckConnProperty.conClose(conn, pstmt);
		} catch (Exception e) {
			System.out.println("입력 디비연동에 문제가 생겼습니다.  : " + e);
		} finally {
			try {
				KckConnProperty.conClose(conn, pstmt);
			} catch (Exception e2) {
			}
		}

		return bool;
	}

	@Override
	public ArrayList<KckMemberVO> kmemSelectName(KckMemberVO kvo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<KckMemberVO> kmemSelectId(KckMemberVO kvo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean kmemIdCheck(KckMemberVO kvo) {
		// TODO Auto-generated method stub
		return false;
	}

}
