package org.zt.xdsmartpark.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import org.zt.xdsmartpark.R;
import org.zt.xdsmartpark.model.response.ParkInfoResponse;

import java.util.ArrayList;
import java.util.List;

public class ParkInfoAdapter extends RecyclerView.Adapter<ParkInfoAdapter.LinearViewHolder>{


    private Context mContext;
    private List<ParkInfoResponse> parkInfoResponseList=new ArrayList<>();
    private OnItemClickListener mListener;

    int[] imageResources={
            R.mipmap.parklot0,R.mipmap.parklot1,R.mipmap.parklot2,R.mipmap.parklot3,R.mipmap.parklot4,R.mipmap.parklot5,R.mipmap.parklot6,
            R.mipmap.parklot7,R.mipmap.parklot8,R.mipmap.parklot9,R.mipmap.parklot10,R.mipmap.parklot11,R.mipmap.parklot12,R.mipmap.parklot13
    };

    public ParkInfoAdapter(Context context){
        this.mContext=context;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener=listener;
    }

    @Override
    public ParkInfoAdapter.LinearViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.layout_parkinglot,null);
        LinearViewHolder vh=new LinearViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ParkInfoAdapter.LinearViewHolder holder, int position) {

        if(null==parkInfoResponseList){
            Toast.makeText(mContext, "获取停车场信息失败", Toast.LENGTH_SHORT).show();
            holder.parkingLotPhoto.setImageResource(imageResources[position]);
        }else{
            //holder.textView.setText(dataList.get(position));
            holder.parkingLotName.setText(parkInfoResponseList.get(position).getParkName());
            holder.parkingLotAddress.setText(parkInfoResponseList.get(position).getParkAddress());
            String parkSpace="剩余停车位："+parkInfoResponseList.get(position).getParkSpace();
            holder.parkingSpace.setText(parkSpace);

            holder.parkingLotPhoto.setImageResource(imageResources[parkInfoResponseList.get(position).getParkId()]);
        }
        if(mListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(mContext, "点击事件", Toast.LENGTH_SHORT).show();
                    mListener.onItemClick(position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if(null==this.parkInfoResponseList){
            return 2;
        }
        return this.parkInfoResponseList.size();
    }

    public void setData(List<ParkInfoResponse> dataList) {
        if (null != dataList) {
            if(null!=this.parkInfoResponseList){
                this.parkInfoResponseList.clear();
            }
            this.parkInfoResponseList.addAll(dataList);
            notifyDataSetChanged();
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int pos);
    }

    class LinearViewHolder extends RecyclerView.ViewHolder {

        private ImageView parkingLotPhoto;
        private TextView parkingLotName;
        private TextView parkingLotAddress;
        private TextView parkingSpace;


        public LinearViewHolder(View itemView){
            super(itemView);
            parkingLotPhoto=itemView.findViewById(R.id.parkingLotPhoto);
            parkingLotAddress=itemView.findViewById(R.id.parkingLotAddress);
            parkingLotName=itemView.findViewById(R.id.parkingLotName);
            parkingSpace=itemView.findViewById(R.id.parkingSpace);
        }

    }
}
