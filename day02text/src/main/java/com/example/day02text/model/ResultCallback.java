package com.example.day02text.model;

import com.example.day02text.Bean;
import com.example.day02text.RecentBean;

/**
 * Created by 雪碧 on 2019/11/19.
 */

public interface ResultCallback{
    void onSuccess(Bean bean);
    void onFail(String error);
}
