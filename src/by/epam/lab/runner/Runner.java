package by.epam.lab.runner;

import by.epam.lab.entity.Byn;
import by.epam.lab.entity.Purchase;
import by.epam.lab.entity.PurchaseList;
import by.epam.lab.exception.ArrayNotSortedException;

import java.util.Comparator;

public class Runner {
    public static void main(String[] args) {
        String fileName = "src/by/epam/lab/input.txt";
        Comparator<Purchase> purchaseComparator = Comparator.comparing(Purchase::getCost);
        PurchaseList purchaseList = new PurchaseList(fileName, purchaseComparator);
        purchaseList.getPurchaseList().forEach(System.out::println);
        System.out.println(purchaseList.getAllCost());
        System.out.println(purchaseList.toCsvString());
        purchaseList.sortPurchaseList();
        purchaseList.getPurchaseList().forEach(System.out::println);
        try {
            System.out.println(purchaseList.searchPurchase(new Purchase("butter", new Byn(370), 1)));
        } catch (ArrayNotSortedException e) {
            System.err.println(e.getMessage());
        }
    }
}