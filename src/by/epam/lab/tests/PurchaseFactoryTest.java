package by.epam.lab.tests;

import by.epam.lab.entity.Purchase;
import by.epam.lab.exception.AbstractCsvExceptions;
import by.epam.lab.factory.PurchaseFactory;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import static org.junit.Assert.*;

public class PurchaseFactoryTest {
    private static final int VALID_SIZE_OF_ARRAY = 8;
    private static final String CORRECT_CSV_PATH = "src/by/epam/lab/correctTestCsv.txt";

    @Test
    public void getPurchaseFromFactoryTest() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader(CORRECT_CSV_PATH));
        List<Purchase> purchaseList = new ArrayList<>();
        sc.useLocale(Locale.ENGLISH);
        while (sc.hasNext()) {
            try {
                Purchase purchase = PurchaseFactory.getPurchaseFromFactory(sc);
                purchaseList.add(purchase);
            } catch (AbstractCsvExceptions e) {
                System.err.println(e.getMessage());
            }
        }
        assertEquals(VALID_SIZE_OF_ARRAY, purchaseList.size());
    }
}