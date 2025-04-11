package com.verycool.storeapi.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.verycool.storeapi.data.model.ClothesDetailsItemModel
import com.verycool.storeapi.domain.UiState
import com.verycool.storeapi.viewmodel.ClothesViewModel

private const val TAG = "ClothesListScreen"

@Composable
fun ClothesListScreen(
    modifier: Modifier = Modifier,
    viewModel: ClothesViewModel
){
    LaunchedEffect (Unit){
        viewModel.getClothesList()
    }
    val state = viewModel.clothesState.collectAsState().value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp)
    ) {
        when(state){
            is UiState.ERROR -> {
                Log.e(TAG, "ClothesListScreen ${state.e}")
            }
            is UiState.SUCCESS -> {
                val state = state.response
                LazyColumn(
                    modifier
                        .fillMaxSize()
                        .padding(2.dp)
                ) {
                    items(state) { clothes ->
                        ClothesCard(clothes = clothes, viewModel = viewModel)
                    }
                }
            }
            UiState.LOADING -> {
                Column {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun ClothesCard(
    clothes: ClothesDetailsItemModel,
    viewModel: ClothesViewModel
) {
    var newPrice by remember { mutableStateOf("") }
    val context = LocalContext.current


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row {
            Text("${clothes.id}")
            AsyncImage(
                model = clothes.image,
                contentDescription = clothes.description,
                modifier = Modifier.size(80.dp)
            )
            Column {
                Text("${clothes.rating}")
                Text("${clothes.price}")
                OutlinedTextField(
                    value = newPrice,
                    onValueChange = { input ->
                        if (input.matches(Regex("^\\d*\\.?\\d{0,2}$"))) {
                            newPrice = input
                        } else {
                            Toast.makeText(context, "Enter a valid monetary value", Toast.LENGTH_SHORT).show()
                        }
                    },
                    label = { Text("New Price") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(4.dp))

                Button(
                    onClick = {
                        if (newPrice.isNotBlank()) {
                            viewModel.updatePrice(clothes.id ?: 0, newPrice)
                        } else {
                            Toast.makeText(context, "Please enter a price", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Submit")
                }
            }
        }
    }
}
