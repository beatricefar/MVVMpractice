package com.beatricefarias.mvvmpractice.model

data class TaxCalculation(
    val priceBeforeTax: Double = 0.0,
    val taxPercentage: Int = 0,
    val taxAmount: Double = 0.0,
    val grandTotal: Double = 0.0
)