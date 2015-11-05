package wang.gagalulu.lovehouse.util;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;


public class TestUtil {
	public static void main(String[] args) {
		Date d = new Date();
		String s = DigestUtils.sha1Hex(d.getTime()+"");
		System.out.println(d.getTime());
		System.out.println(s);
	}
}
