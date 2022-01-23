package by.epam.lab.entity;

import by.epam.lab.exception.CsvArgsException;
import by.epam.lab.exception.CsvLineException;

import java.util.Scanner;

public class PriceDiscountPurchase extends Purchase {
    private final Byn discount;

    public PriceDiscountPurchase(String name, Byn price, int number, Byn discount) {
        super(name, price, number);
        this.discount = discount;
    }

    public PriceDiscountPurchase(Scanner sc) {
        super(sc);
        this.discount = new Byn(sc.nextInt());
    }

    public PriceDiscountPurchase(Byn discount, Purchase purchase) {
        super(purchase);
        this.discount = discount;
    }

    public PriceDiscountPurchase(PriceDiscountPurchase priceDiscountPurchase) {
        super(priceDiscountPurchase);
        discount = priceDiscountPurchase.discount;
    }

    @Override
    public Purchase getCopy() {
        return new PriceDiscountPurchase(this);
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