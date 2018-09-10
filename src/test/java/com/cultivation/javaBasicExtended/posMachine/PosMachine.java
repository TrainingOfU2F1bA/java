package com.cultivation.javaBasicExtended.posMachine;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings({"WeakerAccess", "unused", "RedundantThrows"})
public class PosMachine {

    public static final String LINE = System.lineSeparator();
    public static final String RECIEPT_BOTTOM = "------------------------------------------------------------\nPrice: %s\n";
    public static final String RECIEPT_HEADER = "Receipts" + LINE + "------------------------------------------------------------" + LINE;
    public static final String ONE_LINE_OF_RPODUCT_CONTENT = "                                %d          %d\n";

    private Map<String, Product> productSet;

    public void readDataSource(Reader reader) throws IOException {
        // TODO: please implement the following method to pass the test
        // <--start
        ObjectMapper mapper = new ObjectMapper();
        productSet = mapper.<List<Product>>readValue(reader, new TypeReference<List<Product>>() { }) .stream()
                .collect(Collectors.toMap(Product::getId, product -> product));
        // --end-->
    }

    public String printReceipt(String barcodeContent) throws IOException {
        // TODO: please implement the following method to pass the test
        // <--start
        if (productSet == null) throw new IllegalStateException();

        StringBuilder receipt = new StringBuilder().append(RECIEPT_HEADER);

        HashMap<String, Integer> amountMap = new HashMap<>();

        if (barcodeContent != null && !barcodeContent.equals("[]")) {
            String[] productIdArray = getProductIdArray(barcodeContent);
            calculateTotalPriceofOneProduct(productIdArray, amountMap);
            printContentofProduct(receipt, productIdArray, amountMap);
        }

        receipt.append(String.format(RECIEPT_BOTTOM, getAmountPrice(amountMap)));

        return receipt.toString();
        // --end-->
    }

    private int getAmountPrice(HashMap<String, Integer> amountMap) {
        return amountMap.values().stream().mapToInt(x->x).sum();
    }

    private String[] getProductIdArray(String barcodeContent) {
        return barcodeContent.replaceAll("\"|\\[|\\]", "").split(",");
    }

    private void printContentofProduct(StringBuilder receipt, String[] strings, HashMap<String, Integer> amountMap) {
        String[] productIdArray = Arrays.stream(strings).distinct().toArray(String[]::new);
        Arrays.stream(productIdArray)
                .map(productId-> productSet.get(productId))
                .forEach(product->
                generateSingleContent(receipt, amountMap, product, product.getName(), product.getPrice() )
        );
    }

    private void generateSingleContent(StringBuilder receipt, HashMap<String, Integer> amountMap, Product product, String productName, Integer unitPrice) {
        receipt.append(String.format(linkProductName(productName)
                , unitPrice
                , amountMap.get(product.getId()) / unitPrice));
    }

    private String linkProductName(String productName) {
        return productName+ONE_LINE_OF_RPODUCT_CONTENT.substring(productName.length());
    }

    private void calculateTotalPriceofOneProduct(String[] productIdList, HashMap<String, Integer> amountMap) {
        Arrays.stream(productIdList).map(productSet::get).forEach(
                product-> amountMap.compute( product.getId(),
                        (key, value) -> (value == null?0:value) + product.getPrice()
                ));
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