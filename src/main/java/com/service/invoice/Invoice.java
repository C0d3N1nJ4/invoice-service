package com.service.invoice;

import jakarta.persistence.*;

import java.math.BigDecimal;
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public BigDecimal getSubTotal() {
        return lineItems.stream().map(LineItem::getLineItemTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getVat() {
        return getSubTotal().multiply(new BigDecimal(vatRate)).divide(new BigDecimal(100));
    }

    public BigDecimal getTotal() {
        return getSubTotal().add(getVat());
    }
}
