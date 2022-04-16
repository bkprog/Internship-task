package com.virtuslab.internship.receipt;

import com.virtuslab.internship.product.Product;

import java.math.BigDecimal;

public record ReceiptEntry(
        Product product,
        Long quantity,
        BigDecimal totalPrice) {

    public ReceiptEntry(Product product, Long quantity) {
        this(product, quantity, product.price().multiply(BigDecimal.valueOf(quantity)));
    }

   public Product getProduct(){
        return this.product;
   }
   public Long getQuantity(){
        return this.quantity;
   }
}
