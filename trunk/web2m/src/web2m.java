import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class web2m {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception{
        HttpClient httpclient = new DefaultHttpClient();

        List<NameValuePair> qparams = new ArrayList<NameValuePair>();
        qparams.add(new BasicNameValuePair("p1", "dokkaebi"));
        qparams.add(new BasicNameValuePair("sort", "17"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(qparams, "UTF-8");
        HttpPost httpPost = new HttpPost("http://cafe.gongdong.or.kr/cafe.php?p1=dokkaebi&sort=17");
        httpPost.setEntity(entity);

        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String responseBody = "";
        responseBody = httpclient.execute(httpPost, responseHandler);
        String[] body = responseBody.split("\n");
        List<String> sb = new ArrayList<String>();
        List<String> skipTag = new ArrayList<String>();
        skipTag.add("<td");
        skipTag.add("</tr");
        skipTag.add("<tr");
        skipTag.add("</td");
        skipTag.add("<TD");

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
        boolean flagRead = false;
        boolean skip = false;
        String tmp = "";
        for (String line : body) {
            if (line.indexOf("\"board_list_line\"") > -1) {
                flagRead = true;
            }

            if (flagRead) {
                for(String tag : skipTag){
                    if (line.indexOf(tag) > -1) skip = true;
                }
                if (line.trim().length() < 1) skip = true;

                if (!skip) {
                    tmp = line.trim();
                    for(String tag:delTag){
                        tmp = tmp.replaceAll(tag, "");
                    }
                    sb.add(tmp);
                }

                if (line.indexOf("</tr>") > -1) {
                    flagRead = false;
                }
            }
            skip = false;
        }

        int idx = 0;
        /*
         * 0 : 머릿말 , 순번
         * 1 : 제목
         * 2 : 작성자
         * 3 : 작성일
         * 4 : 조회수
         * 5 : 제목링크
         * 6 : 댓글수
         * 7 : new 여부
         * 8 : re cnt
         */
        String[] cont = new String[9];
        while (idx < sb.size()) {
            cont[0] =  sb.get(idx);
            System.out.println("0. " +cont[0]);
            idx++;
            cont[1] =  sb.get(idx);
            System.out.println("1. " +cont[1]);
            if (cont[0].indexOf("공지]") == -1) {
                idx++;
                cont[2] =  sb.get(idx);
                System.out.println("2. " +cont[2]);
            }
            idx++;
            cont[3] =  sb.get(idx);
            System.out.println("3. " +cont[3]);
            idx++;
            cont[4] =  sb.get(idx);
            System.out.println("4. " +cont[4]);
            idx++;
            if(cont[1].indexOf("href=\"") > -1) {
                cont[5] = cont[1].substring(cont[1].indexOf("href=\"")+6,cont[1].indexOf("view\"")+4);
                System.out.println("5. " +cont[5]);
            } else {
                cont[5] ="";
            }
            if(cont[1].indexOf("<span style=\"font-size:7pt;\" ><b>") > -1) {
                cont[6] = cont[1].substring(cont[1].indexOf("<span style=\"font-size:7pt;\" ><b>")+34,cont[1].indexOf("</b>")-1);
                System.out.println("6. " +cont[6]);
            } else {
                cont[6] ="";
            }
            if(cont[1].indexOf("new_s.gif") > -1) {
                cont[7] = "new";
                System.out.println("7. " +cont[7]);
            } else {
                cont[7] ="";
            }
            if(cont[1].indexOf("reply.gif") > -1) {
                cont[8] = "1";
                System.out.println("8. " +cont[8]);
            } else {
                cont[8] ="";
            }
            
        }

    }
}
