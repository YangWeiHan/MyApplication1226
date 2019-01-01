package com.example.mvp_newsdata.data.okhttputil;

import java.io.IOException;

public interface ICallBack {

    void setData(Object o);

    void setFail(IOException msg);
}
