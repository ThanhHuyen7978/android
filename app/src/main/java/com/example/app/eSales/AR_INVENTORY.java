package com.example.app.eSales;

public class AR_INVENTORY {

    private String mInvtID;
    private String mName;
    private String mUnit;
    private String mPrice;

    public AR_INVENTORY() {

    }

    public AR_INVENTORY(String mInvtID, String mName, String mUnit, String mPrice) {
        this.mInvtID = mInvtID;
        this.mName = mName;
        this.mUnit = mUnit;
        this.mPrice = mPrice;
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
}
