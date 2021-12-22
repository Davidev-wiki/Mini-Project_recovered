package com.kosmo.kck.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class KckConnProperty {
	// 상수
	// 데이터베이스 연결 정보 : DataSource : 앞으로는 데이터베이스 연결정보를 데이터소스라고 부른다.
	// 데이터베이스 연결 정보에는 무엇이 있어야 하는가
	// 1. jdbc 드라이버 시작점 네임스페이스
	// 2. 데이터베이스 연결 url
	// 3. 계정명
	// 4. 계정명의 패스워드
	private static final String ORCL_JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String ORCL_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String ORCL_USER = "David";
	private static final String ORCL_PASS = "1234";

	// 데이터베이스 드라이버를 찾고, 연결 까지 하는 함수
	// 추상함수가 아닌 static 일반함수
	// ConnProperty.getConnection() 이케 사용하면 된다.
	public static Connection getConnection() {

		Connection conn = null;

		try {
			// 1. 첫번째 라인에서 ojdbc6.jar 에서 oracle.jdbc.driver.OracleDriver 클래스를 찾아서 메모리에 올려 놓는다. 
			Class.forName(KckConnProperty.ORCL_JDBC_DRIVER);
			
			// 2. 두번째 라인에서 java.sql.Connection 인터페이스를 
			//    상속해서 Oracle Vender에서  jdbc 드라이버를 만드는 팀에서 실현한 oracle.jdbc.driver.T4CConnection@2d38eb89 클래스로 
			//    우리가 제공한 datasource 연결정보를 가지고 Ex_OracleTest_1 클래스와 orclKOSMO00.scott 계정에 연결을 한다. 
			//    두 객체(자바어플 과 데이터베이스)가 연결이 되면 자동커밋(auto commit)으로 세션이 열리게 된다. 
			conn = DriverManager.getConnection(   KckConnProperty.ORCL_URL
												, KckConnProperty.ORCL_USER
												, KckConnProperty.ORCL_PASS);

		} catch (Exception e) {
			System.out.println(
					"ConnProperty :: 데이터베이스를 연결하는데 \n" + "드라이버 또는 연결 과정에서 문제가 생김 >>> : \n" + e.getMessage() + "\n");
		}

		return conn;
	}

	// 연결 해제하는 함수
	// SELECT시 데이터를 가져오기 위해 rsRs를 사용하므로 매개변수 3개
	public static void conClose(Connection conn, PreparedStatement pstmt, ResultSet rsRs) {
		try {
			if(rsRs != null) {try{rsRs.close(); rsRs=null;}catch(Exception e) {}}
			if(pstmt != null) {try{pstmt.close(); pstmt=null;}catch(Exception e) {}}
			if(conn != null) {try{conn.close(); conn=null;}catch(Exception e) {}}		
		} catch(Exception e2){
			System.out.println("예외 발생! : " + e2.getMessage());
		}
	}
	
	// 오버로딩
	// INSERT, UPDATE, DELETE는 리턴(조회한 데이터)이 없으니까 매개변수 2개
	public static void conClose(Connection conn, PreparedStatement pstmt) {
		try {
			if(pstmt != null) {try{pstmt.close(); pstmt=null;}catch(Exception e) {}}
			if(conn != null) {try{conn.close(); conn=null;}catch(Exception e) {}}		
		} catch(Exception e2){
			System.out.println("예외 발생 : " + e2.getMessage());
		}
	}
}
