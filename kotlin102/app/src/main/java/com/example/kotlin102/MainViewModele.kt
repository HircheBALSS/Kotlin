import androidx.lifecycle.ViewModel
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


    //
    fun retrieveCountries() {
        val call = api.getAllCountries()
        call.enqueue(object : Callback<List<Country>> {
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                if (response.isSuccessful) {
                    val countries = response.body()
                    if (countries != null) {
                        for (country in countries) {
                            println(country)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                println("Request failed")
            }
        })
    }


    // interface de qui envoi une liste des pays
    interface Api {
        @GET("all")
        fun getAllCountries(): Call<List<Country>>
    }

    // class des trucs a recuperer dans l'api
    data class Country(
        val name: String,
        val capital: String,
        val population: Int
    )

}