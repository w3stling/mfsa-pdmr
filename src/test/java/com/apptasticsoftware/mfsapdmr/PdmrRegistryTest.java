package com.apptasticsoftware.mfsapdmr;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


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
            assertNotEquals(Double.NaN, pdmr.getPrice());
            assertNotEquals(Double.NaN, pdmr.getVolume());
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
            assertNotEquals(pdmrList.get(i - 1), pdmrList.get(i));
        }
    }

    @Test
    public void testHash() throws IOException {
        PdmrRegistry register = new PdmrRegistry();
        List<Transaction> pdmrList = register.getTransactions()
                                             .distinct()
                                             .collect(Collectors.toList());

        assertTrue(pdmrList.size() > 500);

        HashSet<Transaction> set = new HashSet<>(pdmrList);
        assertEquals(pdmrList.size(), set.size());
    }
}
