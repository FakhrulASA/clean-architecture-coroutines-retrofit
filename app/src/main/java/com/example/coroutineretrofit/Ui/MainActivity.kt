package com.example.coroutineretrofit.Ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutineretrofit.Adapter.PostAdapter
import com.example.coroutineretrofit.Model.PostData
import com.example.coroutineretrofit.R
import com.example.coroutineretrofit.Repository.PostRepo

class MainActivity : AppCompatActivity() {
    private lateinit var postRepo: PostRepo
    private lateinit var postAdapter: PostAdapter
    private lateinit var postViewModel: PostViewModel
    private lateinit var postViewModelFactory: PostViewModelFactory
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        recyclerView = findViewById(R.id.recycleLayout)
        postRepo = PostRepo()
        postViewModelFactory = PostViewModelFactory(postRepo)
        postViewModel = ViewModelProvider(this, postViewModelFactory).get(PostViewModel::class.java)
        postViewModel.getPost()
        postViewModel.myResponse.observe(this, Observer {
            postAdapter.setData(it as ArrayList<PostData>)
        })


    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.recycleLayout)
        postAdapter = PostAdapter(this, ArrayList())
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter=postAdapter

        }
    }
}