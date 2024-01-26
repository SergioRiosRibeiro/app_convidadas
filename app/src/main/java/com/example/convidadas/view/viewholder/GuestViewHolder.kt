package com.example.convidadas.view.viewholder

import android.content.DialogInterface
import androidx.recyclerview.widget.RecyclerView
import com.example.convidadas.databinding.RowGuestBinding
import com.example.convidadas.model.GuestModel
import com.example.convidadas.view.listener.OnGuestListener

class GuestViewHolder(private val bind: RowGuestBinding, private val listener: OnGuestListener) :
    RecyclerView.ViewHolder(bind.root) {

    fun bind(guest: GuestModel) {
        bind.textName.text = guest.name

        bind.textName.setOnClickListener {
            listener.onClick(guest.id)
        }

        bind.textName.setOnLongClickListener {
            androidx.appcompat.app.AlertDialog.Builder(itemView.context)
                .setTitle("Atenção!")
                .setMessage("Tem certeza de que quer remover?")
                .setPositiveButton("Sim") { dialog, which -> listener.onDelete(guest.id) }
                .setNegativeButton("Não", null)
                .create()
                .show()
            true
        }
    }
}