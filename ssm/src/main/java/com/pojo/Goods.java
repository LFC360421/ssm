package com.pojo;

import java.sql.Date;

public class Goods {
    double sum;
    String areas;
    Date   orderdata;

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getAreas() {
        return areas;
    }

    public void setAreas(String areas) {
        this.areas = areas;
    }

    public Date getOrderdata() {
        return orderdata;
    }

    public void setOrderdata(Date orderdata) {
        this.orderdata = orderdata;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "sum=" + sum +
                ", areas='" + areas + '\'' +
                ", orderdata=" + orderdata +
                '}';
    }
}
