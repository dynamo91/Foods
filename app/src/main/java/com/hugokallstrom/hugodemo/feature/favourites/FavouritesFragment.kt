package com.hugokallstrom.hugodemo.feature.favourites

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.hugokallstrom.hugodemo.R
import com.hugokallstrom.hugodemo.RepoActivity
import com.hugokallstrom.hugodemo.models.Food

class FavouritesFragment : Fragment(), FavouritesView {

    private lateinit var presenter: FavouritesPresenter
    private lateinit var favouritesAdapter: FavouritesAdapter

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is RepoActivity) {
            val foodRepo = context.getFoodRepo()
            presenter = FavouritesPresenter(this, foodRepo)
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.getFavourites()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_favourites, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFavouritesList(view)
    }

    private fun setupFavouritesList(view: View) {
        favouritesAdapter = FavouritesAdapter(requireContext())
        view.findViewById<RecyclerView>(R.id.favourites_list).apply {
            setHasFixedSize(true)
            adapter = favouritesAdapter
        }
    }

    override fun setFavourites(favourites: List<Food>) {
        favouritesAdapter.items = favourites
    }

    override fun showError(error: Error) {
        Toast.makeText(
            requireContext(),
            error.getErrorText(requireContext()),
            Toast.LENGTH_SHORT
        ).show()
    }

}