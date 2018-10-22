package com.hugokallstrom.hugodemo.feature.favourites

import android.content.Context
import com.hugokallstrom.hugodemo.R
import com.hugokallstrom.hugodemo.models.Food

interface FavouritesView {
    fun setFavourites(favourites: List<Food>)
    fun showError(error: com.hugokallstrom.hugodemo.feature.favourites.Error)
}

enum class Error {
    DATABASE_ERROR
}

fun Error.getErrorText(context: Context): String = when (this) {
    Error.DATABASE_ERROR -> context.getString(R.string.error_database)
}
