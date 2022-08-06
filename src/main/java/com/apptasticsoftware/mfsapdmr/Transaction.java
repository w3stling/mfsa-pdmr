/*
 * MIT License
 *
 * Copyright (c) 2020, Apptastic Software
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
package com.apptasticsoftware.mfsapdmr;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

/**
 * Person Discharging Managerial Responsibility (PDMR) transaction
 */
public class Transaction implements Comparable<Transaction> {
    private static final Comparator<Transaction> COMPARATOR = Comparator.comparing(Transaction::getDate)
                                                                        .thenComparing(Transaction::getIssuer)
                                                                        .thenComparing(Transaction::getPdmr);
    private String issuer;
    private String pdmr;
    private boolean isCloselyAssociated;
    private LocalDate date;
    private String instrumentType;
    private String natureOfTransaction;
    private String placeOfTransaction;
    private String currency;
    private double price;
    private double volume;
    private String role;
    private String otherInformation;

    /**
     * The name of issuer for example Kindred Group plc
     * @return issuer
     */
    public String getIssuer() {
        return issuer;
    }

    /**
     * Set the name of issuer
     * @param issuer issuer
     */
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    /**
     * The person discharging managerial responsibility (PDMR) for example Michael Baker
     * @return pdmr
     */
    public String getPdmr() {
        return pdmr;
    }

    /**
     * Set the person discharging managerial responsibility
     * @param pdmr pdmr
     */
    public void setPdmr(String pdmr) {
        this.pdmr = pdmr;
    }

    /**
     * Check if there is a closely associated for the transaction.
     * @return is closely associated
     */
    public boolean isCloselyAssociated() {
        return isCloselyAssociated;
    }

    /**
     * Set if there is a closely associated for the transaction.
     * @param closelyAssociated - is closely associated
     */
    public void setCloselyAssociated(boolean closelyAssociated) {
        isCloselyAssociated = closelyAssociated;
    }

    /**
     * The date of the transaction
     * @return date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Set the date of the transaction
     * @param date date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * The tnstrument type for example equity
     * @return instrument type
     */
    public String getInstrumentType() {
        return instrumentType;
    }

    /**
     * Set the instrument type
     * @param instrumentType instrument type
     */
    public void setInstrumentType(String instrumentType) {
        this.instrumentType = instrumentType;
    }

    /**
     * The Nature of transaction example purchase, buy, bought, sold, and sell
     * @return nature of transaction
     */
    public String getNatureOfTransaction() {
        return natureOfTransaction;
    }

    /**
     * Set the nature of the transaction
     * @param natureOfTransaction nature of transaction
     */
    public void setNatureOfTransaction(String natureOfTransaction) {
        this.natureOfTransaction = natureOfTransaction;
    }

    /**
     * Place of transaction or trading venue for example Malta Stock Exchange
     * @return place of transaction
     */
    public String getPlaceOfTransaction() {
        return placeOfTransaction;
    }

    /**
     * Set the place of transaction
     * @param placeOfTransaction place of transaction
     */
    public void setPlaceOfTransaction(String placeOfTransaction) {
        this.placeOfTransaction = placeOfTransaction;
    }

    /**
     * The currency of the transaction for example EUR
     * @return currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Set the currency fo the transaction
     * @param currency currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * The price of the transaction
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Set the price of the transaction
     * @param price price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * The volume of the transaction
     * @return volume
     */
    public double getVolume() {
        return volume;
    }

    /**
     * Set the volume of the transaction
     * @param volume volume
     */
    public void setVolume(double volume) {
        this.volume = volume;
    }

    /**
     * The role of the PDMR for example Chief Executive Officer
     * @return role
     */
    public Optional<String> getRole() {
        return Optional.ofNullable(role);
    }

    /**
     * Set the role of the transaction
     * @param role role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Get the other information or a description for the transaction
     * @return info
     */
    public String getOtherInformation() {
        return otherInformation;
    }

    /**
     * Set the other information of the transaction
     * @param otherInformation info
     */
    public void setOtherInformation(String otherInformation) {
        this.otherInformation = otherInformation;
    }

    @Override
    public int compareTo(Transaction o) {
        return COMPARATOR.compare(this, o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return getIssuer().equals(that.getIssuer()) &&
                getPdmr().equals(that.getPdmr()) &&
                getDate().equals(that.getDate()) &&
                getInstrumentType().equals(that.getInstrumentType()) &&
                getNatureOfTransaction().equals(that.getNatureOfTransaction()) &&
                getPlaceOfTransaction().equals(that.getPlaceOfTransaction()) &&
                getCurrency().equals(that.getCurrency()) &&
                getPrice() == that.getPrice() &&
                getVolume() == that.getVolume() &&
                getRole().equals(that.getRole()) &&
                getOtherInformation().equals(that.getOtherInformation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIssuer(), getPdmr(), getDate(), getInstrumentType(), getNatureOfTransaction(), getPlaceOfTransaction(), getCurrency(), getPrice(), getVolume(), getRole(), getOtherInformation());
    }
}
