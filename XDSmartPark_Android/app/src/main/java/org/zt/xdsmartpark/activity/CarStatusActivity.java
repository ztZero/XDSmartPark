package org.zt.xdsmartpark.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.zt.xdsmartpark.R;
import org.zt.xdsmartpark.adapter.CarStatusAdapter;
import org.zt.xdsmartpark.model.request.CarStatusRequest;
import org.zt.xdsmartpark.model.response.CarStatusResponse;
import org.zt.xdsmartpark.network.Network;
import org.zt.xdsmartpark.utils.LocalHost;

import java.util.ArrayList;
import java.util.List;

public class CarStatusActivity extends AppCompatActivity{

    ListView MyParkingStatus;
    CarStatusRequest carStatusRequest;
    List<CarStatusResponse> datas;
    CarStatusAdapter adapter;
    private Network network;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_parking_status);
        network = new Network(this);

        MyParkingStatus = (ListView) findViewById(R.id.MyParkingStatus);

        datas = new ArrayList<>();
        adapter = new CarStatusAdapter(this,datas);
        MyParkingStatus.setAdapter(adapter);

        carStatusRequest = new CarStatusRequest(LocalHost.INSTANCE.getUserId());
        network.CarStatus(carStatusRequest, new Network.MyCallback<List<CarStatusResponse>>() {
            @Override
            public void onSuccess(List<CarStatusResponse> response) {
                datas.clear();
                datas.addAll(response);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getApplication(), "获取车辆状态信息失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
