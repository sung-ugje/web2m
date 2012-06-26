package kr.pe.ekxkaks.web2m.common;

import java.util.ArrayList;
import java.util.List;


public class Constants {

	public static final String REPLY_DELIMITER = "↕";

	public static String domain = "http://cafe.gongdong.or.kr";
	public static String listSvl = "/list.dkb";
	public static String viewSvl = "/view.dkb";
	
	public static List<String> listSkipTag = new ArrayList<String>();
    public static List<String> listDelTag = new ArrayList<String>();
    public static List<String> viewSkipTag = new ArrayList<String>();
    public static List<String> viewDelTag = new ArrayList<String>();

    public static void load(){
     listSkipTag.add("<td");
     listSkipTag.add("</tr");
     listSkipTag.add("<tr");
     listSkipTag.add("</td");
     listSkipTag.add("<TD");
     listSkipTag.add("<table");
     listSkipTag.add("/space.gif\" WIDTH=\"40\"");
     listSkipTag.add("</table");
     listSkipTag.add("<TR");
     listSkipTag.add("\\[Last\\]");
     listSkipTag.add("/media/icon/");

     listDelTag.add("<font face=\"Tahoma\">");
     listDelTag.add("</font>");
     listDelTag.add("<div align=\"right\">");
     listDelTag.add("<div align=\"center\">");
     listDelTag.add("</div>");
     listDelTag.add("&nbsp;");
     listDelTag.add("<span style=\"font-size:8pt;\">");
     listDelTag.add("<span style=\"font-size:7pt;\">");
     listDelTag.add("</span>");
     listDelTag.add("<a href=\"javascript:ui\\('.*','.*','.*'\\);\" >");
     listDelTag.add("</a>");
     
     viewSkipTag.add("height=1></td>");
     viewSkipTag.add("preedit=");
     viewSkipTag.add("predel=");
     viewSkipTag.add("<table");
     viewSkipTag.add("<td");
     viewSkipTag.add("<TD");
     viewSkipTag.add("download");
     viewSkipTag.add("프린트하기");
     viewSkipTag.add("bt_reply.gif");
     viewSkipTag.add("contents_box");
     viewSkipTag.add("<div align=\"left\">");
     viewSkipTag.add("<form ");
     viewSkipTag.add("<input");
     viewSkipTag.add("_~^^~_");
     viewSkipTag.add("<div><b>");
     viewSkipTag.add("<script");
     viewSkipTag.add("function");
     viewSkipTag.add(".toggle();");
     viewSkipTag.add("</script>");
     viewSkipTag.add("reply_r");

	}
}