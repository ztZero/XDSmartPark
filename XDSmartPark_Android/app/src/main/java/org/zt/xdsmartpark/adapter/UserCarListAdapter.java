package org.zt.xdsmartpark.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.zt.xdsmartpark.R;
import org.zt.xdsmartpark.model.response.CarListResponse;

import java.util.List;


public class UserCarListAdapter extends BaseAdapter {

    private List<CarListResponse> datas;
    private Context context;
    private LayoutInflater mInflate;

    public UserCarListAdapter(Context context, List<CarListResponse> datas) {
        this.context = context;
        this.datas = datas;
        mInflate= LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public CarListResponse getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh=null;
        if (view==null){
            view=mInflate.inflate(R.layout.mycar_item,viewGroup,false);
            vh=new ViewHolder(view);
            view.setTag(vh);
        }else {
            vh= (ViewHolder) view.getTag();
        }

        vh.mCar.setText(getItem(i).getCarPlate());

        return view;
    }


    static class ViewHolder{
        public TextView mCar;

        public ViewHolder(View v) {
            mCar = (TextView) v.findViewById(R.id.MyCarString);

        }
    }


}
