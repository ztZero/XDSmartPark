package org.zt.xdsmartpark.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.zt.xdsmartpark.R;
import org.zt.xdsmartpark.adapter.UserCarListAdapter;
import org.zt.xdsmartpark.model.request.CarDeleteRequest;
import org.zt.xdsmartpark.model.request.CarListRequest;
import org.zt.xdsmartpark.model.response.CarListResponse;
import org.zt.xdsmartpark.network.Network;
import org.zt.xdsmartpark.utils.LocalHost;

import java.util.ArrayList;
import java.util.List;

public class MyCarActivity extends AppCompatActivity{

    ListView MyCaritem;
    CarListRequest carListRequest;
    CarDeleteRequest carDeleteRequest;
    Button addCarBtn;

    private List<CarListResponse> datas;
    private UserCarListAdapter adapter;

    private SharedPreferences.Editor editor;
    private Network network;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_car);
        network = new Network(this);

        MyCaritem = (ListView) findViewById(R.id.MyCaritem);
        addCarBtn = (Button) findViewById(R.id.addCarBtn);

        datas = new ArrayList<>();
        adapter = new UserCarListAdapter(this,datas);
        MyCaritem.setAdapter(adapter);

        carDeleteRequest = new CarDeleteRequest();

        editor = getSharedPreferences("XDSmartPark", MODE_PRIVATE).edit();

        itemOnClickListener();

        carListRequestShow();

        addCarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyCarActivity.this, MyCarAddActivity.class);
                startActivityForResult(intent, 1);
            }
        });

    }

    private void itemOnClickListener() {
        //点击选中
        MyCaritem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CarListResponse carListResponse = datas.get(i);
                LocalHost.INSTANCE.setCarPlate(carListResponse.getCarPlate());
                //持久化存储
                editor.putString("carPlate", carListResponse.getCarPlate());
                editor.apply();
                //全局变量
//                LocalHost.INSTANCE.setCarPlate(carListResponse.getCarPlate());
                setResult(200);
                finish();
            }
        });
        //长按删除
        MyCaritem.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                deleteCar(i);
                return true;
            }
        });
    }

    private void deleteCar(final int i) {
        AlertDialog.Builder isExitLog = new AlertDialog.Builder(MyCarActivity.this);
        isExitLog.setTitle("提示")
                .setMessage("确定删除此车牌？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        userCarDelete(i);
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    private void userCarDelete(int i) {

        CarListResponse userCar = datas.get(i);

        carDeleteRequest.setUserId(LocalHost.INSTANCE.getUserId());
        carDeleteRequest.setCarId(userCar.getCarId());
        if (userCar.getCarPlate().equals(LocalHost.INSTANCE.getCarPlate())){
            editor.remove("carPlate");
            editor.apply();
            LocalHost.INSTANCE.setCarPlate("");
            setResult(300);
        }
        network.CarDelete(carDeleteRequest, new Network.MyCallback<String>() {
            @Override
            public void onSuccess(String response) {
                Toast.makeText(getApplication(), "删除车辆成功", Toast.LENGTH_SHORT).show();
                carListRequestShow();

            }

            @Override
            public void onError(String error) {
                Toast.makeText(getApplication(), "删除车辆失败", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void carListRequestShow() {
        carListRequest = new CarListRequest();
        carListRequest.setUserId(LocalHost.INSTANCE.getUserId());
        network.CarList(carListRequest, new Network.MyCallback<List<CarListResponse>>() {
            @Override
            public void onSuccess(List<CarListResponse> response) {
                datas.clear();
                datas.addAll(response);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getApplication(), "获取车辆信息失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
