package com.putra.test.adapter

import android.content.Context
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.putra.test.databinding.ListItemPhotosBinding
import com.putra.test.databinding.ListItemUserBinding
import com.putra.test.model.ResponseGetData
import com.putra.test.roomdatauser.DataUser


class AdapterUser(val context: Context, private val listPhotos: List<DataUser?>?):
    RecyclerView.Adapter<AdapterUser.Holder>() {

    inner class Holder(view: View): RecyclerView.ViewHolder(view){
        private val binding = ListItemUserBinding.bind(view)
        fun bind(item: DataUser){
            with(binding) {
                tvId.text = item.id.toString()
                tvEmail.text = item.email
                tvRole.text = item.role
                tvUsername.text = item.username
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterUser.Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemUserBinding.inflate(inflater)
        return Holder(binding.root)
    }

    override fun onBindViewHolder(holder: AdapterUser.Holder, position: Int) {
        val user: DataUser = listPhotos?.get(position)!!
        holder.bind(user)
    }

    override fun getItemCount(): Int = listPhotos?.size!!


}