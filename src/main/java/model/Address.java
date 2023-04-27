package model;

import java.io.Serializable;

public class Address implements Serializable {
    private String addressDetail;

    public Address(String addressDetail) {
        this.addressDetail = addressDetail;
    }

}
