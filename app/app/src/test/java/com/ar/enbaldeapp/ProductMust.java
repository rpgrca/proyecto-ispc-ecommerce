package com.ar.enbaldeapp;

import static com.ar.enbaldeapp.support.Constants.PRODUCT_DESCRIPTION;
import static com.ar.enbaldeapp.support.Constants.PRODUCT_ID;
import static com.ar.enbaldeapp.support.Constants.PRODUCT_IMAGE;
import static com.ar.enbaldeapp.support.Constants.PRODUCT_NAME;
import static com.ar.enbaldeapp.support.Constants.PRODUCT_PRICE;
import static com.ar.enbaldeapp.support.Constants.PRODUCT_QUANTITY;
import static com.ar.enbaldeapp.support.Constants.PRODUCT_TYPE_ID;
import static com.ar.enbaldeapp.support.Constants.PRODUCT_TYPE_NAME;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;

import com.ar.enbaldeapp.models.Product;
import com.ar.enbaldeapp.models.ProductType;

import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class ProductMust {
    @Test
    public void beCreatedCorrectly_whenInformationIsCorrect() {
        ProductType type = new ProductType(PRODUCT_TYPE_ID, PRODUCT_TYPE_NAME);
        Product sut = new Product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_DESCRIPTION, PRODUCT_PRICE, PRODUCT_QUANTITY, PRODUCT_IMAGE, type);

        assertEquals(PRODUCT_ID, sut.getId());
        assertEquals(PRODUCT_NAME, sut.getName());
        assertEquals(PRODUCT_DESCRIPTION, sut.getDescription());
        assertEquals(PRODUCT_PRICE, sut.getPrice(), 0.001);
        assertEquals(PRODUCT_QUANTITY, sut.getQuantity());
        assertEquals(PRODUCT_IMAGE, sut.getImage());
        assertSame(type, sut.getType());
    }

    @DataPoints("invalid strings")
    public static String[] invalidStrings() {
        return new String[]{"", null, "   "};
    }

    @DataPoints("invalid ids")
    public static long[] invalidIds() {
        return new long[]{-1, 0};
    }

    @DataPoints("invalid prices")
    public static double[] invalidPrices() { return new double[] { -1, 0 }; }

    @Theory
    public void throwException_whenIdIsInvalid(@FromDataPoints("invalid ids") long invalidId) {
        ProductType type = new ProductType(PRODUCT_TYPE_ID, PRODUCT_TYPE_NAME);
        Exception exception = assertThrows(RuntimeException.class, () -> new Product(invalidId, PRODUCT_NAME, PRODUCT_DESCRIPTION, PRODUCT_PRICE, PRODUCT_QUANTITY, PRODUCT_IMAGE, type));
        assertEquals("El id es inválido", exception.getMessage());
    }

    @Theory
    public void throwException_whenNameIsInvalid(@FromDataPoints("invalid strings") String invalidName) {
        ProductType type = new ProductType(PRODUCT_TYPE_ID, PRODUCT_TYPE_NAME);
        Exception exception = assertThrows(RuntimeException.class, () -> new Product(PRODUCT_ID, invalidName, PRODUCT_DESCRIPTION, PRODUCT_PRICE, PRODUCT_QUANTITY, PRODUCT_IMAGE, type));
        assertEquals("El nombre es inválido", exception.getMessage());
    }

    @Theory
    public void throwException_whenDescriptionIsInvalid(@FromDataPoints("invalid strings") String invalidDescription) {
        ProductType type = new ProductType(PRODUCT_TYPE_ID, PRODUCT_TYPE_NAME);
        Exception exception = assertThrows(RuntimeException.class, () -> new Product(PRODUCT_ID, PRODUCT_NAME, invalidDescription, PRODUCT_PRICE, PRODUCT_QUANTITY, PRODUCT_IMAGE, type));
        assertEquals("La descripción es inválida", exception.getMessage());
    }

    @Theory
    public void throwException_whenPriceIsInvalid(@FromDataPoints("invalid prices") double invalidPrice) {
        ProductType type = new ProductType(PRODUCT_TYPE_ID, PRODUCT_TYPE_NAME);
        Exception exception = assertThrows(RuntimeException.class, () -> new Product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_DESCRIPTION, invalidPrice, PRODUCT_QUANTITY, PRODUCT_IMAGE, type));
        assertEquals("El precio es inválido", exception.getMessage());
    }

    @Test
    public void throwException_whenQuantityIsInvalid() {
        ProductType type = new ProductType(PRODUCT_TYPE_ID, PRODUCT_TYPE_NAME);
        Exception exception = assertThrows(RuntimeException.class, () -> new Product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_DESCRIPTION, PRODUCT_PRICE, -1, PRODUCT_IMAGE, type));
        assertEquals("La cantidad es inválida", exception.getMessage());
    }

    @Theory
    public void throwException_whenImageIsInvalid(@FromDataPoints("invalid strings") String invalidImage) {
        ProductType type = new ProductType(PRODUCT_TYPE_ID, PRODUCT_TYPE_NAME);
        Exception exception = assertThrows(RuntimeException.class, () -> new Product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_DESCRIPTION, PRODUCT_PRICE, PRODUCT_QUANTITY, invalidImage, type));
        assertEquals("La imagen es inválida", exception.getMessage());
    }

    @Test
    public void throwException_whenTypeIsInvalid() {
        Exception exception = assertThrows(RuntimeException.class, () -> new Product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_DESCRIPTION, PRODUCT_PRICE, PRODUCT_QUANTITY, PRODUCT_IMAGE, null));
        assertEquals("El tipo es inválido", exception.getMessage());
    }
}
