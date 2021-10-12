package com.tellmeajoke.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tellmeajoke.data.db.entities.FavouriteJoke
import com.tellmeajoke.databinding.FragmentFavBinding
import com.tellmeajoke.ui.adapter.JokesAdapter
import com.tellmeajoke.ui.adapter.onDeleteClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteFragment : Fragment(), onDeleteClickListener {

    private lateinit var favouriteViewModel: FavouriteViewModel
    private var _binding: FragmentFavBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favouriteViewModel =
            ViewModelProvider(this).get(FavouriteViewModel::class.java)

        _binding = FragmentFavBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView = binding.favJokeRecyclerView

        val jokesAdapter = JokesAdapter(this)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = jokesAdapter
        }

        favouriteViewModel.getAllFavJokes().observe(viewLifecycleOwner, Observer {
            jokesAdapter.submitList(it)
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDeleteClick(joke: FavouriteJoke) {
        favouriteViewModel.deleteJoke(joke)
    }
}