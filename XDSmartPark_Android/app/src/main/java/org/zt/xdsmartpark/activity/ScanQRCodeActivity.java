package org.zt.xdsmartpark.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.PointF;
import android.os.Bundle;
import android.widget.Toast;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

import org.zt.xdsmartpark.R;

public class ScanQRCodeActivity extends AppCompatActivity implements QRCodeReaderView.OnQRCodeReadListener{

    private QRCodeReaderView mydecoderview;
//    private ScanQRRequest scanQRRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_code);

        mydecoderview = (QRCodeReaderView) findViewById(R.id.qrdecoderview);
        mydecoderview.setOnQRCodeReadListener(this);

//        scanQRRequest = new ScanQRRequest();
//        scanQRRequest.setOnResponseListener(this);
//        scanQRRequest.setRequestType(0);
    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {

        mydecoderview.getCameraManager().stopPreview();
        //Toast.makeText(scanQRCodeActivity.this, text, Toast.LENGTH_LONG).show();
        String logid = null;
        String markerid = null;
        try {
            logid = text.split(",")[0];
            markerid = text.split(",")[1];
        } catch (Exception e) {
            Toast.makeText(ScanQRCodeActivity.this, "这不是有效的二维码", Toast.LENGTH_LONG).show();
            Toast.makeText(ScanQRCodeActivity.this, "但是二维码内容是："+text, Toast.LENGTH_LONG).show();
            finish();
        }

//        if (logid.equals("")||markerid.equals("")){
//
//        }else{
//        scanQRRequest.logid = logid;
//        scanQRRequest.userid = LocalHost.INSTANCE.getUserid();
//        scanQRRequest.carid = LocalHost.INSTANCE.getUserCar();
//        scanQRRequest.markerid = markerid;
//        scanQRRequest.post();
        //}

    }

    @Override
    public void cameraNotFound() {

    }

    @Override
    public void QRCodeNotFoundOnCamImage() {
        //Toast.makeText(scanQRCodeActivity.this,"图像未识别",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mydecoderview.getCameraManager().startPreview();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mydecoderview.getCameraManager().stopPreview();
    }
}
