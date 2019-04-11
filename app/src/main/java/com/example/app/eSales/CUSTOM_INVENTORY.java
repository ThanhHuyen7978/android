package com.example.app.eSales;

import android.os.Parcel;
import android.os.Parcelable;

public class CUSTOM_INVENTORY implements Parcelable {
    private String mInvtID;
    private String mName;
    private String mUnit;
    private String mPrice;
    private String mAmount;
    private String mTotalMoney;

    public CUSTOM_INVENTORY() {

    }

    public CUSTOM_INVENTORY(String mInvtID, String mName, String mUnit, String mPrice, String mAmount, String mTotalMoney) {
        this.mInvtID = mInvtID;
        this.mName = mName;
        this.mUnit = mUnit;
        this.mPrice = mPrice;
        this.mAmount = mAmount;
        this.mTotalMoney = mTotalMoney;
    }

    public String getmInvtID() {
        return mInvtID;
    }

    public void setmInvtID(String mInvtID) {
        this.mInvtID = mInvtID;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmUnit() {
        return mUnit;
    }

    public void setmUnit(String mUnit) {
        this.mUnit = mUnit;
    }

    public String getmPrice() {
        return mPrice;
    }

    public void setmPrice(String mPrice) {
        this.mPrice = mPrice;
    }

    public String getmAmount() {
        return mAmount;
    }

    public void setmAmount(String mAmount) {
        this.mAmount = mAmount;
    }

    public void setmTotalMoney(String mTotalMoney) {
        this.mTotalMoney = mTotalMoney;
    }

    public String getmTotalMoney() {
        return mTotalMoney;
    }

    protected CUSTOM_INVENTORY(Parcel in) {
        mInvtID = in.readString();
        mName = in.readString();
        mUnit = in.readString();
        mPrice = in.readString();
        mAmount = in.readString();
        mTotalMoney = in.readString();
    }

    public static final Creator<CUSTOM_INVENTORY> CREATOR = new Creator<CUSTOM_INVENTORY>() {
        @Override
        public CUSTOM_INVENTORY createFromParcel(Parcel in) {
            return new CUSTOM_INVENTORY(in);
        }

        @Override
        public CUSTOM_INVENTORY[] newArray(int size) {
            return new CUSTOM_INVENTORY[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mInvtID);
        dest.writeString(mName);
        dest.writeString(mUnit);
        dest.writeString(mPrice);
        dest.writeString(mAmount);
        dest.writeString(mTotalMoney);
    }
}
