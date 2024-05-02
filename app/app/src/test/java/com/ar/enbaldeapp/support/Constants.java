package com.ar.enbaldeapp.support;

import com.ar.enbaldeapp.models.Offer;
import com.ar.enbaldeapp.models.PaymentType;
import com.ar.enbaldeapp.models.Product;
import com.ar.enbaldeapp.models.ProductType;
import com.ar.enbaldeapp.models.Selection;
import com.ar.enbaldeapp.models.ShippingMethod;
import com.ar.enbaldeapp.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Constants {
    public static final long USER_ID = 1;
    public static final String LAST_NAME = "Agote";
    public static final String FIRST_NAME = "Luis";
    public static final String EMAIL = "luis.agote@gmail.com";
    public static final String PHONE = "1234-5678";
    public static final String ADDRESS = "123 Main St Miami, FL";
    public static final String OBSERVATIONS = "Good client";

    public static final long PRODUCT_TYPE_ID = 1;
    public static final String PRODUCT_TYPE_NAME = "Balde";

    public static final long PRODUCT_ID = 1;
    public static final String PRODUCT_NAME = "Helado de chocolate";
    public static final String PRODUCT_DESCRIPTION = "Un helado muy rico de chocolate con chips";
    public static final double PRODUCT_PRICE = 1100;
    public static final int PRODUCT_QUANTITY = 13;
    public static final String PRODUCT_IMAGE = "http://localhost:4200/enbalde/assets/chocolate.png";
    public static final ProductType PRODUCT_TYPE = new ProductType(PRODUCT_TYPE_ID, PRODUCT_TYPE_NAME);

    public static final long SHIPPING_METHOD_ID = 1;
    public static final String SHIPPING_METHOD_NAME = "Hasta 3km de distancia";
    public static final double SHIPPING_METHOD_COST = 3000;

    public static final long OFFER_ID = 1;
    public static final String OFFER_NAME = "Especial Navidad";
    public static final double OFFER_DISCOUNT = 15;
    public static final Date OFFER_EXPIRATION_DATE = DateUtils.date(2024, 12, 26);

    static class DateUtils {
        public static Date date(int year, int month, int date) {
            Calendar working = GregorianCalendar.getInstance();
            working.set(year, month, date, 0, 0, 1);
            return working.getTime();
        }
    }

    public static final long SELECTION_ID = 1;
    public static final Product SELECTION_PRODUCT = new Product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_DESCRIPTION, PRODUCT_PRICE, PRODUCT_QUANTITY, PRODUCT_IMAGE, PRODUCT_TYPE);
    public static final int SELECTION_QUANTITY = 3;
    public static final Offer SELECTION_OFFER = new Offer(OFFER_ID, OFFER_NAME, OFFER_DISCOUNT, OFFER_EXPIRATION_DATE);
    public static final ArrayList<Offer> SELECTION_OFFERS = new ArrayList<>(Arrays.asList(SELECTION_OFFER));
    public static final double SELECTION_DISCOUNT = 1300;
    public static final double SELECTION_TOTAL = 8700;

    public static final long SALE_ID = 1;
    public static final User SALE_USER = new User(USER_ID, LAST_NAME, FIRST_NAME, EMAIL, ADDRESS, PHONE, OBSERVATIONS);
    public static final Date SALE_DATE = DateUtils.date(2024, 5, 2);
    public static final List<Selection> SALE_SELECTIONS = new ArrayList<>(Arrays.asList(new Selection(SELECTION_ID, SELECTION_PRODUCT, SELECTION_QUANTITY, SELECTION_OFFERS, SELECTION_DISCOUNT, SELECTION_TOTAL)));
    public static final double SALE_TOTAL = 10000;
    public static final ShippingMethod SALE_SHIPPING = new ShippingMethod(SHIPPING_METHOD_ID, SHIPPING_METHOD_NAME, SHIPPING_METHOD_COST);
    public static final PaymentType SALE_PAYMENT_TYPE = PaymentType.CASH;
    public static final String SALE_TRANSACTION = "c80c9ae7-95ed-49b7-a629-f57746f6a8aa";
}
