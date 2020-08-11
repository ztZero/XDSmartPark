package org.zt.xdsmartpark.activity;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.zt.xdsmartpark.R;
import org.zt.xdsmartpark.model.request.LoginRequest;
import org.zt.xdsmartpark.model.response.LoginResponse;
import org.zt.xdsmartpark.network.Network;
import org.zt.xdsmartpark.utils.LocalHost;

public class LoginActivity extends AppCompatActivity {

    EditText Login_username;
    EditText Login_password1;
    Button Login_login_btn;
    TextView Login_exchange_btn;
    private Network network;
    SharedPreferences.Editor ShareEditor;

    String regEx = "[a-zA-Z0-9]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ShareEditor = getSharedPreferences("XDSmartPark",MODE_PRIVATE).edit();
        initWeight();

        initOnCilckListener();

        network = new Network(this);

    }

    private void initOnCilckListener() {

        Login_exchange_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRegister();
            }
        });

        Login_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = String.valueOf(Login_username.getText());
                String password = String.valueOf(Login_password1.getText());
                if(checkUsername(username,20) && checkPassword(password,regEx,20)){
                    //发送登录请求
                    final LoginRequest request = new LoginRequest(username, password);

                    network.Login(request, new Network.MyCallback<LoginResponse>() {
                        @Override
                        public void onSuccess(LoginResponse response) {
                            Toast.makeText(getApplication(), "登录成功", Toast.LENGTH_SHORT).show();

                            LocalHost.INSTANCE.setUserId(response.getUserId());
                            LocalHost.INSTANCE.setUserName(response.getUserName());
                            //数据持久化到SharePreference中
                            ShareEditor.putInt("userId",response.getUserId());
                            ShareEditor.putString("userName", response.getUserName());
                            ShareEditor.commit();
                            goToMain();
                        }

                        @Override
                        public void onError(String error) {
                            Toast.makeText(getApplication(), "登录失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    Toast.makeText(getApplication(), "请检查输入", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean checkUsername(String username , int maxLength){
        if (username.equals("")){
            Toast.makeText(getApplication(), "用户名不能为空", Toast.LENGTH_SHORT).show();
        }else if (username.length()>maxLength) {
            Toast.makeText(getApplication(), "用户名不能超过" + maxLength + "字符", Toast.LENGTH_SHORT).show();
        }else {
            return true;
        }
        return false;
    }

    private boolean checkPassword(String username , String regEx , int maxLength){
        if (username.equals("")){
            Toast.makeText(getApplication(), "密码不能为空", Toast.LENGTH_SHORT).show();
        }else if (username.length()>maxLength) {
            Toast.makeText(getApplication(), "密码不能超过" + maxLength + "字符", Toast.LENGTH_SHORT).show();
        }else if (!fitString(username,regEx)){
            Toast.makeText(getApplication(), "密码只能为英文字母与数字", Toast.LENGTH_SHORT).show();
        }else {
            return true;
        }
        return false;
    }

    private boolean fitString(String str, String regEx) {
        return str.replaceAll(regEx, "").length() == 0;
    }


    private void initWeight() {

        Login_username = (EditText) findViewById(R.id.Login_username);
        Login_password1 = (EditText) findViewById(R.id.Login_password1);
        Login_login_btn = (Button) findViewById(R.id.Login_login_btn);
        Login_exchange_btn = (TextView) findViewById(R.id.Login_exchange_btn);
    }

    private void goToMain() {
        Intent intentMain = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intentMain);
    }

    private void goToRegister() {
        Intent intentMain = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intentMain);
    }

}
