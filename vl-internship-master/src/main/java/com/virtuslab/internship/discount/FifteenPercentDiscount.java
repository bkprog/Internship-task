package com.virtuslab.internship.discount;

import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;


import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class FifteenPercentDiscount {

    public static String NAME15 = "FifteenPercentDiscount";

    public Receipt apply15(Receipt receipt) {
        if (shouldApply15(receipt)) {
            var totalPrice = receipt.totalPrice().multiply(BigDecimal.valueOf(0.85));
            var discounts = receipt.discounts();
            discounts.add(NAME15);
            return new Receipt(receipt.entries(), discounts, totalPrice);
        }
        return receipt;
    }

    private boolean shouldApply15(Receipt receipt) {

        Long grainsQuantity=0L;

        List<ReceiptEntry> entryList=receipt.entries().stream()
                .filter(c-> Product.Type.GRAINS.equals(c.getProduct().getType()))
                .collect(Collectors.toList());



        for(int i=0; i<entryList.size();i++){
            grainsQuantity+=entryList.get(i).getQuantity();
        }
        if(grainsQuantity>=3){
            return true;
         }
        else {
            return false;
        }

}
}
