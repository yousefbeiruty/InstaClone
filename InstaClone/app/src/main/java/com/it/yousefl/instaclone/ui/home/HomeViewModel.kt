package com.it.yousefl.instaclone.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.it.yousefl.instaclone.data.InstaRepository
import com.it.yousefl.libinstaclone.models.Tag
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository = InstaRepository()
    private val _tagGalleries = MutableLiveData<List<Tag>>()
    val tagGalleries: LiveData<List<Tag>> = _tagGalleries

    fun fetchTags()=viewModelScope.launch(Dispatchers.IO){
        _tagGalleries.postValue(repository.getTags())
    }
}