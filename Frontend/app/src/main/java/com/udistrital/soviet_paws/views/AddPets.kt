package com.udistrital.soviet_paws.views

import PetService
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.udistrital.soviet_paws.Navigation.AppNav
import com.udistrital.soviet_paws.Navigation.AppViews
import com.udistrital.soviet_paws.R
import com.udistrital.soviet_paws.models.PetType
import com.udistrital.soviet_paws.viewModels.AddPetsViewModel

@Composable
fun AddPets(navController: NavController) {
    Surface(
        color = colorResource(R.color.soviet_red),
        modifier = Modifier.fillMaxSize()) {
        val context = LocalContext.current
        val petService = PetService(context)
        val viewModel = AddPetsViewModel(petService, context)

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.size(50.dp))
            Text(text = "Add pet",
                fontSize = 30.sp,
                modifier = Modifier.padding(40.dp),
                color = Color.White)
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                ),
                shape = RoundedCornerShape(
                        topEnd = 30.dp,
                        topStart = 30.dp,
                        bottomEnd = 0.dp,
                        bottomStart = 0.dp
                    ),
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterHorizontally),
            ){
                PetForm(navController, viewModel)
            }
        }


    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun AddPetsPreview() {
    val navController = rememberNavController()
    AddPets(navController = navController)
}


@Composable
fun PetForm(navController: NavController, viewModel: AddPetsViewModel) {
    val name: String by viewModel.name.observeAsState(initial = "")
    val type: String by viewModel.type.observeAsState(initial = "Select Pet Type")
    val age: Int by viewModel.age.observeAsState(initial = 0)
    val breed: String by viewModel.breed.observeAsState(initial = "Select Pet Breed")
    val uri: String? by viewModel.imageUri.observeAsState()


    Column(
        modifier= Modifier.padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.size(30.dp))

        NameInput(
            nameState = name,
            OnNameChange = { viewModel.setName(it) }
        )

        TypeInput(
            typeState = type,
            OnTypeChange = { viewModel.setType(it) },
            OnBreedTypeChange = { viewModel.setBreed(it) }
        )

        AgeInput(
            ageState = age,
            onAgeChange = { viewModel.setAge(it) }
        )

        BreedInput(
            breedState = breed,
            OnBreedChange = { viewModel.setBreed(it) },
            viewModel = viewModel
        )

        Spacer(modifier = Modifier.size(10.dp))

        ImageInput(uriState = uri, onUriChange = { viewModel.setImageUri(it) })

        Spacer(modifier = Modifier.size(10.dp))

        SubmitButton(
            textId = "Save",
            onClick = { viewModel.save()
            viewModel.printInfo()}
        )

        Button(
            shape = RectangleShape,
            onClick = {  navController.navigate(AppViews.homeScreen.route) },
             modifier = Modifier
                 .fillMaxWidth()) {
            Text("Cancel")
        }
    }
}

@Composable
fun ImageInput(uriState: String?, onUriChange: (String) -> Unit, labelId: String = "ImageUri") {
    var selectedImageUri by remember { mutableStateOf<Uri?>(uriState?.let { Uri.parse(it) }) }
    val context = LocalContext.current
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
        uri?.let { onUriChange(it.toString()) }
    }

    LaunchedEffect(selectedImageUri) {
        selectedImageUri?.let { uri ->
            bitmap.value = if (Build.VERSION.SDK_INT < 28) {
                MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
            } else {
                val source = ImageDecoder.createSource(context.contentResolver, uri)
                ImageDecoder.decodeBitmap(source)
            }
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = labelId)

        bitmap.value?.let { bmp ->
            Image(
                bitmap = bmp.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .padding(20.dp)
            )
        }

        Button(
            colors = ButtonDefaults
                .buttonColors(
                    containerColor = colorResource(R.color.soviet_red)
                ),
            onClick = { launcher.launch("image/*") }) {
            Text(text = "Select Image")
        }
    }
}



@Composable
fun SubmitButton(textId: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(),
        shape = RectangleShape,
        colors = ButtonDefaults
            .buttonColors(
                containerColor = colorResource(R.color.soviet_red)
            ),
    ) {
        Text(
            text = textId, modifier = Modifier.padding(5.dp)
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
fun TypeInput(
    typeState: String,
    OnTypeChange: (String) -> Unit,
    OnBreedTypeChange: (String) -> Unit,
    labelId: String = "Type"
) {
    var expanded by remember { mutableStateOf(false) }
    val petTypes = PetType.values().map { it.type }

    Column(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)) {
        Text(
            text = typeState,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .clickable(onClick = { expanded = true })
        )
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
                        OnBreedTypeChange(petType)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun BreedInput(
    breedState: String,
    OnBreedChange: (String) -> Unit,
    labelId: String = "Breed",
    viewModel: AddPetsViewModel
) {
    var expanded by remember { mutableStateOf(false) }
    val type = viewModel.breed.value ?: ""
    val breeds = when (type) {
        "Dogs" -> PetType.DOGS.breeds
        "Cats" -> PetType.CATS.breeds
        "Rabbits" -> PetType.RABBITS.breeds
        "Poultry" -> PetType.POULTRY.breeds
        "Hamsters" -> PetType.HAMSTERS.breeds
        "Fishes" -> PetType.FISHES.breeds
        "Exotic Reptiles" -> PetType.EXOTIC_REPTILES.breeds
        "Invertebrates" -> PetType.INVERTEBRATES.breeds
        "Horses" -> PetType.HORSES.breeds
        "Cows" -> PetType.COWS.breeds
        else -> emptyList()
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = breedState,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .clickable(onClick = { expanded = true })
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            breeds.forEach { breed ->
                DropdownMenuItem(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    text = {
                        Text(
                            text = breed,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    },
                    onClick = {
                        OnBreedChange(breed)
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
