package com.service.invoice;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String client;

    private Long vatRate;

    private Date invoiceDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "lineItem_id")
    private List<LineItem> lineItems;

    public Invoice(Long id, String client, Long vatRate, Date invoiceDate) {
        this.id = id;
        this.client = client;
        this.vatRate = vatRate;
        this.invoiceDate = invoiceDate;
    }

    public Invoice(Long id, String client, Long vat, Date invoiceDate, List<LineItem> lineItems) {
        this.id = id;
        this.client = client;
        this.vatRate = vat;
        this.invoiceDate = invoiceDate;
        this.lineItems = lineItems;
    }

    public Invoice() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Long getVatRate() {
        return vatRate;
    }

    public void setVatRate(Long vatRate) {
        this.vatRate = vatRate;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public double getSubTotal() {
        return lineItems.stream().mapToDouble(lineItem -> lineItem.getUnitPrice().doubleValue() * lineItem.getQuantity()).sum();
    }

    public double getVat() {
        return getSubTotal() * vatRate / 100;
    }

    public double getTotal() {
        return getSubTotal() + getVat();
    }
}
