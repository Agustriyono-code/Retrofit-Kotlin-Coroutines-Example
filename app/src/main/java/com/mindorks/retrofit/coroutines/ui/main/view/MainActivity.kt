package com.mindorks.retrofit.coroutines.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mindorks.retrofit.coroutines.R.*
import com.mindorks.retrofit.coroutines.data.api.ApiHelper
import com.mindorks.retrofit.coroutines.data.api.RetrofitBuilder
import com.mindorks.retrofit.coroutines.data.model.User
import com.mindorks.retrofit.coroutines.ui.base.ViewModelFactory
import com.mindorks.retrofit.coroutines.ui.main.adapter.MainAdapter
import com.mindorks.retrofit.coroutines.ui.main.viewmodel.MainViewModel
import com.mindorks.retrofit.coroutines.utils.Status.ERROR
import com.mindorks.retrofit.coroutines.utils.Status.LOADING
import com.mindorks.retrofit.coroutines.utils.Status.SUCCESS
import kotlinx.android.synthetic.main.activity_main.progressBar
import kotlinx.android.synthetic.main.activity_main.recyclerView

//TODO 1: Class View Activity main

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    //menghubungkanke layout_activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        setupViewModel()
        setupUI()
        setupObservers()
    }


    //mengsetting viewmodel
    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    //TODO 2:recycleview
    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    //TODO 9:Bagian untuk menampilkan gambar
    //mengakses viewmodel dengam mengambil data dari user
    //dam menampilkan gambar kelayar
    private fun setupObservers() {
        viewModel.getUsers().observe(this, Observer {
            it?.let { resource ->
                //mengakses file resource.kt
               when (resource.status) {
                    SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { users -> retrieveList(users) }
                    }
                    ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    //mengakses file adapter denga menggunkan list
    private fun retrieveList(users: List<User>) {
        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }
    }
}
