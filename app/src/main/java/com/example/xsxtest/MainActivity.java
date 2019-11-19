package com.example.xsxtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mName;
    private EditText mPassword;
    /**
     * 登入
     */
    private Button mEnter;
    /**
     * 注册
     */
    private Button mRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mName = (EditText) findViewById(R.id.name);
        mPassword = (EditText) findViewById(R.id.password);
        mEnter = (Button) findViewById(R.id.enter);
        mEnter.setOnClickListener(this);
        mRegister = (Button) findViewById(R.id.register);
        mRegister.setOnClickListener(this);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String pass = intent.getStringExtra("pass");
        mName.setText(name);
        mPassword.setText(pass);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.enter:
                initData();
                break;
            case R.id.register:
                initRegister();
                break;
        }
    }

    private void initData() {
        String name = mName.getText().toString().trim();
        String pass = mPassword.getText().toString().trim();
        Retrofit build = new Retrofit.Builder()
                .baseUrl(Apiservice.url)
                .build();
        build.create(Apiservice.class)
                .initLogin(name,pass)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            Log.i("bei", "bei"+response.body().string());
                            startActivity(new Intent(MainActivity.this,OkActivity.class));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
    }

    private void initRegister() {
        startActivity(new Intent(this,RegisterActivity.class));
    }
}
