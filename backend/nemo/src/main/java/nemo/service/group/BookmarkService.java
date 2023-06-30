<<<<<<< HEAD
package nemo.service.group;

import nemo.dao.group.BookmarkDAO;

public class BookmarkService {
	BookmarkDAO bookmarkDAO;

	public BookmarkService() {
		bookmarkDAO = new BookmarkDAO();
	}

	// 찜 추가/삭제
	public void toggleBookmark(String user_id, int group_id, Boolean isBookmark) {
		if(isBookmark) {
			bookmarkDAO.deleteBookmark(user_id, group_id);
			
		} else {
			bookmarkDAO.insertBookmark(user_id, group_id);
		}

	} // end of toggleBookmark

}
=======
package nemo.service.group;

import nemo.dao.group.BookmarkDAO;

public class BookmarkService {
	BookmarkDAO bookmarkDAO;

	public BookmarkService() {
		bookmarkDAO = new BookmarkDAO();
	}

	// 찜 추가/삭제
	/*
	public void toggleBookmark(String user_id, int group_id, Boolean isBookmark) {
		if(isBookmark) {
			bookmarkDAO.deleteBookmark(user_id, group_id);
			
		} else {
			bookmarkDAO.insertBookmark(user_id, group_id);
		}

	} */
	// end of toggleBookmark
	
	// 찜 추가/삭제
	public Boolean toggleBookmark(String user_id, int group_id) {
		Boolean isBookmark = bookmarkDAO.isBookmark(user_id, group_id);
		
		if(isBookmark) {
			bookmarkDAO.deleteBookmark(user_id, group_id);
			
		} else {
			bookmarkDAO.insertBookmark(user_id, group_id);
		}

		return !isBookmark;
	} // end of toggleBookmark


}
>>>>>>> 2062e277dc0f7c50a22a1237656803c42da8557d
