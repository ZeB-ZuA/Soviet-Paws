package com.udistrital.soviet_paws.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.filled.MapsHomeWork
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(navController: NavController){
    val currentScreen = remember { mutableStateOf("home") }
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    text = "Soviet Paws ☭",
                    fontSize = 22.sp,
                    modifier = Modifier.padding(26.dp))
                MyNavigationDrawerItem(
                    "Home", Icons.Filled.MapsHomeWork,
                    "home", currentScreen.value == "home"
                ) {
                    currentScreen.value = "home"
                    scope.launch { drawerState.close() }
                }
                MyNavigationDrawerItem(
                    "Add Soviet Paws ☭", Icons.Filled.Pets,
                    "AddPets", currentScreen.value == "add"
                ) {
                    currentScreen.value = "add"
                    scope.launch { drawerState.close() }
                }
                MyNavigationDrawerItem(
                   "List Soviet Paws ☭", Icons.Filled.ListAlt,
                    contentDescription = "My schedules", currentScreen.value == "list"
                ) {
                    currentScreen.value = "list"
                    scope.launch { drawerState.close() }
                }

            }
        }
    ) {
        // Screen content
        when (currentScreen.value) {
            "home" -> Home(navController)
            "list" -> ListPets(navController)
            "add" -> AddPets(navController)

        }
    }







}

@Composable
fun Home(navController: NavController) {
    Column {
        Text(text = "Home")
    }

}
@Composable
fun MyNavigationDrawerItem(
    label: String,
    icon: ImageVector,
    contentDescription: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    NavigationDrawerItem(
        colors = NavigationDrawerItemDefaults.colors(
            selectedContainerColor = Color.Cyan,
        ),
        modifier = Modifier.padding(6.dp),
        label = { Text(text = label, fontWeight = FontWeight(500)) },
        icon = {
            Icon(
                icon,
                contentDescription = contentDescription
            )
        },
        selected = selected,
        onClick = onClick
    )
}
