/*
 * MIT License
 *
 * Copyright (c) 2019, Apptastic Software
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.apptastic.mfsapdmr;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * PDMR transaction registry for Malta Financial Services Authority (MFSA)
 */
public class PdmrRegistry {
    private static final String URL = "https://www.mfsa.mt/firms/capital-markets/prevention-financial-market-abuse/notices/";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Get stream of PDMR transactions
     * @return transactions transactions
     * @throws IOException io exception
     */
    public Stream<Transaction> getTransactions() throws IOException {
        return parse();
    }

    private Stream<Transaction> parse() throws IOException {
        ArrayList<Transaction> transactions = new ArrayList<>();

        Document doc = Jsoup.connect(URL).get();
        Element tableElement = doc.select("table").first();

        Elements tableHeaderElements = tableElement.select("thead tr th");
        String[] headers = new String[16];

        for (int i = 0; i < tableHeaderElements.size(); i++) {
            headers[i] = tableHeaderElements.get(i).text();
        }

        TransactionMapper mapper = new TransactionMapper();
        mapper.initialize(headers);

        Elements tableRowElements = tableElement.select(":not(thead) tr");

        for (int i = 0; i < tableRowElements.size(); i++) {
            Element row = tableRowElements.get(i);
            Elements rowItems = row.select("td");

            Transaction transaction = new Transaction();
            for (int j = 0; j < rowItems.size(); j++) {
                mapper.createTransaction(transaction, j, rowItems.get(j).text());
            }

            if (isValid(transaction)) {
                transactions.add(transaction);
            }
        }

        return transactions.stream();
    }

    private boolean isValid(Transaction transaction) {
        return transaction != null && transaction.getDate() != null &&
                !Double.isNaN(transaction.getPrice()) && !Double.isNaN(transaction.getVolume()) &&
                transaction.getIssuer() != null && !transaction.getIssuer().isBlank();
    }

    static class TransactionMapper {
        private static final String COLUMN_ISSUER = "Name of Issuer";
        private static final String COLUMN_PDMR = "Person Discharging Managerial Responsibility";
        private static final String COLUMN_DATE = "Date";
        private static final String COLUMN_INSTRUMENT_TYPE = "Instrument Type";
        private static final String COLUMN_NATURE_OF_TRANSACTION = "Nature of Transaction";
        private static final String COLUMN_PLACE_OF_TRANSACTION = "Place of Transaction";
        private static final String COLUMN_CURRENCY = "Currency";
        private static final String COLUMN_PRICE = "Price";
        private static final String COLUMN_VOLUME = "Volume";
        private static final String COLUMN_OTHER_INFORMATION = "Other Information";


        private static final HashMap<String, BiConsumer<Transaction, String>> COLUMN_NAME_FIELD_MAPPING;
        final ArrayList<BiConsumer<Transaction, String>> columnIndexFieldMapping;


        TransactionMapper() {
            columnIndexFieldMapping = new ArrayList<>();
        }


        int initialize(String[] header) {
            int i;

            for (i = 0; i < header.length; ++i) {

                if (header[i] == null)
                    break;

                var headerColumnText = header[i].trim();
                columnIndexFieldMapping.add(i, COLUMN_NAME_FIELD_MAPPING.get(headerColumnText));
            }

            return i;
        }

        Transaction createTransaction(Transaction transaction, int index, String value) {
            var fieldMapping = columnIndexFieldMapping.get(index);

            if (fieldMapping != null)
                fieldMapping.accept(transaction, value);

            return transaction;
        }

        private static double parseDouble(String value) {
            var floatNumber = 0.0;

            try {
                value = value.replace(",","").trim();
                value = value.replace(" ", "");
                floatNumber = Double.valueOf(value);
            }
            catch (Exception e) {
                var logger = Logger.getLogger("com.apptastic.mfsapdmr");

                if (logger.isLoggable(Level.WARNING))
                    logger.log(Level.WARNING, "Failed to parse double. ", e);

                floatNumber = Double.NaN;
            }

            return floatNumber;
        }

        static {
            COLUMN_NAME_FIELD_MAPPING = new HashMap<>(32);

            // Name of Issuer
            COLUMN_NAME_FIELD_MAPPING.put(COLUMN_ISSUER, Transaction::setIssuer);

            // Person Discharging Managerial Responsibility
            COLUMN_NAME_FIELD_MAPPING.put(COLUMN_PDMR, Transaction::setPdmr);

            // Date
            COLUMN_NAME_FIELD_MAPPING.put(COLUMN_DATE, (t, v) -> t.setDate(LocalDate.parse(v, DATE_FORMATTER)));

            // Instrument Type
            COLUMN_NAME_FIELD_MAPPING.put(COLUMN_INSTRUMENT_TYPE, Transaction::setInstrumentType);

            // Nature of Transaction
            COLUMN_NAME_FIELD_MAPPING.put(COLUMN_NATURE_OF_TRANSACTION, Transaction::setNatureOfTransaction);

            // Place of Transaction
            COLUMN_NAME_FIELD_MAPPING.put(COLUMN_PLACE_OF_TRANSACTION, Transaction::setPlaceOfTransaction);

            // Currency
            COLUMN_NAME_FIELD_MAPPING.put(COLUMN_CURRENCY, Transaction::setCurrency);

            // Price
            COLUMN_NAME_FIELD_MAPPING.put(COLUMN_PRICE, (t, v) -> t.setPrice(parseDouble(v)));

            // Volume
            COLUMN_NAME_FIELD_MAPPING.put(COLUMN_VOLUME, (t, v) -> t.setVolume(parseDouble(v)));

            // Other Information
            COLUMN_NAME_FIELD_MAPPING.put(COLUMN_OTHER_INFORMATION, (t, v) -> {
                t.setOtherInformation(v);
                if (v != null) {
                    t.setCloselyAssociated(v.toLowerCase().contains("closely associated"));

                    int start1 = v.indexOf("holds the position of");
                    int start2 = v.indexOf("holds the positions of");
                    int start3 = v.indexOf("is a member of");
                    int start4 = v.indexOf("is the");
                    int start5 = v.indexOf("holds a position of");
                    int start = Math.max(start1, start2);
                    int end = v.lastIndexOf("within");
                    int end2 = v.lastIndexOf("of");

                    String role = null;
                    if (start != -1 && end != -1) {
                        role = v.substring(start + 22, end - 1).trim();
                    } else if (start5 != -1 && end != -1) {
                        role = v.substring(start5 + 19, end - 1).trim();
                    } else if (start3 != -1  && end2 != -1 && start3 < end2) {
                        role = v.substring(start3 + 5, end2 - 1).trim();
                    } else if (start4 != -1 && end2 != -1) {
                        role = v.substring(start4 + 3, end2 - 1).trim();
                    }

                    if (role != null) {
                        role = role.trim();
                        if (role.startsWith("a ")) {
                            role = role.substring(2).trim();
                        } else if (role.startsWith("an ")) {
                            role = role.substring(3).trim();
                        }

                        t.setRole(role);
                    }
                }
            });
        }
    }

}
