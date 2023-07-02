package nemo.dao.common;

import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import nemo.vo.group.GroupVO;
import nemo.vo.group.KaKaoGeoRes;
import nemo.vo.user.InterestsVO;
import nemo.vo.user.UserVO;

public class IndexDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public IndexDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");

		} catch (Exception e) {
			System.out.println("IndexDAO: DB 연결 오류");
			e.printStackTrace();
		}
	} // end of constructor

	public UserVO findUserById(String user_id) {
		UserVO userVO = new UserVO();
		try {
			conn = dataFactory.getConnection();
			String query = "select * from user_tbl where user_id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			user_id = rs.getString("user_id");
			String password = rs.getString("password");
			String user_name = rs.getString("user_name");
			String nickname = rs.getString("nickname");
			String zipcode = rs.getString("zipcode");
			String user_addr1 = rs.getString("user_addr1");
			String user_addr2 = rs.getString("user_addr2");
			String phone = rs.getString("phone");
			String email = rs.getString("email");
			Date join_date = rs.getDate("join_date");
			Date birthdate = rs.getDate("birthdate");
			String user_img = rs.getString("user_img");
			int admin = rs.getInt("admin");
	
			userVO.setUser_id(user_id);
			userVO.setPassword(password);
			userVO.setUser_name(user_name);
			userVO.setNickname(nickname);
			userVO.setZipcode(zipcode);
			userVO.setUser_addr1(user_addr1);
			userVO.setUser_addr2(user_addr2);
			userVO.setPhone(phone);
			userVO.setEmail(email);
			userVO.setJoin_date(join_date);
			userVO.setBirthdate(birthdate);
			userVO.setUser_img(user_img);
			userVO.setAdmin(admin);	
							
			//System.out.println("DAO내부" + myProVO );
			rs.close();
			pstmt.close();
			conn.close();		
			
			
		} catch (Exception e) {
			System.out.println("IndexDAO:findUserById 오류");
			e.printStackTrace();
		}
		
		return userVO;
	}
	

	public List<GroupVO> findRandomGroup() {
		List<GroupVO> groupList = new ArrayList<GroupVO>();
		
		try {
			conn = dataFactory.getConnection();
			String query = "select * from "
					+ "(select * from group_tbl order by dbms_random.value) "
					+ "where rownum <=4";
			pstmt = conn.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				GroupVO groupVO = new GroupVO();
				groupVO.setGrp_id(rs.getInt("grp_id"));
				groupVO.setGrp_name(rs.getString("grp_name"));
				groupVO.setGrp_mng(rs.getString("grp_mng"));
				groupVO.setMem_no(rs.getInt("mem_no"));
				groupVO.setGrp_zipcode(rs.getString("grp_zipcode"));
				groupVO.setGrp_addr1(rs.getString("grp_addr1"));
				groupVO.setGrp_addr2(rs.getString("grp_addr2"));
				groupVO.setCreate_date(rs.getDate("create_date"));
				groupVO.setGrp_intro(rs.getString("grp_intro"));
				groupVO.setApp_st(rs.getInt("app_st"));
				groupVO.setMain_name(rs.getString("main_name"));
				groupVO.setSub_name(rs.getString("sub_name"));
				groupVO.setGrp_img(rs.getString("grp_img"));
				
				groupList.add(groupVO);
			}
			
			
		} catch (Exception e) {
			System.out.println("IndexDAO:findUserById 오류");
			e.printStackTrace();
		}
		
		return groupList;
	}

	
	public List<InterestsVO> findInterests(String user_id) {
		List<InterestsVO> interestsList = new ArrayList<InterestsVO>();
		
		try {
			conn = dataFactory.getConnection();
			String query = "select * from interests_tbl where user_id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				InterestsVO interestsVO = new InterestsVO();
				interestsVO.setUser_id(user_id);
				interestsVO.setMain_name(rs.getString("main_name"));
				interestsVO.setSub_name(rs.getString("sub_name"));
				
				interestsList.add(interestsVO);
			}
			
			
		} catch (Exception e) {
			System.out.println("IndexDAO: findInterests 오류");
			e.printStackTrace();
		}
		
		return interestsList;
	}

	
	public List<GroupVO> findInterestGroups(List<InterestsVO> interestsList) {
		List<GroupVO> groupsList = new ArrayList<GroupVO>();
		
		try {
			conn = dataFactory.getConnection();
			
			for(InterestsVO interests : interestsList) {
				String query = "select * from "
						+ "(select * from group_tbl where main_name = ? OR sub_name = ? order by dbms_random.value) "
						+ "where rownum <= 4";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, interests.getMain_name());
				pstmt.setString(2, interests.getSub_name());
				
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					GroupVO groupVO = new GroupVO();

					groupVO.setGrp_id(rs.getInt("grp_id"));
					groupVO.setGrp_name(rs.getString("grp_name"));
					groupVO.setGrp_mng(rs.getString("grp_mng"));
					groupVO.setMem_no(rs.getInt("mem_no"));
					groupVO.setGrp_zipcode(rs.getString("grp_zipcode"));
					groupVO.setGrp_addr1(rs.getString("grp_addr1"));
					groupVO.setGrp_addr2(rs.getString("grp_addr2"));
					groupVO.setCreate_date(rs.getDate("create_date"));
					groupVO.setGrp_intro(rs.getString("grp_intro"));
					groupVO.setApp_st(rs.getInt("app_st"));
					groupVO.setMain_name(rs.getString("main_name"));
					groupVO.setSub_name(rs.getString("sub_name"));
					groupVO.setGrp_img(rs.getString("grp_img"));
					
					groupsList.add(groupVO);
				}
			}
			
			Collections.shuffle(groupsList);
			if(groupsList.size() > 4) {
				groupsList = groupsList.subList(0, 4);
			}
			
			
			
		} catch (Exception e) {
			System.out.println("IndexDAO: findInterests 오류");
			e.printStackTrace();
		}
		
		return groupsList;
	}

	
	public List<GroupVO> findNearGroups(String user_addr1) {
		List<GroupVO> groupsList = new ArrayList<GroupVO>();
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT * FROM group_tbl";
			pstmt = conn.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				GroupVO groupVO = new GroupVO();

				groupVO.setGrp_id(rs.getInt("grp_id"));
				groupVO.setGrp_name(rs.getString("grp_name"));
				groupVO.setGrp_mng(rs.getString("grp_mng"));
				groupVO.setMem_no(rs.getInt("mem_no"));
				groupVO.setGrp_zipcode(rs.getString("grp_zipcode"));
				groupVO.setGrp_addr1(rs.getString("grp_addr1"));
				groupVO.setGrp_addr2(rs.getString("grp_addr2"));
				groupVO.setCreate_date(rs.getDate("create_date"));
				groupVO.setGrp_intro(rs.getString("grp_intro"));
				groupVO.setApp_st(rs.getInt("app_st"));
				groupVO.setMain_name(rs.getString("main_name"));
				groupVO.setSub_name(rs.getString("sub_name"));
				groupVO.setGrp_img(rs.getString("grp_img"));
				
				groupsList.add(groupVO);
			}
			
			String APIKey = "KakaoAK c73306afc68803d77548f1b3dea5d5c2";
			double userLatdbl = 0.0;
			double userLngdbl = 0.0;
			
			try {
				
				String address = user_addr1;
				String apiURL = "https://dapi.kakao.com/v2/local/search/address.json?query=" 
	                    + URLEncoder.encode(address, "UTF-8");
				
				HttpResponse<JsonNode> response = Unirest.get(apiURL).header("Authorization", APIKey).asJson();
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
				
				KaKaoGeoRes bodyJson = objectMapper.readValue(response.getBody().toString(), KaKaoGeoRes.class);
			
				if(bodyJson.getDocuments().size() != 0) {
					userLatdbl = bodyJson.getDocuments().get(0).getY();
					userLngdbl = bodyJson.getDocuments().get(0).getX();
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			for(int i = groupsList.size()-1; i>=0; i--) {
				
				try {
					GroupVO group = (GroupVO) groupsList.get(i);
					String address = group.getGrp_addr1();
					String apiURL = "https://dapi.kakao.com/v2/local/search/address.json?query=" 
		                    + URLEncoder.encode(address, "UTF-8");
					
					HttpResponse<JsonNode> response = Unirest.get(apiURL).header("Authorization", APIKey).asJson();
					ObjectMapper objectMapper = new ObjectMapper();
					objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
					
					KaKaoGeoRes bodyJson = objectMapper.readValue(response.getBody().toString(), KaKaoGeoRes.class);
				
					double lat = 0.0;
					double lng = 0.0;
					
					if(bodyJson.getDocuments().size() > 0) {
						System.out.println("bodyJson="+bodyJson.getDocuments().size());
						lat = bodyJson.getDocuments().get(0).getY();
						lng = bodyJson.getDocuments().get(0).getX();
					}
					
					
					System.out.println("lat = " + lat);
					System.out.println("lng = " + lng);
					
					double dLat = Math.toRadians(userLatdbl - lat);
					double dLng = Math.toRadians(userLngdbl - lng);
					
					double temp1 = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(Math.toRadians(lat))*
							Math.cos(Math.toRadians(userLatdbl/2))*Math.sin(dLng/2)*Math.sin(dLng/2);
					double temp2 = 2 * Math.atan2(Math.sqrt(temp1), Math.sqrt(1-temp1));
					double dist = 6371 * temp2 * 1000;
					System.out.println("dist="+ dist);
					
					if(dist > 2000) {
						groupsList.remove(i);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			} // end of for
			
			
			Collections.shuffle(groupsList);
			if(groupsList.size() > 4) {
				groupsList = groupsList.subList(0, 4);
			}

			
		} catch (Exception e) {
			System.out.println("IndexDAO: findInterests 오류");
			e.printStackTrace();
		}
		
		return groupsList;
	}
	
	
	
	
}
