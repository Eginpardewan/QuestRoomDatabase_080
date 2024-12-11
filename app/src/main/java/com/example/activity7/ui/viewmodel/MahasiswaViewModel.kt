package com.example.activity7.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.activity7.data.entity.Mahasiswa
import com.example.activity7.repository.RepositoryMhs
import kotlinx.coroutines.launch

// ViewModel untuk mengelola data Mahasiswa
class MahasiswaViewModel(private val repositoryMhs: RepositoryMhs) : ViewModel() {

    // State untuk UI
    var uiState = mutableStateOf(MhsUIState()) // Menggunakan mutableStateOf dengan properti value

    // Memperbarui state berdasarkan input pengguna
    fun updateState(mahasiswaEvent: MahasiswaEvent) {
        uiState.value = uiState.value.copy(
            mahasiswaEvent = mahasiswaEvent
        )
    }

    // Validasi data input pengguna
    private fun validateFields(): Boolean {
        val event = uiState.value.mahasiswaEvent
        val errorState = FormErrorState(
            nim = if (event.nim.isNotEmpty()) null else "NIM tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            jeniskelamin = if (event.jeniskelamin.isNotEmpty()) null else "Jenis Kelamin tidak boleh kosong",
            alamat = if (event.alamat.isNotEmpty()) null else "Alamat tidak boleh kosong",
            kelas = if (event.kelas.isNotEmpty()) null else "Kelas tidak boleh kosong",
            angkatan = if (event.angkatan.isNotEmpty()) null else "Angkatan tidak boleh kosong"
        )
        uiState.value = uiState.value.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    // Menyimpan data ke repository
    fun saveData() {
        val currentEvent = uiState.value.mahasiswaEvent
        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryMhs.insertMhs(currentEvent.toMahasiswaEntity())
                    uiState.value = uiState.value.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        mahasiswaEvent = MahasiswaEvent(),
                        isEntryValid = FormErrorState()
                    )
                } catch (e: Exception) {
                    uiState.value = uiState.value.copy(
                        snackBarMessage = "Input tidak valid. Periksa kembali data Anda."
                    )
                }
            }
        }
    }

    // Reset pesan SnackBar
    fun resetSnackBarMessage() {
        uiState.value = uiState.value.copy(snackBarMessage = null)
    }
}

// State untuk UI
data class MhsUIState(
    val mahasiswaEvent: MahasiswaEvent = MahasiswaEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null
)

// State untuk error
data class FormErrorState(
    val nim: String? = null,
    val nama: String? = null,
    val jeniskelamin: String? = null,
    val alamat: String? = null,
    val kelas: String? = null,
    val angkatan: String? = null
) {
    // Validasi apakah semua kolom sudah valid
    fun isValid(): Boolean {
        return nim == null && nama == null && jeniskelamin == null &&
                alamat == null && kelas == null && angkatan == null
    }
}

// Data class untuk event mahasiswa (input form)
data class MahasiswaEvent(
    val nim: String = "",
    val nama: String = "",
    val jeniskelamin: String = "",
    val alamat: String = "",
    val kelas: String = "",
    val angkatan: String = ""
)

// Extension function untuk mengkonversi MahasiswaEvent ke Mahasiswa Entity
fun MahasiswaEvent.toMahasiswaEntity(): Mahasiswa = Mahasiswa(
    nim = nim,
    nama = nama,
    jeniskelamin = jeniskelamin,
    alamat = alamat,
    kelas = kelas,
    angkatan = angkatan
)



