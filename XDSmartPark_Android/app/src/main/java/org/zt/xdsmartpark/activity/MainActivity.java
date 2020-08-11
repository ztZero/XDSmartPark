package org.zt.xdsmartpark.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.StrictMode;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.ferfalk.simplesearchview.SimpleSearchView;
import com.ferfalk.simplesearchview.utils.DimensUtils;

import org.zt.xdsmartpark.R;
import org.zt.xdsmartpark.fragment.BlankFragment;
import org.zt.xdsmartpark.fragment.MeFragment;
import org.zt.xdsmartpark.fragment.NeighborFragment;
import org.zt.xdsmartpark.fragment.RecommendFragment;
import org.zt.xdsmartpark.network.HP2P.cluster.MobileNode;
import org.zt.xdsmartpark.network.HP2P.protocol.Protocol;
import org.zt.xdsmartpark.utils.LocalHost;
import org.zt.xdsmartpark.utils.XDSmartPark;


public class MainActivity extends AppCompatActivity implements OnClickListener, AMapLocationListener {

    private LinearLayout mTabNeighbor;
    private LinearLayout mTabSearch;
    private LinearLayout mTabMe;

    private ImageButton mImgNeighbor;
    private ImageButton mImgSearch;
    private ImageButton mImgMe;

    private TextView mFontNeighbor;
    private TextView mFontSearch;
    private TextView mFontMe;

    private Fragment mTabFragNeighbor;
    private Fragment mTabFragSearch;
    private Fragment mTabFragMe;

    private ImageButton scanBtn;

    private EditText searchEditText;
    private String searchText;
    private SimpleSearchView simpleSearchView;

    private final int fontColorUnSelect = 0xff272636;
    private final int fontColorBeSelect = 0xff11CD6E;

    private static final int REQUEST_CODE_SCAN = 0x0000;// 扫描二维码

    public static final int EXTRA_REVEAL_CENTER_PADDING = 40;
    private SimpleSearchView searchView;

    private double currentLatitude = 0.0;
    private double currentLongitude = 0.0;
    private Protocol protocol;
    public XDSmartPark xdSmartPark;
    public MobileNode mobileNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        xdSmartPark=(XDSmartPark)getApplication();
        mobileNode=xdSmartPark.getMobileNode();
        protocol=new Protocol();
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchView = findViewById(R.id.searchView);

