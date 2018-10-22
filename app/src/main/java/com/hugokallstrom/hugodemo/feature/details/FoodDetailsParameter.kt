package com.hugokallstrom.hugodemo.feature.details

import android.os.Parcelable
import com.hugokallstrom.hugodemo.models.Food
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FoodDetailsParameter(
    val selectedFood: Food
) : Parcelable
