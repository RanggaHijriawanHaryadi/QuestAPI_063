package com.example.pertemuan11

import android.app.Application
import com.example.pertemuan11.dependeciesinjection.AppContainer
import com.example.pertemuan11.dependeciesinjection.MahasiswaContainer

class MahasiswaApplications:Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = MahasiswaContainer()
    }
}