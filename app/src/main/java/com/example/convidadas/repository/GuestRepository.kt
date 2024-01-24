package com.example.convidadas

class GuestRepository private constructor() {

    //Singleton -> Acesso assíncrono. Controla acessos à instâncias da classe. Importante p.ex. p/ BD's

    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(): GuestRepository {
            if (!::repository.isInitialized) {
                repository = GuestRepository()
            }
            return repository
        }
    }

}