package by.epam.lab.entity;

import by.epam.lab.exception.CsvArgsException;
import by.epam.lab.exception.CsvLineException;

import java.util.Scanner;

public class PriceDiscountPurchase extends Purchase {
    private final Byn discount;

    public PriceDiscountPurchase(String name, Byn price, int number, Byn discount) throws CsvArgsException {
        super(name, price, number);
        if (discount.compareTo(new Byn(0)) > 0 && price.compareTo(discount) > 0 ) {
            this.discount = discount;
        } else throw new CsvArgsException();
    }

    public PriceDiscountPurchase(Scanner sc) throws CsvArgsException {
        super(sc);
        this.discount = new Byn(sc.nextInt());
    }

    public PriceDiscountPurchase(Byn discount, Purchase purchase) throws CsvArgsException {
        super(purchase);
        this.discount = discount;
    }

    @Override
    public Byn getCost() {
        return super.getCost().sub(discount.mul(getNumber()));
    }

    @Override
    public String toString() {
        return String.format("%s;%s", super.toString(), discount);
    }
}