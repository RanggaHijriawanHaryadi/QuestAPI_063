package com.example.pertemuan11.repository

import com.example.pertemuan11.model.Mahasiswa
import com.example.pertemuan11.servis.MahasiswaServis
import retrofit2.Response
import java.io.IOException


interface MahasiswaRepository {
    suspend fun getMahasiswa(): List<Mahasiswa>

    suspend fun insertMahasiswa(mahasiswa: Mahasiswa)

    suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa)

    suspend fun deleteMahasiswa(nim: String)

    suspend fun getMahasiswaById(nim: String):Mahasiswa
}

class NetworkMahasiswaRepository(
    private val mahasiswaApiServis: MahasiswaServis
): MahasiswaRepository{
    override suspend fun getMahasiswa(): List<Mahasiswa> = mahasiswaApiServis.getMahasiswa()

    override suspend fun insertMahasiswa(mahasiswa: Mahasiswa) {
        mahasiswaApiServis.insertMahasiswa(mahasiswa)
    }

    override suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa) {
        mahasiswaApiServis.updateMahasiswa(nim, mahasiswa)
    }

    override suspend fun deleteMahasiswa(nim: String) {
        try {
            val response = mahasiswaApiServis.deleteMahasiswa(nim)
            if (!response.isSuccessful){
                throw IOException("Failled to delete kontak. HTTP Status code: " +
                        "${response.code()}")
            }else{
                response.message()
                println(response.message())
            }
        }catch (e:Exception){
            throw e
        }
    }



    override suspend fun getMahasiswaById(nim: String): Mahasiswa {
        return mahasiswaApiServis.getMahasiswaByNim(nim)
    }
}