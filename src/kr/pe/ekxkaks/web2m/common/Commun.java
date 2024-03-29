package kr.pe.ekxkaks.web2m.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 통신부분을 담당하는 클래스.
 * @author 다타만
 *
 */
public class Commun {

	/**
	 * 지정한 url 로 http Post 호출을 수행한다. 
	 * @param url post 보낼 url
	 * @return
	 */
	public static String post(String url) {
		if (Constants.isDebug)
			System.out.println("Post Url : " + url);
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		List<NameValuePair> queryParm = new ArrayList<NameValuePair>();
		String rtnBody = "";
		CloseableHttpResponse response = null; 
		try {
			post.setEntity(new UrlEncodedFormEntity(queryParm, "UTF-8"));
			response = httpclient.execute(post);
			rtnBody = EntityUtils.toString(response.getEntity());
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		} catch (ClientProtocolException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (Constants.isDebug)
			System.out.println(rtnBody);
		return rtnBody;
	}
}