package mx.edu.delasalle.mvvmlogin.app.presentation.Computers.detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import mx.edu.delasalle.mvvmlogin.app.repositories.ComputerRepository
import mx.edu.delasalle.mvvmlogin.app.repositories.Result
import mx.edu.delasalle.mvvmlogin.app.states.ComputerListState
import javax.inject.Inject

@HiltViewModel
class ComputerListViewModel
@Inject
constructor(
    private val computerRepository: ComputerRepository
): ViewModel() {
    private val _state: MutableState<ComputerListState> = mutableStateOf(ComputerListState())
    val state: State<ComputerListState> = _state

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing : StateFlow<Boolean> = _isRefreshing

    init {
        getComputerList()
    }

    fun getComputerList() {
        computerRepository.getComputerList().onEach { result ->
            when(result){
                is Result.Error -> {
                    _state.value = ComputerListState(error = result.message ?: "Error inesperado")
                }
                is Result.Loading -> {
                    _state.value = ComputerListState(isLoading = true)
                }
                is Result.Success -> {
                    _state.value = ComputerListState(computer = result.data ?: emptyList())
                }
            }

        }.launchIn(viewModelScope)
    }

    fun deleteComputer(computerId: String){
        computerRepository.deleteComputer(computerId)
    }

}