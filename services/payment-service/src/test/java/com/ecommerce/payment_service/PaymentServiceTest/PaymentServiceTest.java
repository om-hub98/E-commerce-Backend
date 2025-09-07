package com.ecommerce.payment_service.PaymentServiceTest;

import com.ecommerce.payment_service.dto.PaymentTransactionRequestDTO;
import com.ecommerce.payment_service.dto.PaymentTransactionResponseDTO;
import com.ecommerce.payment_service.entity.PaymentTransaction;
import com.ecommerce.payment_service.repository.PaymentTransactionRepo;
import com.ecommerce.payment_service.service.PaymentTransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {


    @InjectMocks
    private PaymentTransactionService paymentTransactionService;

    @Mock
    private PaymentTransactionRepo paymentTransactionRepo;

    @Mock
    PaymentTransactionRequestDTO requestDTO;

    @Mock
    PaymentTransaction mockEntity;


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
        requestDTO.setCustomerId(customerId);
        requestDTO.setAmount(amount);
        requestDTO.setCurrency(currency);
        requestDTO.setPaymentMethod(paymentMethod);
        requestDTO.setStatus(status);
        requestDTO.setTransactionReference(transactionReference);

        // Mock entity
        mockEntity.setId(1L);
        mockEntity.setOrderId(orderId);
        mockEntity.setCustomerId(customerId);
        mockEntity.setAmount(amount);
        mockEntity.setCurrency(currency);
        mockEntity.setPaymentMethod(paymentMethod);
        mockEntity.setStatus(status);
        mockEntity.setTransactionReference(transactionReference);
    }

    @Test
    void testSavePaymentTransaction(){

        // mock repository behaviour
        Mockito.when(paymentTransactionRepo.save(any(PaymentTransaction.class))).thenReturn(mockEntity);

        // Act
        PaymentTransactionResponseDTO response = paymentTransactionService.savePaymentTransaction(requestDTO);

        // Assert
       // assertEquals(response.getOrderId(), 109L);
       //assertEquals(BigDecimal.valueOf(1000),response.getAmount());
        assertEquals("INITIATED",response.getStatus());

        //verify repository call => based on the number of call it made
        verify(paymentTransactionRepo, times(1)).save(any(PaymentTransaction.class));
    }
}
