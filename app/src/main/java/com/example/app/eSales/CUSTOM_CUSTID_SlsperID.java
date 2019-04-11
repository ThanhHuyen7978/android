package com.example.app.eSales;

import android.os.Parcel;
import android.os.Parcelable;

public class CUSTOM_CUSTID_SlsperID implements Parcelable {

    String CustID;
    String SlsperID;

    public CUSTOM_CUSTID_SlsperID() {

    }

    public CUSTOM_CUSTID_SlsperID(String custID, String slsperID) {
        CustID = custID;
        SlsperID = slsperID;
    }

    public String getCustID() {
        return CustID;
    }

    public void setCustID(String custID) {
        CustID = custID;
    }

    public String getSlsperID() {
        return SlsperID;
    }

    public void setSlsperID(String slsperID) {
        SlsperID = slsperID;
    }

    protected CUSTOM_CUSTID_SlsperID(Parcel in) {
        CustID = in.readString();
        SlsperID = in.readString();
    }

    public static final Creator<CUSTOM_CUSTID_SlsperID> CREATOR = new Creator<CUSTOM_CUSTID_SlsperID>() {
        @Override
        public CUSTOM_CUSTID_SlsperID createFromParcel(Parcel in) {
            return new CUSTOM_CUSTID_SlsperID(in);
        }

        @Override
        public CUSTOM_CUSTID_SlsperID[] newArray(int size) {
            return new CUSTOM_CUSTID_SlsperID[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(CustID);
        dest.writeString(SlsperID);
    }
}
