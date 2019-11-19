package com.example.day02text.presenter;

import android.util.Log;

import com.example.day02text.Bean;
import com.example.day02text.Fragment.MyFragment;
import com.example.day02text.RecentBean;
import com.example.day02text.model.ResultCallback;
import com.example.day02text.model.mainModel;
import com.example.day02text.view.MainView;

/**
 * Created by 雪碧 on 2019/11/19.
 */

public class MainPresenter {
    private MainView mView;
    private final mainModel mMainModel;

    public MainPresenter(MainView view) {
        mMainModel = new mainModel();
        mView = view;
    }

    public  void getData() {
        mainModel.getData(new ResultCallback() {
            @Override
            public void onSuccess(Bean bean) {
                if (bean!=null){
                    mView.setData(bean);
                }

            }

            @Override
            public void onFail(String error) {
                Log.i("bei", "bei"+error.toString());
            }
        });

    }
}
