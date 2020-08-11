package org.zt.xdsmartpark.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.zt.xdsmartpark.R;
import org.zt.xdsmartpark.adapter.UserParkingLogAdapter;
import org.zt.xdsmartpark.model.request.ParkingLogRequest;
import org.zt.xdsmartpark.model.response.ParkingLogResponse;
import org.zt.xdsmartpark.network.Network;
import org.zt.xdsmartpark.utils.LocalHost;

import java.util.ArrayList;
import java.util.List;

public class MyParkinglogActivity extends AppCompatActivity {

    private ParkingLogRequest parkingLogRequest;
    private ListView parkingLogList;
    private Network network;

    private List<ParkingLogResponse> datas;
    private UserParkingLogAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_parkinglog);
        network = new Network(this);
        parkingLogList = (ListView) findViewById(R.id.parkingLogList);
        datas = new ArrayList<>();
        adapter = new UserParkingLogAdapter(this,datas);
        parkingLogList.setAdapter(adapter);

        parkingLogRequest = new ParkingLogRequest(LocalHost.INSTANCE.getUserId());
        network.ParkingLog(parkingLogRequest, new Network.MyCallback<List<ParkingLogResponse>>() {
            @Override
            public void onSuccess(List<ParkingLogResponse> response) {
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
