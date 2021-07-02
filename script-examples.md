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
offer.gtin.raw == "123456789"
```

---
The offer gtin starts with "123":

```groovy
offer.gtin.raw.startsWith("123")
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
