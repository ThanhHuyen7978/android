/*package com.example.app.eSales.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.app.eSales.CUSTOM_INVENTORY;
import com.example.app.eSales.R;
import com.example.app.eSales.userinterface.Custom_DialogFragment;

import java.util.ArrayList;

public class Adapter_RecyclerView_AddItem extends RecyclerView.Adapter<Adapter_RecyclerView_AddItem.Adapter_RecyclerView_AddItem_ViewHolder> {

    private Context context;
    private ArrayList<CUSTOM_INVENTORY> custom_inventories;
    private View view;
    private Bundle bundle;
    public Adapter_RecyclerView_AddItem adapterRecyclerViewAddItem;

//    Constructor
    public Adapter_RecyclerView_AddItem(Context context, ArrayList<CUSTOM_INVENTORY> custom_inventories) {
        this.context = context;
        this.custom_inventories = custom_inventories;
    }

    @NonNull
    @Override
    public Adapter_RecyclerView_AddItem_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_recyclerview_additem, viewGroup, false);

        return new Adapter_RecyclerView_AddItem_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Adapter_RecyclerView_AddItem_ViewHolder holder, final int i) {
        final CUSTOM_INVENTORY custom_inventory = custom_inventories.get(i);
        holder.number_Item.setText(String.valueOf(i + 1));
        holder.id_Item.setText(custom_inventory.getmInvtID());
        holder.name_Item.setText(custom_inventory.getmName());
        holder.price_Item.setText(custom_inventory.getmPrice());
        holder.amount_Item.setText(custom_inventory.getmAmount());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
//                Toast.makeText(context,
//                        ar_inventories.get(position).getmInvtID() + "\n" +
//                                ar_inventories.get(position).getmName(),
//                        Toast.LENGTH_SHORT).show();

                bundle = new Bundle();
                Custom_DialogFragment dialogFragment = new Custom_DialogFragment();
                bundle.putInt("Type", 2);
                bundle.putString("id_Item", custom_inventories.get(position).getmInvtID());
                bundle.putString("name_Item", custom_inventories.get(position).getmName());
                bundle.putString("price_Item", custom_inventories.get(position).getmPrice());
                bundle.putString("amount_Item", custom_inventories.get(position).getmAmount());
                dialogFragment.setArguments(bundle);
                dialogFragment.show(((AppCompatActivity)context).getSupportFragmentManager(), "dialogfragment_AddItem");

                dialogFragment.get_Amount(custom_inventory, new Custom_DialogFragment.onCallBackAmount() {
                    @Override
                    public void onAmount(int amount) {
                        adapterRecyclerViewAddItem.custom_inventories.get(i).setmAmount(String.valueOf(amount));
                        adapterRecyclerViewAddItem.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return custom_inventories.size();
    }

//    Phương thức lọc danh sách hiển thị
    public void filter_AddItem_List(ArrayList<CUSTOM_INVENTORY> filter_Inventories){
        this.custom_inventories = filter_Inventories;
        notifyDataSetChanged();
    }

//    Tạo interface bắt sự kiện click item
    public interface ItemClickListener{

        void onClick(View view, int position, boolean isLongClick);

    }

//    Phương thức lấy ArrayList custom_inventories
    public ArrayList<CUSTOM_INVENTORY> getCustom_inventories(){
        return custom_inventories;
    }



    //    Class lưu giữ views
    public class Adapter_RecyclerView_AddItem_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView number_Item;
        private TextView id_Item;
        private TextView name_Item;
        private TextView price_Item;
        private TextView amount_Item;
        private ItemClickListener itemClickListener;

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        public Adapter_RecyclerView_AddItem_ViewHolder(@NonNull View itemView) {
        super(itemView);

        number_Item = (TextView) itemView.findViewById(R.id.col_number_additem);
        id_Item = (TextView) itemView.findViewById(R.id.col_id_additem);
        name_Item = (TextView) itemView.findViewById(R.id.col_name_additem);
        price_Item = (TextView) itemView.findViewById(R.id.col_cost_additem);
        amount_Item = (TextView) itemView.findViewById(R.id.col_amount_additem);

        itemView.setOnClickListener(this);

    }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), false);
        }
    }
}
*/