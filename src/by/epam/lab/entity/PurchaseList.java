package by.epam.lab.entity;

import by.epam.lab.exception.CsvLineException;
import by.epam.lab.factory.PurchaseFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class PurchaseList {
    private List<Purchase> purchaseList;

    public PurchaseList(String filename) {
        try (Scanner sc = new Scanner(new FileReader(filename))) {
            purchaseList = new ArrayList<>();
            sc.useLocale(Locale.ENGLISH);
            while (sc.hasNext()) {
                try {
                    // TODO: 22.01.2022 ловить CsvLineException
                    Optional<Purchase> optionalPurchase = PurchaseFactory.getPurchaseFromFactory(sc);
                    optionalPurchase.ifPresent(purchase -> purchaseList.add(purchase));
                } catch (CsvLineException e) {
                    System.err.println(e.getMessage());
                }

            }
        } catch (FileNotFoundException e) {
            System.err.println("File " + filename + " not found");
            purchaseList = new ArrayList<>();
        }
    }

    public List<Purchase> getPurchaseList() {
        return purchaseList;
    }
}
