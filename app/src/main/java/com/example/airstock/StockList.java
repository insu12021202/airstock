package com.example.airstock;

public class StockList {
    private String id;
    private String name;
    private String count;
    private String inDate;
    private String outDate;
    private String inMemo;
    private String outMemo;
    private String inPrice;
    private String outPrice;
    private String receiveClient;
    private String isPositioned;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    public String getInMemo() {
        return inMemo;
    }

    public void setInMemo(String inMemo) {
        this.inMemo = inMemo;
    }

    public String getOutMemo() {
        return outMemo;
    }

    public void setOutMemo(String outMemo) {
        this.outMemo = outMemo;
    }

    public String getInPrice() {
        return inPrice;
    }

    public void setInPrice(String inPrice) {
        this.inPrice = inPrice;
    }

    public String getOutPrice() {
        return outPrice;
    }

    public void setOutPrice(String outPrice) {
        this.outPrice = outPrice;
    }

    public String getReceiveClient() {
        return receiveClient;
    }

    public void setReceiveClient(String receiveClient) {
        this.receiveClient = receiveClient;
    }

    public String getIsPositioned() {
        return isPositioned;
    }

    public void setIsPositioned(String isPositioned) {
        this.isPositioned = isPositioned;
    }

    public StockList(String id, String name, String count, String inDate, String outDate, String inMemo, String outMemo, String inPrice, String  outPrice, String receiveClient, String isPositioned){
        this.id = id;
        this.name = name;
        this.count = count;
        this.inDate = inDate;
        this.outDate = outDate;
        this.inMemo = inMemo;
        this.outMemo = outMemo;
        this.inPrice = inPrice;
        this.outPrice = outPrice;
        this.receiveClient = receiveClient;
        this.isPositioned = isPositioned;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) return true;
//        if (obj == null || getClass() != obj.getClass()) return false;
//
//        StockList stock = (StockList) obj;
//
//        if (id != stock.id) return false;
//        if (!count.equalsIgnoreCase(stock.count)) return false;
//        return name != null ? name.equals(stock.name) : stock.name == null;
//
//    }

//    @Override
//    public int hashCode() {
//        int result = Integer.parseInt(id);
//        result = result + (name != null ? name.hashCode() : 0);
//        result = result + count.hashCode();
//        return result;
//    }

}
