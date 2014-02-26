package kr.pe.ekxkaks.web2m.common;

import java.util.ArrayList;

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
