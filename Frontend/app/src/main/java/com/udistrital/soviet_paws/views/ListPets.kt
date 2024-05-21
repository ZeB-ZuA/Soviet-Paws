package com.udistrital.soviet_paws.views

import android.graphics.drawable.PaintDrawable
import android.net.Uri
import android.provider.CalendarContract
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.udistrital.soviet_paws.Navigation.AppViews
import com.udistrital.soviet_paws.R
import com.udistrital.soviet_paws.models.Pet
import com.udistrital.soviet_paws.viewModels.AddPetsViewModel
import com.udistrital.soviet_paws.viewModels.PetsListViewModel
import java.util.Locale


@Composable
fun ListPets(navController: NavController) {
    val context = LocalContext.current
    val viewModel = PetsListViewModel(context)
    val pets: List<Pet>? = viewModel.petsLiveData.value

    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        color = colorResource(R.color.soviet_red)
    ) {
        if (pets.isNullOrEmpty()) {
            EmptyList()
        } else {
            Pets(pets, navController)
        }
    }
}

@Composable
fun EmptyList() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .padding(5.dp),
            painter = painterResource(R.drawable.sad_dog_img),
            contentDescription = "No found"
        )
        Text(
            modifier = Modifier.padding(30.dp),
            text = stringResource(R.string.no_pets_found),
            fontSize = 30.sp,
            color = Color.White
        )
        Button(
            onClick = { AppViews.addPet.route },
            modifier = Modifier
                .fillMaxWidth()
                .padding(50.dp),
            colors = ButtonDefaults
                .buttonColors(
                    containerColor = colorResource(R.color.soviet_yellow)
                ),
        ) {
            Text(text = stringResource(R.string.add_pet), color = Color.Black)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun Pets(pets: List<Pet>, navController: NavController) {

    val context = LocalContext.current
    val viewModel = PetsListViewModel(context)

    var searchTerm by remember { mutableStateOf("") }
    val items = listOf("Age", "Breed", "Type", "Name")
    var selectedItemIndex by remember { mutableStateOf(0) }
    var expanded by remember { mutableStateOf(false) }
    val filteredPets = pets.filter {
        it.name.contains(searchTerm, ignoreCase = true) || it.type.contains(
            searchTerm,
            ignoreCase = true
        )
    }

    Column {
        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.White,
                focusedBorderColor = Color.White,
                focusedLabelColor = Color.White
                ),
                value = searchTerm,
                onValueChange = { searchTerm = it },
                label = { Text("Search") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(10.dp))

        Box(modifier = Modifier
            .height(70.dp)) {

            Text(
                text = "OrderBy: "+items[selectedItemIndex],
                color= Color.White,
                fontSize = 20.sp,
                modifier = Modifier
                    .height(100.dp)
                    .padding(10.dp, 20.dp)
                    .fillMaxWidth()
                    .clickable { expanded = true }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                items.forEach { item ->
                    DropdownMenuItem(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(),
                        text = {
                            Text(
                                text = item,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        },
                        onClick = {
                            selectedItemIndex = items.indexOf(item)
                            println(items[selectedItemIndex])
                            viewModel.filterPets(searchTerm, items[selectedItemIndex])
                            expanded = false
                        }
                    )
                }

            }

        }

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            items(filteredPets) { pet ->
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier
                        .padding(10.dp)
                        .height(300.dp),
                    shape = RoundedCornerShape(10.dp),
                    onClick = { /*TODO*/ }) {
                    Image(
                        alignment = Alignment.Center,
                        modifier = Modifier
                            .padding(5.dp)
                            .height(100.dp)
                            .align(Alignment.CenterHorizontally),
                        painter = rememberImagePainter(data = pet.image),
                        contentDescription = stringResource(R.string.pet_photo)
                    )
                    Text(
                        pet.name,
                        fontSize = 30.sp,
                        modifier = Modifier.padding(10.dp)
                    )
                    Text(
                        stringResource(R.string.tipo, pet.type),
                        modifier = Modifier.padding(10.dp)
                    )

                    Button(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                        colors = ButtonDefaults
                            .buttonColors(
                                containerColor = colorResource(R.color.soviet_yellow)
                            ),
                        onClick = {
                            println(pet.name + pet.type + pet.age + pet.breed)
                            val action =
                                "${AppViews.petDetails.route}/${pet.name}/${pet.type}/${pet.age}/${pet.breed}/${
                                    Uri.encode(pet.image)
                                }"
                            navController.navigate(action)
                        }) {
                        Text(stringResource(R.string.details), color = Color.Black)
                    }
                }
            }
        }
    }
    }





@OptIn(ExperimentalMaterial3Api::class)



@Composable
@Preview(showSystemUi = true, showBackground = true)
fun PetPreview() {
    val navController = rememberNavController()
    val pets = listOf(
        Pet(
            name = "Max",
            type = "Dog",
            age = 5,
            breed = "Golden Retriever",
            image = "http://localhost:3000/uploads/1716272479925.jpg"
        ),
        Pet(
            name = "Bella",
            type = "Cat",
            age = 3,
            breed = "Siamese",
            image = "http://localhost:3000/uploads/1716272479925.jpg"
        ),
        Pet(
            name = "Charlie",
            type = "Dog",
            age = 2,
            breed = "Labrador Retriever",
            image = "http://localhost:3000/uploads/1716272479925.jpg"
        ),
        Pet(
            name = "Lucy",
            type = "Cat",
            age = 4,
            breed = "Persian",
            image = "http://localhost:3000/uploads/1716272479925.jpg"
        )
    )

    Pets(pets = pets, navController = navController)
}


