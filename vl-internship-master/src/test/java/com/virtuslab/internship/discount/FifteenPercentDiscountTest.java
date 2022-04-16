package com.virtuslab.internship.discount;

import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class FifteenPercentDiscountTest {

    @Test
    void shouldApply15PercentDiscountGrainsIsAbove3() {
        // Given
        var productDb = new ProductDb();
        var bread = productDb.getProduct("Bread");
        var cereals = productDb.getProduct("Cereals");
        var apple=productDb.getProduct("Apple");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(bread, 2L));
        receiptEntries.add(new ReceiptEntry(cereals, 2L));
        receiptEntries.add(new ReceiptEntry(apple,1L));

        var receipt = new Receipt(receiptEntries,new ArrayList<>());
        var discount = new FifteenPercentDiscount();
        var expectedTotalPrice = bread.price().add(bread.price()).add(cereals.price()).add(cereals.price()).add(apple.price()).multiply(BigDecimal.valueOf(0.85));

        // When
        var receiptAfterDiscount = discount.apply15(receipt);



        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(1, receiptAfterDiscount.discounts().size());
    }

     @Test
     void shouldNotApply15PercentDiscountWhenGrainsIsBelow3() {
         // Given
         var productDb = new ProductDb();
         var cheese = productDb.getProduct("Cheese");
         var bread = productDb.getProduct("Bread");
         var cereals = productDb.getProduct("Cereals");
         List<ReceiptEntry> receiptEntries = new ArrayList<>();
         receiptEntries.add(new ReceiptEntry(cheese, 2L));
         receiptEntries.add(new ReceiptEntry(bread,1L));
         receiptEntries.add(new ReceiptEntry(cereals,1L));

         var receipt = new Receipt(receiptEntries,new ArrayList<>());
         var discount = new FifteenPercentDiscount();
         var expectedTotalPrice = cheese.price().add(cheese.price()).add(bread.price()).add(cereals.price());

         // When
         var receiptAfterDiscount = discount.apply15(receipt);

         // Then
         assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
         assertEquals(0, receiptAfterDiscount.discounts().size());
     }













}
