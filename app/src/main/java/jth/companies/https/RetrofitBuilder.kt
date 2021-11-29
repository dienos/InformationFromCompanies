package jth.companies.https

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {
    private const val TIME_OUT : Long = 30000

    private var apiInterface: RetrofitEndPoint?= null
    private var deserializerGson: Gson?= null

    @JvmStatic
    val instance: RetrofitEndPoint
        get() {
            val httpClient = OkHttpClient.Builder()
            httpClient.connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
            httpClient.readTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
            httpClient.writeTimeout(TIME_OUT, TimeUnit.MILLISECONDS)

            val authInterceptor = Interceptor {
                    chain: Interceptor.Chain ->
                chain.withConnectTimeout(chain.connectTimeoutMillis(), TimeUnit.MILLISECONDS)
                chain.withReadTimeout(chain.readTimeoutMillis(), TimeUnit.MILLISECONDS)
                chain.withWriteTimeout(chain.writeTimeoutMillis(), TimeUnit.MILLISECONDS)
                chain.proceed(chain.request().newBuilder().build())
            }
            httpClient.addInterceptor(authInterceptor)

            val fileLogger = HttpLoggingInterceptor.Logger { s: String? -> Platform.get().log(
                Platform.INFO, s, null) }
            val logging = HttpLoggingInterceptor(fileLogger)
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.interceptors().add(logging)

            apiInterface?.let {
                return it
            }?:run {
                val gson = GsonBuilder()
                    .serializeNulls()
                    .create()
                val retrofit = Retrofit.Builder()
                    .baseUrl(RestApiUrl.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(httpClient.build())
                    .build()
                apiInterface = retrofit.create(RetrofitEndPoint::class.java)
                return apiInterface!!
            }
        }

    val deserializer: Gson
        get() {
            if (deserializerGson == null) {
                deserializerGson = GsonBuilder().create()
            }

            return deserializerGson!!
        }
}