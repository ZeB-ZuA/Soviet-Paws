package com.udistrital.soviet_paws.views

import android.graphics.drawable.PaintDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.udistrital.soviet_paws.R
import com.udistrital.soviet_paws.models.Pet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListPets(navController: NavController){
    val pets: List<Pet> = mutableListOf()
    val searchQuery = "In"
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        color = colorResource(R.color.soviet_red)) {



        if(pets.isEmpty()){
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .padding(5.dp),
                    painter = painterResource(R.drawable.sad_dog_img),
                    contentDescription = "No found" )
                Text(
                    modifier = Modifier.padding(30.dp),
                    text = stringResource(R.string.no_pets_found),
                    fontSize = 30.sp,
                    color = Color.White)
                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(50.dp),
                    colors = ButtonDefaults
                        .buttonColors(
                            containerColor = colorResource(R.color.soviet_yellow)
                        ),) {
                    Text(text = stringResource(R.string.add_pet), color = Color.Black)
                }
            }
        }
        else{
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
        ) {

            items(pets) { pet ->

                val petImage = painterResource(R.drawable.pet_icon)
                SearchScreen(
                    searchQuery = searchQuery,
                    searchResults = pets,
                    onSearchQueryChange = {},
                )
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
                        painter = petImage,
                        contentDescription = stringResource(R.string.pet_photo)
                    )
                    Text(
                        stringResource(R.string.name, pet.name),
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
                        onClick = { /*TODO*/ }) {
                        Text(stringResource(R.string.details), color = Color.Black)
                    }
                }
            }

        }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    searchQuery: String,
    searchResults: List<Pet>,
    onSearchQueryChange: (String) -> Unit
) {
    SearchBar(
        query = searchQuery,
        onQueryChange = onSearchQueryChange,
        onSearch = {},
        placeholder = {
            Text(text = "Search movies")
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = null
            )
        },
        trailingIcon = {},
        content = {},
        active = true,
        onActiveChange = {},
        tonalElevation = 0.dp
    )
}
