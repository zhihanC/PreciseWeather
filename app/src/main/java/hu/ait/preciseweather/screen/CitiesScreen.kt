package hu.ait.preciseweather.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.window.Dialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitiesScreen(
    modifier: Modifier = Modifier,
    citiesViewModel: CitiesViewModel = hiltViewModel()
) {

    var showAddCityDialog by rememberSaveable {
        mutableStateOf(false)
    }

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
                        showAddCityDialog = true
//                        shoppingItemToEdit = null
                    }
                ) {
                    Icon(Icons.Filled.Add, null)
                }
            }
        )

        Column(
            modifier = modifier.padding(10.dp)
        ) {
            var citiesList = citiesViewModel._citiesList

            if (showAddCityDialog) {
                AddNewCityForm(citiesViewModel,
                    { showAddCityDialog = false }
                )
            }

            if (citiesList.isEmpty())
                Text(text = "No cities")
            else {
                LazyColumn(modifier = Modifier.fillMaxHeight()) {
                    items(citiesList) {
                        CityCard(
                            cityName = it
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CityCard(
    cityName: String
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp),
        modifier = Modifier.padding(5.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(5.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio =
                        Spring.DampingRatioHighBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = cityName,
                    modifier = Modifier.fillMaxWidth(0.5f)
                )
                Spacer(modifier = Modifier.width(80.dp))
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = "Indo",
//                    modifier = Modifier.clickable {
//                        onRemoveItem()
//                    },
                    tint = Color.Blue
                )
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete",
//                    modifier = Modifier.
//                        .clickable {
//                        onRemoveItem()
//                    },
                    tint = Color.Red
                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun AddNewCityForm(
    citiesViewModel: CitiesViewModel,
    onDialogDismiss: () -> Unit = {},
) {
    var context = LocalContext.current

    Dialog(
        onDismissRequest = onDialogDismiss
    ) {
        var cityName by rememberSaveable {
            mutableStateOf("")
        }

        var nameErrorText by rememberSaveable {
            mutableStateOf("")
        }

        var nameInputErrorState by rememberSaveable {
            mutableStateOf(false)
        }
//
//        fun validateTitle(text: String) {
//            val isBlank = text.isBlank()
//            titleErrorText = context.getString(R.string.please_enter_a_title)
//            titleInputErrorState = isBlank
//        }
//
//        var amountInputErrorState by rememberSaveable {
//            mutableStateOf(false)
//        }
//
//        var amountErrorText by rememberSaveable {
//            mutableStateOf("")
//        }
//
//        fun validateAmount(text: String) {
//            val allDigit = text.all{char -> char.isDigit()}
//            amountErrorText = context.getString(R.string.estimated_price_cannot_be_0)
//            if (text == "0") {
//                amountInputErrorState = true
//            }
//        }

        Column(
            Modifier
                .background(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(10.dp)
        ) {

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = cityName,
//                isError = titleInputErrorState,
                onValueChange = {
                    cityName = it
//                    validateTitle(shoppingItemTitle)
                },
                label = { Text(text = "Enter city name:") },
                singleLine = true,
//                supportingText = {
//                    if (titleInputErrorState)
//                        Text(
//                            text = titleErrorText,
//                            color = MaterialTheme.colorScheme.error,
//                            style = MaterialTheme.typography.labelSmall,
//                            modifier = Modifier.padding(start = 16.dp)
//                        )
//                },
//                trailingIcon = {
//                    if (titleInputErrorState) {
//                        Icon(
//                            Icons.Filled.Warning, "error",
//                            tint = MaterialTheme.colorScheme.error
//                        )
//                    }
//                }
            )

            Row {
                Button(
                    onClick = {
                        if (cityName.isEmpty()) {
                            nameErrorText = "City name cannot be empty!"
                            nameInputErrorState = true
                            // check for a valid API call here
//                        } else if (shoppingItemEstimatedPrice.toString() == "0") {
//                            amountErrorText = context.getString(R.string.estimated_price_cannot_be_0)
//                            amountInputErrorState = true
                        } else {
                            citiesViewModel.addCity(cityName)
                            onDialogDismiss()
                        }
                    })
                {
                    Text(text = "Save")
                }
            }
        }
    }
}
