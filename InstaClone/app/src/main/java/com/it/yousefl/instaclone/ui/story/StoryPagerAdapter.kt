package com.it.yousefl.instaclone.ui.story


import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.Coil
import coil.load
import coil.request.ImageRequest
import com.it.yousefl.instaclone.databinding.ListItemStoryHeadBinding
import com.it.yousefl.instaclone.databinding.PageItemStoryBinding
import com.it.yousefl.instaclone.ui.home.StoriesRecyclerAdapter
import com.it.yousefl.libinstaclone.models.Image

class StoryPagerAdapter() :
    ListAdapter<Image, StoryPagerAdapter.StoryPageViewHolder>(StoryDiffCallBack()) {

    class StoryPageViewHolder(val binding: PageItemStoryBinding) : RecyclerView.ViewHolder(binding.root)

    class StoryDiffCallBack : DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean = (oldItem == newItem)

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean =
            (oldItem.toString() ==
                    newItem.toString())

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryPageViewHolder {
        val inflater: LayoutInflater = parent.context.getSystemService(LayoutInflater::class.java)
        val binding = PageItemStoryBinding.inflate(inflater, parent, false)
        return StoryPageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryPageViewHolder, position: Int) {
        val image=getItem(position)
//        if (image?.isAlbum==true){
//
//        }

        val imageUrl=image.link

        imageUrl?.let {
            holder.binding.storyImage.load(it)
            holder.binding.tvEmptyImage.text=it

        }
        cacheNextImage(position,holder.binding.storyImage)
    }
    private fun cacheNextImage(position:Int,imageView: ImageView){
     val image=try {
         getItem(position+1)
     }catch (e:Exception){
     null
     }
        val imageUrl=image?.link
        imageUrl?.let {
            val request= ImageRequest.Builder(imageView.context)
                .data(it)
                .build()
            Coil.imageLoader(imageView.context).enqueue(request)
        }
    }
}