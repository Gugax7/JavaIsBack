package com.ggx.springIntro;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderRecord(
        @JsonProperty("p-name")
         String productName,
        @JsonProperty("c-name")
         String customerName,
        @JsonProperty("quantity")
         int quantity
) {
}
