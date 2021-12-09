# Script Examples

## Filters & Conditions

This section contains basic examples that give you an idea how to script Filters and Conditions.

The offer has more than 10 competitors:

```groovy
offer.competitors?.size() > 10
```

---
The offer has less than 3 competitors:

```groovy
offer.competitors?.size() < 3
```

---
The offer price is lower than 35:

```groovy
offer.price.amount < 35
```

---
The offer price is equal to or higher than 35:

```groovy
offer.price.amount >= 35
```

---
The offer price is in between 10 and 20:

```groovy
offer.price.amount >= 10 && offer.price.amount <= 20
```

---
The offer gtin is 1234567890123:

```groovy
offer.gtin == "123456789"
```

---
The offer gtin starts with "123":

```groovy
offer.gtin.startsWith("123")
```

(accepts values `123456789`, `1230987654`, `123123123`, `123`)

---
The offer title contains "ABC" (case-sensitive):

```groovy
offer.title.contains("ABC")
```

(accepts values `ABC`, `DEFABC`, `asdfABCqwer`, `qwerABC`)

---
The offer title starts with ABC (case-sensitive):

```groovy
offer.title.startsWith("ABC")
```

(accepts values `ABCasdf`, `ABC`)

---
The offer is available (in stock):

```groovy
import static smarter.ecommerce.repricing_pipeline.main.server.internal.logic.entities.Availability.*

return offer.availability == InStock
```

---
The offer is not available (out of stock):

```groovy
import static smarter.ecommerce.repricing_pipeline.main.server.internal.logic.entities.Availability.*

return offer.availability == OutOfStock
```

---
The offer title is "ABC" (case-sensitive):

```groovy
offer.title == "ABC"
```

(accepts only `ABC`)

---
The offer title is "abc" (case-insensitive):

```groovy
offer.title.toLowerCase() == "abc"
```

(accepts values `abc`, `Abc`, `aBc`, and any other combination of lower-/uppercase letters a, b, c)

---
The brand is "ABC" (case-sensitive):

```groovy
offer.brand == "ABC"
```

---
The brand is one of "ABC", "DEF", or "GHI" (case-sensitive):

```groovy
["ABC", "DEF", "GHI"].contains(offer.brand)
```

---
The brand starts with "abc" (case-insensitive):

```groovy
offer.brand.toLowerCase().startsWith("abc")
```

---
The brand contains "ABC" (case-insensitive):

```groovy
offer.brand.toLowerCase().contains("abc")
```

---
The brand is not "ABC" (case-sensitive):

```groovy
offer.brand != "ABC"
```

---
The brand is not "ABC" (case-insensitive):

```groovy
offer.brand.toLowerCase() != "abc"
```

## Repricing Functions

This section contains basic examples that give you an idea how to script repricing functions.

I want to be cheaper than the cheapest competitor:

```groovy
offer.competitors.collect { it.price.amount }.min() - x
```

(`x`: amount below cheapest competitor)

---
I want to be the second most expensive:

```groovy
offer.competitors.collect { it.price.amount }.sort().reverse()[1]
```

(works only if there are at least 2 competitors)

---
I want to be the most expensive

```groovy
offer.competitors.collect { it.price.amount }.max() + x
```

(`x`: amount above most expensive competitor)

---
I want to decrease the price by x:

```groovy
offer.price.amount - x
```

---
I want to increase the price by x:

```groovy
offer.price.amount + x
```

---
I want to decrease the price by x%:

```groovy
offer.price.amount * (1.0 - x / 100)
```

---
I want to increase the price by x%

```groovy
offer.price.amount * (1.0 + x / 100)
```

---
I want to be x% cheaper than competitor A:

```groovy
offer.competitors.collect { it.price.amount }.min() * (1.0 - x / 100)
```

---
I want to be x% more expensive than the most expensive competitor:

```groovy
offer.competitors.collect { it.price.amount }.max() * (1.0 + x / 100)
```

---
I want to be on average with the competition:

