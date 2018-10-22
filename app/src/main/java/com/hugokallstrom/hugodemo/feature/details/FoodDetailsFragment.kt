package com.hugokallstrom.hugodemo.feature.details

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hugokallstrom.hugodemo.R
import com.hugokallstrom.hugodemo.models.formatContents

class FoodDetailsFragment : Fragment() {

    private lateinit var titleView: TextView
    private lateinit var categoryView: TextView
    private lateinit var contentsView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_food_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(view) {
            titleView = findViewById(R.id.title)
            categoryView = findViewById(R.id.category)
            contentsView = findViewById(R.id.contents)
        }

        arguments?.getParcelable<FoodDetailsParameter>(ARG_PARAMETER)?.let {
            with(it.selectedFood) {
                titleView.text = title
                categoryView.text = category
                contentsView.text = formatContents(requireContext())
            }
        }
    }

    companion object {

        private const val ARG_PARAMETER = "arg_parameter"

        fun newInstance(parameter: FoodDetailsParameter): FoodDetailsFragment {
            return FoodDetailsFragment().apply {
                val bundle = Bundle()
                bundle.putParcelable(ARG_PARAMETER, parameter)
                arguments = bundle
            }
        }
    }

}