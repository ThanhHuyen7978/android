/*package com.example.app.eSales.userinterface;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.EditText;

import com.example.app.eSales.AR_Customer;
import com.example.app.eSales.R;
import com.example.app.eSales.adapter.Adapter_RecyclerView_CustomerList;
import com.example.app.eSales.database.DatabaseHelper;

import java.io.IOException;
import java.util.ArrayList;

public class Fragment_CustomerList extends Fragment {

    private View view;
    private Fragment_CustomerList_ViewHolder holder;
    private Adapter_RecyclerView_CustomerList adapter_recyclerView_customerList;

    public Fragment_CustomerList() {
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

        view = inflater.inflate(R.layout.fragment_customerlist, container, false);

        holder = new Fragment_CustomerList_ViewHolder();
        set_ActionBar();

        if (savedInstanceState != null){

            holder.edt_search.setText(savedInstanceState.getString("search_Item"));

        }else {

        }

        create_DB(); //Tạo CSDL
        load_Data(); //Tạo dữ liệu
        load_RecyclerView(); //Load dữ liệu lên RecyclerView


//        String username = getArguments() != null ? getArguments().getString("username") : null;
//            String password = getArguments() != null ? getArguments().getString("password") : null;
//            Toast.makeText(getActivity(), username + "\n" + password, Toast.LENGTH_LONG).show();

//        Bắt sự kiện thay đổi text
        holder.edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter_Customer(s.toString());
            }
        });


        return view;
    }

//    Tạo CSDL
    public void create_DB(){

        try {
            holder.databaseHelper.create_Database();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onResume() {
        super.onResume();

        set_ActionBar();
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

//                Toast.makeText(getActivity(), "Back clicked", Toast.LENGTH_SHORT).show();
                if (holder.fragmentManager.getBackStackEntryCount() > 0){

                    holder.fragmentManager.popBackStack("fragment_loginscreen", 1);

                }

                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("search_Item", holder.edt_search.getText().toString());
    }

//    Phương thức thiết lập ActionBar
public void set_ActionBar(){

    holder.actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
    holder.actionBar.setTitle("Danh sách khách hàng");

}

//    Tạo dữ liệu ar_salespeople
    public ArrayList<AR_Customer> load_Data(){

        holder.ar_customers = holder.databaseHelper.getList_AR_Customer(holder.ar_customer, holder.ar_customers);
        return holder.ar_customers;


    }

//    Load dữ liệu lên RecyclerView
    public void load_RecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        holder.recyclerView.setLayoutManager(linearLayoutManager);
        holder.recyclerView.setHasFixedSize(true);
        adapter_recyclerView_customerList = new Adapter_RecyclerView_CustomerList(holder.ar_customers, getActivity());
        adapter_recyclerView_customerList.SlsperID = getArguments().getString("username");
        holder.recyclerView.setAdapter(adapter_recyclerView_customerList);
    }

//    Xử lý mục tìm kiếm
    public void filter_Customer(String s){
        holder.filterCustomers = new ArrayList<>();

        for (int i = 0; i < holder.ar_customers.size(); i++){
            if (holder.ar_customers.get(i).getmCustID().toLowerCase().trim().contains(s.toLowerCase().trim()) ||
                    holder.ar_customers.get(i).getmName().toLowerCase().trim().contains(s.toLowerCase().trim()) ||
                    holder.ar_customers.get(i).getmAddress().toLowerCase().trim().contains(s.toLowerCase().trim()) ||
                    holder.ar_customers.get(i).getmPhone().toLowerCase().trim().contains(s.toLowerCase().trim())){

                holder.filterCustomers.add(new AR_Customer(holder.ar_customers.get(i).getmCustID(),
                        holder.ar_customers.get(i).getmName(),
                        holder.ar_customers.get(i).getmAddress(),
                        holder.ar_customers.get(i).getmPhone()));

            }
        }
        adapter_recyclerView_customerList.filter_Customer(holder.filterCustomers);
    }

//    Class lưu trữ View
    public class Fragment_CustomerList_ViewHolder{
        private RecyclerView recyclerView;
        private ArrayList<AR_Customer> ar_customers;
        private ArrayList<AR_Customer> filterCustomers;
        private AR_Customer ar_customer;
        private DatabaseHelper databaseHelper;
        private EditText edt_search;
        private ActionBar actionBar;
        private FragmentManager fragmentManager;

        public Fragment_CustomerList_ViewHolder() {

            recyclerView = (RecyclerView) view.findViewById(R.id.recycler_customerlist);
            edt_search = (EditText) view.findViewById(R.id.edt_customer_search);
            databaseHelper = new DatabaseHelper(getActivity());
            fragmentManager = getActivity().getSupportFragmentManager();
        }
    }
}
*/