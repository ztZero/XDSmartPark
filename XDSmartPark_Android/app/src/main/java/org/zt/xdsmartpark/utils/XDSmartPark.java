package org.zt.xdsmartpark.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.LocationSource;

import org.zt.xdsmartpark.network.HP2P.cluster.MobileNode;
import org.zt.xdsmartpark.network.HP2P.entity.IPAddress;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Random;


public class XDSmartPark extends Application {

    public static Context APP_CONTEXT;

//    private OnLocationChangedListener mListener;
//    private AMapLocationClient mlocationClient;
//
//    private double currentLatitude;
//    private double currentLongitude;

//    public static final String HOST="http://192.168.1.8:8080/parking/client/";
//
//    public static final String BaiduAk="tca2mmjlwcGmPnRcbEu9t7n3mecqUYKa";

    // 读
    public static SharedPreferences sPreferences;
    // 写
    public static SharedPreferences.Editor sEditor;

    private MobileNode mobileNode;

    public MobileNode getMobileNode() {
        return mobileNode;
    }

    public void setMobileNode(MobileNode mobileNode) {
        this.mobileNode = mobileNode;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        LocalHost.INSTANCE.init();

        //读取SharePreference内数据到全局
        sPreferences = getSharedPreferences("XDSmartPark", MODE_PRIVATE);
        //sEditor = sPreferences.edit();
        int userIdTemp = sPreferences.getInt("userId", 0);
        String userNameTemp = sPreferences.getString("userName","");
        String userCarTemp = sPreferences.getString("userCar","");
        if (userIdTemp==0){
            LocalHost.INSTANCE.setUserId(0);
            LocalHost.INSTANCE.setUserName(null);
        }else {
            LocalHost.INSTANCE.setUserId(userIdTemp);
            LocalHost.INSTANCE.setUserName(userNameTemp);
        }
        LocalHost.INSTANCE.setCarPlate(userCarTemp);


        APP_CONTEXT=getApplicationContext();
//        VolleyManager.INSTANCE.initQueue(10<<10<<10);

        initMobileNode(APP_CONTEXT);

    }

    //初始化mobile节点
    public void initMobileNode(Context context){

        String ip=getMobileIp(context);
        int port=getAPort(context);
        IPAddress ipAddress=new IPAddress(ip,port);
        //对mobile节点的ip地址进行hash，得到nodeId
        int nodeId = ipAddress.hashCode();
        mobileNode=new MobileNode();
        mobileNode.setNodeId(nodeId);
        mobileNode.setIpAddress(ipAddress);
    }

    //得到mobile的ip地址
    public String getMobileIp(Context context){
        NetworkInfo info = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {//当前使用2G/3G/4G网络
                try {
                    //Enumeration<NetworkInterface> en=NetworkInterface.getNetworkInterfaces();
                    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                        NetworkInterface intf = en.nextElement();
                        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                            InetAddress inetAddress = enumIpAddr.nextElement();
                            if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                return inetAddress.getHostAddress();
                            }
                        }
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                }

            } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {//当前使用无线网络
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());//得到IPV4地址
                return ipAddress;
            }
        } else {
            //当前无网络连接,请在设置中打开网络
        }
        return null;
    }

    /**
     * 将得到的int类型的IP转换为String类型
     *
     * @param ip
     * @return
     */
    public static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);
    }



    /**
     * 判断某个端口是否被占用
     * @param port 端口
     * @return 占用还是没有被占用
     */
    public boolean IsPortInUse(int port,Context context)
    {
        try {

            Socket tcpSocket = new Socket(getMobileIp(context), port);
            tcpSocket.close();
            return true;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (java.net.ConnectException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            DatagramSocket udpSocket = new DatagramSocket(port);
            udpSocket.close();
            return true;
        } catch (SocketException e) {
            e.printStackTrace();
            return false;
        }

    }

    //region GetAPort
    int ran_start = 0;
    Object so = new Object();


    /**
     * 获得空闲的连续三个端口
     * @return 获取到的空闲端口
     */
    public int getAPort(Context context)
    {
        synchronized (so)
        {
            int portExpect;
            ran_start = (ran_start + 17) % 100000000;
            Random random = new Random(new Date().getTime() + ran_start);
            portExpect = 1024 + random.nextInt(48976);
//            while (IsPortInUse(portExpect) || IsPortInUse(portExpect + 1) || IsPortInUse(portExpect + 2))
//            {
//                portExpect = 1024 + random.nextInt(48976);
//            }
            return portExpect;
//            if(!IsPortInUse(portExpect,context)){
//                return portExpect;
//            }else {
//                return getAPort(context);
//            }
        }
    }

}
