package org.zt.xdsmartpark.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.zt.xdsmartpark.R;
import org.zt.xdsmartpark.model.request.CarAddRequest;
import org.zt.xdsmartpark.network.Network;
import org.zt.xdsmartpark.utils.LocalHost;


public class MyCarAddActivity extends AppCompatActivity {

    private EditText addCarTextBelong;
    private EditText addCarTextNum;
    private Button addCarConfirmBtn;
    private CarAddRequest carAddRequest;
    private Network network;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_car_add);
        network = new Network(this);

        addCarTextBelong = (EditText) findViewById(R.id.addCarTextBelong);
        addCarTextNum = (EditText) findViewById(R.id.addCarTextNum);
        addCarConfirmBtn = (Button) findViewById(R.id.addCarConfirmBtn);

        carAddRequest = new CarAddRequest();

        addCarConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String belong = String.valueOf(addCarTextBelong.getText());
                String num = String.valueOf(addCarTextNum.getText());
                if (!validation(belong,num)){
                    Toast.makeText(MyCarAddActivity.this,"信息有误。请按照要求填写车牌信息", Toast.LENGTH_LONG).show();
                }else{
                    //发送新增车牌请求
                    carAddRequest.setUserId(LocalHost.INSTANCE.getUserId());
                    carAddRequest.setCarPlate(belong +" "+ num);
                    network.CarAdd(carAddRequest, new Network.MyCallback<String>() {
                        @Override
                        public void onSuccess(String response) {
                            Toast.makeText(getApplication(), "添加车辆成功", Toast.LENGTH_SHORT).show();
                            //goToLogin();
                            //goToMyCarActivity();
                        }

                        @Override
                        public void onError(String error) {
                            Toast.makeText(getApplication(), "添加车辆失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private boolean validation(String belong, String num) {

        String rgxx = "^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$";

        String car = belong + num;

        return car.matches(rgxx);
    }

//    private void goToMyCarActivity() {
//        Intent intentMain = new Intent(MyCarAddActivity.this, MyCarActivity.class);
//        startActivity(intentMain);
//    }
}
