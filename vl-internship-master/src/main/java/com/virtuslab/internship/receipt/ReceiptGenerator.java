package com.virtuslab.internship.receipt;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.discount.TenPercentDiscount;
import com.virtuslab.internship.product.Product;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReceiptGenerator {

    public Receipt generate(Basket basket) {
        List<ReceiptEntry> receiptEntries= new ArrayList<>();


        Map<Product,Long> countingMap=basket.getProducts().stream()
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));

        countingMap.forEach((pr,count)->receiptEntries.add(new ReceiptEntry(pr,count)));


        return new Receipt(receiptEntries, new ArrayList<>());
    }


}
