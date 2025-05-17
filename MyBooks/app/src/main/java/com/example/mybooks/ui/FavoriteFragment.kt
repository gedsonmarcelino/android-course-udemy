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
import com.example.mybooks.databinding.FragmentFavoriteBinding
import com.example.mybooks.ui.adapter.BookAdapter
import com.example.mybooks.ui.listener.BookListener
import com.example.mybooks.utils.AppConstants
import com.example.mybooks.viewmodel.FavoriteViewModel

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoriteViewModel by viewModels()
    private var adapter = BookAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        binding.recyclerviewBooksFavorite.layoutManager = LinearLayoutManager(context)
        binding.recyclerviewBooksFavorite.adapter = adapter

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
            if ( it.isEmpty() ){
                binding.imgBook.visibility = View.VISIBLE
                binding.txtMessage.visibility = View.VISIBLE
                binding.recyclerviewBooksFavorite.visibility = View.GONE
            } else {
                binding.imgBook.visibility = View.GONE
                binding.txtMessage.visibility = View.GONE
                binding.recyclerviewBooksFavorite.visibility = View.VISIBLE
                adapter.updateBooks(it)
            }
        }
    }
}