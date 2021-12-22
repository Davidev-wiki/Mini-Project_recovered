package com.kosmo.kck.member;

public abstract class KckMemberSqlMap {

	// 상수
	// ID check
	public static final String ID_CHECK = "SELECT COUNT(A.KNUM) NCNT FROM KCK_MEMBER A WHERE A.DELETEYN = 'Y' AND A.KID = ? ";

	// 함수
	// 회원 채번 MYYYYMMDD0001
	public static String getKckMemberChabunQuery() {

		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT  											\n");
		sb.append("         NVL(MAX(SUBSTR(A.KNUM, -4)), 0) + 1  COMMNO	\n");
		sb.append(" FROM    KCK_MEMBER A 								\n");

		return sb.toString();
	}

	// 전체 조회
	public static String getKckMemberSelectAllQuery() {

		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT 								\n");
		sb.append("      A.KNUM 		KNUM 			\n");
		sb.append("		,A.KNAME  		KNAME 			\n");
		sb.append("		,A.KID  		KID 			\n");
		sb.append("		,A.KPW  		KPW   			\n");
		sb.append("		,A.KBIRTH  		KBIRTH 			\n");
		sb.append("		,A.KGENDER  	KGENDER 		\n");
		sb.append("		,A.KTEL  		KTEL   			\n");
		sb.append("		,A.KHP  		KHP 			\n");
		sb.append("		,A.KEMAIL  		KEMAIL 			\n");
		sb.append("		,A.KADDR  		KADDR   		\n");
		sb.append("		,A.KHOBBY  		KHOBBY 			\n");
		sb.append("		,A.KPHOTO  		KPHOTO 			\n");
		sb.append("		,A.KSKILL  		KSKILL 			\n");
		sb.append("		,A.KJOB  		KJOB   			\n");
		sb.append("		,A.DELETEYN 	DELETEYN		\n");
		sb.append("		,TO_CHAR(A.INSERTDATE, 'YYYY-MM-DD')  INSERTDATE	\n");
		sb.append("		,TO_CHAR(A.UPDATEDATE, 'YYYY-MM-DD')  UPDATEDATE	\n");
		sb.append("	FROM 								\n");
		sb.append("		 KCK_MEMBER A 					\n");
		sb.append("	WHERE DELETEYN = 'Y'				\n");
		sb.append("	ORDER BY 1 DESC						\n");

		return sb.toString();
	}

	// 조건 조회
	public static String getKckMemberSelectQuery() {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT 								\n");
		sb.append("      A.KNUM 		KNUM 			\n");
		sb.append("		,A.KNAME  		KNAME 			\n");
		sb.append("		,A.KID  		KID 			\n");
		sb.append("		,A.KPW  		KPW   			\n");
		sb.append("		,A.KBIRTH  		KBIRTH 			\n");
		sb.append("		,A.KGENDER  	KGENDER 		\n");
		sb.append("		,A.KTEL  		KTEL   			\n");
		sb.append("		,A.KHP  		KHP 			\n");
		sb.append("		,A.KEMAIL  		KEMAIL 			\n");
		sb.append("		,A.KADDR  		KADDR   		\n");
		sb.append("		,A.KHOBBY  		KHOBBY 			\n");
		sb.append("		,A.KPHOTO  		KPHOTO 			\n");
		sb.append("		,A.KSKILL  		KSKILL 			\n");
		sb.append("		,A.KJOB  		KJOB   			\n");
		sb.append("		,A.DELETEYN 	BDELETEYN		\n");
		sb.append("		,TO_CHAR(A.INSERTDATE, 'YYYY-MM-DD')  INSERTDATE	\n");
		sb.append("		,TO_CHAR(A.UPDATEDATE, 'YYYY-MM-DD')  UPDATEDATE	\n");
		sb.append("	FROM 								\n");
		sb.append("		 KCK_MEMBER A 					\n");
		sb.append("	WHERE DELETEYN = 'Y'				\n");
		sb.append("	AND   A.KNUM   = ?					\n"); // placeholder 1

		return sb.toString();
	}

