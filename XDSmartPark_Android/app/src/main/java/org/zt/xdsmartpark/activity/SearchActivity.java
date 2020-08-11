package org.zt.xdsmartpark.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import org.zt.xdsmartpark.R;
import org.zt.xdsmartpark.adapter.ParkInfoAdapter;
import org.zt.xdsmartpark.model.request.SearchRequest;
import org.zt.xdsmartpark.model.response.ParkInfoResponse;
import org.zt.xdsmartpark.network.HP2P.cluster.MobileNode;
import org.zt.xdsmartpark.network.Network;
import org.zt.xdsmartpark.utils.XDSmartPark;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private Network network;
    private SearchRequest searchRequest;
    private ParkInfoAdapter adapter;
    private Context mContext;
    private List<ParkInfoResponse> parkInfoResponseList;
    private String searchText;
    private XDSmartPark xdSmartPark;
    private MobileNode mobileNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        xdSmartPark=(XDSmartPark)getApplication();
        mobileNode=xdSmartPark.getMobileNode();
        initView();
        Intent intent=new Intent();
        searchText=intent.getStringExtra("searchText");
    }

    private void SearchListRequestShow() {
        searchRequest.setSearchContent(searchText);
        network.Search(searchRequest, new Network.MyCallback<List<ParkInfoResponse>>() {
            @Override
            public void onSuccess(List<ParkInfoResponse> response) {
                parkInfoResponseList=response;
                adapter.setData(response);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(mContext, "获取停车场信息失败", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void initView() {
        RecyclerView mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setVerticalScrollBarEnabled(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        adapter=new ParkInfoAdapter(this);
        mRecyclerView.setAdapter(adapter);


        adapter.setOnItemClickListener(new ParkInfoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {

                Intent i = new Intent(SearchActivity.this, ParkingLotInfoActivity.class);
                if(parkInfoResponseList!=null){
                    i.putExtra("parkId",parkInfoResponseList.get(pos).getParkId());
                    i.putExtra("parkingLotName",parkInfoResponseList.get(pos).getParkName());
                    i.putExtra("latitude",parkInfoResponseList.get(pos).getLatitude());
                    i.putExtra("longitude",parkInfoResponseList.get(pos).getLongitude());
                    i.putExtra("score",parkInfoResponseList.get(pos).getScore());
                    i.putExtra("parkFee",parkInfoResponseList.get(pos).getParkFee());
                    i.putExtra("parkAddress",parkInfoResponseList.get(pos).getParkAddress());
                    i.putExtra("parkSpace",parkInfoResponseList.get(pos).getParkSpace());
                    i.putExtra("ip",parkInfoResponseList.get(pos).getIpAddress().getIp());
                    i.putExtra("ip",parkInfoResponseList.get(pos).getIpAddress().getPort());
                }else{
                    i.putExtra("parkId",1);
                    i.putExtra("parkingLotName","西安火车站停车场");
                    i.putExtra("latitude",108.96);
                    i.putExtra("longitude",34.27);
                    i.putExtra("score",4.5);
                    i.putExtra("parkFee",2.0);
                    i.putExtra("parkAddress","陕西省西安市新城区西安站");
                    i.putExtra("parkSpace",67);
                    i.putExtra("ip","");
                    i.putExtra("port",0);
                }
                startActivity(i);
            }
        });

        if(mobileNode.isOnline()){
            network = new Network(this,mobileNode.getSuperNodeList().get(0).getIpAddress());
        }else{
            network=new Network(this);
        }
        searchRequest=new SearchRequest();
        mContext=this;
        SearchListRequestShow();
    }


}
