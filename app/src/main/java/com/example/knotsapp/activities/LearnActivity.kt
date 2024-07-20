package com.example.knotsapp.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.knotsapp.R
import com.example.knotsapp.adapters.AdapterRecyclerViewLearnKnots
import com.example.knotsapp.controller.ControllerKnot

class LearnActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_learn)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        confingRecyclerView(findViewById(R.id.recyclerView))

    }

    fun confingRecyclerView(recyclerView:RecyclerView){

        val layoutManager:RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        var adapter:AdapterRecyclerViewLearnKnots = AdapterRecyclerViewLearnKnots( ControllerKnot(this).getAllKnot() )
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

    }

}