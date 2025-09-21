package com.ecommerce.payment_service.PaymentServiceTest;

import com.ecommerce.payment_service.dto.PaymentRequestDTO;
import com.ecommerce.payment_service.dto.PaymentResponseDTO;
import com.ecommerce.payment_service.entity.Payment;
import com.ecommerce.payment_service.repository.PaymentRepo;
import com.ecommerce.payment_service.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {


    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private PaymentRepo paymentRepo;

    @Mock
    PaymentRequestDTO requestDTO;

    @Mock
    Payment mockEntity;


    @BeforeEach
    void setUp(){
        // Arrange - define consistent test data
        Long orderId = 120L;
        Long customerId = 102L;
        BigDecimal amount = BigDecimal.valueOf(1000.00);
        String currency = "INR";
        String paymentMethod = "UPI";
        String status = "PENDING";
        String transactionReference = "TXN123456";

        // mock Request DTO
        requestDTO.setOrderId(orderId);
        requestDTO.setAmount(amount);
        requestDTO.setCurrency(currency);
        requestDTO.setPaymentMethod(paymentMethod);
        requestDTO.setStatus(status);

        // Mock entity
        mockEntity.setId(1L);
        mockEntity.setOrderId(orderId);
        mockEntity.setAmount(amount);
        mockEntity.setCurrency(currency);
        mockEntity.setPaymentMethod(paymentMethod);
        mockEntity.setStatus(status);
    }

    @Test
    void testSavePaymentTransaction(){

        // mock repository behaviour
        Mockito.when(paymentRepo.save(any(Payment.class))).thenReturn(mockEntity);

        // Act
        PaymentResponseDTO response = paymentService.savePaymentTransaction(requestDTO);

        // Assert
       // assertEquals(response.getOrderId(), 109L);
       //assertEquals(BigDecimal.valueOf(1000),response.getAmount());
        assertEquals("INITIATED",response.getStatus());

        //verify repository call => based on the number of call it made
        verify(paymentRepo, times(1)).save(any(Payment.class));
    }
}
