package com.beatricefarias.mvvmpractice.model

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CountryTaxCalculationTest {
    lateinit var calculator: CountryTaxCalculator

    @Before
    fun setUp() {
        calculator = CountryTaxCalculator()
    }

    @Test
    fun valid() {
        // Assemble
        val priceBeforeTaxInput: Double = 15.0
        val countryTaxPercentageInput: Int = 19
        val expectedTaxCalculation = TaxCalculation(
            priceBeforeTax = priceBeforeTaxInput,
            taxPercentage = countryTaxPercentageInput,
            taxAmount = 2.85,
            grandTotal = 17.85
        )

        // Act
        val result = calculator.calculateTax(priceBeforeTaxInput, countryTaxPercentageInput)

        // Assert
        assertEquals(result, expectedTaxCalculation)
    }

}