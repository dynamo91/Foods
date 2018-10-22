package com.hugokallstrom.hugodemo.network

import com.hugokallstrom.hugodemo.models.Food

data class ApiSearchResult(
    val food: List<Food>
)