```groovy
offer.competitors.collect { it.price.amount }.sum() / offer.competitors.size()
```
## Using the script library
For an easy approach there is a library in place that covers common use cases for scripted filter, condition and repricing.
### Use cases
* Checks if the offer has at least a given amount of competitors.
```groovy
lib.hasAtLeastXCompetitors(2)
```

*  Check if a specific competitor is the only one competing with the offer.
```groovy
lib.isXMyOnlyCompetitor("amazon")
```

* Retrieve the cheapest competitors price for the offer or use a given default value.
```groovy
lib.getCheapestCompetitorPrice(offer.price.amount)
```

* Retrieve the cheapest competitors price for the offer while ignoring a specific competitor or use a given default value that is used when there are no competitors.
```groovy
lib.getCheapestCompetitorPriceIgnoring("amazon", offer.price.amount)
```

/** Retrieve cheapest competitors price for the offer and reduce that price by a given amount or uses a default price when no competitor is found.
```groovy
lib.getCheapestCompetitorPriceReducedBy(0.05, offer.price.amount)
```

### Method Composition
Beside the existing shorthand methods, new use cases can easily be composed by using the underlying functionality.

* [Filter](#competitor-filter) competitors while [excluding](#competitor-exclusion) certain ones and returning their price, or a default value instead when no competitor was found.
```groovy
lib.getCompetitorPrice(lib.cheapest(), lib.excluding("amazon"), offer.price.amount)
```

* [Filter](#competitor-filter) competition for a price reference and use a [modulator](#competitor-price-modulators) to position the price relative to that competitor's price. A default value is used when the filter yields no competition.
```groovy
lib.positionToCompetitor(lib.cheapest(), lib.subtract(0.05), offer.price.amount)
```

#### Competitor Exclusion
Competitor Exclusions are used in combination with [filters](#competitor-filter) to exclude competitors that are not relevant.

* Exclude a competitor if its name matches the given name.
```groovy
lib.cheapest(lib.excluding("amazon"))
```

* Exclude a competitor if its name is contained in the list of names.
```groovy
lib.cheapest(lib.excluding(["amazon", "ebay"]))
```

#### Competitor Filter
Competitor Filters are used in shorthand methods to find a competitor based upon a certain criteria.
* Find a competitor by name
```groovy
lib.byName("amazon")
```

* Find competitor with the cheapest price
```groovy
lib.cheapest()
```

* Find competitor with the cheapest price while using an [exclusion](#competitor-exclusion) to reduce the set of relevant competitors.
```groovy
lib.cheapest(lib.excluding("amazon"))
```

* Find the nth cheapest competitor
```groovy
lib.nthCheapest(2)
```

* Find nth cheapest competitor while using an [exclusion](#competitor-exclusion) to reduce the set of relevant competitors.
```groovy
lib.nthCheapest(2, lib.excluding("amazon"))
```

* Find competitors for the most expensive price
```groovy
lib.mostExpensive()
```

* Find competitor for the most expensive price while using an [exclusion](#competitor-exclusion) to reduce the set of relevant competitors.
```groovy
lib.mostExpensive(lib.excluding("amazon"))
```

* Find the nth most expensive competitor
```groovy
lib.nthMostExpensive(2)
```

* Retrieve the nth most expensive competitor while using an [exclusion](#competitor-exclusion) to reduce the set of relevant competitors.
```groovy
lib.nthMostExpensive(2, lib.excluding("amazon"))
```

#### Competitor Price Modulators
Price Modulators are used in [method composition](#method-composition) to relatively position the new offer price to the price provided by the shorthand method.

* Reduce price by a given percentage
```groovy
lib.percentBelow(5.00)
```

* Increase price by a given percentage
```groovy
lib.percentAbove(5.00)
```
* Reduce price by a given amount
```groovy
lib.subtract(0.05)
```
* Increase price by a given amount
```groovy
lib.add(0.05)
```

### Examples
```groovy
if (lib.isXMyOnlyCompetitor("amazon")) {
    offer.price.amount
} else {
    lib.getCheapestCompetitorPriceReducedBy(0.05, offer.price.amount)
}
```
