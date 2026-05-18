package iuh.chillteam.service.impl;

import iuh.chillteam.entity.Order;
import iuh.chillteam.entity.User;
import iuh.chillteam.entity.enums.PaymentMethod;
import iuh.chillteam.exception.ForbiddenException;
import iuh.chillteam.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    private Order orderOwnedByUser1;

    @BeforeEach
    void setUp() {
        User owner = User.builder().build();
        owner.setId(1L);

        orderOwnedByUser1 = Order.builder()
                .orderCode("OD20260331001")
                .user(owner)
                .build();
        orderOwnedByUser1.setId(100L);
    }

    @Test
    void shouldThrowForbiddenWhenProcessingPaymentForAnotherUsersOrder() {
        when(orderRepository.findById(100L)).thenReturn(Optional.of(orderOwnedByUser1));

        assertThrows(
                ForbiddenException.class,
                () -> paymentService.processPayment(100L, 2L, PaymentMethod.COD)
        );
    }

    @Test
    void shouldThrowForbiddenWhenGeneratingPaymentUrlForAnotherUsersOrder() {
        when(orderRepository.findById(100L)).thenReturn(Optional.of(orderOwnedByUser1));

        assertThrows(
                ForbiddenException.class,
                () -> paymentService.generatePaymentUrl(100L, 2L, 100000.0, PaymentMethod.BANK_TRANSFER, "127.0.0.1")
        );
    }
}
