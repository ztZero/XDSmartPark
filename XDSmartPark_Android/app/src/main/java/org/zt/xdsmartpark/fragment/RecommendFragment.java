package org.zt.xdsmartpark.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import org.zt.xdsmartpark.R;
import org.zt.xdsmartpark.activity.ParkingLotInfoActivity;
import org.zt.xdsmartpark.adapter.ParkInfoAdapter;
import org.zt.xdsmartpark.model.request.RecommendRequest;
import org.zt.xdsmartpark.model.response.ParkInfoResponse;
import org.zt.xdsmartpark.network.Network;
import org.zt.xdsmartpark.utils.LocalHost;

import java.util.List;

public class RecommendFragment extends Fragment{

    private Toolbar toolbar;
    private Network network;
    private RecommendRequest recommendRequest;
    private ParkInfoAdapter adapter;
    private Context mContext;
    private List<ParkInfoResponse> parkInfoResponseList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_recommend, container, false);
        initView(view);
        return view;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void RecommendListRequestShow() {
        recommendRequest.setUserId(LocalHost.INSTANCE.getUserId());
        network.Recommend(recommendRequest, new Network.MyCallback<List<ParkInfoResponse>>() {
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



    public RecommendFragment() {
        // Required empty public constructor
    }




    private void initView(View view) {
//        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setVerticalScrollBarEnabled(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        adapter=new ParkInfoAdapter(getActivity());
        mRecyclerView.setAdapter(adapter);


        adapter.setOnItemClickListener(new ParkInfoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {

                Intent i = new Intent(getActivity(), ParkingLotInfoActivity.class);
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
                    i.putExtra("port",parkInfoResponseList.get(pos).getIpAddress().getPort());
                }else{
                    i.putExtra("parkId",1);
                    i.putExtra("parkingLotName","西安火车站停车场");
                    i.putExtra("latitude",34.277618);
                    i.putExtra("longitude",108.961831);
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
        network = new Network(getContext());
        recommendRequest=new RecommendRequest();
        mContext=getActivity();
        RecommendListRequestShow();
    }


}
