package com.beatricefarias.mvvmpractice.viewmodel

import com.beatricefarias.mvvmpractice.model.CountryTaxCalculator
import com.beatricefarias.mvvmpractice.model.TaxCalculation
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class TaxCalculationViewModelTest {

    lateinit var taxCalculationViewModel: TaxCalculationViewModel

    @Mock
    lateinit var taxCalculator: CountryTaxCalculator

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        taxCalculationViewModel = TaxCalculationViewModel(taxCalculator)
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

        // Act
        taxCalculationViewModel.calculateTax()

        // Assert
        assertEquals(taxCalculationViewModel.taxCalculation, stub)
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