/*package com.example.app.eSales.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.app.eSales.CUSTOM_INVENTORY;
import com.example.app.eSales.R;

import java.util.ArrayList;

public class Adapter_RecyclerView_Order extends RecyclerView.Adapter<Adapter_RecyclerView_Order.Adapter_RecyclerView_Order_ViewHolder>{

    private Context context;
    private ArrayList<CUSTOM_INVENTORY> custom_inventories;
    private View view;

    public Adapter_RecyclerView_Order(Context context, ArrayList<CUSTOM_INVENTORY> custom_inventories) {
        this.context = context;
        this.custom_inventories = custom_inventories;
    }

    @NonNull
    @Override
    public Adapter_RecyclerView_Order_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_recyclerview_orders, viewGroup, false);


        return new Adapter_RecyclerView_Order_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_RecyclerView_Order_ViewHolder holder, int i) {

        CUSTOM_INVENTORY custom_inventory = custom_inventories.get(i);
        holder.mNumber.setText(String.valueOf(i + 1));
        holder.mInvtID.setText(custom_inventory.getmInvtID());
        holder.mName.setText(custom_inventory.getmName());
        holder.mPrice.setText(custom_inventory.getmPrice());
        holder.mAmount.setText(custom_inventory.getmAmount());
        holder.mTotalMoney.setText(custom_inventory.getmTotalMoney());

    }

    @Override
    public int getItemCount() {
        return custom_inventories.size();
    }

//    Phương thức làm mới dữ liệu
    public void reload_Data(ArrayList<CUSTOM_INVENTORY> custom_inventories){

        this.custom_inventories = custom_inventories;
        notifyDataSetChanged();

    }

//    Class lưu giữ views
    public class Adapter_RecyclerView_Order_ViewHolder extends RecyclerView.ViewHolder{

        private TextView mNumber;
        private TextView mInvtID;
        private TextView mName;
        private TextView mPrice;
        private TextView mAmount;
        private TextView mTotalMoney;

    public Adapter_RecyclerView_Order_ViewHolder(@NonNull View itemView) {
        super(itemView);

        mNumber = (TextView) view.findViewById(R.id.col_number_order);
        mInvtID = (TextView) view.findViewById(R.id.col_id_order);
        mName = (TextView) view.findViewById(R.id.col_name_order);
        mPrice = (TextView) view.findViewById(R.id.col_price_order);
        mAmount = (TextView) view.findViewById(R.id.col_amount_order);
        mTotalMoney = (TextView) view.findViewById(R.id.col_money_order);
    }
}
}
*/