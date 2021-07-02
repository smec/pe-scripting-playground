# Data

The example below shows an abstract of a representative merchant feed. The feed is taken from the Google Merchant Center and is then being enriched with competitor data for that specific offer.

## Merchant Catalog
The `offer` is the base entity the Pricing Engine works on. It contains several attributes, each of which can be a complex object containing other attributes or objects.

```
{
    "id": "547090249",
    "customerId": "ACME Corp.",
    "source": {
        "id": "ACME-whoopCatalog-2775",
        "sourceType": "SmecCatalog",
        "isActive": true
    },
    "market": "DE",
    "price": {
        "amount": 335,
        "currency": "EUR"
    },
    "availability": "InStock",
    "salePrice": {
        "amount": 335,
        "currency": "EUR",
        "validity": {
            "from": 0,
            "to": 9223372036854775807
        }
    },
    "brand": "BRAND 1",
    "gtin": {
        "raw": "742315537122"
    },
    "title": "BRAND Item title",
    "description": "",
    "condition": "NEW",
    "productLink": "https://example.com/",
    "imageLink": "https://example.com/imagelink.jpg",
    "mpn": {
        "raw": "125049/1041"
    },
    "itemGroupId": "",
    "dayOfAcquisition": "2021-06-21",
    "categories": [
        {
            "categoryType": "googleCategoryL1",
            "name": "category 1"
        },
        {
            "categoryType": "googleCategoryL2",
            "name": "category 2"
        },
        {
            "categoryType": "mainCategoryL1",
            "name": "category 1"
        },
        {
            "categoryType": "mainCategoryL2",
            "name": "category 2"
        }
    ],
    "competitors": []
}
```

## Competition Data
The competition data is an extension of the `offer` and includes the list of competitors.

```
"competitors": [
        {
            "name": "Competitor 1",
            "price": {
                "amount": 345,
                "currency": "EUR"
            },
            "priceIncludingTax": {
                "amount": 373.46,
                "currency": "EUR"
            },
            "shippingPrices": [
                {
                    "amount": 0,
                    "markets": [
                        "DE"
                    ]
                }
            ],
            "condition": "UNKNOWN",
            "availability": "InStock"
        },
        {
            "name": "Competitor 2",
            "price": {
                "amount": 345,
                "currency": "EUR"
            },
            "priceIncludingTax": {
                "amount": 373.46,
                "currency": "EUR"
            },
            "shippingPrices": [
                {
                    "amount": 0,
                    "markets": [
                        "DE"
                    ]
                }
            ],
            "condition": "UNKNOWN",
            "availability": "InStock"
        }
]
```
