package com.virtuslab.internship.discount;

import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class AllDiscountsTest {

    @Test
    void ShouldApplyBothDiscounts(){

        var productDb = new ProductDb();
        var cheese = productDb.getProduct("Cheese");
        var bread = productDb.getProduct("Bread");
        var cereals = productDb.getProduct("Cereals");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(cheese, 2L));
        receiptEntries.add(new ReceiptEntry(bread,1L));
        receiptEntries.add(new ReceiptEntry(cereals,3L));

        var receipt = new Receipt(receiptEntries,new ArrayList<>());
        var discount = new AllDiscounts();
        var expectedTotalPrice = cheese.price().add(cheese.price()).add(bread.price()).add(cereals.price().add(cereals.price().add(cereals.price()))).multiply(BigDecimal.valueOf(0.85)).multiply(BigDecimal.valueOf(0.90));

        // When
        var receiptAfterDiscount = discount.applyBoth(receipt);

        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(2, receiptAfterDiscount.discounts().size());

    }
     @Test
     void ShouldApply10percentDiscount(){

         var productDb = new ProductDb();
         var cheese = productDb.getProduct("Cheese");
         var bread = productDb.getProduct("Bread");
         var cereals = productDb.getProduct("Cereals");
         List<ReceiptEntry> receiptEntries = new ArrayList<>();
         receiptEntries.add(new ReceiptEntry(cheese, 2L));
         receiptEntries.add(new ReceiptEntry(bread,1L));
         receiptEntries.add(new ReceiptEntry(cereals,1L));

         var receipt = new Receipt(receiptEntries,new ArrayList<>());
         var discount = new AllDiscounts();
         var expectedTotalPrice = cheese.price().add(cheese.price()).add(bread.price()).add(cereals.price()).multiply(BigDecimal.valueOf(0.90));

         // When
         var receiptAfterDiscount = discount.applyBoth(receipt);

         // Then
         assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
         assertEquals(1, receiptAfterDiscount.discounts().size());

     }


     @Test
     void ShouldApply15PercentDiscount(){

         var productDb = new ProductDb();
         var bread = productDb.getProduct("Bread");
         var cereals = productDb.getProduct("Cereals");
         List<ReceiptEntry> receiptEntries = new ArrayList<>();
         receiptEntries.add(new ReceiptEntry(bread,1L));
         receiptEntries.add(new ReceiptEntry(cereals,3L));

         var receipt = new Receipt(receiptEntries,new ArrayList<>());
         var discount = new AllDiscounts();
         var expectedTotalPrice = bread.price().add(cereals.price().add(cereals.price().add(cereals.price()))).multiply(BigDecimal.valueOf(0.85));

         // When
         var receiptAfterDiscount = discount.applyBoth(receipt);

         // Then
         assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
         assertEquals(1, receiptAfterDiscount.discounts().size());

     }
}
