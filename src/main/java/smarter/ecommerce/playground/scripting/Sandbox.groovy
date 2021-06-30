package smarter.ecommerce.playground.scripting

import smarter.ecommerce.repricing_pipeline.main.server.internal.logic.entities.*

import static smarter.ecommerce.repricing_pipeline.main.server.internal.logic.entities.Availability.*

class Sandbox {
    static void main(String[] args) {
        def offer = buildEnrichedOffer()
        def condition = condition5(offer)
        println "condition = " + condition
        if (condition) {
            def recommendedPrice = function5(offer)
            println "recommended price = " + recommendedPrice
        }
    }


    ///////////////////////////////////////////////////////////////////////////
    // Strategy 1: "If my price is higher than the average of all competitors
    // who have the item in stock, reduce my price by 10%".
    //
    static boolean condition1(EnrichedOffer offer) {
        // declare a "closure" to find a list of competitors who have the item in stock
        def getInStockCompetitors = { o -> o.competitors.findAll { it -> it.availability == InStock } }
        // get the list of competitors with the item in stock
        def competitors = getInStockCompetitors(offer)
        // if there is at least 1 competitor
        if (competitors.size() > 0) {
            // calculate the average
            def averageCompetitorPrice = competitors.collect { it.price.amount }.sum() / competitors.size()
            // if our price is higher than the average
            if (offer.price.amount > averageCompetitorPrice) {
                // indicate that a repricing should be done
                return true
            }
        }
        // in all other cases, do not perform a repricing
        return false
    }

    static double function1(EnrichedOffer offer) {
        // simply reduce the price and apply rounding
        return (offer.price.amount * 0.9).round(2)
    }


    ///////////////////////////////////////////////////////////////////////////
    // Strategy 2: "My price for offers in the Google category L2 shall
    // be 1,- EUR cheaper than the prices of competitor X."
    //
    static boolean condition2(EnrichedOffer offer) {
        // retrieve the category name for type "googleCategoryL2"
        def categoryName = offer.categories.find({ it.categoryType == "googleCategoryL2" })?.name
        // find the price of a specific competitor
        def priceOfCompetitorX = offer.competitors.find({ it.name == "TheOneShop" })?.price?.amount
        // indicate repricing if the category is "Accessories", a price for specific competitor is found, and our price is higher
        return categoryName == "Accessories" && priceOfCompetitorX != null && offer.price.amount > priceOfCompetitorX
    }

    static double function2(EnrichedOffer offer) {
        // reduce the price by 1
        return (offer.price.amount - 1).round(2)
    }


    ///////////////////////////////////////////////////////////////////////////
    // Strategy 3: "My price for offers shall be the highest where I have
    // few competitors (max. 10 competitors)."
    //
    static boolean condition3(EnrichedOffer offer) {
        // indicate repricing if there are less than 10 competitors
        return offer.competitors.size() > 0 && offer.competitors.size() <= 10
    }

    static double function3(EnrichedOffer offer) {
        // find the highest competitor price
        def highestCompetitorPrice = offer.competitors.max({ it.price.amount })?.price?.amount
        if (highestCompetitorPrice != null) {
            // go even higher
            return (highestCompetitorPrice + 0.01).round(2)
        }
        return offer.price.amount
    }


    ///////////////////////////////////////////////////////////////////////////
    // Strategy 4: "I want to be the second most expensive for all offers,
    // except for brand X. Prices for offers of brand X shall not change."
    //
    static boolean condition4(EnrichedOffer offer) {
        return offer.brand != "Ignoring" && offer.competitors.size() > 0
    }

    static double function4(EnrichedOffer offer) {
        // find the highest competitor price
        def highestCompetitorPrice = offer.competitors.max({ it.price.amount })?.price?.amount
        if (highestCompetitorPrice != null) {
            // go slightly lower
            return (highestCompetitorPrice - 0.01).round(2)
        }
        return offer.price.amount
    }


    ///////////////////////////////////////////////////////////////////////////
    // Strategy 5: "If competitor X is out of stock for offers of brand Y,
    // my offers for this brand Y shall be raised by 5 %, but only if the
    // price raised is still below 100,- EUR."
    //
    static boolean condition5(EnrichedOffer offer) {
        // only for specific brand
        if (offer.brand == "Brand") {
            // is there a competition offer from a specific competitor which is not in stock
            def competitor = offer.competitors.find({ it.name == "OnlineShopper" && it.availability != InStock })
            // if yes, this indicates that a repricing should be done
            return competitor != null
        }
        return false
    }

    static double function5(EnrichedOffer offer) {
        // find the price for a specific competitor which has the item not in stock
        def competitorPrice = offer.competitors.find({ it.name == "OnlineShopper" && it.availability != InStock })?.price?.amount
        if (competitorPrice != null) {
            // new price is either competitorPrice plus 5%, or 99.99 (whichever is lower)
            return Math.min(competitorPrice * 1.05, 99.99).round(2)
        }
        return offer.price.amount
    }


    static EnrichedOffer buildEnrichedOffer() {
        return new EnrichedOffer(
                new Price(16.79, Currency.EUR),
                InStock,
                new SalePrice(16.79, Currency.EUR, new Validity(0, Long.MAX_VALUE)),
                "Brand", "CoolProduct 1337x", "Description of CoolProduct 1337x",
                Arrays.asList(
                        new Category("mainCategoryL1", "Electronics"),
                        new Category("mainCategoryL2", "PC Components"),
                        new Category("googleCategoryL1", "Computer"),
                        new Category("googleCategoryL2", "Accessories")
                ),
                Arrays.asList(
                        new Competitor("TheOneShop", new Price(16.69, Currency.EUR), InStock),
                        new Competitor("BuyStuffOnline", new Price(16.49, Currency.EUR), OutOfStock),
                        new Competitor("InternetShop", new Price(16.79, Currency.EUR), InStock),
                        new Competitor("OnlineShopper", new Price(16.29, Currency.EUR), OutOfStock)
                )
        )
    }
}
