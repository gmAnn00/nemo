package nemo.service.grpmng;

import java.util.ArrayList;
import java.util.List;

import nemo.dao.grpmng.GroupMangerDAO;
import nemo.vo.group.GroupVO;
import nemo.vo.user.UserVO;

//메소드
public class GroupMangerService {
	UserVO userVO;
	GroupVO groupVO;
	GroupMangerDAO groupMangerDAO;
	
	public GroupMangerService() {
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
	public void approveListUpdate(int grp_id) {
		groupMangerDAO.approveListUpdate(grp_id);
	}
	
	//거절 메소드
	public void rejectUser(UserVO userVO, int grp_id) {
		groupMangerDAO.rejectUser(userVO, grp_id);
	}
	
	//추방 메소드
	public void exileGroupMember(UserVO userVO, int grp_id) {
		groupMangerDAO.exileGroupMember(userVO, grp_id);
	}
	
	//위임 메소드
	public void mandateGroupManager(UserVO userVO, int grp_id) {
		groupMangerDAO.mandateGroupManager(userVO, grp_id);
	}
	
	//소모임 삭제
	public void deleteGroup(int grp_id) {
		groupMangerDAO.deleteGroup(grp_id);
	}
	
}
