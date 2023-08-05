package mx.edu.delasalle.mvvmlogin.app.repositories

import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import mx.edu.delasalle.mvvmlogin.app.model.Computer
import mx.edu.delasalle.mvvmlogin.app.repositories.Result
import mx.edu.delasalle.mvvmlogin.app.ui.list.components.ComputerList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ComputerRepository

@Inject

constructor(
    private val computerList : CollectionReference
) {

    fun addNewComputer(computer: Computer){

        try {
            computerList.document(computer.id).set(computer)
        }catch (e: Exception){
            e.printStackTrace()
        }

    }

    fun getComputerList(): Flow<Result<List<Computer>>> = flow {

        try {
            emit(Result.Loading<List<Computer>>())

            val computerList = computerList.get().await().map{ document ->
                document.toObject(Computer::class.java)
            }

            emit(Result.Success<List<Computer>>(data = computerList))

        }catch (e: Exception){
            emit(Result.Error<List<Computer>>(message = e.localizedMessage ?: "Error desconocido"))
        }

    }


    fun getComputerById(computerId: String): Flow<Result<Computer>> = flow {
        try {
            emit(Result.Loading<Computer>())

            val computer = computerList
                .whereGreaterThanOrEqualTo("id", computerId)
                .get()
                .await()
                .toObjects(Computer::class.java)
                .first()

            emit(Result.Success<Computer>(data = computer))

        }catch (e: Exception){
            emit(Result.Error<Computer>(message = e.localizedMessage ?: "Error desconocido"))
        }
    }

    fun updateComputer(computerId: String, computer: Computer){
        try {
            val map = mapOf(
                "title" to computer.title,
                "description" to computer.description
            )

            computerList.document(computerId).update(map)

        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun deleteComputer(computerId: String){
        try {
            computerList.document(computerId).delete()
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

}