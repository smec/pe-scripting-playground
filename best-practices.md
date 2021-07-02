# Best Practices

This is a collection of "best practices" showcasing
* how to make best use of the Pricing Engine concepts and scripting;
* what works best according to feedback we received.

Please note that the code shown here can be simplified or written differently, but readability and comments are more important for understanding what's going on.

## Conditions should focus on the "Why" of a Repricing Function

A Repricing Function should focus purely on coming up with a Price Recommendation, without the need of any control flow (no `if ... else`, etc.). Therefore, a Condition needs to cover all cases which lead to the Repricing Function.

### Example of How It Should Not Be Done:

#### Strategy

> I want my prices to be cheaper than the cheapest competitor.

#### Condition

```groovy
true
```

#### Repricing Function

```groovy
// try to find the cheapest competitor price
def cheapestCompetitorPrice = offer.competitors.find { it.price.amount }?.min()?.price?.amount
// if the cheapest competitor price was found and it is lower than our offer price
if (cheapestCompetitorPrice != null && offer.price.amount > cheapestCompetitorPrice) {
    // then recommend a cheaper price
    return cheapestCompetitorPrice - 0.01
} else {
    // otherwise recommend our original price
    return offer.price.amount
}
```

#### Why not?

The Condition governs if a Price Recommendation should be created. If the Condition evaluates to `false`, then the
next Rule (Condition & Repricing Function) is used. In the case shown above the Condition will always lead to a Price
Recommendation and all subsequent Rules (and Strategies!) will be ignored, which is not intended in most cases.

### Example of How It Should Be Done:

#### Strategy

> I want my prices to be cheaper than the cheapest competitor.

#### Condition

```groovy
// try to find the cheapest competitor price
def cheapestCompetitorPrice = offer.competitors.find { it.price.amount }?.min()?.price?.amount
// if the cheapest competitor price was found and it is lower than our offer price
if (cheapestCompetitorPrice != null && offer.price.amount > cheapestCompetitorPrice) {
    // indicate that the Repricing Function should come up with a Price Recommendation
    return true
} else {
    // otherwise let other Rules and Strategies evaluate the offer
    return false
}
```

#### Repricing Function

```groovy
// at this point we know from the condition that there is a cheaper competitor
// so we simply take the cheapest price and reduce it
return offer.competitors.find { it.price.amount }?.min()?.price?.amount - 0.01
```
