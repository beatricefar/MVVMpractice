package com.beatricefarias.mvvmpractice.viewmodel

import android.app.Application
import com.beatricefarias.mvvmpractice.R
import com.beatricefarias.mvvmpractice.model.CountryTaxCalculator
import com.beatricefarias.mvvmpractice.model.TaxCalculation

class TaxCalculationViewModel @JvmOverloads constructor(
    app: Application,
    private val countryTaxCalculator: CountryTaxCalculator = CountryTaxCalculator()
): ObservableViewModel(app) {

    var spendingAmount = ""
    var taxPercentage = ""
    var taxCalculation = TaxCalculation()

    var outputPriceBeforeTax = ""
    var outputTaxAmount = ""
    var outputGrandTotal = ""


    init {
        updateOutputs(taxCalculation)
    }

    private fun updateOutputs(taxCalculation: TaxCalculation) {
        outputPriceBeforeTax = getApplication<Application>().getString(R.string.dollar_amount, taxCalculation.priceBeforeTax)
        outputTaxAmount = getApplication<Application>().getString(R.string.dollar_amount, taxCalculation.taxAmount)
        outputGrandTotal = getApplication<Application>().getString(R.string.dollar_amount, taxCalculation.grandTotal)
    }

    fun calculateTax() {
        val checkAmount = spendingAmount.toDoubleOrNull()
        val taxPercentage = taxPercentage.toIntOrNull()

        if (checkAmount != null && taxPercentage != null) {
            taxCalculation = countryTaxCalculator.calculateTax(checkAmount, taxPercentage)
            updateOutputs(taxCalculation)
            notifyChange()
        }
    }

}