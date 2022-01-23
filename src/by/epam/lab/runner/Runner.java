package by.epam.lab.runner;

import by.epam.lab.entity.Purchase;
import by.epam.lab.entity.PurchaseList;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Runner {
    public static void main(String[] args) {
        String fileName = "src/by/epam/lab/input.txt";
        Comparator<Purchase> purchaseComparator = Comparator.comparing(Purchase::getCost);
        PurchaseList purchaseList = new PurchaseList(fileName, purchaseComparator);
        purchaseList.getPurchaseList().forEach(System.out::println);
        System.out.println(purchaseList.getAllCost());
        System.out.println(purchaseList.toCsvString());
        PurchaseList sortedPurchaseList = new PurchaseList(
                purchaseList.getPurchaseList().stream()
                        .sorted(purchaseList.getPurchaseComparator())
                        .collect(Collectors.toList()),
                purchaseComparator);
        sortedPurchaseList.getPurchaseList().forEach(System.out::println);
    }
}