package wang.gagalulu.lovehouse.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegUtil {
	private RegUtil(){}
	private static Object synObj = new Object();
	private static RegUtil instance;
	public static RegUtil getInstance(){
		if(instance==null){
			synchronized (synObj) {
				if(instance==null){
					instance = new RegUtil();
				}
			}
		}
		return instance;
	}
	public List<Map<String,String>> regTest(String regEx,String str,String[] keys){
        Pattern pattern = Pattern.compile(regEx,Pattern.CASE_INSENSITIVE|Pattern.DOTALL);  
        Matcher matcher = pattern.matcher(str); 
        List<Map<String,String>> returnList = new ArrayList<Map<String,String>>();
        while(matcher.find()){
        	int groupLen = matcher.groupCount();
        	Map<String,String> resultMap = new HashMap<String,String>();
        	for(int i=0;i<keys.length;i++){
        		String key = keys[i];
        		if(i<groupLen){
        			resultMap.put(key, matcher.group(i+1));
        		}
        	}
        	returnList.add(resultMap);
        }
        return returnList;
	}
	
	public RegUtil filterList(List<Map<String,String>> list ,String[] regExs,String[] repstrs){
		for(int i=0;i<list.size();i++){
			Map<String,String> resultMap = list.get(i);
			Iterator<String> it = resultMap.keySet().iterator();
			while(it.hasNext()){
				String key = it.next();
				String value = resultMap.get(key);
				for(int k=0;k<regExs.length;k++){
					value=value.replaceAll(regExs[k], repstrs[k]);
				}
				resultMap.put(key, value);
			}
		}
		return this;
	}
	
	public static void main(String[] args) {
//		HttpUtil httpUtil = HttpUtil.getInstance();
//		HttpBean httpBean = httpUtil.parseHttpBeanByUrl("http://www.neihan8.com/article/","gbk");
//		String txt = httpBean.getContent();
//		System.out.println(txt);
		RegUtil regUtil = RegUtil.getInstance();
//		List<String[]> list = regUtil.regTest("<div class=\"f18 mb20\">(.*?)</div>\\n\\s*<div class=\"ft\">", txt,new int[]{1});
//		regUtil.filterList(list, new String[]{"&nbsp;","<(.*?)>","\t","&rdquo;","&ldquo;","\\s"}, new String[]{"","","","","",""});
//		for(int i=0;i<list.size();i++){
//			String str = list.get(i)[0];
//			System.out.println(str);
//			System.out.println("----------------------");
//		}
		List<Map<String,String>> resultMapList = regUtil.regTest("问：(.*?)答：(.*?)", "问：你好！答：我很好！", new String[]{"question","answer"});
		System.out.println(resultMapList.get(0).get("question"));
	}
}
