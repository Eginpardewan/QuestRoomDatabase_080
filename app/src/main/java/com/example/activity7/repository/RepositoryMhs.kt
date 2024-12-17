package com.example.activity7.repository

import com.example.activity7.data.entity.Mahasiswa
import kotlinx.coroutines.flow.Flow

interface RepositoryMhs {
    suspend fun insertMhs(mahasiswa: Mahasiswa)

    fun getAllMhs(): Flow<List<Mahasiswa>>

    fun getMhs(nim: String): Flow<Mahasiswa>

    //delete
    suspend fun deleteMhs(mahasiswa: Mahasiswa)

    //update
    suspend fun updateMhs(mahasiswa: Mahasiswa)
}