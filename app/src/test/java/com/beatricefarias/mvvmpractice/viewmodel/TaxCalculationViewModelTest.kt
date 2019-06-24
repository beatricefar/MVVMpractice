package com.beatricefarias.mvvmpractice.viewmodel

import android.app.Application
import com.beatricefarias.mvvmpractice.model.CountryTaxCalculator
import com.beatricefarias.mvvmpractice.model.TaxCalculation
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when`
import com.beatricefarias.mvvmpractice.R.*


class TaxCalculationViewModelTest {

    private lateinit var taxCalculationViewModel: TaxCalculationViewModel

    @Mock
    lateinit var taxCalculator: CountryTaxCalculator
    @Mock
    lateinit var application: Application

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        `when`(application.getString(eq(string.dollar_amount), any(Double::class.java))).thenReturn("$0.00")
        taxCalculationViewModel = TaxCalculationViewModel(application, taxCalculator)
    }

    @Test
    fun valid() {
        // Assemble
        taxCalculationViewModel.spendingAmount = "15.08"
        taxCalculationViewModel.taxPercentage = "10"
        val stub = TaxCalculation(
            priceBeforeTax = 15.08,
            taxPercentage = 10,
            taxAmount = 1.51,
            grandTotal = 16.59
        )
        `when`(taxCalculator.calculateTax(15.08, 10)).thenReturn(stub)
        `when`(application.getString(eq(string.dollar_amount), any(Double::class.java)))
            .thenReturn("$15.08", "$1.51", "$16.59")

        // Act
        taxCalculationViewModel.calculateTax()

        // Assert
        assertEquals(taxCalculationViewModel.taxCalculation, stub)
        assertEquals("$15.08", taxCalculationViewModel.outputPriceBeforeTax)
        assertEquals("$1.51", taxCalculationViewModel.outputTaxAmount)
        assertEquals("$16.59", taxCalculationViewModel.outputGrandTotal)
    }

    @Test
    fun invalid_amountEmpty() {
        // Assemble
        taxCalculationViewModel.spendingAmount = ""
        taxCalculationViewModel.taxPercentage = "10"

        // Act
        taxCalculationViewModel.calculateTax()

        // Assert
        verify(taxCalculator, never()).calculateTax(anyDouble(), anyInt())
    }

    @Test
    fun invalid_percentageEmpty() {
        // Assemble
        taxCalculationViewModel.spendingAmount = "15.08"
        taxCalculationViewModel.taxPercentage = ""

        // Act
        taxCalculationViewModel.calculateTax()

        // Assert
        verify(taxCalculator, never()).calculateTax(anyDouble(), anyInt())
    }

}