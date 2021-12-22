package com.kosmo.kck.login;

public abstract class KckLoginSqlMap {
	
	public static String gethLoginCheckQuery() {
			
		StringBuffer sb = new StringBuffer();	
		
		sb.append(" SELECT 								\n");
		sb.append("       COUNT(A.KNUM)  NCNT 			\n");		
	    sb.append("	FROM 								\n");	    
	    sb.append("		  KCK_MEMBER 	A 				\n");
	    sb.append("	WHERE A.DELETEYN 	= 'Y'			\n");	    
	    sb.append("	AND   A.KID 	  	= ?				\n");
	    sb.append("	AND   A.KPW  		= ?   			\n");

	    return sb.toString();
	    
	}

}
