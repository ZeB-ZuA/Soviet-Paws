import com.udistrital.soviet_paws.models.Pet
import com.udistrital.soviet_paws.network.RetrofitClient
import com.udistrital.soviet_paws.repository.PetsRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class PetService: PetsRepository {

    override fun save(pet: Pet) {
       GlobalScope.launch {
                val imageFile = File(pet.imageUri?.path ?: "")
                if (imageFile.exists()) {
                    val imagePart = MultipartBody.Part.createFormData(
                        "image",
                        imageFile.name,
                        imageFile.asRequestBody("image/*".toMediaTypeOrNull())
                    )

                    val typePart = RequestBody.create("text/plain".toMediaTypeOrNull(), pet.type)
                    val namePart = RequestBody.create("text/plain".toMediaTypeOrNull(), pet.name)
                    val agePart = RequestBody.create("text/plain".toMediaTypeOrNull(), pet.age.toString())
                    val breedPart = RequestBody.create("text/plain".toMediaTypeOrNull(), pet.breed)

                    try {
                        val response = RetrofitClient.petsApi.saveWithImage(
                            imagePart,
                            typePart,
                            namePart,
                            agePart,
                            breedPart
                        )
                    } catch (e: Exception) {
                    }
                } else {
                    println("Image file not found: ${pet.imageUri?.path}")
                }
            }
    }

    override fun list(): List<Pet> {
        return runBlocking {
            val response = RetrofitClient.petsApi.getAll()
            response
        }
    }

    override fun filterByNameAndSort(name: String?, sortBy: String?): List<Pet> {
        TODO("Not yet implemented")
    }

    override fun pet(): Pet {
        return runBlocking {
            val response = RetrofitClient.petsApi.get()
            response
        }
    }
}


