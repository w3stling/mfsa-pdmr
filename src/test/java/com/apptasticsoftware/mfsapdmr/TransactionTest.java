package com.apptasticsoftware.mfsapdmr;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


class TransactionTest {

    @Test
    void testEquals() {
        Transaction t1 = createTransaction(1.0, 2.0);
        Transaction t2 = createTransaction(2.0, 3.0);
        assertEquals(t1, t1);
        assertNotEquals(null, t1);
        assertNotEquals(t1, t2);
    }

    @Test
    void testHashCode() {
        Transaction t1 = createTransaction(1.0, 2.0);
        Transaction t2 = createTransaction(2.0, 3.0);
        assertEquals(t1.hashCode(), t1.hashCode());
        assertNotEquals(t1.hashCode(), t2.hashCode());
    }

    Transaction createTransaction(double price, double volume) {
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
