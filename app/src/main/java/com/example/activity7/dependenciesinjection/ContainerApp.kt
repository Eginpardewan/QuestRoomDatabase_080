package com.example.activity7.dependenciesinjection

import android.content.Context
import com.example.activity7.data.dao.MahasiswaDao
import com.example.activity7.data.database.KrsDatabase
import com.example.activity7.repository.LocalRepositoryMhs
import com.example.activity7.repository.RepositoryMhs

// Interface untuk Dependency Injection
interface InterfaceContainerApp {
    val repositoryMhs: RepositoryMhs
}

// Implementasi ContainerApp
class ContainerApp(private val context: Context) : InterfaceContainerApp {
    override val repositoryMhs: RepositoryMhs by lazy {
        val MahasiswaDao: MahasiswaDao = KrsDatabase.getDatabase(context).MahasiswaDao()
        LocalRepositoryMhs(MahasiswaDao)
    }
}
