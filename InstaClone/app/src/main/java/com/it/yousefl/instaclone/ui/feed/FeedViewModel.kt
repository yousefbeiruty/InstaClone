package com.it.yousefl.instaclone.ui.feed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.it.yousefl.instaclone.data.InstaRepository
import com.it.yousefl.libinstaclone.models.Image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FeedViewModel : ViewModel() {
    private val repository = InstaRepository()
    private val _feed = MutableLiveData<List<Image>>()

    val feed: LiveData<List<Image>> = _feed
    val imageList: ArrayList<Image> = ArrayList()
    fun updateFeed(feedType: String) = viewModelScope.launch(Dispatchers.IO) {
        when (feedType) {
            "hot" -> {
                repository.getHotFeed()?.map {
                    it.images?.let { imageList.addAll(it) }
                }
                _feed.postValue(imageList)
            }
            "top" -> {
                repository.getTopFeed()?.map {
                    it.images?.let { imageList.addAll(it) }
                }
                _feed.postValue(imageList)
            }
            else -> Log.e("FEED", "feed need to be hot or top")
        }
    }
}