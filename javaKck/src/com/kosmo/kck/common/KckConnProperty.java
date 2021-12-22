package com.kosmo.kck.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class KckConnProperty {
	// ���
	// �����ͺ��̽� ���� ���� : DataSource : �����δ� �����ͺ��̽� ���������� �����ͼҽ���� �θ���.
	// �����ͺ��̽� ���� �������� ������ �־�� �ϴ°�
	// 1. jdbc ����̹� ������ ���ӽ����̽�
	// 2. �����ͺ��̽� ���� url
	// 3. ������
	// 4. �������� �н�����
	private static final String ORCL_JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String ORCL_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String ORCL_USER = "David";
	private static final String ORCL_PASS = "1234";

	// �����ͺ��̽� ����̹��� ã��, ���� ���� �ϴ� �Լ�
	// �߻��Լ��� �ƴ� static �Ϲ��Լ�
	// ConnProperty.getConnection() ���� ����ϸ� �ȴ�.
	public static Connection getConnection() {

		Connection conn = null;

		try {
			// 1. ù��° ���ο��� ojdbc6.jar ���� oracle.jdbc.driver.OracleDriver Ŭ������ ã�Ƽ� �޸𸮿� �÷� ���´�. 
			Class.forName(KckConnProperty.ORCL_JDBC_DRIVER);
			
			// 2. �ι�° ���ο��� java.sql.Connection �������̽��� 
			//    ����ؼ� Oracle Vender����  jdbc ����̹��� ����� ������ ������ oracle.jdbc.driver.T4CConnection@2d38eb89 Ŭ������ 
			//    �츮�� ������ datasource ���������� ������ Ex_OracleTest_1 Ŭ������ orclKOSMO00.scott ������ ������ �Ѵ�. 
			//    �� ��ü(�ڹپ��� �� �����ͺ��̽�)�� ������ �Ǹ� �ڵ�Ŀ��(auto commit)���� ������ ������ �ȴ�. 
			conn = DriverManager.getConnection(   KckConnProperty.ORCL_URL
												, KckConnProperty.ORCL_USER
												, KckConnProperty.ORCL_PASS);

		} catch (Exception e) {
			System.out.println(
					"ConnProperty :: �����ͺ��̽��� �����ϴµ� \n" + "����̹� �Ǵ� ���� �������� ������ ���� >>> : \n" + e.getMessage() + "\n");
		}

		return conn;
	}

	// ���� �����ϴ� �Լ�
	// SELECT�� �����͸� �������� ���� rsRs�� ����ϹǷ� �Ű����� 3��
	public static void conClose(Connection conn, PreparedStatement pstmt, ResultSet rsRs) {
		try {
			if(rsRs != null) {try{rsRs.close(); rsRs=null;}catch(Exception e) {}}
			if(pstmt != null) {try{pstmt.close(); pstmt=null;}catch(Exception e) {}}
			if(conn != null) {try{conn.close(); conn=null;}catch(Exception e) {}}		
		} catch(Exception e2){
			System.out.println("���� �߻�! : " + e2.getMessage());
		}
	}
	
	// �����ε�
	// INSERT, UPDATE, DELETE�� ����(��ȸ�� ������)�� �����ϱ� �Ű����� 2��
	public static void conClose(Connection conn, PreparedStatement pstmt) {
		try {
			if(pstmt != null) {try{pstmt.close(); pstmt=null;}catch(Exception e) {}}
			if(conn != null) {try{conn.close(); conn=null;}catch(Exception e) {}}		
		} catch(Exception e2){
			System.out.println("���� �߻� : " + e2.getMessage());
		}
	}
}
