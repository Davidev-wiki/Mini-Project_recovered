package com.kosmo.kck.board;

public abstract class KckBoardSqlMap {

	// 채번 쿼리
	public static String getKboardChabunQueryMap() {

		StringBuffer sb = new StringBuffer();

		sb.append("SELECT											 \n");
		sb.append("   NVL(MAX(SUBSTR(A.BNUM, -4)), 0) +1  COMMNO	 \n");
		sb.append("        FROM kCK_BOARD A 						 \n");

		return sb.toString();
	}

	// 전체 조회 쿼리
	public static String getKboardSelectAllQueryMap() {

		StringBuffer sb = new StringBuffer();

		// 등록일, 수정일은 'YYYY-MM-DD' 형태로 문자열로 변환해서 갖고온다.
		sb.append(" SELECT 								\n");
		sb.append("      A.BNUM 		BNUM 			\n");
		sb.append("		,A.BSUBJECT  	BSUBJECT 		\n");
		sb.append("		,A.BWRITER  	BWRITER 		\n");
		sb.append("		,A.BCONTENTS  	BCONTENTS		\n");
		sb.append("		,A.BPW  		BPW   			\n");
		sb.append("		,A.DELETEYN 	BDELETEYN		\n");
		sb.append("		,TO_CHAR(A.INSERTDATE, 'YYYY-MM-DD')  INSERTDATE	\n");
		sb.append("		,TO_CHAR(A.UPDATEDATE, 'YYYY-MM-DD')  UPDATEDATE	\n");
		sb.append("	FROM 								\n");
		sb.append("		 KCK_BOARD A 					\n");
		sb.append("	WHERE DELETEYN = 'Y'				\n");
		sb.append("	ORDER BY 1 DESC						\n");

		return sb.toString();
	}

	// 글 번호로 조회
	public static String getKboardSelectQueryMap() {

		StringBuffer sb = new StringBuffer();

		// 등록일, 수정일은 'YYYY-MM-DD' 형태로 문자열로 변환해서 갖고온다.
		sb.append(" SELECT 								\n");
		sb.append("      A.BNUM 		BNUM 			\n");
		sb.append("		,A.BSUBJECT  	BSUBJECT 		\n");
		sb.append("		,A.BWRITER  	BWRITER 		\n");
		sb.append("		,A.BCONTENTS  	BCONTENTS		\n");
		sb.append("		,A.BPW  		BPW   			\n");
		sb.append("		,A.DELETEYN 	BDELETEYN		\n");
		sb.append("		,TO_CHAR(A.INSERTDATE, 'YYYY-MM-DD')  INSERTDATE	\n");
		sb.append("		,TO_CHAR(A.UPDATEDATE, 'YYYY-MM-DD')  UPDATEDATE	\n");
		sb.append("	FROM 								\n");
		sb.append("		 KCK_BOARD A 					\n");
		sb.append("	WHERE DELETEYN 		= 'Y'	 		\n");
		sb.append(" AND A.BNUM  		=  ?			\n"); // placeholder 1

		return sb.toString();

	}

	// 글 등록 쿼리
	public static String getKboardInsertQueryMap() {

		StringBuffer sb = new StringBuffer();
		sb.append("	INSERT INTO 						\n");
		sb.append("		KCK_BOARD 				    	\n");
		sb.append("		          (			    		\n");
		sb.append("     			 BNUM 				\n"); // COLUMN 1
		sb.append("				  	,BSUBJECT 			\n"); // COLUMN 2
		sb.append("					,BWRITER 			\n"); // COLUMN 3
		sb.append("					,BCONTENTS			\n"); // COLUMN 4
		sb.append("					,BPW   				\n"); // COLUMN 5
		sb.append("					,DELETEYN			\n"); // COLUMN 6
		sb.append("					,INSERTDATE			\n"); // COLUMN 7
		sb.append("					,UPDATEDATE			\n"); // COLUMN 8
		sb.append("			      )						\n");
		sb.append("	       VALUES (  					\n");
		sb.append("						 ? 				\n");// placeholder 1
		sb.append("						,?   			\n");// placeholder 2
		sb.append("						,?   			\n");// placeholder 3
		sb.append("						,?   			\n");// placeholder 4
		sb.append("						,? 				\n");// placeholder 5
		sb.append("						,'Y'			\n");
		sb.append("						,SYSDATE 		\n");
		sb.append("						,SYSDATE 		\n");
		sb.append("	              )						\n");

		return sb.toString();
	}

	// 글 수정 쿼리
	public static String getKboardUpdateQueryMap() {

		StringBuffer sb = new StringBuffer();

		sb.append("	UPDATE  							\n");
		sb.append("		   KCK_BOARD 			    	\n");
		sb.append("	SET  								\n");
		sb.append("		   BSUBJECT	  		= ?			\n");// placeholder 1
		sb.append("		  ,BCONTENTS		= ?			\n");// placeholder 2
		sb.append("		  ,UPDATEDATE 		= SYSDATE	\n");
		sb.append("	WHERE  BNUM 			= ?			\n");// placeholder 3
		sb.append("	AND    DELETEYN 		= 'Y'  		\n");

		return sb.toString();
	}

	// 글 삭제 쿼리
	public static String getKboardDeleteQueryMap() {

		StringBuffer sb = new StringBuffer();

		sb.append("	UPDATE  							\n");
		sb.append("		   KCK_BOARD 			    	\n");
		sb.append("	SET  								\n");
		sb.append("		   DELETEYN 	= 'N'			\n");
		sb.append("		  ,UPDATEDATE 	= SYSDATE		\n");
		sb.append("	WHERE  BNUM	 		= ?				\n");// placeholder 1
		sb.append("	AND    DELETEYN 	= 'Y'  			\n");

		return sb.toString();
	}

}
