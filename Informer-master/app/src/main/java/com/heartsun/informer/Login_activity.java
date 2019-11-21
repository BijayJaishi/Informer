package com.heartsun.informer;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.heartsun.informer.progressDialog.ShowProgress;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_activity extends AppCompatActivity {

    TextView login,reg;
    ImageView prev,logo;
    EditText num,pwd;
    Button Login;
    SharedPrefrenceClass prefrenceClass;
    ShowProgress progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);

        prefrenceClass = new SharedPrefrenceClass(getApplicationContext());

        login = findViewById(R.id.logolabel);

        reg = findViewById(R.id.reg);

        progress = new ShowProgress(Login_activity.this);

        prev = findViewById(R.id.preview);
        logo = findViewById(R.id.looogo);
        num = findViewById(R.id.phnum);
        pwd = findViewById(R.id.pwd);
        Login = findViewById(R.id.loginbtn);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginData();

    }

        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Register_activity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            if (!prefrenceClass.getMobile().equals("")){
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void loginData(){
        String mbl = num.getText().toString().trim();
        String pass = pwd.getText().toString().trim();

        if (mbl.isEmpty()){
            num.setError("Number Required");
            return;
        }

        if (pass.isEmpty()){
            pwd.setError("Password Required");
            return;
        }

        sendLoginData(mbl,pass);

    }

    public void sendLoginData(final String mobileNo, String password){
        progress.showProgress();
        RetrofitApiInterface loginInterface = RetrofitClient.getRetrofit().create(RetrofitApiInterface.class);

        RequestBody requestBody = new MultipartBody
                .Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("MobileNo",mobileNo)
                .addFormDataPart("Password",password)
                .build();

        loginInterface.login(requestBody).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        String success = jsonObject.optString("success");
                        if (success.equals("0")){
                            try {
                                if (progress != null){
                                    progress.hideProgress();
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            Toast.makeText(Login_activity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }else{
                            try {
                                if (progress != null){
                                    progress.hideProgress();
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            prefrenceClass.saveMobile(mobileNo);
                            Toast.makeText(Login_activity.this, "Successfully Login", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                try {
                    if (progress != null){
                        progress.hideProgress();
                    }
                    Toast.makeText(Login_activity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

}
