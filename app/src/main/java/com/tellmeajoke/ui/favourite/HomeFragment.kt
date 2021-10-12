package com.tellmeajoke.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tellmeajoke.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textJoke
        val favButton : ImageView = binding.favButton

        homeViewModel.joke.observe(viewLifecycleOwner, Observer {
            println(it)
            textView.text = it.joke
            favButton.isSelected = it.isFav
        })

        homeViewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.INVISIBLE
            }
        }

        val refreshButton : Button = binding.refreshButton

        refreshButton.setOnClickListener {
            homeViewModel.fetchJoke()
        }
        favButton.setOnClickListener {
            homeViewModel.addJoke()
        }

        homeViewModel.fetchJoke()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}