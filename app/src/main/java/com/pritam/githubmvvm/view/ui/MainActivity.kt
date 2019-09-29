package com.pritam.githubmvvm.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pritam.githubmvvm.R
import com.pritam.githubmvvm.extensions.addFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val factFragment = FactListFragment()
        val factFragment = UserSerachListFragment()
        // Added Fragment
        addFragment(factFragment, R.id.fragment_container)
    }
}
