package com.virtuslab.internship.discount;

import com.virtuslab.internship.receipt.Receipt;

public class AllDiscounts {

    private FifteenPercentDiscount fifteenPercentDiscount= new FifteenPercentDiscount();
    private TenPercentDiscount tenPercentDiscount= new TenPercentDiscount();

    public Receipt applyBoth(Receipt receipt){

        Receipt receiptAfterFirstDiscount=fifteenPercentDiscount.apply15(receipt);
        return tenPercentDiscount.apply(receiptAfterFirstDiscount);
    }
}
