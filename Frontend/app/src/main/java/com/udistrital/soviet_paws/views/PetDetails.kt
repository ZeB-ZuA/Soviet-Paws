package com.udistrital.soviet_paws.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.udistrital.soviet_paws.Navigation.AppNav
import com.udistrital.soviet_paws.Navigation.AppViews
import com.udistrital.soviet_paws.R

@Composable
fun PetDetails(navController: NavController, petId: String){
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        color = colorResource(R.color.soviet_red)
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.pet_icon),
                contentDescription = "",
                alignment = Alignment.Center,
                modifier = Modifier
                    .padding(30.dp)
                    .height(300.dp)
                    .align(Alignment.CenterHorizontally),)
            Text(
                text = "Pet Details",
                color = Color.White,
                fontSize = 40.sp,
                )
            Text(text = "Pet Details", color = Color.White, fontSize = 30.sp,)
            Text(text = "Pet Details", color = Color.White, fontSize = 30.sp,)
            Button(onClick = { AppViews.listPets.route }) {
                Text(text = "Back to list", fontSize = 30.sp,)
            }
        }
    }
}