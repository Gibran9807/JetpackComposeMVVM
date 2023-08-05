package mx.edu.delasalle.mvvmlogin.app.presentation.Computers.detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import mx.edu.delasalle.mvvmlogin.app.model.Computer
import mx.edu.delasalle.mvvmlogin.app.repositories.ComputerRepository
import mx.edu.delasalle.mvvmlogin.app.repositories.Result
import mx.edu.delasalle.mvvmlogin.app.states.ComputerDetailState
import mx.edu.delasalle.mvvmlogin.app.states.ComputerListState
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ComputerDetailViewModel
@Inject
constructor(

    private val computerRepository: ComputerRepository,
    savedStateHandle: SavedStateHandle

): ViewModel() {

    private val _state : MutableState<ComputerDetailState> = mutableStateOf(ComputerDetailState())

    val state : State<ComputerDetailState>
        get() = _state

    init {
        savedStateHandle.get<String>("computerId")?.let { computerId ->
            getComputer(computerId)
        }
    }

    fun addNewComputer(title: String, description: String){
        val computer = Computer(
            id = UUID.randomUUID().toString(),
            imageURL = "https://www.cnet.com/a/img/resize/3130972a23cde013bb22606995752b938fcd4021/hub/2021/10/12/d90a3854-6df3-4bbc-8289-3856202d6351/tech-computing-hub-promo-3.png?auto=webp&fit=crop&height=630&width=1200",
            title = title,
            description = description,
            rating = 4.3f,
            stock = 37
        )

        computerRepository.addNewComputer(computer)

    }

    private fun getComputer(computerId: String){
        computerRepository.getComputerById(computerId).onEach { result ->
            when(result){
                is Result.Error -> {
                    _state.value = ComputerDetailState(error = result.message ?: "Error inesperado")
                }
                is Result.Loading -> {
                    _state.value = ComputerDetailState(isLoader = true)
                }
                is Result.Success -> {
                    _state.value = ComputerDetailState(computer = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun updateComputer(newTitle: String, newDescription: String){
        if (state.value.computer == null){
            _state.value = ComputerDetailState(error = "Error inesperado")
            return
        }

        val computerEdited = state.value.computer!!.copy(
            title = newTitle,
            description = newDescription
        )

        computerRepository.updateComputer(computerEdited.id, computerEdited)

    }

}