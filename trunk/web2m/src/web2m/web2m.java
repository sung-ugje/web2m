package web2m;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import kr.pe.ekxkaks.Constants;

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
        ListData list = readList("466");
        log(list.get(3).toString());
        DetailData body = readDetail("17", "1001284");
        log(body.toString());
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

        String responseBody = post(domain + "/cafe.php?sort=1598&p1=dokkaebi&number=1001284&mode=view");
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

        boolean readContents1 = false;
        boolean readReply = false;
        String isReply = "false";
        String Contents1 = "";
        String Contents2 = "";
        String Reply = "";
        DetailData data = new DetailData();
        String[] tmp;
        // ------------------------ 컬럼 추출
        for (String line : listTarget) {
            // log(":::::] "+line);
            if (line.indexOf("margin-right:10; margin-left:10;") > -1) {
                data.setTitle(line.substring(line.indexOf("margin-right:10; margin-left:10;") + 37, line.indexOf("</div>")));
                // log("[" + data.getTitle() + "]");
            }
            if (line.indexOf("<div align=\"right\"><b>작성자 :") > -1) {
                data.setWriter(line.substring(line.indexOf("');\" >") + 6, line.indexOf("</a></b></div>")));
                // log("[" + data.getWriter() + "]");
            }
            if (line.indexOf("</a> , 입력 : ") > -1) {
                data.setWriteDate(line.substring(line.indexOf("<span title=") + 34, line.indexOf("</span>, &nbsp;조회")));
                // log("[" + data.getWriteDate() + "]");
            }
            if (line.indexOf("<!---- contents end ---->") > -1) {
                data.setContents1(Contents1);
                // log("["+data.getContents1()+"]");
                readContents1 = false;
            }
            if (readContents1) {
                Contents1 += line + "\n";
            }
            if (line.indexOf("<!---- contents start ---->") > -1) {
                readContents1 = true;
            }
            if (line.indexOf("resizeImage2(this)") > -1) {
                tmp = line.split("<img ");
                Contents2 = tmp[0];
                for (String tr : tmp) {
                    if (tr.indexOf("src=\"") > -1) {
                        Contents2 += "<img " + tr.substring(tr.indexOf("src=\""));
                    }
                }
                data.setContents2(Contents2.replaceAll("</td>", "").trim());
                // log("[" + data.getContents2() + "]");
            }
            if (line.indexOf("reply.gif") > -1 || line.indexOf("(ID : <a href=") > -1) { // 댓글의 시작)
                if (!"".equals(Reply)) {
                    data.addReply(Reply + Constants.REPLY_DELIMITER + isReply); // 한번만 등록된다.
                    Reply = "";
                    isReply = "false";
                }
            }
            if (line.indexOf("<!-- 댓글 끝 -->") > -1) {
                readReply = false;
            }

            if (line.indexOf("reply.gif") > -1) {
                line = line.substring(0, line.lastIndexOf("<img src=\"images/reply.gif"));
                isReply = "true";
                readReply = false;
            }
            if (line.indexOf("(ID : <a href=") > -1) { // 새로운 컬럼의 시작
                Reply += line.substring(line.indexOf("');\" >") + 6, line.indexOf("</a></b></font> <span"))
                        + Constants.REPLY_DELIMITER;
                Reply += line.substring(line.indexOf("<span title=") + 34, line.indexOf("</span>"))
                        + Constants.REPLY_DELIMITER;
                readReply = true;
            } else if (readReply) {
                Reply += line;
            }

        }
        data.addReply(Reply + Constants.REPLY_DELIMITER + isReply);
        // log(data.toString());
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
                // log("["+skip+"] "+ line);
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
