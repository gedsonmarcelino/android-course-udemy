package com.example.mybooks.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mybooks.R
import com.example.mybooks.databinding.FragmentHomeBinding
import com.example.mybooks.ui.adapter.BookAdapter
import com.example.mybooks.ui.listener.BookListener
import com.example.mybooks.utils.AppConstants
import com.example.mybooks.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private var adapter = BookAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.recyclerviewBooks.layoutManager = LinearLayoutManager(context)
        binding.recyclerviewBooks.adapter = adapter

        attachListeners()

        viewModel.getAllBooks()
        setObservers()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun attachListeners() {
        adapter.attachListener(object : BookListener {
            override fun onClick(id: Int) {
                val bundle = Bundle()
                bundle.putInt(AppConstants.BOOK.ID, id)
                findNavController().navigate(R.id.navigation_details, bundle)
            }

            override fun toggleFavoriteStatus(id: Int) {
                viewModel.toggleFavoriteStatus(id)
                viewModel.getAllBooks()
            }
        })
    }

    private fun setObservers() {
        viewModel.books.observe(viewLifecycleOwner) {
            adapter.updateBooks(it)
        }
    }
}