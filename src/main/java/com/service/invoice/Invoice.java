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

    private Long vat;

    private Date invoiceDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "lineItem_id")
    private List<LineItem> lineItems;

    public Invoice(Long id, String client, Long vat, Date invoiceDate) {
        this.id = id;
        this.client = client;
        this.vat = vat;
        this.invoiceDate = invoiceDate;
    }

    public Invoice(Long id, String client, Long vat, Date invoiceDate, List<LineItem> lineItems) {
        this.id = id;
        this.client = client;
        this.vat = vat;
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

    public Long getVat() {
        return vat;
    }

    public void setVat(Long vat) {
        this.vat = vat;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
}
