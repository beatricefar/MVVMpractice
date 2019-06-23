package com.beatricefarias.mvvmpractice.model

import java.math.RoundingMode

class CountryTaxCalculator {

    fun calculateTax(priceBeforeTax: Double, taxPercentage: Int): TaxCalculation {
        val taxAmount = (priceBeforeTax * (taxPercentage.toDouble() / 100.0))
            .toBigDecimal()
            .setScale(2, RoundingMode.HALF_UP)
            .toDouble()

        val grandTotal = priceBeforeTax + taxAmount

        return TaxCalculation(
            priceBeforeTax,
            taxPercentage,
            taxAmount,
            grandTotal
        )
    }

}