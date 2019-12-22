package com.apptastic.mfsapdmr;

import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class PdmrRegistryTest {

    @Test
    public void testSearch() throws IOException {
        PdmrRegistry register = new PdmrRegistry();
        List<Transaction> pdmrList = register.getTransactions()
                                         .collect(Collectors.toList());

        assertTrue(pdmrList.size() > 500);
        for (Transaction pdmr : pdmrList) {
            assertNotNull(pdmr.getIssuer());
            assertNotNull(pdmr.getPdmr());
            assertNotNull(pdmr.getDate());
            assertNotNull(pdmr.getInstrumentType());
            assertNotNull(pdmr.getNatureOfTransaction());
            assertNotNull(pdmr.getPlaceOfTransaction());
            assertNotNull(pdmr.getCurrency());
            assertNotNull(pdmr.getPrice());
            assertNotNull(pdmr.getVolume());
            assertNotNull(pdmr.getOtherInformation());
            assertNotNull(pdmr.getRole());
        }
    }

    @Test
    public void testEquals() throws IOException {
        PdmrRegistry register = new PdmrRegistry();
        List<Transaction> pdmrList = register.getTransactions()
                                             .distinct()
                .collect(Collectors.toList());

        assertTrue(pdmrList.size() > 500);
        for (int i = 1; i < pdmrList.size(); ++i) {
            assertFalse(pdmrList.get(i-1).equals(pdmrList.get(i)));
        }
    }

    @Test
    public void testHash() throws IOException {
        PdmrRegistry register = new PdmrRegistry();
        List<Transaction> pdmrList = register.getTransactions()
                .distinct()
                .collect(Collectors.toList());

        assertTrue(pdmrList.size() > 500);

        HashSet<Transaction> set = new HashSet<>();

        for (int i = 0; i < pdmrList.size(); ++i) {
            set.add(pdmrList.get(i));
        }

        assertEquals(pdmrList.size(), set.size());
    }
}
