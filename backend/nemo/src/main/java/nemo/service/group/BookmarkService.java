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
