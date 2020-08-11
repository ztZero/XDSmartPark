package org.zt.xdsmartpark.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.MyLocationStyle;

import org.zt.xdsmartpark.R;
import org.zt.xdsmartpark.activity.ParkingLotInfoActivity;
import org.zt.xdsmartpark.adapter.ParkInfoAdapter;
import org.zt.xdsmartpark.model.request.NeighborSearchRequest;
import org.zt.xdsmartpark.model.response.ParkInfoResponse;
import org.zt.xdsmartpark.network.HP2P.cluster.MobileNode;
import org.zt.xdsmartpark.network.Network;
import org.zt.xdsmartpark.utils.XDSmartPark;

import java.util.List;


public class NeighborFragment extends Fragment implements LocationSource, AMapLocationListener{


    private OnLocationChangedListener mListener;
    private AMapLocationClient mlocationClient;

    private MapView mapView;
    //地图实例
    private AMap aMap;

    //定位方向

    private double currentLatitude;
    private double currentLongitude;

    private Network network;
    private NeighborSearchRequest neighborSearchRequest;
    private ParkInfoAdapter adapter;
    private Context mContext;
    private List<ParkInfoResponse> parkInfoResponseList;

    private XDSmartPark xdSmartPark;
    private MobileNode mobileNode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_neighbor, container, false);

        //初始化页面内控件
        initWeight(view);
        //获得地图实例
        mapView.onCreate(savedInstanceState);

        if (aMap == null) {
            aMap = mapView.getMap();
        }
        //图层显示模式
        aMap.setMapType(AMap.MAP_TYPE_NAVI);
        //交通路况图层显示
        aMap.setTrafficEnabled(true);

//        xdSmartPark=new XDSmartPark();
        xdSmartPark=(XDSmartPark)getActivity().getApplication();
        mobileNode=xdSmartPark.getMobileNode();

        //初始化定位服务
        initLocation();

        initView(view);

        return view;
    }



    private void initWeight(View view) {
        //地图控件
        mapView = (MapView) view.findViewById(R.id.map_view);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == 1) {
                    Log.d("导航完成", "做点什么");
                    //TODO:自动弹出窗口询问是否要扫码入库
                } else {
                    Log.d("导航停止返回", "doSomething");
                }
        }
    }

    private void initLocation() {
        // 自定义系统定位小蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        // 设置小蓝点的图标
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory
                .fromResource(R.mipmap.navi_map_gps_locked));
        // 设置圆形的边框颜色
        myLocationStyle.strokeColor(Color.argb(100, 0, 0, 180));
        // 设置圆形的填充颜色
        myLocationStyle.radiusFillColor(Color.argb(100, 0, 0, 180));
        //myLocationStyle.anchor(0.5,0.5);//设置小蓝点的锚点
        // 设置圆形的边框粗细
        myLocationStyle.strokeWidth(1.0f);
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setLocationSource(this);// 设置定位监听
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.getUiSettings().setCompassEnabled(true);
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        // aMap.setMyLocationType()
        aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
        deactivate();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    //定位成功后回调函数
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {
            if (amapLocation != null
                    && amapLocation.getErrorCode() == 0) {
                //float x = amapLocation.getBearing();
                mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
                currentLatitude = amapLocation.getLatitude();
                currentLongitude = amapLocation.getLongitude();
            } else {
                String errText = "定位失败," + amapLocation.getErrorCode() + ": " + amapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }
    }

    //激活定位
    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;

        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(getActivity());
            AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
            mLocationOption.setOnceLocation(false);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();
        }
    }

    //停止定位
    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    private void initView(View view) {
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
                    i.putExtra("ip",parkInfoResponseList.get(pos).getIpAddress().getPort());
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
        System.out.println("***********************************");
        System.out.println(mobileNode.toString());
        if(mobileNode.isOnline()){
            network = new Network(getContext(),mobileNode.getSuperNodeList().get(0).getIpAddress());
        }else{
            network=new Network(getContext());
        }
        neighborSearchRequest=new NeighborSearchRequest();
        mContext=getActivity();
        NeighborSearchListRequestShow();
    }

    private void NeighborSearchListRequestShow() {
        neighborSearchRequest.setLatitude(currentLatitude);
        neighborSearchRequest.setLongitude(currentLongitude);
        network.NeighborSearch(neighborSearchRequest, new Network.MyCallback<List<ParkInfoResponse>>() {
            @Override
            public void onSuccess(List<ParkInfoResponse> response) {
                parkInfoResponseList=response;
                adapter.setData(response);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(mContext, "获取附近停车场信息失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
