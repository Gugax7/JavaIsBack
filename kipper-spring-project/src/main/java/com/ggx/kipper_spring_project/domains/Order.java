package com.ggx.kipper_spring_project.domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;

public class Order {

    @JsonProperty("p-name")
    private String productName;
    @JsonProperty("c-name")
    private String customerName;
    @JsonProperty("q")
    private int quantity;

    @Override
    public String toString() {
        return "Order{" +
                "productName='" + productName + '\'' +
                ", customerName='" + customerName + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
