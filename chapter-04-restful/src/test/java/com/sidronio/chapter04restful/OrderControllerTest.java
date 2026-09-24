package com.sidronio.chapter04restful;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @Test
    void shouldReturnNoOrders() throws Exception {
        when(orderService.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/api/v1/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void shouldReturnAnOrderById() throws Exception {
        UUID uuid = UUID.randomUUID();

        OrderSummary order = new OrderSummary(
                uuid,
                "0001",
                "Name",
                100f
        );

        when(orderService.getOrder(uuid))
                .thenReturn(Optional.of(order));

        mockMvc.perform(get("/api/v1/orders/{orderId}", uuid))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(uuid.toString()))
                .andExpect(jsonPath("$.number").value("0001"))
                .andExpect(jsonPath("$.customerName").value("Name"))
                .andExpect(jsonPath("$.amount").value(100.0));
    }

    @Test
    void shouldNotReturnAnOrderByNonExistingId() throws Exception {
        final var uuid = UUID.randomUUID();

        when(orderService.getOrder(uuid)).thenReturn(
                Optional.empty()
        );

        mockMvc.perform(get("/api/v1/orders/" + uuid))
                .andExpect(status().isNotFound());
    }
}