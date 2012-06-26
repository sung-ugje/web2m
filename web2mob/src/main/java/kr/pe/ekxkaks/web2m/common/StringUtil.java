package kr.pe.ekxkaks.web2m.common;

public class StringUtil {

    public static String nvl(String val,String rep){
    	String rtn = val;
    	if (val == null || "".equals(val.trim())){
    		rtn = rep;
    	}
    	return rtn;
    	
    }
}
