package com.example.convidadas.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.convidadas.databinding.FragmentAllGuestsBinding
import com.example.convidadas.viewmodel.AllGuestsViewModel

class AllGuestsFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AllGuestsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this).get(AllGuestsViewModel::class.java)

        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel.getAll()

        observ()

        return root
    }

    private fun observ() {
        viewModel.guests.observe(viewLifecycleOwner) {
            val s = ""
            //Lista de convidados
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}