	// '회원 이름'으로 조회
	public static String getKckMemberSelectNameQuery() {

		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT 								\n");
		sb.append("      A.KNUM 		KNUM 			\n");
		sb.append("		,A.KNAME  		KNAME 			\n");
		sb.append("		,A.KID  		KID 			\n");
		sb.append("		,A.KPW  		KPW   			\n");
		sb.append("		,A.KBIRTH  		KBIRTH 			\n");
		sb.append("		,A.KGENDER  	KGENDER 		\n");
		sb.append("		,A.KTEL  		KTEL   			\n");
		sb.append("		,A.KHP  		KHP 			\n");
		sb.append("		,A.KEMAIL  		KEMAIL 			\n");
		sb.append("		,A.KADDR  		KADDR   		\n");
		sb.append("		,A.KHOBBY  		KHOBBY 			\n");
		sb.append("		,A.KPHOTO  		KPHOTO 			\n");
		sb.append("		,A.KSKILL  		KSKILL 			\n");
		sb.append("		,A.KJOB  		KJOB   			\n");
		sb.append("		,A.DELETEYN 	BDELETEYN		\n");
		sb.append("		,TO_CHAR(A.INSERTDATE, 'YYYY-MM-DD')  INSERTDATE	\n");
		sb.append("		,TO_CHAR(A.UPDATEDATE, 'YYYY-MM-DD')  UPDATEDATE	\n");
		sb.append("	FROM 								\n");
		sb.append("		 KCK_MEMBER A 					\n");
		sb.append("	WHERE DELETEYN = 'Y'				\n");
		sb.append("	AND   A.KNAME LIKE  '%' || ? || '%' \n"); // placeholder 1

		return sb.toString();

	}

	// '회원 아이디'로 조회
	public static String getKckMemberSelectIdQuery() {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT 								\n");
		sb.append("      A.KNUM 		KNUM 			\n");
		sb.append("		,A.KNAME  		KNAME 			\n");
		sb.append("		,A.KID  		KID 			\n");
		sb.append("		,A.KPW  		KPW   			\n");
		sb.append("		,A.KBIRTH  		KBIRTH 			\n");
		sb.append("		,A.KGENDER  	KGENDER 		\n");
		sb.append("		,A.KTEL  		KTEL   			\n");
		sb.append("		,A.KHP  		KHP 			\n");
		sb.append("		,A.KEMAIL  		KEMAIL 			\n");
		sb.append("		,A.KADDR  		KADDR   		\n");
		sb.append("		,A.KHOBBY  		KHOBBY 			\n");
		sb.append("		,A.KPHOTO  		KPHOTO 			\n");
		sb.append("		,A.KSKILL  		KSKILL 			\n");
		sb.append("		,A.KJOB  		KJOB   			\n");
		sb.append("		,A.DELETEYN 	BDELETEYN		\n");
		sb.append("		,TO_CHAR(A.INSERTDATE, 'YYYY-MM-DD')  INSERTDATE	\n");
		sb.append("		,TO_CHAR(A.UPDATEDATE, 'YYYY-MM-DD')  UPDATEDATE	\n");
		sb.append("	FROM 								\n");
		sb.append("		 KCK_MEMBER A 					\n");
		sb.append("	WHERE DELETEYN = 'Y'				\n");
		sb.append("	AND   A.KID  LIKE '%' || ? || '%'	\n"); // placeholder 1

		return sb.toString();
	}

	// 회원 등록
	public static String getKckMemberInsertQuery() {

		StringBuffer sb = new StringBuffer();
		sb.append("	INSERT INTO 						\n");
		sb.append("		KCK_MEMBER 				    	\n");
		sb.append("		          (			    		\n");
		sb.append("      			 KNUM 				\n"); // COLUMN 1
		sb.append("					,KNAME 				\n"); // COLUMN 2
		sb.append("					,KID 				\n"); // COLUMN 3
		sb.append("					,KPW   				\n"); // COLUMN 4
		sb.append("					,KBIRTH				\n"); // COLUMN 5
		sb.append("					,KGENDER 			\n"); // COLUMN 6
		sb.append("					,KTEL   			\n"); // COLUMN 7
		sb.append("					,KHP 				\n"); // COLUMN 8
		sb.append("					,KEMAIL				\n"); // COLUMN 9
		sb.append("					,KADDR 				\n"); // COLUMN 10
		sb.append("					,KHOBBY				\n"); // COLUMN 11
		sb.append("					,KPHOTO 			\n"); // COLUMN 12
		sb.append("					,KSKILL				\n"); // COLUMN 13
		sb.append("					,KJOB				\n"); // COLUMN 14
		sb.append("					,DELETEYN			\n"); // COLUMN 15
		sb.append("					,INSERTDATE			\n"); // COLUMN 16
		sb.append("					,UPDATEDATE			\n"); // COLUMN 17
		sb.append("			      )						\n");
		sb.append("	       VALUES   					\n");
		sb.append("	       		 (  					\n");
		sb.append("     				 ? 				\n"); // placeholder 1
		sb.append("						,? 				\n"); // placeholder 2
		sb.append("						,?   			\n"); // placeholder 3
		sb.append("						,?   			\n"); // placeholder 4
		sb.append("						,? 				\n"); // placeholder 5
		sb.append("						,? 				\n"); // placeholder 6
		sb.append("						,?				\n"); // placeholder 7
		sb.append("						,?				\n"); // placeholder 8
		sb.append("						,?				\n"); // placeholder 9
		sb.append("						,?   			\n"); // placeholder 10
		sb.append("						,? 				\n"); // placeholder 11
		sb.append("						,? 				\n"); // placeholder 12
		sb.append("						,?				\n"); // placeholder 13
		sb.append("						,?				\n"); // placeholder 14
		sb.append("						,'Y'			\n"); // placeholder 15
		sb.append("						,SYSDATE 		\n"); // placeholder 16
		sb.append("						,SYSDATE 		\n"); // placeholder 17
		sb.append("	              )						\n");

		return sb.toString();

	}

