import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET

class SecondFragmentViewModel : ViewModel() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://restcountries.com/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(Api::class.java)

    internal val pays = MutableLiveData<List<Country>?>()
    val countries: MutableLiveData<List<Country>?> get() = pays

    init {
        getAllCountries()
    }

    private fun getAllCountries() {
        GlobalScope.launch(Dispatchers.Main) {
            val response = api.getAllCountries()
            response.enqueue(object : Callback<List<Country>> {
                override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                    if (response.isSuccessful) {
                        countries.value = response.body()
                    }
                }

                override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                    countries.value = null
                }
            })
        }
    }

    // interface de qui envoi une liste des pays
    interface Api {
        @GET("all")
        fun getAllCountries(): Call<List<Country>>
    }

    // class des trucs a recuperer dans l'api
    data class Country(
        val name: String,
        val regionalbloc: String,
        val population: Int
    )

}
