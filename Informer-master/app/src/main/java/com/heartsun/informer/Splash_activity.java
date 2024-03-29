package com.heartsun.informer;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class Splash_activity extends AppCompatActivity {
    ImageView logo;
    TextView title;
    SharedPrefrenceClass prefrenceClass;
    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activity);

        prefrenceClass = new SharedPrefrenceClass(getApplicationContext());

        getToken();

        logo = findViewById(R.id.logo);
        title = findViewById(R.id.appname);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        Animation anticlock = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anticlock);
        logo.setAnimation(animation);
        title.setAnimation(anticlock);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
                try {
                    if (!prefrenceClass.getMobile().equals("")){
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }else{
                        startActivity(new Intent(getApplicationContext(),Login_activity.class));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        try{
            count = prefrenceClass.getCloseApp();
            count = count + 1;
            prefrenceClass.closeApp(count);

            if (prefrenceClass.getCloseApp() == 10){
                finishAffinity();
            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public void getToken(){
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("Token", "getInstanceId failed", task.getException());

                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();


                        // Log and toast
                        String msg = getString(R.string.msg_token_fmt, token);
                        prefrenceClass.saveToken(token);
                        Log.d("Message", msg);
                        System.out.println("Token : "+token);
//                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    }
                });

    }
}