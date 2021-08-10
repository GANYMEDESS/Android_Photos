package com.bae.photoproject.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bae.photoproject.databinding.ItemPhotoLayoutBinding
import com.bae.photoproject.model.entities.Pexels
import com.bae.photoproject.view.fragments.SearchPhotosFragment
import com.bumptech.glide.Glide

class SearchPhotoAdapter(private val fragment: Fragment): RecyclerView.Adapter<SearchPhotoAdapter.ViewHolder>()
{
    private var photos: List<Pexels.Photos> = listOf()

    class ViewHolder(view: ItemPhotoLayoutBinding): RecyclerView.ViewHolder(view.root){
        val ivPhotoImage = view.ivPhotoImage
        val tvTitle = view.tvPhotoTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemPhotoLayoutBinding = ItemPhotoLayoutBinding.inflate(
            LayoutInflater.from(fragment.context), parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = photos[position]
        Glide.with(fragment)
            .load(photo.src.tiny)
            .into(holder.ivPhotoImage)

        holder.tvTitle.text = photo.photographer

        holder.itemView.setOnClickListener {
            if(fragment is SearchPhotosFragment) {
                fragment.photoDetails(photo.src.original)
            }
        }
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun photosList(list: List<Pexels.Photos>) {
        photos = list
        notifyDataSetChanged()
    }
}