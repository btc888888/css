package com.lwoptl.common.kit;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.lwoptl.common.util.IdWorker;
import com.lwoptl.common.util.UUidUtiles;

/**
 * UUID主键工具类
 * 
 * @author yujie.li
 *
 */
public class IdKit {

	/**
	 * 32位的UUID值
	 * 
	 * @return
	 * @author yujie.li
	 * @date 2018年7月27日
	 */
	public static String createUUID() {
		return UUidUtiles.getInstance().createUUID();
	}

	/**
	 * 根据前缀名自动生成ID
	 * 
	 * @param prefixName
	 * @param maxValue
	 * @param maxLength
	 * 
	 * @return abc00000000000101
	 */
	public static String makeUid(String prefixName, Long maxValue, int maxLength) {
		int num = prefixName.length();
		Long num2 = maxValue + 1L;
		int num3 = num2.toString().length();
		int num4 = maxLength - num - num3;
		String str = "";
		for (int i = 0; i < num4; i++) {
			str = str + "0";
		}
		return prefixName + str + num2;
	}

	private static int sequence = 0;

	private static int length = 5;

	/**
	 * YYYYMMDDHHmmss+5位自增长码(19位)Long的最大长度为19位
	 * 
	 * @author yujie.li
	 * @return
	 */
	public static synchronized String getLocalTrmSeqNum() {
		sequence = sequence >= 999999 ? 1 : sequence + 1;
		String datetime = new SimpleDateFormat("yyyyMMddHHmmss")
				.format(new Date());
		String s = Integer.toString(sequence);
		return datetime + addLeftZero(s, length);
	}

	/**
	 * 获取最大的自增长码
	 * 
	 * @return 00007
	 * @author yujie.li
	 * @date 2018年8月6日
	 */
	public static synchronized String getSeqNum() {
		sequence = sequence >= 999999 ? 1 : sequence + 1;
		String s = Integer.toString(sequence);
		return addLeftZero(s, length);
	}

	/**
	 * 左填0
	 * 
	 * @author yujie.li
	 * @param s
	 * @param length
	 * @return
	 */
	public static String addLeftZero(String s, int length) {
		int old = s.length();
		if (length > old) {
			char[] c = new char[length];
			char[] x = s.toCharArray();
			if (x.length > length) {
				throw new IllegalArgumentException(
						"Numeric value is larger than intended length: " + s
								+ " LEN " + length);
			}
			int lim = c.length - x.length;
			for (int i = 0; i < lim; i++) {
				c[i] = '0';
			}
			System.arraycopy(x, 0, c, lim, x.length);
			return new String(c);
		}
		return s.substring(0, length);

	}

	/**
	 * 创建上传文件名称Id
	 * 
	 * @return 18080631100010
	 * @author yujie.li
	 * @date 2018年8月6日
	 */
	public static String createFileId() {
		String id = getLocalTrmSeqNum();
		return id.substring(2);
	}

	/**
	 * 分布式自增长ID
	 * 
	 * @return
	 * @author yujie.li
	 * @date 2018年8月6日
	 */
	public static long createIdWorker() {
		return IdWorker.id.nextId();
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println("uuid-------->" + createUUID());
			System.out.println("自增id------>" + getLocalTrmSeqNum());
			Long l = Long.valueOf(getSeqNum());
			System.out.println("前缀自增Id-->" + makeUid("abc", l, (short) 13));
			System.out.println("文件名称Id-->" + createFileId());
			System.out.println("分布式自增Id>" + createIdWorker());
		}
	}
}
