package web2m;

import java.util.ArrayList;
import java.util.List;

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


}
