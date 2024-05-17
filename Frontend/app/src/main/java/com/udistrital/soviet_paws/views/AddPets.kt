package com.udistrital.soviet_paws.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.udistrital.soviet_paws.viewModels.AddPetsViewModel

@Composable
fun AddPets(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        val viewModel = AddPetsViewModel()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            PetForm(navController, viewModel)

        }


    }

}
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun AddPetsPreview(){
    val navController = rememberNavController()
    AddPets(navController = navController)
}


@Composable
fun PetForm(navController: NavController, viewModel: AddPetsViewModel) {
    val name: String by viewModel.name.observeAsState(initial = "")
    val type: String by viewModel.type.observeAsState(initial = "Select Pet Type")
    val age: Int by viewModel.age.observeAsState(initial = 0)
    val breed: String by viewModel.breed.observeAsState(initial = "Select Pet Breed")


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        NameInput(
            nameState = name,
            OnNameChange = { viewModel.setName(it) }
        )

        TypeInput(
            typeState = type,
            OnTypeChange = { viewModel.setType(it) }
        )

        AgeInput(
            ageState= age,
            onAgeChange = {viewModel.setAge(it)}
        )

    }
}

@Composable
fun AgeInput(ageState: Int, onAgeChange: (Int) -> Unit, labelId: String = "Age") {
    var ageString by remember { mutableStateOf(ageState.toString()) }
    InputField(
        valuesState = ageString,
        onValueChange = { newAge ->
            ageString = newAge
            newAge.toIntOrNull()?.let { onAgeChange(it) }
        },
        labelId = labelId
    )
}


@Composable
fun NameInput(
    nameState: String, OnNameChange: (
        String
    ) -> Unit, labelId: String = "Name"
) {
    InputField(valuesState = nameState, onValueChange = OnNameChange, labelId = labelId)
}
@Composable
fun TypeInput(typeState: String, OnTypeChange: (String) -> Unit, labelId: String = "Type") {
    var expanded by remember { mutableStateOf(false) }
    var otherPetType by remember { mutableStateOf("") }
    val petTypes = listOf(
        "Dogs",
        "Cats",
        "Rabbits",
        "Poultry",
        "Hamsters",
        "Fishes",
        "Exotic Reptiles",
        "Invertebrates",
        "Horses",
        "Cows",
        "Others"
    )

    Box(modifier = Modifier.fillMaxWidth()) {
        if (typeState == "Others") {
            TextField(
                value = otherPetType,
                onValueChange = { otherPetType = it },
                label = { Text("Enter other pet type") },
                modifier = Modifier.fillMaxWidth()
            )
            OnTypeChange(otherPetType)
        } else {
            Text(
                text = typeState,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { expanded = true })
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            petTypes.forEach { petType ->
                DropdownMenuItem(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    text = {
                        Text(
                            text = petType,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    },
                    onClick = {
                        OnTypeChange(petType)
                        expanded = false
                    }
                )
            }
        }
    }
}


@Composable
fun InputField(
    valuesState: String, onValueChange: (String) -> Unit, labelId: String

) {
    OutlinedTextField(
        value = valuesState,
        onValueChange = onValueChange,
        label = { Text(text = labelId) },
        modifier = Modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth()


    )
}















