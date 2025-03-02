package com.example.adapter.web.order;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.adapter.web.api_rest.model.CreateOrderRequestDTO;
import com.example.app.port.in.order.CreateOrderUseCase;
import com.example.domain.order.Order;
import com.fasterxml.jackson.databind.ObjectMapper;



@WebMvcTest(OrderController.class)
@ContextConfiguration(classes = OrderController.class)
@Import(value = { OrderMapperImpl.class })
public class OrderControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreateOrderUseCase createOrderUseCase;

    @Test
    public void createOrderTest() throws Exception {
        final var request = Instancio.create(CreateOrderRequestDTO.class);
        final var order = Instancio.create(Order.class);
        given(createOrderUseCase.createOrder(any())).willReturn(order);

        mockMvc.perform(post("/orders")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(request)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.userId").exists())
               .andExpect(jsonPath("$.placedAt").exists())
               .andExpect(jsonPath("$.id").exists());
    }
}
