package com.hit.cqcoder.bk;

 import java.util.Date;
 import java.util.UUID;

public class Item {

    private double mCost;
    private Date mDate;
    private String mContent;
    private String mSort;
    private String mDateString;
    private String mCostString;


    //Setter & getter
    public String getmDateString() {
        return mDateString;
    }
    public String getmCostString() {
        return mCostString;
    }
    public Date getmDate() {
        return mDate;
    }
//    public UUID getmID() {
//        return mID;
//    }
    public double getmCost() {
        return mCost;
    }
    public String getmContent(){
        return mContent;
    }
    public String getmSort() {
        return mSort;
    }


    public void setmDateString(String mDateString) {
        this.mDateString = mDateString;
    }
//    public void setmID(UUID mID) {
//        this.mID = mID;
//    }
    public void setmCost(double mCost) {
        this.mCostString = Double.toString(mCost);
        this.mCost = mCost;
    }
    public void setmCostString(String mCostString) {
        this.mCost = Double.parseDouble(mCostString);
        this.mCostString = mCostString;
    }
    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }
    public void setmContent(String mContent){
        this.mContent = mContent;
    }
     public void setmSort(String mSort) {
        this.mSort = mSort;
    }

    //调用的方法
    public String getDate(){
        return (String)android.text.format.DateFormat.format("yyyy-MM-dd",mDate);
    }
    public Item(double cost,String sort,String dateString,String content){

        mCostString = Double.toString(cost);
        mCost = cost;
        mSort = sort;
        mDateString = dateString;
        mContent = content;

    }
    public Item(){};


}
