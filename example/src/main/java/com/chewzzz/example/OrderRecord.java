package com.chewzzz.example;

public record OrderRecord(
        String customerName,
        String productName,
        int quantity
) {
}
