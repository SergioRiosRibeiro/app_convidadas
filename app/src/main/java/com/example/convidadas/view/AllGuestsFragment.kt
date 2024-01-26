package com.example.convidadas.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.convidadas.constants.DataBaseConstants
import com.example.convidadas.databinding.FragmentAllGuestsBinding
import com.example.convidadas.view.adapter.GuestsAdapter
import com.example.convidadas.view.listener.OnGuestListener
import com.example.convidadas.viewmodel.AllGuestsViewModel

class AllGuestsFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AllGuestsViewModel
    private val adapter = GuestsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        viewModel = ViewModelProvider(this).get(AllGuestsViewModel::class.java)
        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)

        // Layout
        binding.recyclerAllGuests.layoutManager = LinearLayoutManager(context)

        // Adapter
        binding.recyclerAllGuests.adapter = adapter

        val listener = object : OnGuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)

                //Definindo um bundle, e depois sua chave e valor
                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.GUEST.ID, id)
                //Diferenciando do startActivity comum, usando o Bundle para passar mais parâmetros
                intent.putExtras(bundle)
                //Passando a intent com um valor a ser adicionado, com referência no id passado na bundle
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                viewModel.delete(id)
                viewModel.getAll()
            }
        }

        adapter.attachListener(listener)

        viewModel.getAll()

        observ()

        return binding.root
    }

    private fun observ() {
        //Lista de convidados sendo observada e passada para o GuestAdapter
        viewModel.guests.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}