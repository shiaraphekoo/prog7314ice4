package com.example.cryptotracker

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var adapter: CoinAdapter
    private lateinit var viewModel: CoinViewModel
    private lateinit var swipe: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.coinsRecycler)
        val progress = findViewById<View>(R.id.progress)
        swipe = findViewById(R.id.swipeRefresh)

        adapter = CoinAdapter(emptyList())
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        // Create viewModel manually with repository
        val repository = CoinRepository(NetworkModule.apiService)
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return CoinViewModel(repository) as T
            }
        }).get(CoinViewModel::class.java)

        swipe.setOnRefreshListener { viewModel.refresh() }

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.uiState.collect { state ->
                when (state) {
                    is CoinUiState.Loading -> {
                        progress.visibility = View.VISIBLE
                        swipe.isRefreshing = false
                    }
                    is CoinUiState.Success -> {
                        progress.visibility = View.GONE
                        swipe.isRefreshing = false
                        adapter.updateItems(state.coins)
                    }
                    is CoinUiState.Error -> {
                        progress.visibility = View.GONE
                        swipe.isRefreshing = false
                        // quick feedback - you can replace with Snackbar/Toast
                        android.widget.Toast.makeText(this@MainActivity, "Error: ${state.message}", android.widget.Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}
