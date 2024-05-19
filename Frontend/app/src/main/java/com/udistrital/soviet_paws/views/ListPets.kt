package com.udistrital.soviet_paws.views

import android.content.res.Resources
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListPets(navController: NavController){
    Surface(
        color = colorResource(R.color.soviet_red)) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(5.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
    ){
        val itemsList = (0..10).toList()
        items(itemsList){ item ->
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
                    painter = painterResource(R.drawable.pet_icon),
                    contentDescription = "pet-photo")
                Text(
                    stringResource(R.string.name, item),
                    fontSize = 30.sp,
                    modifier = Modifier.
                padding(10.dp))
                Text(
                    stringResource(R.string.tipo, item),
                    modifier = Modifier.
                    padding(10.dp))

                Button(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults
                        .buttonColors(
                            containerColor = colorResource(R.color.soviet_yellow)),
                    onClick = { /*TODO*/ }) {
                    Text(stringResource(R.string.details), color = Color.Black)
                }
            }
        }
    }
    }
}

