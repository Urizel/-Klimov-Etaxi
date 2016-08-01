package com.spb.kbv.etest.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Trip implements Comparable<Trip> {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("startAddress")
    @Expose
    private Address startAddress;
    @SerializedName("endAddress")
    @Expose
    private Address endAddress;
    @SerializedName("price")
    @Expose
    private Price price;
    @SerializedName("orderTime")
    @Expose
    private Date orderTime;
    @SerializedName("vehicle")
    @Expose
    private Vehicle vehicle;

    public Address getEndAddress() {
        return endAddress;
    }

    public Address getStartAddress() {
        return startAddress;
    }

    public Price getPrice() {
        return price;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    //Make Trip.class sortable by order time
    @Override
    public int compareTo(Trip trip) {
        return orderTime.compareTo(trip.orderTime);
    }

    public class Address {
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("address")
        @Expose
        private String address;

        public String getCity() {
            return city;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    public class Vehicle {
        @SerializedName("regNumber")
        @Expose
        private String regNumber;
        @SerializedName("modelName")
        @Expose
        private String modelName;
        @SerializedName("photo")
        @Expose
        private String photo;
        @SerializedName("driverName")
        @Expose
        private String driverName;

        public String getRegNumber() {
            return regNumber;
        }

        public String getModelName() {
            return modelName;
        }

        public String getPhoto() {
            return photo;
        }

        public String getDriverName() {
            return String.format("Водитель %s", driverName);
        }
    }

    public class Price {
        @SerializedName("amount")
        @Expose
        private Integer amount;
        @SerializedName("currency")
        @Expose
        private String currency;

        public Integer getAmount() {
            return amount;
        }

        public String getCurrency() {
            return currency;
        }
    }

    public String getRoute() {
        return String.format("Маршрут:%n %s - %s", startAddress.address, endAddress.address);
    }

    public String getPriceString() {
        return String.format("Стоимость %s %s", price.getAmount()/100, price.getCurrency());
    }

    public String getDateString(){
        SimpleDateFormat dateFormat  = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.format(orderTime);
    }

    public String getTimeString () {
        SimpleDateFormat dateFormat  = new SimpleDateFormat("HH:mm");
        return String.format("Время заказа %s", dateFormat.format(orderTime));
    }

    public String getVehicleString(){
        return String.format("Машина %s, гос.номер %s", vehicle.modelName, vehicle.getRegNumber());
    }

}