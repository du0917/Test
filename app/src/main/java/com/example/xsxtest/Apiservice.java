package com.example.xsxtest;

import com.example.xsxtest.Bean.VerifyBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by 雪碧 on 2019/11/18.
 */

public interface Apiservice {
    String url="http://yun918.cn/study/public/index.php/";

    //注册
    @POST("register")
    @FormUrlEncoded
    Call<ResponseBody> initRegister(@Field("username") String username
            , @Field("password") String password
            , @Field("phone") String phone
            , @Field("verify") String verify
    );
    //登入
    @POST("login")
    @FormUrlEncoded
    Call<ResponseBody> initLogin(
            @Field("username") String username
            ,@Field("password") String password
    );
    //验证码
    @GET("verify")
    Observable<VerifyBean> initVerify();

}
