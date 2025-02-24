package com.ggx.springIntro;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Order {

    @JsonProperty("p-name")
    private String productName;
    @JsonProperty("c-name")
    private String customerName;
    @JsonProperty("quantity")
    private int quantity;

    @Override
    public String toString() {
        return "Order{\n" +
                "\tproductName='" + productName + '\'' +
                "\n\tcustomerName='" + customerName + '\'' +
                "\n\tquantity=" + quantity +
                "\n}";
    }

}
