package nemo.service.mypage;

import nemo.dao.mypage.DuplicateDAO;

public class DuplicateService {
	DuplicateDAO duplDAO;
	
	public DuplicateService() {
		duplDAO = new DuplicateDAO();
	}
	//아이디 중복확인 메서드
	public boolean overlappedID(String user_id) {
		boolean result = false;
		result = duplDAO.overlappedID(user_id);
		return result;
	}
	
	//닉네임 중복확인 메서드
	public boolean overlappedNn(String nickname) {
		boolean result = false;
		result = duplDAO.overlappedNickname(nickname);
		return result;
	}
	
	//이메일 중복확인 메서드
	public boolean overlappedEmail(String email) {
		boolean result = false;
		result = duplDAO.overlappedEmail(email);
		return result;
	}
		
}
