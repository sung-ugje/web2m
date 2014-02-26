package kr.pe.ekxkaks.web2m.common;

import java.util.ArrayList;

/**
 * 리스트 유형의 html 을 파싱해서 담고 있는 데이터
 * @author ekxkaks
 *
 */
public class ListData extends ArrayList<RowData> {
    
    private static final long serialVersionUID = -1539842382030905806L;
    

    public String toString(){
    	String rtn = "[ListData ";
    	for(RowData row:this){
    		rtn += "[ " + row.toString() + "]\n";
    	}
    	rtn += "]";
    	return rtn;
    }
}
