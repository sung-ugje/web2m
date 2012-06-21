package web2m;

public class RowData {
    String head;
    String title;
    String link;
    String writer;
    String writeDate;
    boolean notice;
    boolean nuovo;
    boolean reply;
    int viweCnt;
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
        builder.append("doc [head=");
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
    public String getConvLink(){
        String rtn = link.substring(link.indexOf('?'));
        String[] aa = rtn.split("&");
        String[] bb;
        String vv = "";

        for (String a : aa) {
            if (a.indexOf('=') > -1) {
                bb = a.split("=");
                if (bb.length > 1) {
                    vv += a + "&";
                }
            }
        }

        rtn = link.substring(0, link.indexOf('?')) + vv;
        return rtn.substring(0,rtn.length()-1);
    }

}
