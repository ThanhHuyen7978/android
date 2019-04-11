/*package com.example.app.eSales.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//import com.example.app.eSales.AR_Customer;
import com.example.app.eSales.R;
import com.example.app.eSales.database.model.Customer;
//import com.example.app.eSales.userinterface.Fragment_OrderList;

import java.util.ArrayList;

public class Adapter_RecyclerView_CustomerList extends RecyclerView.Adapter<Adapter_RecyclerView_CustomerList.ViewHolder> {

    private ArrayList<Customer> ar_customers;
    private Context context;
    private View view;
    private Bundle bundle;
    public int id;

    public Adapter_RecyclerView_CustomerList(ArrayList<Customer> ar_customers, Context context) {
        this.ar_customers = ar_customers;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Customer ar_customer = ar_customers.get(position);
        holder.customer_number.setText(String.valueOf(position + 1 ));
        holder.customer_name.setText(ar_customer.getId());
        holder.customer_address.setText(ar_customer.getAddress());
        holder.customer_phone.setText(context.getResources().getText(R.string.col_phone_customer_text) + " " + ar_customer.getPhone());

        holder.setItemSelectedListener(new ItemSelectedListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
//                Toast.makeText(context, ar_customers.get(position).getmCustID() + "\n" +
//                        ar_customers.get(position).getmName() + "\n" +
//                ar_customers.get(position).getmAddress() + "\n" +
//                ar_customers.get(position).getmPhone(), Toast.LENGTH_SHORT).show();

                bundle = new Bundle();
                Fragment_OrderList fragment_orderList = new Fragment_OrderList();
                bundle.putString("slsperID", SlsperID);
                bundle.putString("ar_customer_CustID", ar_customers.get(position).getmCustID());
                bundle.putString("ar_customer_Name", ar_customers.get(position).getmName());
                bundle.putString("ar_customer_Address", ar_customers.get(position).getmAddress());
                bundle.putString("ar_customer_Phone", ar_customers.get(position).getmPhone());
                fragment_orderList.setArguments(bundle);
                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.replace(R.id.activity_contain_main, fragment_orderList, "Fragment_OrderList");
                transaction.addToBackStack("Fragment_CustomerList");
                transaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return ar_customers.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_recyclerview_customerlist, viewGroup, false);

        return new ViewHolder(view);
    }

//    Phương thức dùng để lọc danh sách
    public void filter_Customer(ArrayList<Customer> filter_Customers){
        this.ar_customers = filter_Customers;
        notifyDataSetChanged();
    }

    //    Class ViewHolder lưu giữ View
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView customer_number;
        private TextView customer_name;
        private TextView customer_address;
        private TextView customer_phone;
        private ItemSelectedListener itemSelectedListener;

    public void setItemSelectedListener(ItemSelectedListener itemSelectedListener) {
        this.itemSelectedListener = itemSelectedListener;
    }
//----
    public ViewHolder(@NonNull View itemView) {
            super(itemView);

            customer_number = (TextView) itemView.findViewById(R.id.col_number_customer);
            customer_name = (TextView) itemView.findViewById(R.id.col_name_customer);
            customer_address = (TextView) itemView.findViewById(R.id.col_address_customer);
            customer_phone = (TextView) itemView.findViewById(R.id.col_phone_customer);

            itemView.setOnClickListener(this);
        }

    @Override
    public void onClick(View v) {
        itemSelectedListener.onClick(v, getAdapterPosition(), false);
    }
}

//    Tạo interface bắt sự kiện onItemClick
    public interface ItemSelectedListener{
        void onClick(View view, int position, boolean isLongClick);
}

}
*/