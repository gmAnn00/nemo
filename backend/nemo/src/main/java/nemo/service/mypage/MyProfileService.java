package nemo.service.mypage;

import nemo.dao.mypage.MyProfileDAO;
import nemo.vo.user.UserVO;

public class MyProfileService {
	MyProfileDAO myProfileDAO;	
	
	public MyProfileService() {
		myProfileDAO = new MyProfileDAO();	
	}	
	
	//마이페이지 프로필
	public UserVO searchProfileById(String user_id) {
		UserVO userVO = myProfileDAO.searchMyProfileById(user_id);
		return userVO;
	}
	
	//회원정보 수정
	public void modProfile(UserVO userVO) {
		myProfileDAO.modProfile(userVO);		
	}
	//이미지 수정
	public void modUserImg(UserVO userVO) {
		myProfileDAO.modUserimg(userVO);
	}

	
	//회원탈퇴
	public void delMember(String user_id, String delpassword) {
		myProfileDAO.delMember(user_id, delpassword);	
	}
	
}
