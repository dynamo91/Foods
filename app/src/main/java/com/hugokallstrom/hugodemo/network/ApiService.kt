package com.hugokallstrom.hugodemo.network

import com.hugokallstrom.hugodemo.BuildConfig
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryName
import java.net.URL


class ApiService {

    private val api: LifeSumApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(LifeSumApi.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        api = retrofit.create<LifeSumApi>(LifeSumApi::class.java)
    }

    fun searchFood(searchParameter: SearchParameter): Single<ApiSearchResult> {
        with(searchParameter) {
            return api.searchFood(lang, country, searchString)
        }
    }

}

data class SearchParameter(
    val lang: String = "en",
    val country: String = "se",
    val searchString: String
)

interface LifeSumApi {
    @GET("foods/{language}/{country}/{searchString}")
    fun searchFood(@Path("language") lang: String,
                   @Path("country") country: String,
                   @Path("searchString") searchString: String
    ): Single<ApiSearchResult>

    companion object {
        const val URL = "https://api.lifesum.com/icebox/v1/"
    }
}