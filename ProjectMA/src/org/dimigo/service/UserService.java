package org.dimigo.service;

import java.sql.Connection;
import java.util.List;

import org.dimigo.dao.UserDao;
import org.dimigo.vo.UserVO;

public class UserService extends AbstractService {
	
	public UserVO login(UserVO user) throws Exception {
		Connection conn = null;
		try {
			conn = getConnection();
			
			UserDao dao = new UserDao(conn);
			UserVO result = dao.searchUser(user);
			
			if(result == null) throw new Exception("아이디나 비밀번호가 바르지 않습니다.");
			
			return result;
			
		} finally {
			if(conn != null) conn.close();
		}
	}
	
	public void signup(UserVO user) throws Exception {
		Connection conn = null;
		try {
			conn = getConnection();
			
			UserDao dao = new UserDao(conn);
			UserVO result = dao.searchUserID(user);
			
			if(result != null) {
				throw new Exception("이미 사용 중인 아이디입니다.");
			}
			dao.insertUser(user);
		} finally {
			if(conn != null) conn.close();
		}
	}
	
}
