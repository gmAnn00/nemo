package nemo.service.grpmng;

import java.util.ArrayList;
import java.util.List;

import nemo.dao.grpmng.GroupMangerDAO;
import nemo.vo.group.GroupVO;
import nemo.vo.group.UserVO;

//메소드
public class GroupMangerSettingService {
	UserVO userVO;
	GroupVO groupVO;
	GroupMangerDAO groupMangerDAO;
	
	public GroupMangerSettingService() {
		groupMangerDAO=new GroupMangerDAO();
	}
	
	//그룹 정보
	public List<GroupVO> groupShow(int grp_id) {
		List<GroupVO> groupList = new ArrayList<>();
		groupList= groupMangerDAO.groupShow(grp_id);
		return groupList;
	}
	
	//그룹 멤버 목록
	public List<UserVO> userShow(int group_id) {
		List<UserVO> userList = new ArrayList<>();
		userList=groupMangerDAO.userShow(group_id);
		return userList;
	}
	
	//가입 대기자 목록
	public List<UserVO> approveMemberShow(int group_id) {
		List<UserVO> approveUserList = new ArrayList<>();
		approveUserList=groupMangerDAO.approveMemberShow(group_id);
		return approveUserList;
	}
	
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
