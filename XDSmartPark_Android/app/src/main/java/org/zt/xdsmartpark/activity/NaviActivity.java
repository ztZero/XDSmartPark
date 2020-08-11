package org.zt.xdsmartpark.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.amap.api.navi.AMapNaviView;
import com.amap.api.navi.AMapNaviViewOptions;
import com.amap.api.navi.enums.NaviType;
import com.amap.api.navi.model.NaviLatLng;

import org.zt.xdsmartpark.R;

public class NaviActivity extends BaseActivity {

    private double startLatitude;
    private double startLongitude;
    private double endLatitude;
    private double endLongitude;
//    private NaviLatLng mStartLatlng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi);

        mAMapNaviView = (AMapNaviView) findViewById(R.id.navi_view);
        mAMapNaviView.onCreate(savedInstanceState);
        mAMapNaviView.setAMapNaviViewListener(this);

        AMapNaviViewOptions options = new AMapNaviViewOptions();
        options.setScreenAlwaysBright(false);
        mAMapNaviView.setViewOptions(options);

        Intent intent = getIntent();
        startLatitude = intent.getDoubleExtra("currentLatitude", 0.0);
        startLongitude = intent.getDoubleExtra("currentLongitude",0.0);

        endLatitude = intent.getDoubleExtra("endLatitude",0.0);
        endLongitude = intent.getDoubleExtra("endLongitude",0.0);
        Toast.makeText(this, "jingdu"+endLatitude+",weidu"+endLongitude, Toast.LENGTH_SHORT).show();

        mStartLatlng = new NaviLatLng(startLatitude, startLongitude);
        mEndLatlng = new NaviLatLng(endLatitude, endLongitude);
//        sList.add(mStartLatlng);
//        eList.add(mEndLatlng);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAMapNaviView.onResume();
        sList.add(mStartLatlng);
        eList.add(mEndLatlng);
    }

    @Override
    public void onInitNaviSuccess() {
        super.onInitNaviSuccess();
        /**
         * 方法: int strategy=mAMapNavi.strategyConvert(congestion, avoidhightspeed, cost, hightspeed, multipleroute); 参数:
         *
         * @congestion 躲避拥堵
         * @avoidhightspeed 不走高速
         * @cost 避免收费
         * @hightspeed 高速优先
         * @multipleroute 多路径
         *
         *  说明: 以上参数都是boolean类型，其中multipleroute参数表示是否多条路线，如果为true则此策略会算出多条路线。
         *  注意: 不走高速与高速优先不能同时为true 高速优先与避免收费不能同时为true
         */
        int strategy = 0;
        try {
            //再次强调，最后一个参数为true时代表多路径，否则代表单路径
            strategy = mAMapNavi.strategyConvert(true, false, false, false, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mAMapNavi.setCarNumber("京", "DFZ588");
        mAMapNavi.calculateDriveRoute(sList, eList, mWayPointList, strategy);

    }

    @Override
    public void onCalculateRouteSuccess(int[] ids) {
        super.onCalculateRouteSuccess(ids);
        mAMapNavi.startNavi(NaviType.GPS);
    }
}
