package com.example.convidadas.viewmodel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.convidadas.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application)
}
