package com.kosmo.kck.login;

import com.kosmo.kck.member.KckMemberVO;

public class KckLoginServiceImpl implements KckLoginService {

	@Override
	public int kLoginCheck(KckMemberVO kvo) {
		// TODO Auto-generated method stub
		
		System.out.println("KckLoginServiceImpl.kLoginCheck()함수 진입 ");

		KckLoginDAO kdao = new KckLoginDAOImpl();
		return kdao.kLoginCheck(kvo);
		
	}

}
