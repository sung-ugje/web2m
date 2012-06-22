package web2m;

import java.util.ArrayList;
import java.util.List;

import kr.pe.ekxkaks.Constants;

public class DetailData {
    String title;
    String writer;
    String writeDate;
    String Contents1;
    String Contents2;
    List<RepleData> reply = new ArrayList<RepleData>();

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
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

    public String getContents1(){
        return Contents1;
    }

    public void setContents1(String contents1){
        Contents1 = contents1;
    }

    public String getContents2(){
        return Contents2;
    }

    public void setContents2(String contents2){
        Contents2 = contents2;
    }

    public List<RepleData> getReply(){
        return reply;
    }

    public void setReply(List<RepleData> reply){
        this.reply = reply;
    }

    public void addReply(String[] reply){
        if (reply.length == 4){
            RepleData rd = new RepleData();
            rd.setWriter(reply[0]);
            rd.setWriteDate(reply[1]);
            rd.setContents1(reply[2]);
            rd.setReple("1,true,reply,t".indexOf(reply[3].toLowerCase()) > -1);
            this.reply.add(rd);    
        }
        

    }
    public void addReply(RepleData reply){
        this.reply.add(reply);

    }

    public void addReply(String reply){
        addReply(reply.split(Constants.REPLY_DELIMITER));
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("DetailData [title=");
        builder.append(title);
        builder.append(", writer=");
        builder.append(writer);
        builder.append(", writeDate=");
        builder.append(writeDate);
        builder.append(", Contents1=");
        builder.append(Contents1);
        builder.append(", Contents2=");
        builder.append(Contents2);
        builder.append(", reply={");
        for(RepleData rp:reply){
            builder.append(rp.toString());    
        }
        builder.append("}]");
        return builder.toString();
    }

}
