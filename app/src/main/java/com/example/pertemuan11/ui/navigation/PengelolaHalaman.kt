package com.example.pertemuan11.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType


import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pertemuan11.ui.view.mahasiswa.DestinasiDetail
import com.example.pertemuan11.ui.view.mahasiswa.DestinasiEntry
import com.example.pertemuan11.ui.view.mahasiswa.DestinasiHome
import com.example.pertemuan11.ui.view.mahasiswa.DestinasiUpdate
import com.example.pertemuan11.ui.view.mahasiswa.DetailView
import com.example.pertemuan11.ui.view.mahasiswa.EntryMhsScreen
import com.example.pertemuan11.ui.view.mahasiswa.HomeScreen
import com.example.pertemuan11.ui.view.mahasiswa.UpdateView

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()){
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier,
    ){
        composable(DestinasiHome.route){
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route)},
                onDetailClick = { nim->
                    navController.navigate("${DestinasiDetail.route}/$nim")
                }
            )
        }
        composable(DestinasiEntry.route){
            EntryMhsScreen(navigateBack = {
                navController.navigate(DestinasiHome.route){
                    popUpTo(DestinasiHome.route){
                        inclusive = true
                    }
                }
            })
        }
        composable(
            DestinasiDetail.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetail.NIM){
                    type = NavType.StringType
                }
            )
        ) {
            val nim = it.arguments?.getString(DestinasiDetail.NIM)
            nim?.let {
                DetailView(
                    NavigateBack = {
                        navController.navigate(DestinasiHome.route) {
                            popUpTo(DestinasiHome.route) {
                                inclusive = true
                            }
                        }
                    },
                    onEditClick =  {navController.navigate("${DestinasiUpdate.route}/$nim")},
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(
            "${DestinasiUpdate.route}/{nim}",
            arguments = listOf(
                navArgument("nim") {type = NavType.StringType}
            )
        ){
            backStackEntry ->
            val nim = backStackEntry.arguments?.getString("nim")
            nim?.let { nim ->
                UpdateView(
                    onBack = {
                        navController.navigate(DestinasiHome.route)},
                    onNavigate = {navController.navigate(DestinasiHome.route)},
                )
            }
        }
    }
}