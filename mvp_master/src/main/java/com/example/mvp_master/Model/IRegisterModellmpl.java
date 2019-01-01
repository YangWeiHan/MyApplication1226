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

public class IRegisterModellmpl implements IRegisterModel {
    public static final String REQUEST_URL = "http://172.17.8.100/small/user/v1/register";

    @Override
    public void containData(String userName,String pwd,CallBack callBack) {
        //TODO 请求数据
        new SuBAysncTask(userName,pwd,callBack).execute(REQUEST_URL);
    }


    class SuBAysncTask extends AsyncTask<String,Integer,String>{
        String userName;
        String pwd;
        CallBack callBack;
        public SuBAysncTask(String userName, String pwd, CallBack callBack){
            this.userName = userName;
            this.pwd = pwd;
            this.callBack = callBack;
        }
        @Override
        protected String doInBackground(String... urlStrings) {

            try {
                //请求的URL字符串
                String urlString = urlStrings[0];
                String completeUrlString = urlString+"?phone="+userName+"&pwd="+pwd;
                //设置URL 的地址
                URL url = new URL(completeUrlString);
                //设置请求对象
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                //设置请求的方式
                urlConnection.setRequestMethod("POST");
                //连接
                urlConnection.connect();
                if (urlConnection.getResponseCode() == 200){
                    StringBuffer stringBuffer = new StringBuffer();
                    String line = null;
                    //设置读取
                    InputStream inputStream = urlConnection.getInputStream();
                    //高效字节流
                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                    while ((line = br.readLine()) != null){
                        stringBuffer.append(line);
                    }
                    //把读取的内容转换成字符串
                    return stringBuffer.toString();
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
            //数据回传给P层   接口回调
            callBack.responseData(response);
        }
    }
}
