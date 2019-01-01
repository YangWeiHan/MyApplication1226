package com.example.mvp_master.Model;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ILoginlModellmpl implements ILoginlModel {
    public static final String LOGIN_URL = "http://172.17.8.100/small/user/v1/login";

    @Override
    public void containData(String userName, String pwd,CallBack callBack) {
        new SubAysncTask(userName, pwd,callBack).execute(LOGIN_URL);
    }

    class SubAysncTask extends AsyncTask<String,Integer,String>{
        String userName ;
        String pwd;
        CallBack callBack;

        public SubAysncTask(String userName, String pwd,CallBack callBack) {
            this.userName = userName;
            this.pwd = pwd;
            this.callBack = callBack;
        }

        @Override
        protected String doInBackground(String... urlStrings) {
            try {
                //请求的URL
                String urlString = urlStrings[0];
                //url  地址的拼接
                String completeUrlString = urlString+"?phone="+userName+"&pwd="+pwd;
                URL url = new URL(completeUrlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.connect();
                if (urlConnection.getResponseCode() == 200){
                   StringBuilder stringBuilder = new StringBuilder();
                    String line = null;
                    //输入流读取
                    InputStream inputStream = urlConnection.getInputStream();
                    //搞笑字节流
                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                    while ((line = br.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    //把读取的内容转换成字串
                    return stringBuilder.toString();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            callBack.responseData(response);
        }
    }
}
