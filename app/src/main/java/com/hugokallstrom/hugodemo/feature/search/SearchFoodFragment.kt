package com.hugokallstrom.hugodemo.feature.search

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.hugokallstrom.hugodemo.MainActivity
import com.hugokallstrom.hugodemo.R
import com.hugokallstrom.hugodemo.RepoActivity
import com.hugokallstrom.hugodemo.asViewVisibility
import com.hugokallstrom.hugodemo.feature.details.FoodDetailsParameter

class SearchFoodFragment : Fragment(), SearchFoodView,
    SearchResultsAdapter.SearchAdapterClickListener {

    lateinit var presenter: SearchFoodPresenter

    private lateinit var searchView: SearchView
    private lateinit var emptyState: ViewGroup
    private lateinit var searchResultsAdapter: SearchResultsAdapter

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is RepoActivity) {
            val foodRepo = context.getFoodRepo()
            presenter = SearchFoodPresenter(this, foodRepo)
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_search_food, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emptyState = view.findViewById(R.id.empty_state)
        setupSearchList(view)
        setupSearchView(view)
    }

    private fun setupSearchList(view: View) {
        searchResultsAdapter = SearchResultsAdapter(requireContext(), this)
        view.findViewById<RecyclerView>(R.id.search_results).apply {
            setHasFixedSize(true)
            adapter = searchResultsAdapter
        }
    }

    private fun setupSearchView(view: View) {
        searchView = view.findViewById<SearchView>(R.id.search_view).apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(searchString: String?): Boolean {
                    return if (searchString != null) {
                        presenter.search(searchString)
                        true
                    } else {
                        false
                    }
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    return true
                }

            })
        }
    }

    override fun setFoodItems(result: List<SearchResultItem>) {
        emptyState.visibility = result.isEmpty().asViewVisibility()
        searchResultsAdapter.items = result
    }

    override fun onFoodClicked(id: String) = presenter.onFoodClicked(id)

    override fun onAddFavourite(id: String) = presenter.onAddFavourite(id)

    override fun onRemoveFavourite(id: String) = presenter.onRemoveFavourite(id)

    override fun showDetailsFragment(parameter: FoodDetailsParameter) {
        (requireActivity() as MainActivity).showDetailsFragment(parameter)
    }

    override fun showError(error: Error) {
        Toast.makeText(
            requireContext(),
            error.getErrorText(requireContext()),
            Toast.LENGTH_SHORT
        ).show()
    }
}
