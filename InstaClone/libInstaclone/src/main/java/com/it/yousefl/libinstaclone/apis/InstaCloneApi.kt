package com.it.yousefl.libinstaclone.apis

import com.it.yousefl.libinstaclone.models.GalleryResponse
import com.it.yousefl.libinstaclone.models.TagResponse
import com.it.yousefl.libinstaclone.models.TagsResponse
import com.it.yousefl.libinstaclone.params.Section
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface InstaCloneApi {

    @GET("gallery/{section}")//TODO:use path params
    suspend fun getGallery(
        @Path("section") section: Section,
        @Query("album_previews") albumPreviews: Boolean? = true
    ): Response<GalleryResponse>

    @GET("tags")
    suspend fun getTags(): Response<TagsResponse>

    @GET("gallery/t/{tag}")
    suspend fun getTagGallery(
        @Path("tag")tag:String
    ):Response<TagResponse>

}