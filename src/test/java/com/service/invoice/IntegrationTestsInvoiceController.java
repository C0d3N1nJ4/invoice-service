package com.service.invoice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
public class IntegrationTestsInvoiceController {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void saveCustomer_StatusCREATED() throws Exception{

        this.mockMvc.perform(post("/invoices")

                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"client\": \"ABC Company\",\n" +
                                "  \"vatRate\": 20,\n" +
                                "  \"invoiceDate\": \"2023-04-26\",\n" +
                                "  \"lineItems\": [\n" +
                                "    {\n" +
                                "      \"description\": \"Item 1\",\n" +
                                "      \"quantity\": 2,\n" +
                                "      \"unitPrice\": 10.50\n" +
                                "    },\n" +
                                "    {\n" +
                                "      \"description\": \"Item 2\",\n" +
                                "      \"quantity\": 3,\n" +
                                "      \"unitPrice\": 5.25\n" +
                                "    }\n" +
                                "  ]\n" +
                                "}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void getAllInvoicesTest_StatusOK() throws Exception{
        mockMvc.perform(get("/invoices").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void getInvoiceByIdTest_StatusOK() throws Exception{
        mockMvc.perform(get("/invoices/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}
