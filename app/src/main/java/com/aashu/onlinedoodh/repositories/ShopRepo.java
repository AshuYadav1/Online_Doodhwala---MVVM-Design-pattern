package com.aashu.onlinedoodh.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.aashu.onlinedoodh.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShopRepo {


    private MutableLiveData<List<Product>> mutableProductList;

    public LiveData<List<Product>> getProducts() {
        if (mutableProductList == null) {
            mutableProductList = new MutableLiveData<>();
            loadProducts();
        }
        return mutableProductList;
    }

    private void loadProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(UUID.randomUUID().toString(), "Paneer", 200, true, "https://m.media-amazon.com/images/I/61+ZGA2G+EL._SL1400_.jpg" ));
        productList.add(new Product(UUID.randomUUID().toString(), "Dahi", 50, true, "https://5.imimg.com/data5/SELLER/Default/2021/1/RY/OQ/WH/118764700/amul-masti-dahi-500x500.jpg"));
        productList.add(new Product(UUID.randomUUID().toString(), "Amul", 40, true, "https://d2gxays8f387d8.cloudfront.net/prodstore/productimg/britannia_sandwich_bread_600gm_f.jpg"));
       // productList.add(new Product(UUID.randomUUID().toString(), "Bread", 30, true, "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"));
        productList.add(new Product(UUID.randomUUID().toString(), "Brown Bread", 40, false, "https://m.media-amazon.com/images/I/813QtiWCz7L._SY879_.jpg"));
        productList.add(new Product(UUID.randomUUID().toString(), "Bread", 15, true, "https://www.jiomart.com/images/product/original/490010311/amul-taaza-homogenised-toned-long-life-milk-1-l-tetra-pak-0-20211115.jpg"));
        productList.add(new Product(UUID.randomUUID().toString(), "Lassi", 20, true, "https://www.bigbasket.com/media/uploads/p/xxl/30007081-2_1-amul-lassi-rose-flavour.jpg"));
        productList.add(new Product(UUID.randomUUID().toString(), "Amul 500ml", 25, true, "https://quickrly.com/wp-content/uploads/2020/08/84faebd973c57f6838cf24a574738732.png"));
        productList.add(new Product(UUID.randomUUID().toString(), "Amul Ghee", 200, true, "https://www.jiomart.com/images/product/600x600/490022073/amul-cow-ghee-1-l-tin-0-20210701.jpg"));
        productList.add(new Product(UUID.randomUUID().toString(), "Cow Milk", 80, true, "https://5.imimg.com/data5/SELLER/Default/2020/10/UM/ZY/WP/50447457/a2-gir-cow-milk-pouch-pack--500x500.jpg"));
        productList.add(new Product(UUID.randomUUID().toString(), "Amul Gold 1l", 30, true, "https://www.bigbasket.com/media/uploads/p/xxl/104707_8-amul-homogenised-standardised-milk.jpg"));
        mutableProductList.setValue(productList);
    }
}