	// 정보 수정
	public static String getKckMemberUpdateQuery() {

		/*
		 * 수정 가능하게 해줄 필드

			kvo.setKnum(kname);  1
			kvo.setKid(kid);  2
			kvo.setKpw(kpw);3
			kvo.setKbirth(kbirth);4
			kvo.setKgender(kgender);5
			kvo.setKtel(ktel);6
			kvo.setKhp(khp);7
			kvo.setKemail(kemail);8
			kvo.setKaddr(kaddr);9
			
		sb.append("	UPDATE  							\n");
		sb.append("		   KCK_BOARD 			    	\n");
		sb.append("	SET  								\n");
		sb.append("		   BSUBJECT	  		= ?			\n");
		sb.append("		  ,BCONTENTS		= ?			\n");
		sb.append("		  ,BWRITER		    = ?			\n");
		sb.append("		  ,UPDATEDATE 		= SYSDATE	\n");
		sb.append("	WHERE  BNUM 			= ?			\n");
		sb.append("	AND    DELETEYN 		= 'Y'  		\n");
		 */
		StringBuffer sb = new StringBuffer();
		sb.append("	UPDATE  								\n");
		sb.append("		   	 KCK_MEMBER 			    	\n");
		sb.append("	SET  									\n");
		sb.append("			 KNAME  			= ?			\n"); // placeholder 1
		sb.append("			,KID				= ?			\n"); // placeholder 2
		sb.append("			,KPW 				= ? 		\n"); // placeholder 3
		sb.append("			,KBIRTH 	  		= ?			\n"); // placeholder 4
		sb.append("			,KGENDER   			= ?			\n"); // placeholder 5
		sb.append("			,KTEL   			= ?			\n"); // placeholder 6
		sb.append("			,KHP  				= ?			\n"); // placeholder 7
		sb.append("			,KEMAIL   			= ?			\n"); // placeholder 8
		sb.append("			,KADDR   			= ?			\n"); // placeholder 9
		sb.append("			,KHOBBY   			= ?			\n"); // placeholder 10
		sb.append("			,KJOB   			= ?			\n"); // placeholder 11
		sb.append("		  	,UPDATEDATE 		= SYSDATE	\n");
		sb.append("	WHERE  	 DELETEYN 			= 'Y'		\n"); 
		sb.append("	AND    	 KID 				=  ?  		\n"); // placeholder 12

		return sb.toString();

	}

	// 정보 삭제(Deleteyn의 속성을 'Y' -> 'N' 으로 바꾸는 것)
	public static String getKckMemberDeleteQuery() {

		StringBuffer sb = new StringBuffer();
		sb.append("	UPDATE  								\n");
		sb.append("		   KCK_MEMBER 			    		\n");
		sb.append("	SET  									\n");
		sb.append("		   DELETEYN 		= 'N'			\n");
		sb.append("		  ,UPDATEDATE 		= SYSDATE		\n");
		sb.append("	WHERE  KID 				= ?				\n"); // placeholder 1
		sb.append("	AND    DELETEYN 		= 'Y'  			\n");

		return sb.toString();

	}

}
