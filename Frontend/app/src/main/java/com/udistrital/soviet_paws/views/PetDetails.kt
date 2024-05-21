package com.udistrital.soviet_paws.views
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
            modifier = Modifier.padding(30.dp, 10.dp)
        ) {
            Text(
                text = "Pet Name ",
                color = Color.White,
                fontSize = 40.sp,
            )
            Image(painter = painterResource(id = R.drawable.pet_icon),
                contentDescription = "",
                alignment = Alignment.Center,
                modifier = Modifier
                    .padding(20.dp)
                    .height(300.dp)
                    .align(Alignment.CenterHorizontally),)

            Text(text = "Pet age", color = Color.White, fontSize = 30.sp,)
            Text(text = "Pet type", color = Color.White, fontSize = 30.sp,)
            Text(text = "Pet breed", color = Color.White, fontSize = 30.sp,)
            Spacer(modifier = Modifier.size(20.dp))
            Button(
                onClick = {navController.navigateUp()},
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.soviet_yellow)
                ),
                modifier = Modifier.fillMaxWidth()) {
                Text(text = "Back to list", fontSize = 20.sp, color = Color.Black)
            }
        }
    }
}

