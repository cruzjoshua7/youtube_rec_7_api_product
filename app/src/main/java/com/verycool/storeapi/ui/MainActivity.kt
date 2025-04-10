package com.verycool.storeapi.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.verycool.storeapi.ui.screens.ClothesListScreen
import com.verycool.storeapi.ui.theme.StoreApiTheme
import com.verycool.storeapi.viewmodel.ClothesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel : ClothesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StoreApiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ClothesListScreen(
                        modifier = Modifier
                            .padding(innerPadding),
                        viewModel
                    )
                }
            }
        }
    }
}
//@all Store API Interview Question from CVS interview R2:
//Our coding question for you is designed to see how you take on a new project from
//scratch. Your assignment is to use the API provided to make an application that shows
//a list of different types of products in a list view.
//
//Once you a select a product from the
//list make its price editable, you should using the API be able to update the product. This
//
// API was selected because it is straightforward and requires no form of authentication. 
//Api:
//https://fakestoreapi.com/docs
//Happy Coding!

