package chapter10;

import chapter10.model.Price;
import chapter10.service.BasicPriceProcessor;
import chapter10.service.DisCountPriceProcessor;
import chapter10.service.PriceProcessor;
import chapter10.service.TaxPriceProcessor;

public class DecoratorPatternPractice {

    public static void main(String[] args) {

        Price unprocessedPrice = new Price("Original Price");

        PriceProcessor basicPriceProcessor = new BasicPriceProcessor();
        PriceProcessor disCountPriceProcessor = new DisCountPriceProcessor();
        PriceProcessor taxPriceProcessor = new TaxPriceProcessor();

        PriceProcessor decoratedPriceProcessor = basicPriceProcessor
                .andThen(disCountPriceProcessor)
                .andThen(taxPriceProcessor);

        Price processedPrice = decoratedPriceProcessor.process(unprocessedPrice);
        System.out.println(processedPrice.getPrice());

        PriceProcessor decoratedPriceProcessor2 = basicPriceProcessor
                .andThen(taxPriceProcessor)
                .andThen(price -> new Price(price.getPrice() + ", then applied another procedure"));

        Price processedPrice2 = decoratedPriceProcessor2.process(unprocessedPrice);
        System.out.println(processedPrice2.getPrice());
    }
}
