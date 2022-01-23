package by.epam.lab.runner;

import by.epam.lab.entity.PurchaseList;

public class Runner {
    public static void main(String[] args) {
        String fileName = "src/by/epam/lab/input.txt";
        PurchaseList purchaseList = new PurchaseList(fileName);
        purchaseList.getPurchaseList().forEach(System.out::println);
    }
}