        searchEditText=findViewById(R.id.searchEditText);
        searchText=String.valueOf(searchEditText.getText());
//        simpleSearchView=new SimpleSearchView(this);

        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //跳转activity
                    Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                    if(searchText!=null){
                        intent.putExtra("searchText",searchText);
                    }else{
                        intent.putExtra("searchText","西安火车站");
                    }
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        initView();
        initEvent();
        setSelect(0);
    }


    private void initEvent() {
        mTabNeighbor.setOnClickListener(this);
        mTabSearch.setOnClickListener(this);
        mTabMe.setOnClickListener(this);
        mImgNeighbor.setOnClickListener(this);
        mImgSearch.setOnClickListener(this);
        mImgMe.setOnClickListener(this);
    }

    private void initView() {
        mTabNeighbor =findViewById(R.id.Tab_Neighbor);
        mTabSearch =findViewById(R.id.Tab_Search);
        mTabMe =  findViewById(R.id.Tab_Me);

        mImgNeighbor = findViewById(R.id.Img_Neighbor);
        mImgSearch = findViewById(R.id.Img_Search);
        mImgMe = findViewById(R.id.Img_Me);

        mFontNeighbor = findViewById(R.id.Font_Neighbor);
        mFontSearch = findViewById(R.id.Font_Search);
        mFontMe =  findViewById(R.id.Font_Me);

        scanBtn = findViewById(R.id.scanBtn);

        scanBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (LocalHost.INSTANCE.getUserId()==0){
//                    Toast.makeText(MainActivity.this,"请先登录", Toast.LENGTH_LONG).show();
//                }else{
//                    Intent i = new Intent(MainActivity.this,ScanQRCodeActivity.class);
//                    Intent i = new Intent(MainActivity.this,ScanActivity.class);
//                    startActivity(i);
//                }
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
                } else {
                    goScan();
                }
            }
        });

    }

    private void setSelect(int i) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        switch (i) {
            case 0:
                if (mTabFragNeighbor == null) {
                    mTabFragNeighbor = new NeighborFragment();
                    transaction.add(R.id.id_content, mTabFragNeighbor);

                } else {
                    transaction.show(mTabFragNeighbor);
                }
                mFontNeighbor.setTextColor(fontColorBeSelect);
                mImgNeighbor.setImageResource(R.mipmap.neighbor_press);
                break;
            case 1:
                if (mTabFragSearch == null) {
                    mTabFragSearch = new RecommendFragment();
                    transaction.add(R.id.id_content, mTabFragSearch);
                } else {
                    transaction.show(mTabFragSearch);
                }
                mFontSearch.setTextColor(fontColorBeSelect);
                mImgSearch.setImageResource(R.mipmap.search_press);
                break;
            case 2:
                if (mTabFragMe == null) {
                    mTabFragMe = new MeFragment();
                    transaction.add(R.id.id_content, mTabFragMe);
                } else {
                    transaction.show(mTabFragMe);
                }
                mFontMe.setTextColor(fontColorBeSelect);
                mImgMe.setImageResource(R.mipmap.me_press);
                break;

            default:
                break;
        }

        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (mTabFragNeighbor != null) {
            transaction.hide(mTabFragNeighbor);
        }
        if (mTabFragSearch != null) {
            transaction.hide(mTabFragSearch);
        }
        if (mTabFragMe != null) {
            transaction.hide(mTabFragMe);
        }
    }

    @Override
    public void onClick(View v) {
        resetImgs();
        resetFonts();
        switch (v.getId())
        {
            case R.id.Tab_Neighbor:
                setSelect(0);
                break;
            case R.id.Img_Neighbor:
                setSelect(0);
                break;
            case R.id.Tab_Search:
                setSelect(1);
                break;
            case R.id.Img_Search:
                setSelect(1);
                break;
            case R.id.Tab_Me:
                setSelect(2);
                break;
            case R.id.Img_Me:
                setSelect(2);
            default:
                break;
        }
    }

    private void resetFonts() {
        mFontNeighbor.setTextColor(fontColorUnSelect);
        mFontSearch.setTextColor(fontColorUnSelect);
        mFontMe.setTextColor(fontColorUnSelect);
    }

    private void resetImgs() {
        mImgNeighbor.setImageResource(R.mipmap.neighbor);
        mImgSearch.setImageResource(R.mipmap.search);
        mImgMe.setImageResource(R.mipmap.me);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            AlertDialog.Builder isExit = new AlertDialog.Builder(MainActivity.this);
            isExit.setTitle("提示")
                    .setMessage("确定退出软件？")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .setNegativeButton("取消", null)
                    .show();
        }

        return false;
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        setupSearchView(menu);
        setupHP2P(menu);

        return true;
    }

    private void setupSearchView(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        // Adding padding to the animation because of the hidden menu item
        Point revealCenter = searchView.getRevealAnimationCenter();
        revealCenter.x -= DimensUtils.convertDpToPx(EXTRA_REVEAL_CENTER_PADDING, this);
    }

    private void setupHP2P(Menu menu) {
        MenuItem item = menu.findItem(R.id.HP2P);
        item.setOnMenuItemClickListener(new MyMenuItemClickListener());
    }


    public class MyMenuItemClickListener implements MenuItem.OnMenuItemClickListener {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            //加入HP2P网络
            protocol.joinCluster(MainActivity.this,mobileNode);
            return true;

        }
    }

    //获取mobile节点当前的经纬度值
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
        mobileNode.setLatitude(currentLatitude);
        mobileNode.setLongitude(currentLongitude);

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(currentLatitude);
        System.out.println(currentLongitude);
        System.out.println(mobileNode.toString());
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

    }

    @Override
    public void onBackPressed() {
        if (searchView.onBackPressed()) {
            return;
        }

        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (searchView.onActivityResult(requestCode, resultCode, data)) {
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_CODE_SCAN:// 二维码
                // 扫描二维码回传
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        //获取扫描结果
                        Bundle bundle = data.getExtras();
                        String result = bundle.getString(ScanActivity.EXTRA_STRING);
//                        tv_scanResult.setText("扫描结果：" + result);
                    }
                }
                break;
            default:
                break;
        }

    }

    /**
     * 跳转到扫码界面扫码
     */
    private void goScan() {
        Intent intent = new Intent(MainActivity.this, ScanActivity.class);
        startActivityForResult(intent, REQUEST_CODE_SCAN);
    }

    private void goParkinfo(){
        Intent intent = new Intent(MainActivity.this, ParkingLotInfoActivity.class);
        startActivity(intent);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    goScan();
                } else {
                    Toast.makeText(this, "你拒绝了权限申请，可能无法打开相机扫码哟！", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

}
