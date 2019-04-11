/*package com.example.app.eSales.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.app.eSales.OM_SALESORD;
import com.example.app.eSales.R;

import java.util.ArrayList;

public class Adapter_RecyclerView_OrderList extends RecyclerView.Adapter<Adapter_RecyclerView_OrderList.Adapter_RecyclerView_OrderList_ViewHolder> {

    private Context context;
    private ArrayList<OM_SALESORD> om_salesords;
    private View view;

    public Adapter_RecyclerView_OrderList(Context context, ArrayList<OM_SALESORD> om_salesords) {
        this.context = context;
        this.om_salesords = om_salesords;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_RecyclerView_OrderList_ViewHolder holder, int position) {
        OM_SALESORD om_salesord = om_salesords.get(position);
        holder.mOrderList_Number.setText(String.valueOf(position + 1));
        holder.mOrderList_Name.setText(om_salesord.getmOrderNbr());
        holder.mOrderList_Money.setText(String.valueOf(om_salesord.getmOrdAmt()));
    }

    @Override
    public int getItemCount() {
        return om_salesords.size();
    }

    @NonNull
    @Override
    public Adapter_RecyclerView_OrderList_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_recyclerview_orderlist, viewGroup, false);

        return new Adapter_RecyclerView_OrderList_ViewHolder(view);
    }

//    Phương thức lọc dữ liệu
    public void filterOrderList(ArrayList<OM_SALESORD> om_salesords){
        this.om_salesords = om_salesords;
        notifyDataSetChanged();
    }

    //    Class lưu giữ Views
    public class Adapter_RecyclerView_OrderList_ViewHolder extends RecyclerView.ViewHolder{

        private TextView mOrderList_Number;
        private TextView mOrderList_Name;
        private TextView mOrderList_Money;

    public Adapter_RecyclerView_OrderList_ViewHolder(@NonNull View itemView) {
        super(itemView);

        mOrderList_Number = (TextView) itemView.findViewById(R.id.col_number_orderlist);
        mOrderList_Name = (TextView) itemView.findViewById(R.id.col_name_orderlist);
        mOrderList_Money = (TextView) itemView.findViewById(R.id.col_money_orderlist);
    }
}
}
*/