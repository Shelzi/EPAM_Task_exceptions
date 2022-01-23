package by.epam.lab.entity;

import by.epam.lab.exception.CsvArgsException;
import by.epam.lab.exception.CsvLineException;

import java.util.Objects;
import java.util.Scanner;

public class Purchase {
    private final String name;
    private final Byn price;
    private final int number;

    public Purchase(String name, Byn price, int number) throws CsvArgsException {
        if (!name.isEmpty() && price.compareTo(new Byn(0)) > 0 && number > 0){
            this.name = name;
            this.price = price;
            this.number = number;
        } else throw new CsvArgsException();
    }

    public Purchase(Scanner sc) throws CsvArgsException {
        this(sc.next(), new Byn(sc.nextInt()), sc.nextInt());
    }

    public Purchase(Purchase purchase) throws CsvArgsException {
        this(purchase.name, purchase.price, purchase.number);
    }

    public String getName() {
        return name;
    }

    public Byn getPrice() {
        return new Byn(price);
    }

    public int getNumber() {
        return number;
    }

    public Byn getCost() {
        return new Byn(price).mul(number, RoundMethod.FLOOR, 2);
    }

    public String toString() {
        return String.format("%s;%s;%d;%s", name, price.toString(), number, getCost());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Purchase)) {
            return false;
        }
        Purchase purchase = (Purchase) o;
        return number == purchase.number &&
                Objects.equals(name, purchase.name) &&
                Objects.equals(price, purchase.price);
    }
}