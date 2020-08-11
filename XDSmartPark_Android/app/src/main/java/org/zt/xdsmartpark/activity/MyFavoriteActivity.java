package org.zt.xdsmartpark.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import org.zt.xdsmartpark.R;
import org.zt.xdsmartpark.adapter.UserFavoriteListAdapter;
import org.zt.xdsmartpark.model.request.GetParkLocationRequest;
import org.zt.xdsmartpark.model.request.UserFavoriteDeleteRequest;
import org.zt.xdsmartpark.model.request.UserFavoriteListRequest;
import org.zt.xdsmartpark.model.response.GetParkLocationResponse;
import org.zt.xdsmartpark.model.response.UserFavoriteResponse;
import org.zt.xdsmartpark.network.Network;
import org.zt.xdsmartpark.utils.LocalHost;

import java.util.ArrayList;
import java.util.List;

public class MyFavoriteActivity extends AppCompatActivity implements AMapLocationListener {

    ListView favoriteList;

    private List<UserFavoriteResponse> datas;
    private UserFavoriteListAdapter adapter;
    private Network network;

    UserFavoriteListRequest userFavoriteListRequest;
    UserFavoriteDeleteRequest userFavoriteDeleteRequest;
    GetParkLocationRequest getParkLocationRequest;

    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;

    private double currentLatitude = 0.0;
    private double currentLongitude = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favorite);

        network = new Network(this);

        favoriteList = (ListView) findViewById(R.id.favoriteList);

        datas = new ArrayList<>();
        adapter = new UserFavoriteListAdapter(this,datas);
        favoriteList.setAdapter(adapter);

        //初始化请求
        userFavoriteListRequest = new UserFavoriteListRequest();

        userFavoriteDeleteRequest = new UserFavoriteDeleteRequest();

        getParkLocationRequest = new GetParkLocationRequest();

        itemOnClickListener();

        FavoriteParkListRequestShow();


        locationClient = new AMapLocationClient(this.getApplicationContext());
        locationOption = new AMapLocationClientOption();
        // 设置定位模式为高精度模式
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        // 设置定位监听
        locationClient.setLocationListener(this);
        locationClient.startLocation();

    }

    private void FavoriteParkListRequestShow() {

        userFavoriteListRequest.setUserId(LocalHost.INSTANCE.getUserId());
        network.UserFavoriteList(userFavoriteListRequest, new Network.MyCallback<List<UserFavoriteResponse>>() {
            @Override
            public void onSuccess(List<UserFavoriteResponse> response) {
                datas.clear();
                datas.addAll(response);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getApplication(), "获取停车场信息失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void itemOnClickListener() {
        favoriteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //点击事件，一键导航
                getParkLocationRequest.setParkId(datas.get(i).getParkId());
                network.GetParkLocation(getParkLocationRequest, new Network.MyCallback<GetParkLocationResponse>() {
                    @Override
                    public void onSuccess(GetParkLocationResponse response) {
                        double endLatitude = response.getLatitude();
                        double endLongitude = response.getLongitude();
                        startNavi(endLatitude,endLongitude);
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(getApplication(), "登录失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        favoriteList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //长按事件，取消收藏该停车场
                comfirmdeleteFavorite(i);
                return true;
            }
        });

    }

    private void comfirmdeleteFavorite(final int i){
        AlertDialog.Builder isExitLog = new AlertDialog.Builder(MyFavoriteActivity.this);
        isExitLog.setTitle("提示")
                .setMessage("确定删除此收藏？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        deleteFavorite(i);
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    private void deleteFavorite(int i) {

        userFavoriteDeleteRequest.setFavoriteId(datas.get(i).getFavoriteId());
        userFavoriteDeleteRequest.setUserId(LocalHost.INSTANCE.getUserId());
        network.UserFavoriteDelete(userFavoriteDeleteRequest, new Network.MyCallback<String>() {
            @Override
            public void onSuccess(String response) {
                Toast.makeText(getApplication(), "删除停车场成功", Toast.LENGTH_SHORT).show();
                FavoriteParkListRequestShow();

            }

            @Override
            public void onError(String error) {
                Toast.makeText(getApplication(), "删除停车场失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startNavi(double endLatitude, double endLongitude) {
        final Intent intent = new Intent(MyFavoriteActivity.this, NaviActivity.class);
        //发送当前位置
        intent.putExtra("currentLatitude", currentLatitude);
        intent.putExtra("currentLongitude", currentLongitude);
        //发送目标Marker位置
        intent.putExtra("endLatitude", endLatitude);
        intent.putExtra("endLongitude", endLongitude);
        //创建提示框
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("提示")
                .setMessage("选择导航模式")
                .setPositiveButton("实时导航", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        intent.putExtra("isGPSNaviMode", true);
                        startActivityForResult(intent, 1);
                    }
                })
                .setNegativeButton("取消", null)
                .setNeutralButton("模拟导航", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        intent.putExtra("isGPSNaviMode", false);
                        startActivityForResult(intent, 1);
                    }
                })
                .show();
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
