import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udistrital.soviet_paws.models.Pet
import com.udistrital.soviet_paws.network.RetrofitClient
import com.udistrital.soviet_paws.repository.PetsRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class PetService(private val context: Context) : PetsRepository {
    private val _pets = MutableLiveData<List<Pet>>()
    val pets: LiveData<List<Pet>> = _pets

    override fun save(pet: Pet) {
        GlobalScope.launch {
            val uri = Uri.parse(pet.image)
            val inputStream = context.contentResolver.openInputStream(uri)
            if (inputStream == null) {
                println("Image file not found: ${pet.image}")
            } else {
                val imagePart = MultipartBody.Part.createFormData(
                    "image",
                    "image.jpg",
                    inputStream.readBytes().toRequestBody("image/*".toMediaTypeOrNull())
                )

                val typePart = RequestBody.create("text/plain".toMediaTypeOrNull(), pet.type)
                val namePart = RequestBody.create("text/plain".toMediaTypeOrNull(), pet.name)
                val agePart =  RequestBody.create("text/plain".toMediaTypeOrNull(), pet.age.toString())
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
                    Log.d("PetService", "Saving Pet Error: ${e.message}")
                }
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
        return runBlocking {
            try {
                val filtredPets = RetrofitClient.petsApi.filterByNameAndSort(name, sortBy)
                Log.d("MascotaServicio", "se filtran")
                filtredPets
            } catch (e: Exception) {
                Log.e("PetService", "Filter pets error: ${e.message}")
                emptyList()
            }
        }


    }

    override fun pet(): Pet {
        return runBlocking {
            val response = RetrofitClient.petsApi.get()
            response
        }
    }
}


