package wang.gagalulu.lovehouse.util;

import java.io.UnsupportedEncodingException;

public class StringUtil {
	public static String getStringUTF8(String instr){
		String outStr = "";
		try {
			outStr = new String(instr.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return outStr;
	}
}
