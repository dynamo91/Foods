package com.hugokallstrom.hugodemo.feature.search

import com.hugokallstrom.hugodemo.database.FoodRepository
import com.hugokallstrom.hugodemo.feature.details.FoodDetailsParameter
import com.hugokallstrom.hugodemo.models.Food
import com.hugokallstrom.hugodemo.network.ApiService
import com.hugokallstrom.hugodemo.network.SearchParameter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class SearchFoodPresenter(
    val view: SearchFoodView,
    private val foodRepository: FoodRepository
) {

    private val apiService = ApiService()
    private var searchResult: List<Food>? = null
    private var items: List<SearchResultItem>? = null

    fun onStart() {
        items?.let(view::setFoodItems)
    }

    fun search(searchString: String) {
        val parameter = SearchParameter(searchString = searchString)
        Observable
            .combineLatest(
                apiService.searchFood(parameter).toObservable().map { it.food },
                foodRepository.getAll().toObservable(),
                BiFunction { searchResult: List<Food>, favourites: List<Food> ->
                    SearchResult(searchResult, favourites)
                }
            )
            .map { result: SearchResult ->
                searchResult = result.searchResult
                result.searchResult.map {
                    SearchResultItem(it.id, it.title, result.favourites.contains(it))
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(::handleResult, { view.showError(Error.NETWORK) })
    }

    private fun handleResult(items: List<SearchResultItem>) {
        this.items = items
        view.setFoodItems(items)
    }

    fun onFoodClicked(id: String) {
        searchResult
            ?.firstOrNull { it.id == id }
            ?.let(::FoodDetailsParameter)
            ?.let(view::showDetailsFragment) ?: view.showError(Error.FOOD_NOT_FOUND)
    }

    fun onAddFavourite(id: String) {
        searchResult
            ?.firstOrNull { it.id == id }
            ?.let(foodRepository::persist)
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.subscribe()
    }

    fun onRemoveFavourite(id: String) {
        searchResult
            ?.firstOrNull { it.id == id }
            ?.let(foodRepository::delete)
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.subscribe()
    }

    data class SearchResult(
        val searchResult: List<Food>,
        val favourites: List<Food>
    )
}