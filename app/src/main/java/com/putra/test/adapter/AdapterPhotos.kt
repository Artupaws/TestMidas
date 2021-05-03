package com.putra.test.adapter

import android.content.Context
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.putra.test.databinding.ListItemPhotosBinding
import com.putra.test.model.ResponseGetData


class AdapterPhotos(val context: Context, private val listPhotos: List<ResponseGetData?>?):
    RecyclerView.Adapter<AdapterPhotos.Holder>() {

    inner class Holder(view: View): RecyclerView.ViewHolder(view){
        private val binding = ListItemPhotosBinding.bind(view)
        fun bind(item: ResponseGetData){
            with(binding) {
                tvId.text = item.id.toString()
                tvTitle.text = item.title
                tvUrl.text = item.url
                Glide.with(context).load(item.thumbnailUrl).into(binding.ivPhotos)
                setupHyperLink(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterPhotos.Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemPhotosBinding.inflate(inflater)
        return Holder(binding.root)
    }

    override fun onBindViewHolder(holder: AdapterPhotos.Holder, position: Int) {
        val photo: ResponseGetData = listPhotos?.get(position)!!
        holder.bind(photo)
    }

    override fun getItemCount(): Int = listPhotos?.size!!

    fun setupHyperLink(binding: ListItemPhotosBinding){
        binding.tvUrl.movementMethod = LinkMovementMethod.getInstance();
    }


}