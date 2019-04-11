package com.example.app.eSales.userinterface;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import  com.example.app.eSales.R;
//import  com.example.app.eSales.database.DatabaseHelper;
import com.example.app.eSales.database.model.ARSALESPERSON;
//import com.example.app.eSales.database.model.LoginResponse;
import com.example.app.eSales.database.remote.Api;
import com.example.app.eSales.database.remote.ApiUtils;
import com.example.app.eSales.database.remote.RetrofitClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class Fragment_LoginScreen extends Fragment {

    private View view;
    private Fragment_LoginScreen_ViewHolder holder;

    public Fragment_LoginScreen() {

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

        view = inflater.inflate(R.layout.fragment_loginscreen, container, false);

//        Tự động điều chỉnh layout khi bàn phím hiện lên
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        holder = new Fragment_LoginScreen_ViewHolder(); //Khai báo instance của LoginScreenViewHolder

//        Load trạng thái cho EditText
        if (savedInstanceState != null){
            holder.edt_Id.setText(savedInstanceState.getString("full_name"));
            holder.edt_Pw.setText(savedInstanceState.getString("password"));
        }



//        Xử lý nút đăng nhập
        holder.btn_Login.setOnClickListener(new View.OnClickListener() {
                                                @Override
          public void onClick(View v) {

//                Ẩn bàn phím
                                                    InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
                                                    View focusView = getActivity().getWindow().getCurrentFocus();
                                                    String full_name = holder.edt_Id.getText().toString();
                                                    String password = holder.edt_Pw.getText().toString();
                                                    if (focusView != null) {
                                                        inputMethodManager.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
                                                    }
                                                    if (full_name.isEmpty()) {

                                                        Toast.makeText(getActivity(), "Hãy nhập tên tài khoản", Toast.LENGTH_SHORT).show();
                                                        holder.edt_Id.requestFocus();

                                                    }
                                                    if (password.isEmpty()) {
                                                        Toast.makeText(getActivity(), "Hãy nhập mật khẩu", Toast.LENGTH_SHORT).show();
                                                        holder.edt_Pw.requestFocus();

                                                    }
                                                    if (full_name.length() > 0 && password.length() > 0) {
                                                        Api api = ApiUtils.getData();
                                                        Call<List<ARSALESPERSON>> call = api.Logindata(full_name, password);

                                                        call.enqueue(new Callback<List<ARSALESPERSON>>() {
                                                            @Override
                                                            public void onResponse(Call<List<ARSALESPERSON>> call, Response<List<ARSALESPERSON>> response) {
                                                                ArrayList<ARSALESPERSON> array = (ArrayList<ARSALESPERSON>) response.body();
                                                                Log.d("ARSALESPERSON", response.message());

                                                                if (array.size() > 0) {
                                                                    //int flag=0;
                                                                    // for (int i = 0; i < array.size(); i++ ) {

                                                                    //if (array.get(i).getFullName().equals(full_name) &&
                                                                    //   array.get(i).getPassword().equals(password)) {
                                                                    //    flag = 1;
                                                                    // if (flag==1) {
                                                                   // Toast.makeText(getActivity(), "đúng", Toast.LENGTH_SHORT).show();
                                                                    Log.d("ok", String.valueOf(array.get(0).getId()));
                                                                    Log.d("ok", array.get(1).getFullName());
                                                                    Log.d("ok", array.get(2).getPassword());
                                                                    //Log.d("lay dc roi","actived");

                                                                     Log.d("ok", array.get(3).getAddress());
                                                                }


                                                                // save user
                                                                   /* Bundle bundle = new Bundle();
                                                                    bundle.putString("fullname", holder.edt_Id.getText().toString());
                                                                    Fragment_CustomerList fragment_customerList = new Fragment_CustomerList();
                                                                    fragment_customerList.setArguments(bundle);
                                                                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                                                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                                                                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                                                                    transaction.replace(R.id.activity_contain_main, fragment_customerList, "fragment_customerList");
                                                                    transaction.addToBackStack("fragment_loginscreen");
                                                                    transaction.commit();
                                                                } else {

                                                                    Toast.makeText(getActivity(), "Tên tài khơản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                                                                }

*/
                                                                // }
                                                                else {
                                                                    Log.d("ARSALESPERSON", response.message());
                                                                    Toast.makeText(getActivity(), "sai 1 trong 2", Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                            // }
                                                            //}

                                                            @Override
                                                            public void onFailure(Call<List<ARSALESPERSON>> call, Throwable t) {

                                                                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
}
               });

              }
           }
                                            });



//        Xử lý nút thoát
        holder.btn_Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Thông báo")
                        .setMessage("Bạn có muốn thoát?")
                        .setCancelable(true)
                        .setPositiveButton(R.string.dialog_button_okay, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getActivity().finish();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
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
        outState.putString("full_name",holder.edt_Id.getText().toString());
        outState.putString("password", holder.edt_Pw.getText().toString());
    }






    //    Phương thức thiết lập action bar
    public void set_ActionBar(){

        holder.actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        holder.actionBar.setTitle("");

    }

    //    Class lưu giữ views
    public class Fragment_LoginScreen_ViewHolder{

        private EditText edt_Id;
        private EditText edt_Pw;
        private Button btn_Login;
        private Button btn_Exit;
        private TextView tv_VersionDate;
        private TextView tv_VersionNumber;
       // private DatabaseHelper databaseHelper;
        //private AR_SALESPERSON ar_salesperson;
        //private ArrayList<AR_SALESPERSON> ar_salespeople;
        private ActionBar actionBar;
        private  ArrayList<ARSALESPERSON> array;
        public Fragment_LoginScreen_ViewHolder() {

            edt_Id = (EditText) view.findViewById(R.id.edt_login_id);
            edt_Pw = (EditText) view.findViewById(R.id.edt_login_pw);
            btn_Login = (Button) view.findViewById(R.id.btn_login);
            btn_Exit = (Button) view.findViewById(R.id.btn_exit);
          // tv_VersionDate = (TextView) view.findViewById(R.id.tv_version_date);
           // tv_VersionNumber = (TextView) view.findViewById(R.id.tv_version_number);
           // databaseHelper =  new DatabaseHelper(getActivity());

        }
    }
}
