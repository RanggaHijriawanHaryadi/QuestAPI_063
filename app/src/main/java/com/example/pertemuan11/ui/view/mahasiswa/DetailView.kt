package com.example.pertemuan11.ui.view.mahasiswa


import com.example.pertemuan11.ui.navigation.DestinasiNavigasi



object DestinasiDetail : DestinasiNavigasi {
    override val route= "detail"
    override val titleRes = "Detail Mahasiswa"
    const val NIM = "nim"
    val routeWithArgs = "$route/{$NIM}"
}

