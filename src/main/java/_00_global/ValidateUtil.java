package _00_global;

import _00_global.model.User;

public class ValidateUtil {

	/**
	 * 驗證會員訊息格式
	 * 
	 * @param user
	 * @return boolean
	 */
	public boolean validate(User user) {
		if ("".equalsIgnoreCase(user.getUser_cellphone()) || validateBy(user.getUser_cellphone())
				|| "".equalsIgnoreCase(user.getUser_email()) || validateBy(user.getUser_email()))
			return true;
		return false;
	}

	/**
	 * 驗證字串是否是手機、email
	 * 
	 * @param string
	 * @return boolean
	 */
	public boolean validateBy(String string) {
		if (string.matches("09[0-9]{2}-[0-9]{6}") || string.matches("09[0-9]{8}")
				|| string.matches("^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$"))
			return true;
		return false;
	}

	/**
	 * 驗證sql字串，不可包含( @ ' % \ _ = ) 等字元
	 * 
	 * @param string
	 * @return boolean
	 */
	public boolean isValidQueryString(String string) {
		if (string == null || string.length() <= 0 || string.indexOf("@") >= 0 || string.indexOf("'") >= 0
				|| string.indexOf("_") >= 0 || string.indexOf("\"") >= 0 || string.indexOf("%") >= 0
				|| string.indexOf("=") >= 0)
			return false;
		return true;
	}
}
