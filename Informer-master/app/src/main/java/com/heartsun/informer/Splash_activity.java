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



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activity);

        prefrenceClass = new SharedPrefrenceClass(getApplicationContext());

        getToken();

        logo = (ImageView)findViewById(R.id.logo);
        title = (TextView)findViewById(R.id.appname);

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
                startActivity(new Intent(getApplicationContext(),Login_activity.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

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