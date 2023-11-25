package hu.ait.preciseweather.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitiesScreen() {
    Column() {
        TopAppBar(
            title = { Text(text = "Precise Weather") },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer),
            actions = {
                IconButton(
                    onClick = {
//                        shoppingListViewModel.deleteAllShoppingItems()
                    }
                ) {
                    Icon(Icons.Filled.Delete, null)
                }
                IconButton(
                    onClick = {
//                        showAddShoppingItemDialog = true
//                        shoppingItemToEdit = null
                    }
                ) {
                    Icon(Icons.Filled.Add, null)
                }
            }
        )
    }
}