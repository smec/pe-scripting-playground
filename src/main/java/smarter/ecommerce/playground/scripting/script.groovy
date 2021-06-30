/******************************************************************************
 * Necessary "boilerplate" code - PLEASE DO NOT REMOVE!
 *****************************************************************************/

package smarter.ecommerce.playground.scripting

import smarter.ecommerce.repricing_pipeline.main.server.internal.logic.entities.*

/**
 * Builds an "EnrichedOffer" entity with price/salePrice, availability,
 * title, description, categories and competitors. This code should
 * be adapted to generate an EnrichedOffer object which can be used
 * in the scripts below.
 * @return an EnrichedOffer object
 */
static EnrichedOffer buildEnrichedOffer() {
    return new EnrichedOffer(
            new Price(16.79, Currency.EUR),
            InStock,
            new SalePrice(16.79, Currency.EUR, new Validity(0, Long.MAX_VALUE)),
            "Brand",
            "CoolProduct 1337x",
            "Description of CoolProduct 1337x",
            Arrays.asList(
                    new Category("mainCategoryL1", "Electronics"),
                    new Category("mainCategoryL2", "PC Components"),
                    new Category("googleCategoryL1", "Computer"),
                    new Category("googleCategoryL2", "Accessories")
            ),
            Arrays.asList(
                    new Competitor(
                            "TheOneShop",
                            new Price(16.69, Currency.EUR),
                            InStock
                    ),
                    new Competitor(
                            "BuyStuffOnline",
                            new Price(16.49, Currency.EUR),
                            OutOfStock
                    ),
                    new Competitor(
                            "InternetShop",
                            new Price(16.89, Currency.EUR),
                            InStock
                    ),
                    new Competitor(
                            "OnlineShopper",
                            new Price(16.29, Currency.EUR),
                            OutOfStock
                    )
            )
    )
}

// a variable called "offer" is available in all scripts, so
// we fulfill this requirement here
def offer = buildEnrichedOffer()

/******************************************************************************
 * Everything below this marker can be copied into
 * a filter, condition, or repricing function.
 *
 * NOTE:
 * - Filter and Condition scripts must always
 *   return a BOOLEAN value (true/false)
 * - Repricing Function scripts must always return a number (type double)
 *
 * Sandbox.groovy contains some examples in a structured form.
 *****************************************************************************/

import static smarter.ecommerce.repricing_pipeline.main.server.internal.logic.entities.Availability.*

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
