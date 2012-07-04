package kr.pe.ekxkaks.web2m.common;

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


public class Commun {

    
    public static String post(String url){
        if (Constants.isDebug) System.out.println("Post Url : "+url);
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
        if (Constants.isDebug) System.out.println(rtnBody);
        return rtnBody;
    }
}