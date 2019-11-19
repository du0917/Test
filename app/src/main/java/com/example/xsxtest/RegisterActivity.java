package com.example.xsxtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xsxtest.Bean.VerifyBean;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 账号
     */
    private EditText mReName;
    /**
     * 密码
     */
    private EditText mRePass;
    /**
     * 确认密码
     */
    private EditText mRePass2;
    /**
     * 手机号
     */
    private EditText mRePhoneNumber;
    /**
     * 验证码
     */
    private EditText mReCoder;
    /**
     * 注册
     */
    private Button mReRegister;
    /**
     * 获取验证码
     */
    private Button mReGet;
    private TextView mVerify;
    private String mName;
    private String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        mReName = (EditText) findViewById(R.id.re_name);
        mRePass = (EditText) findViewById(R.id.re_pass);
        mRePass2 = (EditText) findViewById(R.id.re_pass2);
        mRePhoneNumber = (EditText) findViewById(R.id.re_phoneNumber);
        mReCoder = (EditText) findViewById(R.id.re_coder);
        mReRegister = (Button) findViewById(R.id.re_register);
        mReRegister.setOnClickListener(this);
        mReGet = (Button) findViewById(R.id.re_get);
        mReGet.setOnClickListener(this);
        mVerify = (TextView) findViewById(R.id.verify);
    }

    private void initData() {
        mName = mReName.getText().toString().trim();
        mPassword = mRePass.getText().toString().trim();
        String password2 = mRePass2.getText().toString().trim();
        String phone = mRePhoneNumber.getText().toString().trim();
        String Coder = mReCoder.getText().toString().trim();
        if (!mPassword.equals(password2)) {
            Toast.makeText(this, "密码不一样", Toast.LENGTH_SHORT).show();
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Apiservice.url)
                .build();
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())

        retrofit.create(Apiservice.class)
                .initRegister(mName, mPassword, phone, Coder)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            Log.i("bei", "bei"+response.body().string());

                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            intent.putExtra("name", mName);
                            intent.putExtra("pass", mPassword);
                            startActivity(intent);
                            finish();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.re_register:
                initData();
                break;
            case R.id.re_get:
                initverify();
                break;
        }
    }

    private void initverify() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Apiservice.url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofit.create(Apiservice.class)
                .initVerify()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VerifyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VerifyBean verifyBean) {
                        String data = verifyBean.getData();
                        mVerify.setText(data);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("bei", "bei"+e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
