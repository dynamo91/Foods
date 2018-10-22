package com.hugokallstrom.hugodemo

import android.view.View

fun Boolean.asViewVisibility(): Int {
    return if (this) View.VISIBLE else View.GONE
}