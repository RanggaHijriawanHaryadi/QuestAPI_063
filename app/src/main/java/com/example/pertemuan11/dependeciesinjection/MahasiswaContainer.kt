package com.example.pertemuan11.dependeciesinjection

import com.example.pertemuan11.repository.MahasiswaRepository
import com.example.pertemuan11.repository.NetworkMahasiswaRepository
import com.example.pertemuan11.servis.MahasiswaServis
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer{
    val mahasiswaRepository: MahasiswaRepository
}
class MahasiswaContainer: AppContainer {
    private val baseUrl= "http://10.0.2.2:80/umyTI/" //localhost diganti ip klo run dihp
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()

    private val mahasiswaServis: MahasiswaServis by lazy {
        retrofit.create(MahasiswaServis::class.java)
    }
    override val mahasiswaRepository: MahasiswaRepository by lazy {
        NetworkMahasiswaRepository(mahasiswaServis)
    }
}