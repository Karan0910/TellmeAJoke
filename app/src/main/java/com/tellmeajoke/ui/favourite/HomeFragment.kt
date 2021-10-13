package com.tellmeajoke.ui.favourite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
    private lateinit var textView: TextView
    private lateinit var favButton: ImageView
    private lateinit var refreshButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initViews()
        observerData()
        homeViewModel.fetchJoke() // Fetch random joke

        onClickListener()

        return root
    }

    private fun onClickListener() {

        refreshButton.setOnClickListener {
            homeViewModel.fetchJoke()
        }
        favButton.setOnClickListener {
            homeViewModel.addJoke()
        }

        binding.shareJokeBt.setOnClickListener {

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, homeViewModel.joke.value?.joke)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }

    private fun observerData() {
        homeViewModel.joke.observe(viewLifecycleOwner, Observer {
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
    }

    private fun initViews() {
        textView = binding.textJoke
        favButton = binding.favButton
        refreshButton = binding.refreshButton
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}