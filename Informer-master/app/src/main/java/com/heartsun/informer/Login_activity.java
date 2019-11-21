package com.heartsun.informer;


import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Login_activity extends AppCompatActivity {

    TextView label1,label2,label3,login;
    ImageView prev,logo;
    EditText num,pwd;
    Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);

        login = findViewById(R.id.logolabel);

        prev = findViewById(R.id.preview);
        logo = findViewById(R.id.looogo);
        num = findViewById(R.id.phnum);
        pwd = findViewById(R.id.pwd);
        Login = findViewById(R.id.loginbtn);

Login.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
});

    }

}
