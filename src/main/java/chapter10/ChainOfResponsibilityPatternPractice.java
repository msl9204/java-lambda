package chapter10;

import chapter10.model.Order;
import chapter10.model.OrderLine;
import chapter10.service.OrderProcessStep;

import java.math.BigDecimal;
import java.util.Arrays;

public class ChainOfResponsibilityPatternPractice {

    public static void main(String[] args) {

        OrderProcessStep initializeStep = new OrderProcessStep(order -> {
            if (order.getStatus() == Order.OrderStatus.CREATED) {
                System.out.println("Start processing order " + order.getId());
                order.setStatus(Order.OrderStatus.IN_PROGRESS);
            }
        });

        OrderProcessStep setOrderAmountStep = new OrderProcessStep(order -> {
            if (order.getStatus() == Order.OrderStatus.IN_PROGRESS) {
                System.out.println("Setting amount of order " + order.getId());
                order.setAmount(order.getOrderLines().stream().map(OrderLine::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add));

            }
        });

        OrderProcessStep verifyOrderStep = new OrderProcessStep(order -> {
            if (order.getStatus() == Order.OrderStatus.IN_PROGRESS) {
                System.out.println("Verifying order " + order.getId());

                if (order.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
                    order.setStatus(Order.OrderStatus.ERROR);
                }
            }
        });

        OrderProcessStep processPaymentStep = new OrderProcessStep(order -> {
            if (order.getStatus() == Order.OrderStatus.IN_PROGRESS) {
                System.out.println("Processing payment of order " + order.getId());
                order.setStatus(Order.OrderStatus.PROCESSED);
            }
        });

        OrderProcessStep handleErrorStep = new OrderProcessStep(order -> {
            if (order.getStatus() == Order.OrderStatus.ERROR) {
                System.out.println("Sending out 'Failed to process order' alert for order " + order.getId());
            }
        });

        OrderProcessStep completeProcessingOrderStep = new OrderProcessStep(order -> {
            if (order.getStatus() == Order.OrderStatus.PROCESSED) {
                System.out.println("Finished processing order " + order.getId());
            }
        });

        OrderProcessStep chainedOrderProcessStep = initializeStep
                .setNext(setOrderAmountStep)
                .setNext(verifyOrderStep)
                .setNext(processPaymentStep)
                .setNext(handleErrorStep)
                .setNext(completeProcessingOrderStep);

        Order order = new Order()
                .setId(1001L)
                .setStatus(Order.OrderStatus.CREATED)
                .setOrderLines(Arrays.asList(new OrderLine().setAmount(BigDecimal.valueOf(1000)),
                        new OrderLine().setAmount(BigDecimal.valueOf(2000))));

        Order failingOrder = new Order()
                .setId(1001L)
                .setStatus(Order.OrderStatus.ERROR)
                .setOrderLines(Arrays.asList(new OrderLine().setAmount(BigDecimal.valueOf(1000)),
                        new OrderLine().setAmount(BigDecimal.valueOf(-2000))));

        chainedOrderProcessStep.process(order);

        chainedOrderProcessStep.process(failingOrder);
    }
}
