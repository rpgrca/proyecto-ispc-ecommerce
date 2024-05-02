package com.ar.enbaldeapp.support;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
}
