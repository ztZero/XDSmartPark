package org.zt.xdsmartpark.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.zt.xdsmartpark.R;
import org.zt.xdsmartpark.activity.LoginActivity;
import org.zt.xdsmartpark.activity.UploadDynamicTrafficActivity;
import org.zt.xdsmartpark.activity.MyCarActivity;
import org.zt.xdsmartpark.activity.MyFavoriteActivity;
import org.zt.xdsmartpark.activity.MyOrderActivity;
import org.zt.xdsmartpark.activity.CarStatusActivity;
import org.zt.xdsmartpark.activity.MyParkinglogActivity;
import org.zt.xdsmartpark.utils.LocalHost;


public class MeFragment extends Fragment {

    Button loginBtn;
    LinearLayout userInfoLy;
    TextView userName;
    LinearLayout CarStatus;
    LinearLayout MyCarLy;
    TextView MyCarNum;
    LinearLayout MyOrderLy;
    LinearLayout MyFavoriteLy;
    LinearLayout MyParkingLy;
    LinearLayout DynamicTraffic;
    Button logoutBtn;

    int isLoginUserId = 0;
    String isLoginUserName = null;
    String isLoginCarPlate = null;
    boolean isLoginUserState = false;

    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        //初始化控件
        initWeight(view);

        initOnCilckListener();
        System.out.println(LocalHost.INSTANCE.getUserId());
        if (LocalHost.INSTANCE.getUserId()!= 0){
            //检测是否已经登录
            changeToLogin();
        }

        return view;
    }

    //处理登录成功之后的操作
    private void changeToLogin() {
        loginBtn.setVisibility(View.GONE);
        isLoginUserId = LocalHost.INSTANCE.getUserId();
        isLoginUserName = LocalHost.INSTANCE.getUserName();
        isLoginCarPlate = LocalHost.INSTANCE.getCarPlate();
        userName.setText(isLoginUserName);


        userInfoLy.setVisibility(View.VISIBLE);
        logoutBtn.setVisibility(View.VISIBLE);
        //更新车牌
        if (isLoginCarPlate.equals("")){
            Toast.makeText(getActivity(),"请设置您的车牌", Toast.LENGTH_LONG).show();
            MyCarNum.setText("点击设置");
        }else {
            MyCarNum.setText(isLoginCarPlate);
        }
        MyCarNum.setVisibility(View.VISIBLE);
    }

    //处理退出登录之后操作
    private void changeToUnLogin() {
//        //向服务发送退出登录请求
        //清空本地变量
        isLoginUserName = null;
        isLoginUserId = 0;
        isLoginCarPlate = "";
        //清空全局变量
        LocalHost.INSTANCE.setUserId(0);
        LocalHost.INSTANCE.setUserName(null);
        LocalHost.INSTANCE.setCarPlate("");
        //清空SharePreference
        editor.remove("userId");
        editor.remove("userName");
        editor.remove("carPlate");
        editor.commit();
        //布局操作
        userInfoLy.setVisibility(View.GONE);
        logoutBtn.setVisibility(View.GONE);
        MyCarNum.setVisibility(View.GONE);
        loginBtn.setVisibility(View.VISIBLE);
    }

    private void initOnCilckListener() {

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });
        CarStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLoginUserId != 0) {
                    checkCarStatus();
                } else {
                    userLogin();
                }
            }
        });
        MyCarLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLoginUserId != 0){
                    setMyCar();
                }else {
                    userLogin();
                }

            }
        });
        MyOrderLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkMyOrder();
            }
        });
        MyFavoriteLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLoginUserId != 0){
                    checkMyFavorite();
                }else {
                    userLogin();
                }
            }
        });
        MyParkingLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLoginUserId != 0){
                    myParkingLog();
                }else {
                    userLogin();
                }
            }
        });
        DynamicTraffic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadDynamicTraffic();
            }
        });
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder isExitLog = new AlertDialog.Builder(getActivity());
                isExitLog.setTitle("提示")
                        .setMessage("确定退出登录？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                changeToUnLogin();
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
            }
        });
    }

    private void uploadDynamicTraffic() {
        Intent intent = new Intent(getActivity(), UploadDynamicTrafficActivity.class);
        startActivity(intent);
    }

    private void myParkingLog() {
        Intent intent = new Intent(getActivity(), MyParkinglogActivity.class);
        startActivity(intent);
    }

    private void checkMyFavorite() {
        Intent intent = new Intent(getActivity(), MyFavoriteActivity.class);
        startActivity(intent);
    }

    private void checkMyOrder() {
        Intent i = new Intent(getActivity(), MyOrderActivity.class);
        startActivity(i);
    }

    private void setMyCar() {
        Intent intent = new Intent(getActivity(), MyCarActivity.class);
        startActivityForResult(intent, 2);
    }

    private void checkCarStatus() {

        Intent i = new Intent(getActivity(), CarStatusActivity.class);
        startActivity(i);
    }

    private void userLogin() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivityForResult(intent,1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                //处理LoginActivity返回的信息
                if (resultCode == 200){
                    changeToLogin();
                }
                break;
            case 2:
                if (resultCode == 200){
                    MyCarNum.setText(LocalHost.INSTANCE.getCarPlate());
                }else if (resultCode == 300){
                    MyCarNum.setText("未设置车牌");
                }
                break;
        }
    }

    private void initWeight(View view) {

        loginBtn = (Button) view.findViewById(R.id.loginBtn);
        userInfoLy = (LinearLayout) view.findViewById(R.id.userInfoLy);
        userName = (TextView) view.findViewById(R.id.userName);
        CarStatus= (LinearLayout) view.findViewById(R.id.CarStatus);
        MyCarLy = (LinearLayout) view.findViewById(R.id.MyCarLy);
        MyCarNum = (TextView) view.findViewById(R.id.MyCarNum);
        MyOrderLy = (LinearLayout) view.findViewById(R.id.MyOrderLy);
        MyFavoriteLy = (LinearLayout) view.findViewById(R.id.MyFavoriteLy);
        MyParkingLy = (LinearLayout) view.findViewById(R.id.MyParkingLy);
        DynamicTraffic = (LinearLayout) view.findViewById(R.id.DynamicTraffic);
        logoutBtn = (Button) view.findViewById(R.id.logoutBtn);

        editor = getActivity().getSharedPreferences("XDSmartPark", Context.MODE_PRIVATE).edit();

    }
}
