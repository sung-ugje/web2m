package kr.pe.ekxkaks.web2m.common;

public class RequestQuery {
    String sort;
    String sub_sort;
    String page;
    String startpage;
    String keyfield;
    String key_bs;
    String p1;
    String p2;
    String p3;
    String number;
    String mode;

    public String getSort(){
        return sort;
    }

    public void setSort(String sort){
        this.sort = sort;
    }

    public String getSub_sort(){
        return sub_sort;
    }

    public void setSub_sort(String sub_sort){
        this.sub_sort = sub_sort;
    }

    public String getPage(){
        return page;
    }

    public void setPage(String page){
        this.page = page;
    }

    public String getStartpage(){
        return startpage;
    }

    public void setStartpage(String startpage){
        this.startpage = startpage;
    }

    public String getKeyfield(){
        return keyfield;
    }

    public void setKeyfield(String keyfield){
        this.keyfield = keyfield;
    }

    public String getKey_bs(){
        return key_bs;
    }

    public void setKey_bs(String key_bs){
        this.key_bs = key_bs;
    }

    public String getP1(){
        return p1;
    }

    public void setP1(String p1){
        this.p1 = p1;
    }

    public String getP2(){
        return p2;
    }

    public void setP2(String p2){
        this.p2 = p2;
    }

    public String getP3(){
        return p3;
    }

    public void setP3(String p3){
        this.p3 = p3;
    }

    public String getNumber(){
        return number;
    }

    public void setNumber(String number){
        this.number = number;
    }

    public String getMode(){
        return mode;
    }

    public void setMode(String mode){
        this.mode = mode;
    }

    public String toString(){
        String rtn = "http://cafe.gongdong.or.kr/cafe.php?";
        rtn += "sort=" + sort;
        rtn += "&sub_sort=" + sub_sort;
        rtn += "&page=" + page;
        rtn += "&startpage=" + startpage;
        rtn += "&keyfield=" + keyfield;
        rtn += "&key_bs=" + key_bs;
        rtn += "&p1=" + p1;
        rtn += "&p2=" + p2;
        rtn += "&p3=" + p3;
        rtn += "&number=" + number;
        rtn += "&mode=" + mode;
        return rtn;
    }

    public String getShutListUrl(){
        String rtn = "/cafe.php?";
        rtn += "sort=" + sort;
        rtn += "&page=" + page;
        rtn += "&p1=" + p1;
        return rtn;
    }

    public String getShutViewUrl(){
        String rtn = "/cafe.php?";
        rtn += "sort=" + sort;
        rtn += "&p1=" + p1;
        rtn += "&number=" + number;
        rtn += "&mode=" + mode;
        return rtn;
    }

}
