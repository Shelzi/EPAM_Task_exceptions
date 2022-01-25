package by.epam.lab.entity;

import by.epam.lab.exception.AbstractCsvExceptions;
import by.epam.lab.exception.ArrayNotSortedException;
import by.epam.lab.factory.PurchaseFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class PurchaseList {
    private List<Purchase> purchaseList;
    private final Comparator<Purchase> purchaseComparator;
    private boolean isSorted = false;

    public PurchaseList(String filename, Comparator<Purchase> purchaseComparator) {
        this.purchaseComparator = purchaseComparator;
        try (Scanner sc = new Scanner(new FileReader(filename))) {
            purchaseList = new ArrayList<>();
            sc.useLocale(Locale.ENGLISH);
            while (sc.hasNext()) {
                try {
                    Purchase purchase = PurchaseFactory.getPurchaseFromFactory(sc);
                    purchaseList.add(purchase);
                } catch (AbstractCsvExceptions e) {
                    System.err.println(e.getMessage());
                }

            }
        } catch (FileNotFoundException e) {
            System.err.println("File " + filename + " not found");
            purchaseList = new ArrayList<>();
        }
    }

    public PurchaseList(List<Purchase> purchaseList, Comparator<Purchase> purchaseComparator) {
        this.purchaseList = purchaseList;
        this.purchaseComparator = purchaseComparator;
    }

    public List<Purchase> getPurchaseList() {
        return purchaseList.stream().map(Purchase::getCopy).collect(Collectors.toList());
    }

    public Comparator<Purchase> getPurchaseComparator() {
        return purchaseComparator;
    }

    public <T extends Purchase> void addPurchase(int index, T purchase) {
        try {
            purchaseList.add(index, purchase);
            isSorted = false;
        } catch (IndexOutOfBoundsException e) {
            purchaseList.add(purchase);
            System.err.println(e.getMessage() + "Wrong index, object inserted in the end off array");
        }
    }

    public void deletePurchases(int from, int to) {
        try {
            purchaseList.subList(from, to).clear();
            isSorted = false;
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e.getMessage() + "Wrong index, nothing are deleted");
        }
    }

    public Byn getAllCost() {
        return purchaseList.stream().map(Purchase::getCost).reduce(new Byn(), Byn::add);
    }

    public void sortPurchaseList() {
        purchaseList = purchaseList.stream()
                .sorted(purchaseComparator)
                .collect(Collectors.toList());
        isSorted = true;
    }

    public <T extends Purchase> int searchPurchase(T key) throws ArrayNotSortedException {
        if (isSorted) {
            return Collections.binarySearch(purchaseList, key, purchaseComparator);
        } else {
            throw new ArrayNotSortedException();
        }
    }

    public String toCsvString() {
        return purchaseList.stream().map(Purchase::toString).collect(Collectors.joining("\n"));
    }
}