/*package com.example.app.eSales.userinterface;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.eSales.CUSTOM_CUSTID_SlsperID;
import com.example.app.eSales.CUSTOM_INVENTORY;
import com.example.app.eSales.R;
import com.example.app.eSales.adapter.Adapter_RecyclerView_Order;
import com.example.app.eSales.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.Random;

public class Fragment_Order extends Fragment {

    private View view;
    private Fragment_Order_ViewHolder holder;
    private int REQUEST_CODE = 0;
    private ArrayList<CUSTOM_INVENTORY> custom_inventories;
    private ArrayList<CUSTOM_INVENTORY> temporary_inventories;
    private CUSTOM_CUSTID_SlsperID id;
    private Adapter_RecyclerView_Order adapter_recyclerView_order;

    public Fragment_Order() {
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

        view = inflater.inflate(R.layout.fragment_order, container, false);
        holder = new Fragment_Order_ViewHolder();
        set_ActionBar();

        if (savedInstanceState != null){

            custom_inventories = new ArrayList<>();
            custom_inventories = savedInstanceState.getParcelableArrayList("custom_inventories");

        }else {

            if (custom_inventories == null){

                custom_inventories = new ArrayList<>();

            }


        }

        adapter_recyclerView_order = new Adapter_RecyclerView_Order(getActivity(), custom_inventories);
        set_RecyclerView(); //Thiết lập RecyclerView
        set_TotalMoney();



//        Bắt sự kiện nút thêm đơn hàng
        holder.add_Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                FragmentTransaction transaction = holder.fragmentManager.beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                holder.fragment_addItem.setTargetFragment(Fragment_Order.this, REQUEST_CODE);
                transaction.replace(R.id.activity_contain_main, holder.fragment_addItem, "Fragment_AddItem");
                transaction.addToBackStack("Fragment_Order");
                transaction.commit();

            }
        });

//        Xử lý sự kiện chốt đơn hàng
      holder.confirm_Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(getActivity(), "Confirm button clicked", Toast.LENGTH_SHORT).show();
                if (custom_inventories.size() > 0){
                    double money = 0;
                    for (int i = 0; i < custom_inventories.size(); i++){

                        money+= Double.valueOf(custom_inventories.get(i).getmTotalMoney());
                    }
                    getOderNbrs();
                    id = getArguments().getParcelable("custID_slsperID");
                    holder.OderNbr = random_OrderNbr();
                    holder.LineRef = String.valueOf(getArguments().getInt("lineRef"));
                    holder.OrdDate = getArguments().getString("OrdDate");

                    holder.databaseHelper.insert_OM_SALESORDET(holder.OderNbr, holder.LineRef, custom_inventories);

                    holder.databaseHelper.insert_OM_SALESORD(
                            holder.OderNbr,
                            id,
                            custom_inventories,
                            holder.mTotalMoney.getText().toString(),
                            money,
                            holder.OrdDate,
                            holder.mRemark.getText().toString());

                    holder.fragmentManager.popBackStack("Fragment_OrderList", 1);
                }else {

                    Toast.makeText(getActivity(), "Đơn hàng rỗng", Toast.LENGTH_SHORT).show();

                }
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        set_ActionBar();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_back_forward, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.back:{

                if (holder.fragmentManager.getBackStackEntryCount() > 0){

                    holder.fragmentManager.popBackStack("Fragment_OrderList", 1);

                }

                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList("custom_inventories", custom_inventories);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK){

            if (requestCode == REQUEST_CODE){
                holder.bundle = data.getExtras().getBundle("BUNDLE");
                temporary_inventories = holder.bundle.getParcelableArrayList("custom_inventories");

                if (custom_inventories.size() == 0 && temporary_inventories.size() > 0){

                    for (int i = 0; i < temporary_inventories.size(); i++){

                        custom_inventories.add(new CUSTOM_INVENTORY(
                                temporary_inventories.get(i).getmInvtID(),
                                temporary_inventories.get(i).getmName(),
                                temporary_inventories.get(i).getmUnit(),
                                temporary_inventories.get(i).getmPrice(),
                                temporary_inventories.get(i).getmAmount(),
                                temporary_inventories.get(i).getmTotalMoney()
                        ));

                    }

                }else {

                    if (temporary_inventories.size() > 0){

                        int flag = 0;

                        for (int i = 0; i < temporary_inventories.size(); i++){

                            for (int j = 0; j < custom_inventories.size(); j++){

                                if (custom_inventories.get(j).getmInvtID().equals(temporary_inventories.get(i).getmInvtID()) /*&&
                                        Double.valueOf(custom_inventories.get(j).getmAmount()) < Double.valueOf(temporary_inventories.get(i).getmAmount())){

                                    custom_inventories.remove(j);

                                    custom_inventories.add(new CUSTOM_INVENTORY(
                                            temporary_inventories.get(i).getmInvtID(),
                                            temporary_inventories.get(i).getmName(),
                                            temporary_inventories.get(i).getmUnit(),
                                            temporary_inventories.get(i).getmPrice(),
                                            temporary_inventories.get(i).getmAmount(),
                                            temporary_inventories.get(i).getmTotalMoney()
                                    ));

                                    flag++;
                                    break;

                                }
                            }

                            if (flag == 0){

                                custom_inventories.add(new CUSTOM_INVENTORY(
                                        temporary_inventories.get(i).getmInvtID(),
                                        temporary_inventories.get(i).getmName(),
                                        temporary_inventories.get(i).getmUnit(),
                                        temporary_inventories.get(i).getmPrice(),
                                        temporary_inventories.get(i).getmAmount(),
                                        temporary_inventories.get(i).getmTotalMoney()
                                ));

                            }

                            flag = 0;
                        }

                    }
                }

                adapter_recyclerView_order.reload_Data(custom_inventories);

            }
        }

    }

    //    Phương thức thiết lập ActionBar
    public void set_ActionBar(){

        holder.actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        holder.actionBar.setTitle("Thông tin đơn hàng");

    }

//    Phương thức thiết lập RecyclerView
    public void set_RecyclerView(){

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        holder.recyclerView.setLayoutManager(linearLayoutManager);
        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setAdapter(adapter_recyclerView_order);
    }

//    Phương thức set TextView Tổng tiền
    public void set_TotalMoney(){
        double total = 0;
        for (int i = 0; i < custom_inventories.size(); i++){

            total += Double.valueOf(custom_inventories.get(i).getmTotalMoney());
        }

        holder.mTotalMoney.setText(String.valueOf(total));
    }

//    Phương thức lấy danh sách mã đơn hàng đã tồn tại
    public ArrayList<String> getOderNbrs(){

        holder.OderNbrs = holder.databaseHelper.get_OderNbrs(holder.OderNbrs);

        return holder.OderNbrs;
    }

//    Phương thức Random mã đơn hàng
    public String random_OrderNbr(){

        Random rd = new Random();
        int flag = 0;

        do {

            if (holder.OderNbrs.size() > 0){

                holder.OderNbr = String.valueOf(rd.nextInt(10000));

                for (int i = 0; i < holder.OderNbrs.size(); i++){

                    if (holder.OderNbrs.get(i).equals(holder.OderNbr)){
                        flag++;
                    }
                }
            }

        }while (flag != 0);

        return holder.OderNbr;
    }

//    Class lưu giữ views
    public class Fragment_Order_ViewHolder{

        private Button add_Order;
        private Button confirm_Order;
        private EditText mRemark;
        private TextView mTotalMoney;
        private Bundle bundle;
        private Fragment_AddItem fragment_addItem;
        private RecyclerView recyclerView;
        private ActionBar actionBar;
        private FragmentManager fragmentManager;
        private DatabaseHelper databaseHelper;
        private ArrayList<String> OderNbrs;
        private String OderNbr;
        private String LineRef;
        private String OrdDate;

    public Fragment_Order_ViewHolder() {

        mRemark = (EditText) view.findViewById(R.id.edt_remark_order);
        add_Order = (Button) view.findViewById(R.id.btn_add_order);
        confirm_Order = (Button) view.findViewById(R.id.btn_confirm_order);
        mTotalMoney = (TextView) view.findViewById(R.id.tv_money_order);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_order);
        fragment_addItem = new Fragment_AddItem();
        fragmentManager = getActivity().getSupportFragmentManager();
        databaseHelper = new DatabaseHelper(getActivity());

    }
}
}
*/