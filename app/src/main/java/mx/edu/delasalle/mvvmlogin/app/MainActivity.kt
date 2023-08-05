package mx.edu.delasalle.mvvmlogin.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import mx.edu.delasalle.mvvmlogin.app.presentation.Computers.detail.ComputerDetailViewModel
import mx.edu.delasalle.mvvmlogin.app.presentation.Computers.detail.ComputerListViewModel
import mx.edu.delasalle.mvvmlogin.app.ui.login.LoginScreen
import mx.edu.delasalle.mvvmlogin.app.presentation.Login.LoginViewModel
import mx.edu.delasalle.mvvmlogin.app.routes.Routes.*
import mx.edu.delasalle.mvvmlogin.app.ui.about.AboutScreen
import mx.edu.delasalle.mvvmlogin.app.ui.detail.ComputerDetailScreen
import mx.edu.delasalle.mvvmlogin.app.ui.home.home
import mx.edu.delasalle.mvvmlogin.app.ui.list.ComputerScreen
import mx.edu.delasalle.mvvmlogin.app.ui.theme.MVVMLoginTheme

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMLoginTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val navigationController = rememberNavController()
                    val viewModel : ComputerListViewModel = hiltViewModel()
                    val state = viewModel.state.value
                    val isRefreshing = viewModel.isRefreshing.collectAsState()

                    NavHost(navController = navigationController, startDestination = Home.routes){
                        composable(Home.routes){ home(navigationController) }
                        composable(LoginScreen.routes){ LoginScreen(viewModel = LoginViewModel(), navigationController)}
                        composable(ComputerScreen.routes){ ComputerScreen(
                            state = state,
                            navigationController = navigationController,
                            isRefreshing = isRefreshing.value,
                            refreshData = viewModel::getComputerList,
                            onItemClick = { computerId ->
                                navigationController.navigate(ComputerDetailScreen.routes + "?computerId=$computerId")
                            },
                            deleteComputer = viewModel::deleteComputer
                        )}
                        composable(ComputerDetailScreen.routes + "?computerId={computerId}") {
                            val viewModel: ComputerDetailViewModel = hiltViewModel()
                            val state = viewModel.state.value
                            ComputerDetailScreen(
                                state = state,
                                addNewComputer = viewModel::addNewComputer,
                                updateComputer = viewModel::updateComputer
                            )
                        }

                        composable( AboutScreen.routes){ AboutScreen(
                            navController = navigationController
                        )}
                    }
                }
            }
        }
    }
}

