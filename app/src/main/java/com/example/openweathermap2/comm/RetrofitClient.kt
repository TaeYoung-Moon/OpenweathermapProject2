package com.example.openweathermap2.comm

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    fun getApiService(): RetrofitApi? { // API Interface 객체 얻는 용
        return apiService
    }

    companion object {
        private var retrofitClient: RetrofitClient? = null
        private var apiService: RetrofitApi? = null
        fun getInstance(): RetrofitClient? { //싱글톤으로 선언된 레트로핏 객체 얻는 용
            if (retrofitClient == null) {
                retrofitClient = RetrofitClient()
            }
            return retrofitClient
        }
    }

    init {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor { chain: Interceptor.Chain? ->
            val request = chain?.request()
            val newRequest: Request?
            newRequest = request?.newBuilder()
                    ?.build()
            chain?.proceed(newRequest)
        }
        val client = builder
                .build()
        val retrofit = Retrofit.Builder()
                .baseUrl(Config.API_URL) // OPEN API 사이트 baseURL
                .addConverterFactory(GsonConverterFactory.create()) // 데이터를 자동으로 컨버팅할 수 있는 GsonFactory 활용
                .client(client)
                .build()
        apiService = retrofit.create(RetrofitApi::class.java) //실제 api Method들이선언된 Interface객체 선언
    }
}