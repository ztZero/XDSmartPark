package org.zt.xdsmartpark.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.zt.xdsmartpark.R;
import org.zt.xdsmartpark.model.request.BookRequest;
import org.zt.xdsmartpark.network.HP2P.cluster.MobileNode;
import org.zt.xdsmartpark.network.HP2P.entity.IPAddress;
import org.zt.xdsmartpark.network.Network;
import org.zt.xdsmartpark.utils.LocalHost;
import org.zt.xdsmartpark.utils.XDSmartPark;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BookActivity extends AppCompatActivity {


    private TextView parkingLotName;
    TextView bookDate;
    TextView bookTime;
    Button bookBtn;
    private Network network;

    private BookRequest bookRequest;

    private int parkId;
    private String parkName;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private XDSmartPark xdSmartPark;
    private MobileNode mobileNode;

    private String ip;
    private int port;
    private IPAddress ipAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        xdSmartPark=(XDSmartPark)getApplication();
        mobileNode=xdSmartPark.getMobileNode();
        parkingLotName = findViewById(R.id.parkingLotName);
        bookDate = findViewById(R.id.bookDate);
        bookTime = findViewById(R.id.bookTime);
        bookBtn = findViewById(R.id.bookActionBtn);

        initCalendar();

        initOnClickListener();
        Intent intent=getIntent();
        parkId=intent.getIntExtra("parkId",1);
        parkName=intent.getStringExtra("parkName");
        ip=intent.getStringExtra("ip");
        port=intent.getIntExtra("port",8080);
        parkingLotName.setText(parkName);
        ipAddress=new IPAddress(ip,port);
        if(mobileNode.isOnline()){
            network = new Network(this,ipAddress);
        }else{
            network = new Network(this);
        }
        bookRequest=new BookRequest();
    }


    private void initCalendar() {
        Calendar mycalendar= Calendar.getInstance(Locale.CHINA);
        Date mydate=new Date(); //获取当前日期Date对象
        mycalendar.setTime(mydate);////为Calendar对象设置时间为当前日期

        year=mycalendar.get(Calendar.YEAR); //获取Calendar对象中的年
        month=mycalendar.get(Calendar.MONTH);//获取Calendar对象中的月
        day=mycalendar.get(Calendar.DAY_OF_MONTH);//获取这个月的第几天
        hour=mycalendar.get(Calendar.HOUR_OF_DAY);
        minute=mycalendar.get(Calendar.MINUTE);
        bookDate.setText(year+"-"+(month+1)+"-"+day); //显示当前的年月日
        bookTime.setText(hour+":"+minute);
    }

    private void initOnClickListener() {

        bookDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dpd=new DatePickerDialog(BookActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        year = i;
                        month = i1;
                        day = i2;
                        bookDate.setText(year+"-"+(month+1)+"-"+day); //显示当前的年月日
                    }
                }, year, month, day);
                dpd.show();
            }
        });
        bookTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog tpd = new TimePickerDialog(BookActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        hour = i;
                        minute = i1;
                        if (minute<10){
                            bookTime.setText(hour+":0"+minute);
                        }else {
                            bookTime.setText(hour+":"+minute);
                        }
                    }
                },hour,minute,true);
                tpd.show();
            }
        });
        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO：组装timestamp，markId，userid,---->post到数据库返回Toast显示
                bookRequest.orderTime = new Timestamp(year-1900,month,day,hour,minute,0,0);
                bookRequest.userId = LocalHost.INSTANCE.getUserId();
                bookRequest.parkId = parkId;
                network.Book(bookRequest, new Network.MyCallback<String>() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getApplication(), "预约成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(getApplication(), "预约失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

}
