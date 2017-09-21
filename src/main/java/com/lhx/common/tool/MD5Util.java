package com.lhx.common.tool;

/**
 * MD5加密
 */
public class MD5Util {

	/**
	 * MD5加密
	 * 
	 * @param msg
	 * @return
	 * @throws MD5Exception
	 */
	public static String getMD5(String msg) throws MD5Exception {
		if (msg == null) {
			throw new MD5Exception("Message for md5 calculation is null.");
		}

		byte[] source = msg.getBytes();

		String s = null;
		char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
				'e', 'f' };
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，

			char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，表示成 16 进制需要 32 个字符

			int k = 0; // 表示转换结果中对应的字符位置

			for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
				// 转换成 16 进制字符的转换
				byte byte0 = tmp[i]; // 取第 i 个字节
				str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
				// >>> 为逻辑右移，将符号位一起右移
				str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
			}
			s = new String(str); // 换后的结果转换为字符串
		}
		catch (Exception e) {
			throw new MD5Exception("Calculate MD5 failed.", e);
		}

		return s;
	}

	public static class MD5Exception extends Exception {

		/**
		 *
		 */
		private static final long serialVersionUID = 1L;

		public MD5Exception() {
			super();
		}

		public MD5Exception(String message) {
			super(message);
		}

		public MD5Exception(String message, Throwable cause) {
			super(message, cause);
		}

		public MD5Exception(Throwable cause) {
			super(cause);
		}

	}
}
