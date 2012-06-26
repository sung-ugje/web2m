package kr.pe.ekxkaks.web2m;

import java.util.ArrayList;
import java.util.List;

import kr.pe.ekxkaks.web2m.common.Commun;
import kr.pe.ekxkaks.web2m.common.Constants;
import kr.pe.ekxkaks.web2m.common.ViewData;
import kr.pe.ekxkaks.web2m.common.ListData;
import kr.pe.ekxkaks.web2m.common.RowData;

public class web2m {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception{
        Constants.load();
        ListData list = readList("17","1");
        log(list.toString());
        ViewData body = readView("1598", "1001284");
       // log(body.toString());
    }

    public static ListData readList(String div,String page){
        ListData rows = new ListData();
        log(Constants.domain + "/cafe.php?p1=dokkaebi&page="+page+"&sort=" + div);
        String responseBody = Commun.post(Constants.domain + "/cafe.php?p1=dokkaebi&page="+page+"&sort=" + div);
       
        List<String> listTarget = readBody(responseBody, Constants.listSkipTag, Constants.listDelTag, "\"board_list_line\"", "[Last]");
        int idx = 0;
        RowData listData = new RowData();
        String tmpDate = "";
        int start, end, tmpIdx;
        // ------------------------ 컬럼 추출
        //log("listTarget : "+listTarget.size());
        while (idx + 5 < listTarget.size()) {
        	 listData = new RowData();
            //log("line cnt : " +idx);
            listData.setHead(listTarget.get(idx));
            idx++;
            tmpDate = listTarget.get(idx);
            log(":::::] (" +idx+")"+tmpDate);
            if (listData.getHead().indexOf("공지]") == -1) {
                idx++;
                listData.setWriter(listTarget.get(idx));
            }
            idx++;
            listData.setWriteDate(listTarget.get(idx));
            idx++;
            listData.setViweCnt(listTarget.get(idx));
            idx++;
            if (tmpDate.indexOf("href=\"") > -1 && tmpDate.indexOf("javascript:ui(") == -1) {
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
            if (start > -1)listData.setTitle(tmpDate.substring(start, end));
            rows.add(listData);

        }
        return rows;
    }

    public static ViewData readView(String div, String docNum){

        //http://cafe.gongdong.or.kr/cafe.php?sort=1598&p1=dokkaebi&number=1001284&mode=view
        String responseBody = Commun.post(Constants.domain + "/cafe.php?sort="+div+"&p1=dokkaebi&number="+docNum+"&mode=view");

        List<String> listTarget = readBody(responseBody, Constants.viewSkipTag, Constants.viewDelTag, "margin-right:10; margin-left:10;", "b_modify.gif");

        boolean readContents1 = false;
        boolean readReply = false;
        String isReply = "false";
        String Contents1 = "";
        String Contents2 = "";
        String Reply = "";
        ViewData data = new ViewData();
        String[] tmp;
        // ------------------------ 컬럼 추출
        for (String line : listTarget) {
            //log(":::::] "+line);
            if (line.indexOf("margin-right:10; margin-left:10;") > -1) {
                data.setTitle(line.substring(line.indexOf("margin-right:10; margin-left:10;") + 37, line.indexOf("</div>")));
                log("[" + data.getTitle() + "]");
            }
            if (line.indexOf("<div align=\"right\"><b>작성자 :") > -1) {
                data.setWriter(line.substring(line.indexOf("');\" >") + 6, line.indexOf("</a></b></div>")));
                log("[" + data.getWriter() + "]");
            }
            if (line.indexOf("</a> , 입력 : ") > -1) {
                data.setWriteDate(line.substring(line.indexOf("<span title=") + 34, line.indexOf("</span>, &nbsp;조회")));
                log("[" + data.getWriteDate() + "]");
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
            if (line.indexOf("reply.gif") > -1 || line.indexOf("(ID : <a href=") > -1) {
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
        log(data.toString());
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

    static void log(String msg){
        System.out.println(msg);
    }
}
