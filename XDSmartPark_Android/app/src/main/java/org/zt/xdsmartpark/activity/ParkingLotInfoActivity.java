package org.zt.xdsmartpark.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import org.zt.xdsmartpark.R;

public class ParkingLotInfoActivity extends AppCompatActivity implements AMapLocationListener {

    private ImageView parkingLotPhoto;
    private TextView parkingLotName;
    private TextView parkingLotAddress;
    private TextView parkingLotScore;
    private TextView parkingSpace;
    private TextView parkingLotFee;
    private Button bookBtn;
    private Button naviBtn;
    private int parkId;
    private String parkName;
    private double latitude;
    private double longitude;
    private double score;
    private double parkFee;
    private String parkAddress;
    private int parkSpace;
    private String ip;
    private int port;

    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;

    private double currentLatitude = 0.0;
    private double currentLongitude = 0.0;

    int[] imageResources={
            R.mipmap.parklot0,R.mipmap.parklot1,R.mipmap.parklot2,R.mipmap.parklot3,R.mipmap.parklot4,R.mipmap.parklot5,R.mipmap.parklot6,
            R.mipmap.parklot7,R.mipmap.parklot8,R.mipmap.parklot9,R.mipmap.parklot10,R.mipmap.parklot11,R.mipmap.parklot12,R.mipmap.parklot13
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_lot_info);
        Intent intent=getIntent();
        parkId=intent.getIntExtra("parkId",1);
        parkName= intent.getStringExtra("parkingLotName");
        latitude=intent.getDoubleExtra("latitude",0.0);
        longitude=intent.getDoubleExtra("longitude",0.0);
        score=intent.getDoubleExtra("score",4.5);
        parkFee=intent.getDoubleExtra("parkFee",2.0);
        parkAddress= intent.getStringExtra("parkAddress");
        parkSpace=intent.getIntExtra("parkSpace",20);
        ip=intent.getStringExtra("ip");
        port=intent.getIntExtra("port",8080);
        initView();
        initOnClickListener();

        locationClient = new AMapLocationClient(this.getApplicationContext());
        locationOption = new AMapLocationClientOption();
        // 设置定位模式为高精度模式
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        // 设置定位监听
        locationClient.setLocationListener(this);
        locationClient.startLocation();
    }

    private void initView(){
        parkingLotPhoto=findViewById(R.id.parkingLotPhoto);
        parkingLotName=findViewById(R.id.parkingLotName);
        parkingLotAddress=findViewById(R.id.parkingLotAddress);
        parkingLotScore=findViewById(R.id.parkingLotScore);
        parkingSpace=findViewById(R.id.parkingSpace);
        parkingLotFee=findViewById(R.id.parkingLotFee);
        bookBtn=findViewById(R.id.book_btn);
        naviBtn=findViewById(R.id.navi_btn);
        parkingLotName.setText(parkName);
        parkingLotAddress.setText(parkAddress);
        parkingLotScore.setText("停车场评分:"+score);
        parkingSpace.setText("剩余车位:"+parkSpace);
        parkingLotFee.setText("停车费:"+parkFee+"元/小时");
        parkingLotPhoto.setImageResource(imageResources[parkId]);

    }

    private void initOnClickListener(){
        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ParkingLotInfoActivity.this, BookActivity.class);
                i.putExtra("parkId",parkId);
                i.putExtra("parkName",parkName);
                i.putExtra("ip",ip);
                i.putExtra("port",port);
                startActivity(i);
            }
        });
        naviBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNavi(latitude,longitude);
            }
        });
    }

    private void startNavi(double endLatitude, double endLongitude) {
        final Intent intent = new Intent(ParkingLotInfoActivity.this, NaviActivity.class);
        //发送当前位置
        intent.putExtra("currentLatitude", currentLatitude);
        intent.putExtra("currentLongitude", currentLongitude);
        //发送目标Marker位置
        intent.putExtra("endLatitude", endLatitude);
        intent.putExtra("endLongitude", endLongitude);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        locationClient.startLocation();
    }

    @Override
    public void onPause() {
        super.onPause();
        locationClient.stopLocation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != locationClient) {
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0){
                currentLatitude = aMapLocation.getLatitude();//获取纬度
                currentLongitude = aMapLocation.getLongitude();//获取经度
            }else {
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
            }
        }
    }


}
