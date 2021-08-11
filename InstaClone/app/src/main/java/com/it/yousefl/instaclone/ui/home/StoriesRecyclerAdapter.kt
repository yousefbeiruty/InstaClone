package com.it.yousefl.instaclone.ui.home

import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import coil.load

import com.it.yousefl.instaclone.databinding.ListItemStoryHeadBinding
import com.it.yousefl.instaclone.ui.story.StoryActivity
import com.it.yousefl.libinstaclone.models.Tag

class StoriesRecyclerAdapter() :
    ListAdapter<Tag, StoriesRecyclerAdapter.StoriesViewHolder>(StoriesDiffCallBack()) {


    class StoriesViewHolder(val binding: ListItemStoryHeadBinding) :
        RecyclerView.ViewHolder(binding.root)

    private class StoriesDiffCallBack : DiffUtil.ItemCallback<Tag>() {
        override fun areItemsTheSame(oldItem: Tag, newItem: Tag) = (oldItem == newItem)


        override fun areContentsTheSame(oldItem: Tag, newItem: Tag) =
            (oldItem.toString() == newItem.toString())
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoriesViewHolder {
        val inflater: LayoutInflater = parent.context.getSystemService(LayoutInflater::class.java)
        val binding = ListItemStoryHeadBinding.inflate(inflater, parent, false)
        return StoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoriesViewHolder, position: Int) {
        val tag = getItem(position)
        holder.binding.storyHeadTextView.text = tag.displayName
        holder.binding.storyHeadImageView.load("https://i.imgur.com/${tag.backgroundHash}.jpg")

        holder.binding.root.apply {
            setOnClickListener {
                context.startActivity(
                    Intent(context,StoryActivity::class.java).apply {
                        putExtra("tag",tag.name)
                    }
                )
            }
        }

    }


}

