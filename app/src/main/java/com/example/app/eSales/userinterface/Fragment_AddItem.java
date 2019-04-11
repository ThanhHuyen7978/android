/*package com.example.app.eSales.userinterface;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.app.eSales.CUSTOM_INVENTORY;
import com.example.app.eSales.R;
import com.example.app.eSales.adapter.Adapter_RecyclerView_AddItem;
import com.example.app.eSales.database.DatabaseHelper;

import java.util.ArrayList;

public class Fragment_AddItem extends Fragment {

    private View view;
    private Fragment_AddItem_ViewHolder holder;
    private Adapter_RecyclerView_AddItem adapterRecyclerViewAddItem;

    public Fragment_AddItem() {
        super();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_additem, container, false);
        holder = new Fragment_AddItem_ViewHolder();
        set_ActionBar();

        if (savedInstanceState != null){
            if (adapterRecyclerViewAddItem == null){
                holder.custom_inventories = savedInstanceState.getParcelableArrayList("custom_inventories");
                adapterRecyclerViewAddItem = new Adapter_RecyclerView_AddItem(getActivity(), holder.custom_inventories);
            }

            holder.search_Item.setText(savedInstanceState.getString("search_Item"));

        }else {
            load_Data();
            adapterRecyclerViewAddItem = new Adapter_RecyclerView_AddItem(getActivity(), holder.custom_inventories);

        }



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(linearLayoutManager);
        holder.recyclerView.setAdapter(adapterRecyclerViewAddItem);
        adapterRecyclerViewAddItem.adapterRecyclerViewAddItem = adapterRecyclerViewAddItem;

        holder.search_Item.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                filter_AddItem_List(s.toString());

            }
        });

      holder.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "Button confirm clicked", Toast.LENGTH_SHORT).show();

                Fragment_Order fragment_order = new Fragment_Order();

                for (int i = 0; i < holder.custom_inventories.size(); i++){//

                    if (Integer.valueOf(holder.custom_inventories.get(i).getmAmount()) > 0){

                        holder.getCustom_inventories.add(new CUSTOM_INVENTORY(
                                holder.custom_inventories.get(i).getmInvtID(),
                                holder.custom_inventories.get(i).getmName(),
                                holder.custom_inventories.get(i).getmUnit(),
                                holder.custom_inventories.get(i).getmPrice(),
                                holder.custom_inventories.get(i).getmAmount(),
                                String.valueOf(Double.valueOf(holder.custom_inventories.get(i).getmPrice()) * Double.valueOf(holder.custom_inventories.get(i).getmAmount()))
                                )
                        );
                    }
                }

                Intent intent = new Intent(getContext(), Fragment_AddItem.class);
                holder.bundle = new Bundle();
                holder.bundle.putParcelableArrayList("custom_inventories", holder.getCustom_inventories);
                intent.putExtra("BUNDLE", holder.bundle);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
                getFragmentManager().popBackStack("Fragment_Order", 1);//*


            }
        });

        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack("Fragment_Order", 1);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();

        set_ActionBar();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("search_Item", holder.search_Item.getText().toString());
        outState.putParcelableArrayList("custom_inventories", adapterRecyclerViewAddItem.getCustom_inventories());
    }




//    Phương thức thiết lập ActionBar
    public void set_ActionBar(){

        holder.actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        holder.actionBar.setTitle("Thông tin các mặt hàng");

    }


    //    Load dữ liệu
    public ArrayList<CUSTOM_INVENTORY> load_Data(){

        holder.custom_inventories = holder.databaseHelper.getList_AR_INVENTORY(holder.custom_inventory, holder.custom_inventories);
        return holder.custom_inventories;

//        holder.ar_inventories = new ArrayList<>();
//        holder.ar_inventories.add(new AR_INVENTORY("001", "SP001", "Thùng", "100000"));
//        holder.ar_inventories.add(new AR_INVENTORY("002", "SP002", "Thùng", "100000"));
//        holder.ar_inventories.add(new AR_INVENTORY("003", "SP003", "Thùng", "100000"));
//        holder.ar_inventories.add(new AR_INVENTORY("004", "SP004", "Thùng", "100000"));
//        holder.ar_inventories.add(new AR_INVENTORY("005", "SP005", "Thùng", "100000"));
//        holder.ar_inventories.add(new AR_INVENTORY("001", "SP001", "Thùng", "100000"));
//        holder.ar_inventories.add(new AR_INVENTORY("002", "SP002", "Thùng", "100000"));
//        holder.ar_inventories.add(new AR_INVENTORY("003", "SP003", "Thùng", "100000"));
//        holder.ar_inventories.add(new AR_INVENTORY("004", "SP004", "Thùng", "100000"));
//        holder.ar_inventories.add(new AR_INVENTORY("005", "SP005", "Thùng", "100000"));

    }

//    Phương thức lọc dữ liệu
    public void filter_AddItem_List(String s){

        holder.filter_inventories = new ArrayList<>();

        for (int i = 0; i < holder.custom_inventories.size(); i ++){

            if (holder.custom_inventories.get(i).getmInvtID().toLowerCase().contains(s.toLowerCase())
                    || holder.custom_inventories.get(i).getmName().toLowerCase().contains(s.toLowerCase())){

                holder.filter_inventories.add(new CUSTOM_INVENTORY(
                        holder.custom_inventories.get(i).getmInvtID(),
                        holder.custom_inventories.get(i).getmName(),
                        holder.custom_inventories.get(i).getmUnit(),
                        holder.custom_inventories.get(i).getmPrice(),
                        holder.custom_inventories.get(i).getmAmount(),
                        String.valueOf(Double.valueOf(holder.custom_inventories.get(i).getmPrice()) * Double.valueOf(holder.custom_inventories.get(i).getmAmount()))

                ));
            }
        }

        adapterRecyclerViewAddItem.filter_AddItem_List(holder.filter_inventories);

    }



//    Tạo class lưu giữ View
    public class Fragment_AddItem_ViewHolder{

        private EditText search_Item;
        private RecyclerView recyclerView;
        private ArrayList<CUSTOM_INVENTORY> filter_inventories;
        private ArrayList<CUSTOM_INVENTORY> getCustom_inventories;
        private ArrayList<CUSTOM_INVENTORY> custom_inventories;
        private CUSTOM_INVENTORY custom_inventory;
        private DatabaseHelper databaseHelper;
        private Button confirm;
        private Button cancel;
        private Bundle bundle;
        private ActionBar actionBar;

    public Fragment_AddItem_ViewHolder() {

        search_Item = (EditText) view.findViewById(R.id.edt_search_additem);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_additem);
        confirm = (Button) view.findViewById(R.id.btn_additem_confirm);
        cancel = (Button) view.findViewById(R.id.btn_additem_cancel);
        databaseHelper = new DatabaseHelper(getActivity());
        getCustom_inventories = new ArrayList<>();
    }
}
}
*/