package com.it.yousefl.instaclone.ui.story

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.ViewPropertyAnimator
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import coil.Coil
import coil.request.ImageRequest
import com.it.yousefl.instaclone.R
import com.it.yousefl.instaclone.databinding.ActivityStoryBinding

class StoryActivity : AppCompatActivity() {

    private val storyViewModel by viewModels<StoryViewModel>()
    private lateinit var binding: ActivityStoryBinding
    private val adapter = StoryPagerAdapter()
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tagName = intent.getStringExtra("tag")
        tagName?.let {
            storyViewModel.fetchTagGallery(it)
        }
        binding.storyViewPager.adapter = adapter

        binding.storyViewPager.registerOnPageChangeCallback(pageChangeCallBack)


    }

    private val nextPageRunnable = Runnable {
        binding.storyViewPager.currentItem++
    }

    private val pageChangeCallBack = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            binding.progressView.scaleX = 0F
            binding.progressView.animate().cancel()
            binding.progressView.animate().scaleX(1F).setDuration(5000).setStartDelay(10).start()

            handler.removeCallbacks(nextPageRunnable)
            handler.postDelayed(nextPageRunnable,5000)
        }
    }

    override fun onResume() {
        super.onResume()
        storyViewModel.images.observe(this) {
            it.forEach {
                val request = ImageRequest.Builder(this)
                    .data(it.link)
                    .size(resources.getDimensionPixelSize(R.dimen.story_head_image_size))
                    .build()
                Coil.imageLoader(this).enqueue(request)

            }
            adapter.submitList(it)
        }
    }
}