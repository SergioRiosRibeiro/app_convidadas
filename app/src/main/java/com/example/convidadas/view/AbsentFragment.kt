package com.example.convidadas.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.convidadas.constants.DataBaseConstants
import com.example.convidadas.databinding.FragmentAbsentBinding
import com.example.convidadas.view.adapter.GuestsAdapter
import com.example.convidadas.view.listener.OnGuestListener
import com.example.convidadas.viewmodel.GuestsViewModel

class AbsentFragment : Fragment() {
    private var _binding: FragmentAbsentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: GuestsViewModel
    private val adapter = GuestsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        viewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)
        _binding = FragmentAbsentBinding.inflate(inflater, container, false)

        // Layout do recycler
        binding.recyclerGuests.layoutManager = LinearLayoutManager(context)
        // Adapter do recycler
        binding.recyclerGuests.adapter = adapter

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
                viewModel.getAbsent()
            }
        }
        adapter.attachListener(listener)
        observ()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAbsent()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun observ() {
        //Lista de convidados sendo observada e passada para o GuestAdapter
        viewModel.guests.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)
        }
    }
}