package com.example.app.eSales;

import android.os.Parcel;
import android.os.Parcelable;

public class OM_SALESORD implements Parcelable {

    private String mOrderNbr;
    private String mSlsperID;
    private String mCustID;
    private double mOrdAmt;
    private double mOrdQty;
    private String mOrderDate; //Cần điều chỉnh cho hợp lý
    private String mRemark;

    public OM_SALESORD()
    {

    }
    public OM_SALESORD(String mOrderNbr, String mSlsperID, String mCustID, double mOrdAmt, double mOrdQty, String mOrderDate, String mRemark) {
        this.mOrderNbr = mOrderNbr;
        this.mSlsperID = mSlsperID;
        this.mCustID = mCustID;
        this.mOrdAmt = mOrdAmt;
        this.mOrdQty = mOrdQty;
        this.mOrderDate = mOrderDate;
        this.mRemark = mRemark;
    }

    public String getmOrderNbr() {
        return mOrderNbr;
    }

    public void setmOrderNbr(String mOrderNbr) {
        this.mOrderNbr = mOrderNbr;
    }

    public String getmSlsperID() {
        return mSlsperID;
    }

    public void setmSlsperID(String mSlsperID) {
        this.mSlsperID = mSlsperID;
    }

    public String getmCustID() {
        return mCustID;
    }

    public void setmCustID(String mCustID) {
        this.mCustID = mCustID;
    }

    public double getmOrdAmt() {
        return mOrdAmt;
    }

    public void setmOrdAmt(double mOrdAmt) {
        this.mOrdAmt = mOrdAmt;
    }

    public double getmOrdQty() {
        return mOrdQty;
    }

    public void setmOrdQty(double mOrdQty) {
        this.mOrdQty = mOrdQty;
    }

    public String getmOrderDate() {
        return mOrderDate;
    }

    public void setmOrderDate(String mOrderDate) {
        this.mOrderDate = mOrderDate;
    }

    public String getmRemark() {
        return mRemark;
    }

    public void setmRemark(String mRemark) {
        this.mRemark = mRemark;
    }

    protected OM_SALESORD(Parcel in) {
        mOrderNbr = in.readString();
        mSlsperID = in.readString();
        mCustID = in.readString();
        mOrdAmt = in.readDouble();
        mOrdQty = in.readDouble();
        mRemark = in.readString();
    }

    public static final Creator<OM_SALESORD> CREATOR = new Creator<OM_SALESORD>() {
        @Override
        public OM_SALESORD createFromParcel(Parcel in) {
            return new OM_SALESORD(in);
        }

        @Override
        public OM_SALESORD[] newArray(int size) {
            return new OM_SALESORD[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mOrderNbr);
        dest.writeString(mSlsperID);
        dest.writeString(mCustID);
        dest.writeDouble(mOrdAmt);
        dest.writeDouble(mOrdQty);
        dest.writeString(mRemark);
    }
}
