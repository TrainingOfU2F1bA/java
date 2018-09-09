package com.cultivation.javaBasicExtended.posMachine;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings({"WeakerAccess", "unused", "RedundantThrows"})
public class PosMachine {

    private Map<String, Product> collect;

    public void readDataSource(Reader reader) throws IOException {
        // TODO: please implement the following method to pass the test
        // <--start
        ObjectMapper mapper = new ObjectMapper();
        collect = mapper.<List<Product>>readValue(reader, new TypeReference<List<Product>>() { }) .stream()
                .collect(Collectors.toMap(Product::getId, product -> product));
        // --end-->
    }

    public String printReceipt(String barcodeContent) throws IOException {
        // TODO: please implement the following method to pass the test
        // <--start
        if (collect == null) throw new IllegalStateException();
        String line = System.lineSeparator();
        StringBuilder receipt = new StringBuilder("Receipts" + line + "------------------------------------------------------------" + line);
        int amountPrice = 0;
        if (barcodeContent != null && !barcodeContent.equals("[]")) {
            String[] strings = barcodeContent.replaceAll("\"|\\[|\\]", "").split(",");
            HashMap<String, Integer> amountMap = new HashMap<>();
            ArrayList<String> receiptList = new ArrayList<>();
            for (String productId:strings) {
                Product product = collect.get(productId);
                if (product.getId().equals(productId)) {
                    amountMap.compute(productId,(k,v)->{
                        if (v == null) return product.getPrice();
                        else return v+product.getPrice();
                    });
                }
            }
            String[] productIdArray = Arrays.stream(strings).distinct().toArray(String[]::new);
            for (String productId:productIdArray) {
                String name = collect.get(productId).getName();
                amountPrice += amountMap.get(productId);
                Integer singlePrice = collect.get(productId).getPrice();
                receipt.append(String.format("%s                      %d          %d\n"
                        , name.length() < 10?(name+" "):name
                        , singlePrice
                        , amountMap.get(productId)/ singlePrice));
            }
        }
        receipt.append("------------------------------------------------------------" + line + "Price: "+amountPrice + line);
        return receipt.toString();
        // --end-->
    }
}

@SuppressWarnings("unused")
class Product {
    private String id;
    private String name;
    private Integer price;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (getClass() != obj.getClass()) return false;

        Product other = (Product) obj;

        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}