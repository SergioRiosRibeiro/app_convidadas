package com.example.convidadas.repository

import android.content.Context

class GuestRepository private constructor(context: Context) {

    private val guestDataBase = GuestDataBase(context)

    //Singleton -> Acesso assíncrono. Controla acessos à instâncias da classe. Importante p.ex. p/ BD's

    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {
            if (!Companion::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }
    }

}