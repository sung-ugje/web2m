package kr.pe.ekxkaks.web2m.common;

public class RepleData {
    String writer;
    String writeDate;
    String Contents1;
    boolean reple;

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

    public boolean isReple(){
        return reple;
    }

    public void setReple(boolean reple){
        this.reple = reple;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("RepleData [writer=");
        builder.append(writer);
        builder.append(", writeDate=");
        builder.append(writeDate);
        builder.append(", Contents1=");
        builder.append(Contents1);
        builder.append(", reple=");
        builder.append(reple);
        builder.append("]");
        return builder.toString();
    }

}
