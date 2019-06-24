package com.beatricefarias.mvvmpractice.viewmodel

import android.app.Application
import androidx.databinding.BaseObservable
import com.beatricefarias.mvvmpractice.R
import com.beatricefarias.mvvmpractice.model.CountryTaxCalculator
import com.beatricefarias.mvvmpractice.model.TaxCalculation

class TaxCalculationViewModel(
    private val application: Application,
    private val countryTaxCalculator: CountryTaxCalculator = CountryTaxCalculator()
): BaseObservable() {

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
        outputPriceBeforeTax = application.getString(R.string.dollar_amount, taxCalculation.priceBeforeTax)
        outputTaxAmount = application.getString(R.string.dollar_amount, taxCalculation.taxAmount)
        outputGrandTotal = application.getString(R.string.dollar_amount, taxCalculation.grandTotal)
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