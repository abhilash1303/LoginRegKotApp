package www.rahagloball.loginregkotapp.models
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import www.rahagloball.loginregkotapp.configuration.Configs
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private var retrofit: Retrofit? = null
    private val url = Configs.BASE_URL2
    private var myRetrofitAPI: MyRetrofitAPI? = null

    fun getClient(): MyRetrofitAPI {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(url)
                .client(requestHeader)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            myRetrofitAPI = retrofit!!.create(MyRetrofitAPI::class.java)
        }

        return myRetrofitAPI!!
    }

    private val requestHeader: OkHttpClient
        get() {
            // Initialize the OkHttpClient instance
            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build()

            return okHttpClient
        }
}
