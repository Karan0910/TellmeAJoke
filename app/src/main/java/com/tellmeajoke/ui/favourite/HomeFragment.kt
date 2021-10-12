package com.tellmeajoke.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
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

        homeViewModel.joke.observe(viewLifecycleOwner, Observer {
            textView.text = it.joke
        })

        homeViewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                binding.loadingBar.visibility = View.VISIBLE
            } else {
                binding.loadingBar.visibility = View.INVISIBLE
            }
        }

        val refreshButton : ImageButton = binding.refreshButton

        refreshButton.setOnClickListener {
            homeViewModel.fetchJoke()
        }

        val favButton : ImageButton = binding.favButton

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