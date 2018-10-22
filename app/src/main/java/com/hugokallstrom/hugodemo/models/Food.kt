package com.hugokallstrom.hugodemo.models

import android.content.Context
import android.os.Parcelable
import com.hugokallstrom.hugodemo.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Food(
    val id: String,
    val title: String,
    val category: String,
    val gramsperserving: Double,
    val calories: Int,
    val cholesterol: Double,
    val sugar: Double,
    val fiber: Double,
    val unsaturatedfat: Double,
    val potassium: Double,
    val sodium: Double,
    val protein: Double,
    val fat: Double,
    val carbohydrates: Double,
    val saturatedfat: Double
) : Parcelable

fun Food.formatContents(context: Context): String {
    return StringBuilder()
        .append(context.getString(R.string.contents_grams_per_serving, String.format("%.2f", gramsperserving))).append("\n")
        .append(context.getString(R.string.contents_calories, calories)).append("\n")
        .append(context.getString(R.string.contents_cholesterol, String.format("%.2f", cholesterol))).append("\n")
        .append(context.getString(R.string.contents_sugar, String.format("%.2f", sugar))).append("\n")
        .append(context.getString(R.string.contents_fiber, String.format("%.2f", fiber))).append("\n")
        .append(context.getString(R.string.contents_unsaturatedfat, String.format("%.2f", unsaturatedfat))).append("\n")
        .append(context.getString(R.string.contents_potassium, String.format("%.2f", potassium))).append("\n")
        .append(context.getString(R.string.contents_sodium, String.format("%.2f", sodium))).append("\n")
        .append(context.getString(R.string.contents_protein, String.format("%.2f", protein))).append("\n")
        .append(context.getString(R.string.contents_fat, String.format("%.2f", fat))).append("\n")
        .append(context.getString(R.string.contents_carbohydrates, String.format("%.2f", carbohydrates))).append("\n")
        .append(context.getString(R.string.contents_saturated_fat, String.format("%.2f", saturatedfat))).append("\n")
        .toString()
}