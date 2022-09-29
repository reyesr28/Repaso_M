package com.example.repaso_m

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserViewHolder(view:View):RecyclerView.ViewHolder(view) {

    val id=view.findViewById<TextView>(R.id.txtBDId)
    val userId=view.findViewById<TextView>(R.id.txtBDuserId)
    val title=view.findViewById<TextView>(R.id.txtBDTitle)
    val body=view.findViewById<TextView>(R.id.txtBDBody)

    fun render(posModel:tablePost){
        id.text=posModel.id.toString()
        userId.text=posModel.userid.toString()
        title.text=posModel.title.toString()
        body.text=posModel.body.toString()
    }

}