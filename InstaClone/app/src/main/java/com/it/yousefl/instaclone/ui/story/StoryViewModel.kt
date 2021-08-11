package com.it.yousefl.instaclone.ui.story

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.it.yousefl.instaclone.data.InstaRepository
import com.it.yousefl.libinstaclone.models.Image
import com.it.yousefl.libinstaclone.models.Tag
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StoryViewModel : ViewModel() {
    private val repository = InstaRepository()
    private val _images = MutableLiveData<List<Image>>()
    val images: LiveData<List<Image>> = _images

    fun fetchTagGallery(tagName:String)=viewModelScope.launch(Dispatchers.IO){
        _images.postValue(repository.getTagGallery(tagName))
    }
}