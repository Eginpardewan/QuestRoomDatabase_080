package com.example.activity7

import android.app.Application
import com.example.activity7.dependenciesinjection.ContainerApp

class KrsApp : Application() {
    lateinit var containerApp: ContainerApp // Fungsi untuk menyimpan

    override fun onCreate() {
        super.onCreate()
        containerApp = ContainerApp(this)// Membuat instance
        // instance adalah object yang dibuat dari class
    }
}