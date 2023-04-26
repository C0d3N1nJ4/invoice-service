package com.service.invoice;

import com.service.invoice.exceptions.InvoiceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new invoice", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "202", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Invoice.class)))
    })
    public Invoice createInvoice(@RequestBody Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @GetMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all invoices", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Invoice.class)))
    })
    public Iterable<Invoice> getInvoices() {
        return invoiceRepository.findAll();
    }

    @GetMapping("/{invoiceId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get an invoice by id", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Invoice.class)))
    })
    public Optional<Invoice> getInvoice(@PathVariable Long invoiceId) {
       if (!invoiceRepository.existsById(invoiceId)) {
            throw new InvoiceNotFoundException(invoiceId);
        } else {
            return invoiceRepository.findById(invoiceId);
        }
    }
}
