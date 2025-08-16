package com.example.mvvmproject.ui.theme

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmproject.R
import com.example.mvvmproject.model.Post

class PostsAdapter : ListAdapter<Post, PostsAdapter.PostVH>(DIFF) {

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Post, newItem: Post) = oldItem == newItem
        }
    }

    inner class PostVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.title)
        private val body: TextView = itemView.findViewById(R.id.body)
        fun bind(post: Post) {
            title.text = post.title
            body.text = post.body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostVH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostVH(v)
    }

    override fun onBindViewHolder(holder: PostVH, position: Int) {
        holder.bind(getItem(position))
    }
}