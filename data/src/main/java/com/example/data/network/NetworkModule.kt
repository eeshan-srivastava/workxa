package com.example.data.network

import android.content.Context
import com.example.data.api.AppApiInterface
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    @Named("unauthorized_client")
    fun provideClubsApiRxDashboardInterface(
        gson: Gson,
        @Named("base_url") baseUrl: String,
        @Named("client_app") okHttpClient: OkHttpClient,
    ): AppApiInterface {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(AppApiInterface::class.java)
    }


    @Provides
    @Singleton
    fun providesGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .serializeNulls()
            .create()
    }

    @Provides
    @Singleton
    @Named("client_app")
    fun providesOkhttpClientNew(
        @ApplicationContext context: Context,
    ): OkHttpClient {

        val requestInterceptor = Interceptor { chain ->
            val url = chain.request()
                .url
                .newBuilder()
                .build()
            val  request = chain.request()
                .newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .url(url)
                .build()
            return@Interceptor chain.proceed(request)
        }

        return OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

    }


}