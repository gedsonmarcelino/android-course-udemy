package com.example.mybooks.ui

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.mybooks.R
import com.example.mybooks.viewmodel.DetailViewModel
import com.example.mybooks.databinding.FragmentDetailBinding
import com.example.mybooks.utils.AppConstants

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by viewModels()
    private var bookId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        setObservers()
        setListeners()
        getArgs()

        return binding.root
    }

    private fun getArgs() {
        bookId = arguments?.getInt(AppConstants.BOOK.ID) ?: 0
        viewModel.getBookById(bookId)
    }

    private fun setObservers() {
        viewModel.book.observe(viewLifecycleOwner) {
            binding.txtTitle.text = it.title
            binding.txtAuthor.text = it.author
            binding.txtGenre.text = it.genre
            binding.chkFavorite.isChecked = it.favorite
        }

        viewModel.bookIsRemoved.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.msg_removed_successfully),
                    Toast.LENGTH_SHORT
                ).show()

                requireActivity().supportFragmentManager.popBackStack()
            }

        }
    }

    private fun setListeners() {
        binding.imgBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.btnRemove.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage(getString(R.string.dialog_message_delete_item))
                .setPositiveButton(getString(R.string.dialog_positive_button_yes)) { dialog, _ ->
                    viewModel.delete(bookId)
                }
                .setNegativeButton(getString(R.string.dialog_negative_button_no)) { dialog, _ ->
                    dialog.dismiss()
                }
            builder.create().show()

        }

        binding.chkFavorite.setOnClickListener{
            viewModel.toggleFavoriteStatus(bookId)
        }
    }
}