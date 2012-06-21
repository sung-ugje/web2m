package web2m;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

public class web2m {
    static String domain = "http://cafe.gongdong.or.kr";

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception{
        //Rows list = readList("17");
        // log(list.get(3).getConvLink());
        DetailData body = readDetail("17", "990576");
        // log(body.toString());
    }

    private static ListData readList(String div){
        ListData rows = new ListData();
        // http://cafe.gongdong.or.kr/cafe.php?sort=17&sub_sort=&keyfield=&key_bs=&p1=dokkaebi&p2=&p3=&page=7&startpage=1
        String responseBody = post(domain + "/cafe.php?p1=dokkaebi&page=7&sort=" + div);

        List<String> skipTag = new ArrayList<String>();
        skipTag.add("<td");
        skipTag.add("</tr");
        skipTag.add("<tr");
        skipTag.add("</td");
        skipTag.add("<TD");
        skipTag.add("<table");
        skipTag.add("/space.gif\" WIDTH=\"40\"");
        skipTag.add("</table");
        skipTag.add("<TR");
        skipTag.add("[Last]");
        skipTag.add("/media/icon/");

        List<String> delTag = new ArrayList<String>();
        delTag.add("<font face=\"Tahoma\">");
        delTag.add("</font>");
        delTag.add("<div align=\"right\">");
        delTag.add("<div align=\"center\">");
        delTag.add("</div>");
        delTag.add("&nbsp;");
        delTag.add("<span style=\"font-size:8pt;\">");
        delTag.add("<span style=\"font-size:7pt;\">");
        delTag.add("</span>");
        delTag.add("<a href=\"javascript:ui\\('.*','.*','.*'\\);\" >");
        delTag.add("</a>");

        List<String> listTarget = readBody(responseBody, skipTag, delTag, "\"board_list_line\"", "[Last]");
        int idx = 0;
        RowData listData = new RowData();
        String tmpDate = "";
        int start, end, tmpIdx;
        // ------------------------ 컬럼 추출
        while (idx < listTarget.size()) {
            listData.setHead(listTarget.get(idx));
            idx++;
            tmpDate = listTarget.get(idx);
            if (listData.getHead().indexOf("공지]") == -1) {
                idx++;
                listData.setWriter(listTarget.get(idx));
            }
            idx++;
            listData.setWriteDate(listTarget.get(idx));
            idx++;
            listData.setViweCnt(listTarget.get(idx));
            idx++;
            if (tmpDate.indexOf("href=\"") > -1) {
                listData.setLink(tmpDate.substring(tmpDate.indexOf("href=\"") + 6, tmpDate.indexOf("view\"") + 4));
            } else {
                listData.setLink("");
            }
            if (tmpDate.indexOf("<span style=\"font-size:7pt;\" ><b>") > -1) {
                listData.setReplyCnt(tmpDate.substring(tmpDate.indexOf("<span style=\"font-size:7pt;\" ><b>") + 34, tmpDate.indexOf("</b>") - 1));
            } else {
                listData.setReplyCnt(0);
            }
            if (tmpDate.indexOf("new_s.gif") > -1) {
                listData.setNuovo(true);
            } else {
                listData.setNuovo(false);
            }
            if (tmpDate.indexOf("reply.gif") > -1) {
                listData.setReply(true);
            } else {
                listData.setReply(false);
            }
            start = tmpDate.indexOf("view\">") + 6;
            if (start == 5) {
                start = tmpDate.indexOf("view\" target=\"_blank\">");
                if (start > 1) start += 22;
            }
            end = tmpDate.length();
            tmpIdx = tmpDate.indexOf("<img src=images/new_s.gif border=0>");
            if (tmpIdx > -1 && tmpIdx < end) {
                end = tmpIdx;
            }
            tmpIdx = tmpDate.indexOf("<span style=\"font-size:7pt;\" >");
            if (tmpIdx > -1 && tmpIdx < end) {
                end = tmpIdx;
            }
            listData.setTitle(tmpDate.substring(start, end));
            rows.add(listData);

        }
        return rows;
    }

    private static DetailData readDetail(String div, String docNum){

        String responseBody = post(domain + "/cafe.php?sort=1598&p1=dokkaebi&number=988739&mode=view");
        List<String> skipTag = new ArrayList<String>();
        skipTag.add("height=1></td>");
        skipTag.add("preedit=");
        skipTag.add("predel=");
        skipTag.add("<table");
        skipTag.add("<td");
        skipTag.add("<TD");
        skipTag.add("download");
        skipTag.add("프린트하기");
        skipTag.add("bt_reply.gif");
        skipTag.add("contents_box");
        skipTag.add("<!--");
        skipTag.add("<div align=\"left\">");
        skipTag.add("<form ");
        skipTag.add("<input");
        skipTag.add("_~^^~_");
        skipTag.add("<div><b>");
        skipTag.add("<script");
        skipTag.add("function");
        skipTag.add(".toggle();");
        skipTag.add("</script>");
        skipTag.add("reply_r");
        
        List<String> delTag = new ArrayList<String>();
        
        List<String> listTarget = readBody(responseBody, skipTag, delTag, "margin-right:10; margin-left:10;", "b_modify.gif");


        DetailData data = new DetailData();
        // ------------------------ 컬럼 추출
        for (String row: listTarget) {
           log(row);
        }
        return data;
    }

    private static List<String> readBody(String responseBody, List<String> skipTag, List<String> delTag, String start,
            String end){
        List<String> rtnList = new ArrayList<String>();

        String[] body = responseBody.split("\n");
        boolean flagRead = false;
        boolean skip = false;
        String tmp = "";

        for (String line : body) {
            line = line.replaceAll("\r", "");
            if (line.indexOf(start) > -1) {
                flagRead = true;
            }

            if (flagRead) {
                for (String tag : skipTag) {
                    if (line.indexOf(tag) > -1) skip = true;
                }
                if (line.trim().length() < 9) skip = true;
                //log("["+skip+"] "+ line);
                if (!skip) {
                    tmp = line.trim();
                    for (String tag : delTag) {
                        tmp = tmp.replaceAll(tag, "");
                    }
                    rtnList.add(tmp);
                }

                if (line.indexOf(end) > -1) {
                    flagRead = false;
                }
            }
            skip = false;
        }
        return rtnList;
    }

    static String post(String url){
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost post = new HttpPost(url); //
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        List<NameValuePair> queryParm = new ArrayList<NameValuePair>();
        String rtnBody = "";

        try {
            post.setEntity(new UrlEncodedFormEntity(queryParm, "UTF-8"));
            rtnBody = httpclient.execute(post, responseHandler);
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        } catch (ClientProtocolException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return rtnBody;
    }

    static void log(String msg){
        System.out.println(msg);
    }
}
