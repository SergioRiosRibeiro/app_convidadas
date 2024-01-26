package com.example.convidadas.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.convidadas.viewmodel.GuestFormViewModel
import com.example.convidadas.R
import com.example.convidadas.constants.DataBaseConstants
import com.example.convidadas.databinding.ActivityGuestFormBinding
import com.example.convidadas.model.GuestModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        binding.buttonSave.setOnClickListener(this)
        binding.radioPresent.isChecked = true

        loadData()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_save) {
            val name = binding.editName.text.toString()
            val presence = binding.radioPresent.isChecked

            //passando 0 como padrão pro id. Depois ver o que acontece
            val model = GuestModel(0, name, presence)
            viewModel.insert(model)
        }
    }

    private fun loadData() {
        val bundle = intent.extras
        //Garantindo que a intent não virá vazia
        if (bundle != null) {
            val guestId = bundle.getInt(DataBaseConstants.GUEST.ID)
        }
    }
}