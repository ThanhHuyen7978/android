package com.example.app.eSales;

public class OM_SALESORDDET {

    private String OrderNbr;
    private String LineRef;
    private String InvtID;
    private double LineAmt;
    private double LineQty;

    public OM_SALESORDDET() {

    }

    public OM_SALESORDDET(String orderNbr, String lineRef, String invtID, double lineAmt, double lineQty) {
        OrderNbr = orderNbr;
        LineRef = lineRef;
        InvtID = invtID;
        LineAmt = lineAmt;
        LineQty = lineQty;
    }

    public String getOrderNbr() {
        return OrderNbr;
    }

    public void setOrderNbr(String orderNbr) {
        OrderNbr = orderNbr;
    }

    public String getLineRef() {
        return LineRef;
    }

    public void setLineRef(String lineRef) {
        LineRef = lineRef;
    }

    public String getInvtID() {
        return InvtID;
    }

    public void setInvtID(String invtID) {
        InvtID = invtID;
    }

    public double getLineAmt() {
        return LineAmt;
    }

    public void setLineAmt(double lineAmt) {
        LineAmt = lineAmt;
    }

    public double getLineQty() {
        return LineQty;
    }

    public void setLineQty(double lineQty) {
        LineQty = lineQty;
    }
}
