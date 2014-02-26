package kr.pe.ekxkaks.web2m.common;

import org.apache.commons.lang3.StringUtils;

/**
 * 리스트에 표시되는 하나의 정보를 담고잇는 VO
 * @author ekxkaks
 *
 */
public class RowData {
    String head;
    String title;
    String link;
    /**
     * 작성자
     */
    String writer;
    /**
     * 작성일
     */
    String writeDate;
    /**
     * 공지여부
     */
    boolean notice;
    boolean nuovo;
    /**
     * 답글여부
     */
    boolean reply;
    /**
     * 읽은 수
     */
    int viweCnt;
    /**
     * 댓글 수
     */
    int replyCnt;
   
    public RowData(){

    }

    public String getHead(){
        return head;
    }

    public void setHead(String head){
        this.head = head;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getLink(){
        return link;
    }

    public void setLink(String link){
        this.link = link;
    }

    public String getWriter(){
        return writer;
    }

    public void setWriter(String writer){
        this.writer = writer;
    }

    public String getWriteDate(){
        return writeDate;
    }

    public void setWriteDate(String writeDate){
        this.writeDate = writeDate;
    }

    public boolean isNotice(){
        return notice;
    }

    public void setNotice(boolean notice){
        this.notice = notice;
    }

    public boolean isNuovo(){
        return nuovo;
    }

    public void setNuovo(boolean nuovo){
        this.nuovo = nuovo;
    }

    public boolean isReply(){
        return reply;
    }

    public void setReply(boolean reply){
        this.reply = reply;
    }

    public int getViweCnt(){
        return viweCnt;
    }

    public void setViweCnt(int viweCnt){
        this.viweCnt = viweCnt;
    }

    public int getReplyCnt(){
        return replyCnt;
    }

    public void setReplyCnt(int replyCnt){
        this.replyCnt = replyCnt;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("RowData [head=");
        builder.append(head);
        builder.append(", title=");
        builder.append(title);
        builder.append(", link=");
        builder.append(link);
        builder.append(", writer=");
        builder.append(writer);
        builder.append(", writeDate=");
        builder.append(writeDate);
        builder.append(", notice=");
        builder.append(notice);
        builder.append(", nuovo=");
        builder.append(nuovo);
        builder.append(", reply=");
        builder.append(reply);
        builder.append(", viweCnt=");
        builder.append(viweCnt);
        builder.append(", replyCnt=");
        builder.append(replyCnt);
        builder.append("]");
        return builder.toString();
    }

    public void setViweCnt(String cnt){
        try {
            viweCnt = Integer.parseInt(cnt);
        } catch (NumberFormatException e) {
            viweCnt = 0;
        }

    }

    public void setReplyCnt(String rcnt){
        try {
            replyCnt = Integer.parseInt(rcnt);
        } catch (NumberFormatException e) {
            replyCnt = 0;
        }

    }

    // http://cafe.gongdong.or.kr/cafe.php?sort=17&p1=dokkaebi&number=999341&mode=view
    public String getConvViewLink(){
    	if(Constants.isDebug) System.out.println("@@@@]" + link);
        String rtn = "";
        if(StringUtils.isBlank(link)) return "";
        if(link.indexOf("http:") > -1) {
            rtn = link.substring(link.indexOf('?'));
            String[] tmpItems = rtn.split("&");
            String[] tmpArr;
            String queryString = "";

            for (String a : tmpItems) {
                if (a.indexOf('=') > -1) {
                    tmpArr = a.split("=");
                    if (tmpArr.length > 1) {
                        queryString += a + "&";
                    }
                }
            }
            rtn = Constants.viewSvl + queryString;
            rtn = rtn.substring(0,rtn.length()-1) + "&div=board";
        } else {
            rtn = link.substring(link.indexOf('?'));
            String[] tmpItems = rtn.split("&");
            String[] tmpArr;
            String queryString = "";

            for (String a : tmpItems) {
                if (a.indexOf('=') > -1) {
                    tmpArr = a.split("=");
                    if (tmpArr.length > 1) {
                        queryString += a + "&";
                    }
                }
            }
            rtn = Constants.viewSvl + queryString;
            rtn = rtn.substring(0,rtn.length()-1);
        }

        return rtn;
    }

}
