package com.it.yousefl.instaclone.data

import com.it.yousefl.libinstaclone.InstaClient
import com.it.yousefl.libinstaclone.models.Gallery
import com.it.yousefl.libinstaclone.models.GalleryResponse
import com.it.yousefl.libinstaclone.models.Image
import com.it.yousefl.libinstaclone.models.Tag
import com.it.yousefl.libinstaclone.params.Section

class InstaRepository {

    val api = InstaClient.api


    suspend fun getHotFeed(): List<GalleryResponse.Data>? {//TODO:return a proper error object if null
        val response = api.getGallery(Section.HOT)
        return response.body()?.data
    }

    suspend fun getTopFeed(): List<GalleryResponse.Data>? {//TODO:return a proper error object if null
        val response = api.getGallery(Section.TOP)
        return response.body()?.data
    }

    suspend fun getTags() :List<Tag>?{
        val response=api.getTags()
        return  response.body()?.data?.tags
    }
    suspend fun getTagGallery(tagName:String):List<Image>?{
        val response=api.getTagGallery(tagName)
        return response.body()?.data?.items
    }
}