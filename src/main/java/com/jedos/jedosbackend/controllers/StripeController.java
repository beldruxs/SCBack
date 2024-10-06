// StripeController.java
package com.jedos.jedosbackend.controllers;

import com.jedos.jedosbackend.dto.ServiceDTO;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stripe")
public class StripeController {

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @PostMapping("/create-checkout-session")
    public Map<String, String> createCheckoutSession(@RequestBody List<ServiceDTO> lineItems) {
        Stripe.apiKey = stripeApiKey;

        try {
            SessionCreateParams.Builder builder = new SessionCreateParams.Builder()
                    .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl("http://localhost:4200/success")
                    .setCancelUrl("http://localhost:4200/cancel");

            for (ServiceDTO item : lineItems) {
                builder.addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency("eur")
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName(item.getTitle())
                                                                .setDescription(item.getDescription())
                                                                .build())
                                                .setUnitAmount((long) (item.getPrice() * 100))
                                                .build())
                                .setQuantity(1L) // Set quantity to 0 directly
                                .build());
            }

            Session session = Session.create(builder.build());

            Map<String, String> response = new HashMap<>();
            response.put("id", session.getId());
            return response;
        } catch (Exception e) {
            throw new RuntimeException("Error creating Stripe session", e);
        }
    }
}