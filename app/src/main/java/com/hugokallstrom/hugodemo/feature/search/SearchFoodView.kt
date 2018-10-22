package com.hugokallstrom.hugodemo.feature.search

import android.content.Context
import com.hugokallstrom.hugodemo.R
import com.hugokallstrom.hugodemo.feature.details.FoodDetailsParameter

interface SearchFoodView {
    fun setFoodItems(result: List<SearchResultItem>)
    fun showDetailsFragment(parameter: FoodDetailsParameter)
    fun showError(error: Error)
}

enum class Error {
    FOOD_NOT_FOUND,
    NETWORK
}

fun Error.getErrorText(context: Context): String = when (this) {
    Error.FOOD_NOT_FOUND -> context.getString(R.string.error_food_not_found)
    Error.NETWORK -> context.getString(R.string.error_network)
}
