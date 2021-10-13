package com.tellmeajoke.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tellmeajoke.data.db.entities.FavouriteJoke
import com.tellmeajoke.databinding.FragmentFavBinding
import com.tellmeajoke.ui.adapter.JokesAdapter
import com.tellmeajoke.ui.adapter.onClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteFragment : Fragment(), onClickListener {

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

    override fun onShareClick(joke: FavouriteJoke) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, joke.joke)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}