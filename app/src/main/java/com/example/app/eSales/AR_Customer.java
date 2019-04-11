/*package com.example.app.eSales;

import android.os.Parcel;
import android.os.Parcelable;

public class AR_Customer implements Parcelable {

    private String mCustID;
    private String mName;
    private String mAddress;
    private String mPhone;

    public AR_Customer(String mCustID, String mName, String mAddress, String mPhone) {
        this.mCustID = mCustID;
        this.mName = mName;
        this.mAddress = mAddress;
        this.mPhone = mPhone;
    }

    public String getmCustID() {
        return mCustID;
    }

    public void setmCustID(String mCustID) {
        this.mCustID = mCustID;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    protected AR_Customer(Parcel in) {
        mCustID = in.readString();
        mName = in.readString();
        mAddress = in.readString();
        mPhone = in.readString();
    }

    public static final Creator<AR_Customer> CREATOR = new Creator<AR_Customer>() {
        @Override
        public AR_Customer createFromParcel(Parcel in) {
            return new AR_Customer(in);
        }

        @Override
        public AR_Customer[] newArray(int size) {
            return new AR_Customer[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mCustID);
        dest.writeString(mName);
        dest.writeString(mAddress);
        dest.writeString(mPhone);
    }
}
*/