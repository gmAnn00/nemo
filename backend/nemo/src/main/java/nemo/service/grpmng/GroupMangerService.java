package nemo.service.grpmng;

import nemo.dao.grpmng.GroupMangerDAO;
import nemo.vo.group.GroupVO;
import nemo.vo.group.UserVO;

//메소드
public class GroupMangerService {
	UserVO userVO;
	GroupVO groupVO;
	GroupMangerDAO groupMangerDAO;
	
	public GroupMangerService() {
		groupMangerDAO=new GroupMangerDAO();
	}
	
	//그룹 정보
	
	//그룹 멤버 목록
	
	//가입 대기자 목록
	
	//승인 메소드
	public void approveUser(UserVO userVO) {
		groupMangerDAO.approveUser(userVO);
	}
	
	//승인 후 리스트 업데이트
	public void approveListUpdate() {
		groupMangerDAO.approveListUpdate();
	}
	
	//거절 메소드
	public void rejectUser(UserVO userVO) {
		groupMangerDAO.mandateGroupManager(userVO);
	}
	
	//추방 메소드
	public void exileGroupMember(UserVO userVO) {
		groupMangerDAO.exileGroupMember(userVO);
	}
	
	//위임 메소드
	public void mandateGroupManager(UserVO userVO) {
		groupMangerDAO.mandateGroupManager(userVO);
	}
	
	//게시글 삭제 메소드
	
	//댓글 삭제 메소드
}
