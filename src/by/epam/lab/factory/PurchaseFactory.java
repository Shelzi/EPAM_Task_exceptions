package by.epam.lab.factory;

import by.epam.lab.entity.Byn;
import by.epam.lab.entity.PriceDiscountPurchase;
import by.epam.lab.entity.Purchase;
import by.epam.lab.exception.CsvArgsException;
import by.epam.lab.exception.CsvLineException;

import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class PurchaseFactory {
    enum PurchaseKind {
        GENERAL_PURCHASE {
            Optional<Purchase> getPurchase(String[] args) throws CsvArgsException {
                try {
                    return Optional.of(new Purchase(args[0],
                            new Byn(Integer.parseInt(args[1])),
                            Integer.parseInt(args[2])));
                } catch (IllegalArgumentException | CsvArgsException e) {
                    throw new CsvArgsException(String.join(";", args), e);
                }
            }
        },
        PRICE_DIS_PURCHASE {
            Optional<Purchase> getPurchase(String[] args) throws CsvArgsException {
                try {
                    return Optional.of(new PriceDiscountPurchase(args[0],
                            new Byn(Integer.parseInt(args[1])),
                            Integer.parseInt(args[2]),
                            new Byn(Integer.parseInt(args[3]))));
                } catch (IllegalArgumentException | CsvArgsException e) {
                    throw new CsvArgsException(String.join(";", args), e);
                }
            }
        };

        abstract Optional<Purchase> getPurchase(String[] args) throws CsvArgsException;
    }

    public static Optional<Purchase> getPurchaseFromFactory(Scanner sc) throws CsvLineException {
        String csvLine = sc.nextLine();
        String[] args = csvLine.split(";");
        if (args.length < 3 || args.length > 4) {
            throw new CsvLineException(csvLine);
        }
        return (args.length == 3 ? PurchaseKind.GENERAL_PURCHASE.getPurchase(args) :
                PurchaseKind.PRICE_DIS_PURCHASE.getPurchase(args));
    }
}