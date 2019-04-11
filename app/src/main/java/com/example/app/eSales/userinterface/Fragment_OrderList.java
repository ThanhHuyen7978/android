/*package com.example.app.eSales.userinterface;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.app.eSales.CUSTOM_CUSTID_SlsperID;
import com.example.app.eSales.OM_SALESORD;
import com.example.app.eSales.R;
import com.example.app.eSales.adapter.Adapter_RecyclerView_OrderList;
import com.example.app.eSales.database.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Fragment_OrderList extends Fragment {

    private View view;
    private Fragment_OrderList_ViewHolder holder;

    public Fragment_OrderList() {
        super();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_orderlist, container, false);

        holder = new Fragment_OrderList_ViewHolder();
        set_ActionBar();

        if (savedInstanceState != null){

            holder.om_salesords = savedInstanceState.getParcelableArrayList("om_salesord");

        }else {

        }
        set_Data(); //Tải dữ liệu
        set_CurrentDate(); //set ngày
        count_Total(); //Tính tổng tiền
        set_RecyclerView();

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Custom_DialogFragment dialogFragment = new Custom_DialogFragment();
                holder.bundle.putInt("Type", 1);
                dialogFragment.setArguments(holder.bundle);
               dialogFragment.show(getActivity().getSupportFragmentManager(), "dialog_oderdate");
                dialogFragment.get_Data(new Custom_DialogFragment.onCallBack() {
                    @Override
                    public void onClose(String day, String month, String year) {
                        holder.order_Date = year + "-" + day + "-" + month;
                        holder.orderdate.setText(holder.order_Date);
                    }
                });
            }
        });

        holder.orderdate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter_OrderList(s.toString());
                holder.adapter_recyclerView_orderList.filterOrderList(holder.filter_salesords);
            }
        });


        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.order_list_menu, menu);

        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public void onResume() {
        super.onResume();

        set_ActionBar();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("om_salesord", holder.om_salesords);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.order_list_add:{
//                Toast.makeText(getActivity(), "Option add clicked", Toast.LENGTH_SHORT).show();
                holder.lineRef = holder.om_salesords.size() + 1;
                Bundle bundle = new Bundle();
                bundle.putInt("lineRef", holder.lineRef);
                bundle.putString("OrdDate", holder.orderdate.getText().toString());
                bundle.putParcelable("custID_slsperID", holder.id);
                Fragment_Order fragment_order = new Fragment_Order();
                fragment_order.setArguments(bundle);
                FragmentTransaction transaction = holder.fragmentManager.beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.replace(R.id.activity_contain_main, fragment_order, "Fragment_OrderList");
                transaction.addToBackStack("Fragment_OrderList");
                transaction.commit();

                break;
            }

            case R.id.back:{

//                Toast.makeText(getActivity(), "Option add clicked", Toast.LENGTH_SHORT).show();
                if (holder.fragmentManager.getBackStackEntryCount() > 0){
                    holder.fragmentManager.popBackStack("Fragment_CustomerList", 1);
                }

                break;

            }
        }
        return super.onOptionsItemSelected(item);
    }

//    Phương thức thiết lập AcionBar
    public void set_ActionBar(){

        holder.actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        holder.actionBar.setTitle("Danh sách đơn hàng");

    }

    //    Set data
    public ArrayList<OM_SALESORD> set_Data(){
        holder.om_salesords = holder.databaseHelper.getList_OM_SALESORD(holder.om_salesord, holder.om_salesords, holder.id.getCustID(), holder.id.getSlsperID());
        return holder.om_salesords;
    }

//    Set RecyclerView
    public void set_RecyclerView(){

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        holder.recyclerView.setLayoutManager(linearLayoutManager);
        holder.recyclerView.setHasFixedSize(true);
        holder.adapter_recyclerView_orderList = new Adapter_RecyclerView_OrderList(getActivity(), holder.om_salesords);
        holder.recyclerView.setAdapter(holder.adapter_recyclerView_orderList);

    }

//    Lọc danh sách đơn hàng
    public ArrayList<OM_SALESORD> filter_OrderList(String s){
        holder.filter_salesords = holder.databaseHelper.get_FilterList_OM_SALESORD(holder.om_salesord, holder.om_salesords, s, holder.id.getCustID(), holder.id.getSlsperID());

        return holder.om_salesords;
    }

//    Tính tổng tiền
    public void count_Total(){
        double total = 0;
        for (int i = 0; i < holder.om_salesords.size(); i++){
            total += holder.om_salesords.get(i).getmOrdAmt();
        }

        holder.total_Value.setText(String.valueOf(total));
    }

//    Set current date
    public void set_CurrentDate(){

        holder.simpleDateFormat = new SimpleDateFormat("yyyy-dd-MM");
        holder.date = Calendar.getInstance().getTime();
        holder.orderdate.setText(holder.simpleDateFormat.format(holder.date));

       holder.orderdate.setText(holder.om_salesords.get(0).getmOrderDate());
    }

//    Class lưu giữ Views
    public class Fragment_OrderList_ViewHolder{

        private ArrayList<OM_SALESORD> om_salesords;
        private ArrayList<OM_SALESORD> filter_salesords;
        private Adapter_RecyclerView_OrderList adapter_recyclerView_orderList;
        private OM_SALESORD om_salesord;
        private RecyclerView recyclerView;
        private TextView orderdate;
        private TextView total_Value;
        private Date date;
        private SimpleDateFormat simpleDateFormat;
        private CardView cardView;
        private Bundle bundle;
        private DatabaseHelper databaseHelper;
        private String order_Date;
        private ActionBar actionBar;
        private FragmentManager fragmentManager;
        private CUSTOM_CUSTID_SlsperID id;
        private int lineRef;

    public Fragment_OrderList_ViewHolder() {

        bundle = new Bundle();

        recyclerView = (RecyclerView) view.findViewById(R.id.orderlist_recyclerview);

        orderdate = (TextView) view.findViewById(R.id.tv_orderdate);

        total_Value = (TextView) view.findViewById(R.id.tv_total_value);

        cardView = (CardView) view.findViewById(R.id.cardview_oderdate);

        databaseHelper = new DatabaseHelper(getActivity());

        fragmentManager = getActivity().getSupportFragmentManager();

        id = new CUSTOM_CUSTID_SlsperID(getArguments().getString("ar_customer_CustID"), getArguments().getString("slsperID"));
    }
}

}*/
