package com.virtuslab.internship.controller;

import com.virtuslab.internship.basket.Basket;

import com.virtuslab.internship.discount.AllDiscounts;
import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptGenerator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasketToReceiptController {




        @PostMapping("/generateReceipt")
        public Receipt generateReceipt(@RequestBody BasketDTO productNameList){
                ProductDb productDb= new ProductDb();

                ReceiptGenerator receiptGenerator= new ReceiptGenerator();
                AllDiscounts allDiscounts= new AllDiscounts();
                Basket basket = new Basket();
                Product productFromDb;

                for(int i=0; i<productNameList.getProducts().size();i++){
                    productFromDb= productDb.getProduct(productNameList.getProducts().get(i));
                    basket.getProducts().add(productFromDb);

                }
               Receipt receiptForDiscounts=receiptGenerator.generate(basket);
               return allDiscounts.applyBoth(receiptForDiscounts);
        }


    }


