package com.apptastic.mfsapdmr;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class TransactionTest {

    @Test
    public void testEquals() {
        Transaction t1 = createTransaction(1.0, 2.0);
        Transaction t2 = createTransaction(2.0, 3.0);
        assertEquals(t1, t1);
        assertNotEquals(null, t1);
        assertNotEquals(t1, t2);
    }

    @Test
    public void testHashCode() {
        Transaction t1 = createTransaction(1.0, 2.0);
        Transaction t2 = createTransaction(2.0, 3.0);
        assertEquals(t1.hashCode(), t1.hashCode());
        assertNotEquals(t1.hashCode(), t2.hashCode());
    }

    private Transaction createTransaction(double price, double volume) {
        Transaction transaction = new Transaction();
        transaction.setIssuer("a");
        transaction.setPdmr("b");
        transaction.setDate(LocalDate.now());
        transaction.setInstrumentType("c");
        transaction.setNatureOfTransaction("d");
        transaction.setPlaceOfTransaction("e");
        transaction.setCurrency("e");
        transaction.setPrice(price);
        transaction.setVolume(volume);
        transaction.setOtherInformation("f");
        transaction.setRole("g");
        return transaction;
    }
}
