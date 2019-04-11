package com.example.app.eSales.userinterface;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.eSales.CUSTOM_INVENTORY;
import com.example.app.eSales.R;

public class Custom_DialogFragment extends DialogFragment {

    private View view;
    private Custom_DialogFragment.onCallBack onCallBack;
    private CUSTOM_INVENTORY custom_inventory;
    private Custom_DialogFragment.onCallBackAmount callBackAmount;
    private Custom_DialogFragment_ViewHolder holder;

    public Custom_DialogFragment() {
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

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        switch (getArguments().getInt("Type")){

            case 1:{

                view = inflater.inflate(R.layout.dialogfragment_orderdate, null);
                builder.setView(view)
                        .setPositiveButton(getResources().getText(R.string.btn_confirm), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton(getResources().getText(R.string.btn_cancel), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dismiss();
                            }
                        });
                break;
            }

            case 2:{
                view = inflater.inflate(R.layout.dialogfragment_additem, null);
                holder = new Custom_DialogFragment_ViewHolder();
                holder.id_Item.setText(getArguments().getString("id_Item"));
                holder.name_Item.setText(getArguments().getString("name_Item"));
                holder.price_Item.setText(getArguments().getString("price_Item"));
                holder.amount_Item.setText(getArguments().getString("amount_Item"));
                builder.setView(view)
                        .setPositiveButton(getResources().getText(R.string.btn_confirm), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton(getResources().getText(R.string.btn_cancel), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dismiss();
                            }
                        });
                break;
            }

        }


        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();

        AlertDialog alertDialog = (AlertDialog) getDialog();
        switch (getArguments().getInt("Type")){

            case 1:{

                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String day = null;
                        String month = null;
                        String year = null;

                        DatePicker datePicker = (DatePicker) view.findViewById(R.id.dialogfragment_orderdate);
                        if (datePicker.getDayOfMonth() < 10){
                            day = "0" + String.valueOf(datePicker.getDayOfMonth());
                        }else {
                            day = String.valueOf(datePicker.getDayOfMonth());
                        }

                        if ((datePicker.getMonth() + 1) < 10){
                            month = "0" + String.valueOf(datePicker.getMonth() + 1);
                        }else {
                            month = String.valueOf(datePicker.getMonth() + 1);
                        }

                        year = String.valueOf(datePicker.getYear());

                        onCallBack.onClose(day, month, year);
                        dismiss();
                    }
                });
                break;
            }

            case 2:{

                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Toast.makeText(getActivity(), "Button confirm clicked", Toast.LENGTH_SHORT).show();
                        if (holder.amount_Item.getText().toString().equals("")){

                            Toast.makeText(getActivity(), "Chưa điền số lượng", Toast.LENGTH_SHORT).show();

                        }else{

                            if (Integer.valueOf(holder.amount_Item.getText().toString()) < 0 ){

                                Toast.makeText(getActivity(), "Số lượng sai", Toast.LENGTH_SHORT).show();

                            }else {

                                if (callBackAmount != null){

                                    callBackAmount.onAmount(Integer.valueOf(holder.amount_Item.getText().toString()));
                                    dismiss();

                                }
                            }
                        }
                    }
                });
                break;
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

//    Tạo giao diện nhận sự kiện
    public interface onCallBack{
        void onClose(String day, String month, String year);
    }

//    interface bắt sự kiện callback Amount
    public interface onCallBackAmount{
        void onAmount(int amount);
    }

//    Lấy dữ liệu amount
    public void get_Amount(CUSTOM_INVENTORY custom_inventory, Custom_DialogFragment.onCallBackAmount callBackAmount){
        this.callBackAmount = callBackAmount;
        this.custom_inventory = custom_inventory;
    }

//    Đọc dữ liệu
    public void get_Data(Custom_DialogFragment.onCallBack onCallBack){
        this.onCallBack = onCallBack;
    }

//    Class lưu giữ Views
    public class Custom_DialogFragment_ViewHolder{

        private TextView id_Item;
        private TextView name_Item;
        private TextView price_Item;
        private EditText amount_Item;

    public Custom_DialogFragment_ViewHolder() {

        id_Item = (TextView) view.findViewById(R.id.tv_id_additem);
        name_Item = (TextView) view.findViewById(R.id.tv_name_additem);
        price_Item = (TextView) view.findViewById(R.id.tv_price_additem);
        amount_Item = (EditText) view.findViewById(R.id.edt_additem_amount);
    }
}
}
