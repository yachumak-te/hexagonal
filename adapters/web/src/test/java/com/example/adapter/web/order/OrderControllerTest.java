package com.example.adapter.web.order;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.adapter.web.api_rest.model.CreateOrderRequestDTO;
import com.example.adapter.web.in.order.OrderController;
import com.example.adapter.web.in.order.OrderMapper;
import com.example.adapter.web.in.order.OrderMapperImpl;
import com.example.app.port.in.order.CreateOrderUseCase;
import com.example.domain.order.Order;



@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    private CreateOrderUseCase createOrderUseCase;

    @Spy
    private OrderMapper orderMapper = new OrderMapperImpl();

    @InjectMocks
    private OrderController controller;

    @Test
    public void createOrderTest() {
        // given
        final var request = Instancio.create(CreateOrderRequestDTO.class);
        final var order = Instancio.create(Order.class);
        given(createOrderUseCase.createOrder(any())).willReturn(order);

        // when
        final var actual = controller.createOrder(request);

        // then
        assertNotNull(actual);
        final var expectedCommand = CreateOrderUseCase.CreateOrderCommand.builder().userId(request.getUserId()).build();
        then(createOrderUseCase).should().createOrder(expectedCommand);
        then(orderMapper).should().toCreateOrderCommand(request);
    }
}
