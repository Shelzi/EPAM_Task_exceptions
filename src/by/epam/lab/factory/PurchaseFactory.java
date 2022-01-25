package by.epam.lab.factory;

import by.epam.lab.entity.Byn;
import by.epam.lab.entity.PriceDiscountPurchase;
import by.epam.lab.entity.Purchase;
import by.epam.lab.exception.AbstractCsvExceptions;
import by.epam.lab.exception.CsvArgsException;
import by.epam.lab.exception.CsvLineException;
import by.epam.lab.validator.CsvPurchaseValidator;

import java.util.Scanner;

public class PurchaseFactory {
    enum PurchaseKind {
        GENERAL_PURCHASE {
            Purchase getPurchase(String[] args) {
                return new Purchase(args[0],
                        new Byn(Integer.parseInt(args[1])),
                        Integer.parseInt(args[2]));
            }
        },
        PRICE_DIS_PURCHASE {
            Purchase getPurchase(String[] args) {
                return new PriceDiscountPurchase(args[0],
                        new Byn(Integer.parseInt(args[1])),
                        Integer.parseInt(args[2]),
                        new Byn(Integer.parseInt(args[3])));
            }
        };

        abstract Purchase getPurchase(String[] args);
    }

    public static Purchase getPurchaseFromFactory(Scanner sc) throws AbstractCsvExceptions {
        String csvLine = sc.nextLine();
        if (!CsvPurchaseValidator.isCsvValid(csvLine)) {
            throw new CsvLineException(csvLine);
        }
        String[] args = csvLine.split(";");
        int tempCost = Integer.parseInt(args[1]);
        if (CsvPurchaseValidator.isCostValid(tempCost)) {
            throw new CsvArgsException(csvLine);
        }
        if (args.length == 4) {
            int tempDiscount = Integer.parseInt(args[3]);
            if (CsvPurchaseValidator.isDiscountValid(tempDiscount) || tempCost <= tempDiscount) {
                throw new CsvArgsException(csvLine);
            }
        }
        return (args.length == 3 ? PurchaseKind.GENERAL_PURCHASE.getPurchase(args) :
                PurchaseKind.PRICE_DIS_PURCHASE.getPurchase(args));
    }
}