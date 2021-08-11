package com.it.yousefl.libinstaclone.apis

import com.it.yousefl.libinstaclone.InstaClient
import com.it.yousefl.libinstaclone.params.Section
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class InstaCloneApiTests {

    val api = InstaClient.api

    @Test
    fun `get tags working`() = runBlocking {
        val response = api.getTags()
        print(response.raw().toString())
        assertNotNull(response.body())
    }

    @Test
    fun `get gallery-hot working`() = runBlocking {
        val response = api.getGallery(Section.HOT)
        print(response.raw().toString())
        assertNotNull(response.body())
    }

    @Test
    fun `get gallery-top working`() = runBlocking {
        val response = api.getGallery(Section.TOP)
        print(response.raw().toString())
        assertNotNull(response.body())
    }

    @Test
    fun `get tag-aww working`()= runBlocking{
        val response = api.getTagGallery("aww")
        print(response.raw().toString())
        assertNotNull(response.body())
    }


}