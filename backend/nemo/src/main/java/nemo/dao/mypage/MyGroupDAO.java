package nemo.dao.mypage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import nemo.vo.group.GroupVO;

public class MyGroupDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;


	public MyGroupDAO() {
		try {
			// 커넥션 풀_ JNDI : 키와 값 방식
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			// DataSource : Servers - context.xml에 있는 resource 부분을 읽어옴
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			// 연결오류 메시지가 난다면 이 메시지가 뜨고 Servers프로젝트 - context.xml확인
			System.out.println("DB연결오류");
		}
	}

	// 소모임장인 소모임 조회 메서드
	public List<GroupVO> getManagerGrpId(String user_id) {
		
		List<GroupVO> mngGroupList = new ArrayList<>();
		
		try {
			conn = dataFactory.getConnection();

			//String query = "select GRP_ID from GROUP_TBL where GRP_MNG = ? ";
			String query = "SELECT A.* FROM GROUP_TBL A INNER JOIN GRPJOIN_TBL B ON A.GRP_ID = B.GRP_ID "
					+ "WHERE B.USER_ID = ? AND A.GRP_MNG =? ";
			
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {								
				
				// ("")안의 값은 컬럼명과 같은지 꼭 확인
				int grp_id = rs.getInt("grp_id");
				String grp_name = rs.getString("grp_name");
				String grp_mng = rs.getString("grp_mng");
				int mem_no = rs.getInt("mem_no");
				String grp_zipcode = rs.getString("grp_zipcode");
				String grp_addr1 = rs.getString("grp_addr1");
				String grp_addr2 = rs.getString("grp_addr2");
				Date create_date = rs.getDate("create_date");
				String grp_intro = rs.getString("grp_intro");
				int app_st = rs.getInt("app_st");
				String main_name = rs.getString("main_name");
				String sub_name = rs.getString("sub_name");
				String grp_img = rs.getString("grp_img");
				
				GroupVO groupVO = new GroupVO(grp_id, grp_name, grp_mng, mem_no, grp_zipcode, grp_addr1, grp_addr2, create_date, grp_intro, app_st, main_name, sub_name, grp_img);
//				groupVO.setGrp_id(grp_id);
//				groupVO.setGrp_name(grp_name);
//				groupVO.setGrp_mng(grp_mng);
//				groupVO.setMem_no(mem_no);
//				groupVO.setGrp_zipcode(grp_zipcode);
//				groupVO.setGrp_addr1(grp_addr1);
//				groupVO.setGrp_addr2(grp_addr2);
//				groupVO.setCreate_date(create_date);
//				groupVO.setGrp_intro(grp_intro);
//				groupVO.setApp_st(app_st);
//				groupVO.setMain_name(main_name);
//				groupVO.setSub_name(sub_name);
//				groupVO.setGrp_img(grp_img);
								
				//System.out.println(grp_id);				
				mngGroupList.add(groupVO);
			}
			rs.close();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("GRPJOIN_TBL DB 조회 중 에러!!");
			e.printStackTrace();
		}

		// System.out.println("DAO" + myGroupVO );
		return mngGroupList;
	}

	// 일반회원 소모임 조회 메서드
	public List<GroupVO> getUserGrpId(String user_id) {		
		List<GroupVO> userGroupList = new ArrayList<>();

		try {
			conn = dataFactory.getConnection();

			// 일반회원인 소모임
			//String query = "select GRP_ID from GRPJOIN_TBL where USER_ID = ? and GRP_ID NOT IN(select GRP_ID from GROUP_TBL where GRP_MNG = ?)";
			// ?에 user_id
			String query = "SELECT A.* FROM GROUP_TBL A INNER JOIN GRPJOIN_TBL B ON A.GRP_ID = B.GRP_ID "
					+ "WHERE B.USER_ID = ? AND A.GRP_MNG !=? ";
			
			System.out.println(query);

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				GroupVO groupVO = new GroupVO();
				
				// ("")안의 값은 컬럼명과 같은지 꼭 확인
				int grp_id = rs.getInt("grp_id");
				String grp_name = rs.getString("grp_name");
				String grp_mng = rs.getString("grp_mng");
				int mem_no = rs.getInt("mem_no");
				String grp_zipcode = rs.getString("grp_zipcode");
				String grp_addr1 = rs.getString("grp_addr1");
				String grp_addr2 = rs.getString("grp_addr2");
				Date create_date = rs.getDate("create_date");
				String grp_intro = rs.getString("grp_intro");
				int app_st = rs.getInt("app_st");
				String main_name = rs.getString("main_name");
				String sub_name = rs.getString("sub_name");
				String grp_img = rs.getString("grp_img");

				groupVO.setGrp_id(grp_id);
				groupVO.setGrp_name(grp_name);
				groupVO.setGrp_mng(grp_mng);
				groupVO.setMem_no(mem_no);
				groupVO.setGrp_zipcode(grp_zipcode);
				groupVO.setGrp_addr1(grp_addr1);
				groupVO.setGrp_addr2(grp_addr2);
				groupVO.setCreate_date(create_date);
				groupVO.setGrp_intro(grp_intro);
				groupVO.setApp_st(app_st);
				groupVO.setMain_name(main_name);
				groupVO.setSub_name(sub_name);
				groupVO.setGrp_img(grp_img);
				
				//list에 세팅
				userGroupList.add(groupVO);
			}
			rs.close();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("GRPJOIN_TBL DB 조회 중 에러!!");
			e.printStackTrace();
		}
		return userGroupList;
	}

	//가입 대기중인 소모임 조회
	public List<GroupVO> getWaitGrpId(String user_id) {		
		List<GroupVO> waitGroupList = new ArrayList<>();

		try {
			conn = dataFactory.getConnection();

			// 대기중인 소모임
			//String query = "select GRP_ID from WAITLIST_TBL where USER_ID = ?";
			// ?에 user_id
			String query = "SELECT A.* FROM GROUP_TBL A INNER JOIN WAITLIST_TBL B ON A.GRP_ID = B.GRP_ID WHERE B.USER_ID = ? ";
			System.out.println(query);

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			//pstmt.setString(2, user_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				GroupVO waitGroupVO = new GroupVO();
				String userGrpId = rs.getString("grp_id");
				// 아래 세팅 메서드에서 세팅
				waitGroupVO = setGroupVO(userGrpId);
				waitGroupList.add(waitGroupVO);
			}
			rs.close();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("GRPJOIN_TBL DB 조회 중 에러!!");
			e.printStackTrace();
		}
		return waitGroupList;
	}
		
		
	// 찜한 소모임 조회 메서드
	public List<GroupVO> getBookMarkGrpId(String user_id) {		
		List<GroupVO> bookmarkGroupList = new ArrayList<>();

		try {
			conn = dataFactory.getConnection();

			// 북마크 조회
			//String query = "select GRP_ID from BOOKMARK_TBL where USER_ID = ?";
			// ?에 user_id
			String query = "SELECT A.* FROM GROUP_TBL A INNER JOIN WAITLIST_TBL B ON A.GRP_ID = B.GRP_ID WHERE B.USER_ID = ? ";
			System.out.println(query);

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id);				
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				GroupVO bookmarkGroupVO = new GroupVO();
				
				String bookmarkGrpId = rs.getString("grp_id");
				// 아래 세팅 메서드에서 세팅
				bookmarkGroupVO = setGroupVO(bookmarkGrpId);
				bookmarkGroupList.add(bookmarkGroupVO);
			}
			rs.close();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("BOOKMARK_TBL DB 조회 중 에러!!");
			e.printStackTrace();
		}
		return bookmarkGroupList;
	}

		
	// 그룹정보 세팅 메서드
	public GroupVO setGroupVO(String grpId) {
		GroupVO groupVO = new GroupVO();
		try {
			conn = dataFactory.getConnection();
			
			// 소모임 정보 검색
			String queryGrp = "select * from GROUP_TBL where GRP_ID = ? ";// ?에 위에서 찾은 grp_id
			System.out.println(queryGrp);
			pstmt = conn.prepareStatement(queryGrp);
			
			pstmt.setString(1, grpId);
			ResultSet rs = pstmt.executeQuery();
			rs.next();

			// ("")안의 값은 컬럼명과 같은지 꼭 확인
			int grp_id = rs.getInt("grp_id");
			String grp_name = rs.getString("grp_name");
			String grp_mng = rs.getString("grp_mng");
			int mem_no = rs.getInt("mem_no");
			String grp_zipcode = rs.getString("grp_zipcode");
			String grp_addr1 = rs.getString("grp_addr1");
			String grp_addr2 = rs.getString("grp_addr2");
			Date create_date = rs.getDate("create_date");
			String grp_intro = rs.getString("grp_intro");
			int app_st = rs.getInt("app_st");
			String main_name = rs.getString("main_name");
			String sub_name = rs.getString("sub_name");
			String grp_img = rs.getString("grp_img");

			groupVO.setGrp_id(grp_id);
			groupVO.setGrp_name(grp_name);
			groupVO.setGrp_mng(grp_mng);
			groupVO.setMem_no(mem_no);
			groupVO.setGrp_zipcode(grp_zipcode);
			groupVO.setGrp_addr1(grp_addr1);
			groupVO.setGrp_addr2(grp_addr2);
			groupVO.setCreate_date(create_date);
			groupVO.setGrp_intro(grp_intro);
			groupVO.setApp_st(app_st);
			groupVO.setMain_name(main_name);
			groupVO.setSub_name(sub_name);
			groupVO.setGrp_img(grp_img);

			rs.close();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("grp 정보 DB 조회 중 에러!!");
			e.printStackTrace();
		}
		return groupVO;
	}
	
}
