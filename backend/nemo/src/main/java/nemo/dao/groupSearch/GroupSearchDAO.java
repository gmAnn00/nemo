package nemo.dao.groupSearch;

import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

public class GroupSearchDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	GroupVO gruopVO;

	public GroupSearchDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");

		} catch (Exception e) {
			System.out.println("SearchDAO: DB 연결 오류");
			e.printStackTrace();
		}
	}

	public List<Map> search(Map searchMap) {
		List<Map> resultList = new ArrayList<Map>();
		List<Map> resultListSlice = new ArrayList<Map>();
		Map resultMap;

		try {
			conn = dataFactory.getConnection();

			String user_id = (String) searchMap.get("user_id");
			String searchText = (String) searchMap.get("searchText");
			String main_name = (String) searchMap.get("main_name");
			String sub_name = (String) searchMap.get("sub_name");
			String joinAble = (String) searchMap.get("joinAble");
			String sort = (String) searchMap.get("sort");
			int areaBar = (Integer) searchMap.get("areaBar");
			String userLat = (String) searchMap.get("userLat");
			String userLng = (String) searchMap.get("userLng");
			int section = (int)searchMap.get("section");
			int pageNum = (int)searchMap.get("pageNum"); 

			System.out.println("DAO searchText=" + searchText);
			System.out.println("DAO main_name=" + main_name);
			System.out.println("DAO sub_name=" + sub_name);
			System.out.println("DAO joinAble=" + joinAble);
			System.out.println("DAO sort=" + sort);
			System.out.println("DAO areaBar=" + areaBar);
			System.out.println("DAO userLat=" + userLat);
			System.out.println("DAO userLng=" + userLng);
			System.out.println("DAO section=" + section);
			System.out.println("DAO pageNum=" + pageNum);

			String query = "";
			if(sub_name.equals("none")) {
				query += "SELECT * FROM group_tbl WHERE grp_name LIKE ? order by grp_name asc";
				pstmt = conn.prepareStatement(query);
				String searchTexts = "%" + searchText + "%";
				pstmt.setString(1, searchTexts);
			}else {
				query += "SELECT * FROM group_tbl WHERE grp_name LIKE ? AND sub_name = ? order by grp_name asc";
				pstmt = conn.prepareStatement(query);
				String searchTexts = "%" + searchText + "%";
				pstmt.setString(1, searchTexts);
				pstmt.setString(2, sub_name);
			}
			
			/*
			if(sub_name.equals("none")) {
				query += "SELECT * from"
						+ " (SELECT ROWNUM as recNum, a.* FROM"
						+ " (SELECT ROWNUM, g.*"
						+ " FROM group_tbl g where grp_name LIKE ? ) a)"
						+ " WHERE recNum BETWEEN (?-1)*100+(?-1)*10+1 AND (?-1)*100+?*10";
				pstmt = conn.prepareStatement(query);
				String searchTexts = '%' + searchText + '%';
				pstmt.setString(1, searchTexts);
				pstmt.setInt(2, section);
				pstmt.setInt(3, pageNum);
				pstmt.setInt(4, section);
				pstmt.setInt(5, pageNum);
				
			}else {
				query += "SELECT * from"
						+ " (SELECT ROWNUM as recNum, a.* FROM"
						+ " (SELECT ROWNUM, g.*"
						+ " FROM group_tbl g where grp_name LIKE ? AND sub_name=?) a)"
						+ " WHERE recNum BETWEEN (?-1)*100+(?-1)*10+1 AND (?-1)*100+?*10";
				pstmt = conn.prepareStatement(query);
				String searchTexts = '%' + searchText + '%';
				pstmt.setString(1, searchTexts);
				pstmt.setString(2, sub_name);
				pstmt.setInt(3, section);
				pstmt.setInt(4, pageNum);
				pstmt.setInt(5, section);
				pstmt.setInt(6, pageNum);
			}
			*/

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				resultMap = new HashMap();
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

				int groupMemberNum = groupMemberNum(groupVO.getGrp_id());
				int bookmarkNum = bookmarkNum(groupVO.getGrp_id());
				boolean isBookmark = isBookmark(user_id, groupVO.getGrp_id());
				boolean isFull = isFull(groupVO.getGrp_id());

				resultMap.put("isBookmark", isBookmark);
				resultMap.put("groupVO", groupVO);
				resultMap.put("groupMemberNum", groupMemberNum);
				resultMap.put("bookmarkNum", bookmarkNum);
				resultMap.put("isFull", isFull);
				resultList.add(resultMap);
				// System.out.println(groupVO.toString());
				// System.out.println("groupMemberNum=" + groupMemberNum);
				// System.out.println("bookmarkNum="+bookmarkNum);

			}

			rs.close();
			pstmt.close();
			conn.close();
			
			// 찜순, 이름순 정렬
			if(sort.equals("sortByBookmark")) {
				resultList.sort(
						Comparator.comparing((Map map) -> (Integer)map.get("bookmarkNum")).reversed()
						);
			}else if(sort.equals("sortByNumber")) {
				resultList.sort(
						Comparator.comparing((Map map) -> (Integer)map.get("groupMemberNum")).reversed()
						);
			}
			
			// 가입가능 필터
			if(joinAble.equals("on")) {
				for(int i = resultList.size()-1; i>=0; i--) {
					if((boolean)resultList.get(i).get("isFull") == true) {
						resultList.remove(i);
					}
				}
			}
			
			// 거리 필터
			if(areaBar != -1 && userLat != null && userLng != null) {
				double userLatdbl = Double.parseDouble(userLat);
				double userLngdbl = Double.parseDouble(userLng);
				
				String APIKey = "KakaoAK c73306afc68803d77548f1b3dea5d5c2";    

				HashMap<String, Object> map = new HashMap<>(); //결과를 담을 map

				for(int i = resultList.size()-1; i>=0; i--) {
					try {
						GroupVO groupVO = (GroupVO) resultList.get(i).get("groupVO");
						String address = groupVO.getGrp_addr1();
						String apiURL = "https://dapi.kakao.com/v2/local/search/address.json?query=" 
			                    + URLEncoder.encode(address, "UTF-8");
						
						HttpResponse<JsonNode> response = Unirest.get(apiURL).header("Authorization", APIKey).asJson();
						ObjectMapper objectMapper = new ObjectMapper();
						objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
						
						KaKaoGeoRes bodyJson = objectMapper.readValue(response.getBody().toString(), KaKaoGeoRes.class);
					
						double lat = bodyJson.getDocuments().get(0).getY();
						double lng = bodyJson.getDocuments().get(0).getX();
						
						System.out.println("lat = " + lat);
						System.out.println("lng = " + lng);
						
						double dLat = Math.toRadians(userLatdbl - lat);
						double dLng = Math.toRadians(userLngdbl - lng);
						
						double temp1 = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(Math.toRadians(lat))*
								Math.cos(Math.toRadians(userLatdbl/2))*Math.sin(dLng/2)*Math.sin(dLng/2);
						double temp2 = 2 * Math.atan2(Math.sqrt(temp1), Math.sqrt(1-temp1));
						double dist = 6371 * temp2 * 1000;
						System.out.println("dist="+ dist);
						
						if(dist > areaBar*1000) {
							resultList.remove(i);
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					
				} // end of for
				
				
				
			} // end of if(areaBar != -1 && userLat != null && userLng != null)
			
			
			// 페이징 처리
			int total = resultList.size();
			int start = (section-1)*100+(pageNum-1)*10;
			int cnt = 10;
			
			if(section > total/100 && pageNum > (total/100)/10 + 1) {
				// 마지막 섹션의 마지막 페이지일때
				cnt = total%10;
			}else {
				cnt = 10;
			}

			System.out.println("resultList 개수="+resultList.size());
			resultListSlice = resultList.stream().skip(start).limit(cnt).collect(Collectors.toList());
			System.out.println("resultListSlice 개수=" + resultListSlice.size());
			

		} catch (Exception e) {
			System.out.println("SearchDAO: search 오류");
			e.printStackTrace();
		}


		return resultListSlice;

	}

	public int groupMemberNum(int group_id) {
		int result = 0;
		try {
			conn = dataFactory.getConnection();

			String query = "select count(j.grp_id) cnt from group_tbl g, grpjoin_tbl j where g.grp_id = j.grp_id and g.grp_id=? group by j.grp_id";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, group_id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

			rs.close();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("SearchDAO: groupMemberNum 오류");
			e.printStackTrace();
		}

		return result;

	}

	public int bookmarkNum(int group_id) {
		int result = 0;
		try {
			conn = dataFactory.getConnection();

			String query = "select count(b.grp_id) cnt from group_tbl g, bookmark_tbl b where g.grp_id = b.grp_id and g.grp_id=? group by b.grp_id";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, group_id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

			rs.close();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("SearchDAO: bookmarkNum 오류");
			e.printStackTrace();
		}

		return result;

	}

	public boolean isBookmark(String user_id, int group_id) {
		boolean result = false;
		try {
			conn = dataFactory.getConnection();
			String query = "select decode(count(*) , 1, 'true', 'false') as result "
					+ "from bookmark_tbl where user_id=? and grp_id=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			pstmt.setInt(2, group_id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			result = Boolean.parseBoolean(rs.getString("result"));
			//System.out.println("result = " + result);

			rs.close();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("isInterest: 중 오류");
			e.printStackTrace();
		}

		return result;
	}

	// 소모임이 꽉 차있으면 true, 아니면 false 반환
	public boolean isFull(int group_id) {
		int groupNum = -1;
		int maxNum = -2;
		String query;

		try {
			conn = dataFactory.getConnection();
			query = "select count(*) as cnt from grpjoin_tbl where grp_id=?";
			//System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, group_id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			groupNum = rs.getInt("cnt");

			rs.close();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("isFull 중 오류");
			e.printStackTrace();
		}

		try {
			conn = dataFactory.getConnection();
			query = "select mem_no from group_tbl where grp_id=?";
			//System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, group_id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			maxNum = rs.getInt("mem_no");

			rs.close();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("isFull 중 오류");
			e.printStackTrace();
		}

		if (groupNum == maxNum) {
			return true;
		} else {
			return false;
		}

	} // end of isFull

	public int findTotGroup(Map searchMap) {
		int totGroup = 0;
		
		try {
			conn = dataFactory.getConnection();

			String user_id = (String) searchMap.get("user_id");
			String searchText = (String) searchMap.get("searchText");
			String main_name = (String) searchMap.get("main_name");
			String sub_name = (String) searchMap.get("sub_name");
			String joinAble = (String) searchMap.get("joinAble");
			String sort = (String) searchMap.get("sort");
			int section = (int)searchMap.get("section");
			int pageNum = (int)searchMap.get("pageNum"); 

			System.out.println("DAO searchText=" + searchText);
			System.out.println("DAO main_name=" + main_name);
			System.out.println("DAO sub_name=" + sub_name);
			System.out.println("DAO joinAble=" + joinAble);
			System.out.println("DAO sort=" + sort);
			System.out.println("DAO section=" + section);
			System.out.println("DAO pageNum=" + pageNum);
			
			String query = "SELECT count(*) FROM group_tbl WHERE grp_name LIKE ?";
			if(sub_name.equals("none")) {
				pstmt = conn.prepareStatement(query);
				String searchTexts = "%" + searchText + "%";
				pstmt.setString(1, searchTexts);
				
			}else {
				query += "AND sub_name=?";
				pstmt = conn.prepareStatement(query);
				String searchTexts = "%" + searchText + "%";
				pstmt.setString(1, searchTexts);
				pstmt.setString(2, sub_name);

			}
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			totGroup = rs.getInt(1);
			
			rs.close();
			pstmt.close();
			conn.close();
			
			
		} catch (Exception e) {
			System.out.println("findTotGroup 중 오류");
			e.printStackTrace();
		}
		
		return totGroup;
	}


}

