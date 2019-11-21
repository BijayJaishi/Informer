package com.heartsun.informer;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

public class Register_activity extends AppCompatActivity {

    String check,membername,membernumber,pwd,confirmpassword;
    ImageView logo,back;
    TextView title,account,login;
    EditText memname,memnum,password,confirmpass;
    Button register;
    ShowProgress progress;
    SharedPrefrenceClass prefrenceClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activity);

        logo = findViewById(R.id.logo);
        title = findViewById(R.id.reg);
        account = findViewById(R.id.account);
        login = findViewById(R.id.log);



        prefrenceClass = new SharedPrefrenceClass(getApplicationContext());

        back = findViewById(R.id.back);

        progress = new ShowProgress(Register_activity.this);

        memname = findViewById(R.id.name);
        memnum = findViewById(R.id.num);
        password = findViewById(R.id.password);
        confirmpass =findViewById(R.id.retype);
        register = findViewById(R.id.regbtn);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        membername = memname.getText().toString();
        membernumber = memnum.getText().toString();
        pwd = password.getText().toString();
        confirmpassword = confirmpass.getText().toString();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TextWatcher numberwatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                check = s.toString();

                if (check.length() > 10) {
                    memnum.setError("Number cannot be grated than 10 digits");
                } else if (check.length() < 10) {
                    memnum.setError("Number should be 10 digits");
                }

            }
        };

        TextWatcher passWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //none
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //none
            }

            @Override
            public void afterTextChanged(Editable s) {

                check = s.toString();

                if (check.length() < 4 || check.length() > 20) {
                    password.setError("Password Must consist of 4 to 20 characters");
                } else if (!check.matches("^[A-za-z0-9@]+")) {
                    password.setError("Only @ special character allowed");
                }
            }

        };

        TextWatcher cnfpassWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //none
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //none
            }

            @Override
            public void afterTextChanged(Editable s) {

                check = s.toString();

                if (!check.equals(confirmpass.getText().toString())) {
                    confirmpass.setError("Both the passwords do not match");
                }
            }

        };

        memnum.addTextChangedListener(numberwatcher);
        password.addTextChangedListener(passWatcher);
        confirmpass.addTextChangedListener(cnfpassWatcher);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerData();


            }
        });
    }

    public void registerData(){
        String mbl = memnum.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String memName = memname.getText().toString().trim();

        if (memName.isEmpty()){
            memname.setError("Name Required");
            return;
        }

        if (mbl.isEmpty()){
            memnum.setError("Number Required");
            return;
        }

        if (pass.isEmpty()){
            password.setError("Password Required");
            return;
        }



        sendRegisterData(mbl,pass,memName);

    }

    public void sendRegisterData(final String mobileNo, String password,String MemName){
        progress.showProgress();
        RetrofitApiInterface loginInterface = RetrofitClient.getRetrofit().create(RetrofitApiInterface.class);

        RequestBody requestBody = new MultipartBody
                .Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("MemName",MemName)
                .addFormDataPart("MobileNo",mobileNo)
                .addFormDataPart("Password",password)
                .addFormDataPart("Token",prefrenceClass.getToken())
                .build();

        loginInterface.register(requestBody).enqueue(new Callback<ResponseBody>() {
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
                            Toast.makeText(Register_activity.this, "Registered Failed", Toast.LENGTH_SHORT).show();
                        }else if (success.equals("2")){
                            try {
                                if (progress != null){
                                    progress.hideProgress();
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            Toast.makeText(Register_activity.this, "Number Already Exists", Toast.LENGTH_SHORT).show();

                        }else{
                            try {
                                if (progress != null){
                                    progress.hideProgress();
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            prefrenceClass.saveMobile(mobileNo);
                            Toast.makeText(Register_activity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    try {
                        if (progress != null){
                            progress.hideProgress();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(Register_activity.this, "Error in registration", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                try {
                    if (progress != null){
                        progress.hideProgress();
                        Toast.makeText(Register_activity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }


}
