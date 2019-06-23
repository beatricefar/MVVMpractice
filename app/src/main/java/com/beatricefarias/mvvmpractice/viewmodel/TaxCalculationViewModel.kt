package com.beatricefarias.mvvmpractice.viewmodel

import com.beatricefarias.mvvmpractice.model.CountryTaxCalculator
import com.beatricefarias.mvvmpractice.model.TaxCalculation

class TaxCalculationViewModel(
    private val countryTaxCalculator: CountryTaxCalculator = CountryTaxCalculator()
) {

    var spendingAmount = ""
    var taxPercentage = ""
    var taxCalculation = TaxCalculation()

    fun calculateTax() {
        val checkAmount = spendingAmount.toDoubleOrNull()
        val taxPercentage = taxPercentage.toIntOrNull()

        if (checkAmount != null && taxPercentage != null) {
            taxCalculation = countryTaxCalculator.calculateTax(checkAmount, taxPercentage)
        }
    }

}