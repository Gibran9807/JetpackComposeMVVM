package mx.edu.delasalle.mvvmlogin.app.presentation.Login

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import mx.edu.delasalle.mvvmlogin.app.routes.Routes
import mx.edu.delasalle.mvvmlogin.app.routes.Routes.*

class LoginViewModel : ViewModel(){

    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable : LiveData<Boolean> = _loginEnable

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _loginEnable.value = isValidEmail(email) && isValidPassword(password)
    }

    private fun isValidPassword(password: String): Boolean = password.length > 6

    private fun isValidEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    suspend fun onLoginSelected(navController: NavController){

        FirebaseAuth.getInstance().signInWithEmailAndPassword(
            email.value.toString(),
            password.value.toString()
        ).addOnCompleteListener {

            if (it.isSuccessful){
                Log.d("Tag", "Accedio")
                navController.navigate(ComputerScreen.routes)

            }else  {
                Log.d("Tag", "No accedio")
            }
        }
        _isLoading.value = true
        delay(4000)
        _isLoading.value = false
    }

    suspend fun signUpButton() {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
            email.value.toString(),
            password.value.toString()
        ).addOnCompleteListener {
            if (it.isSuccessful){
                Log.d("Tag", "success")
            }else  {
                Log.d("Tag", "error")
            }
        }
        _isLoading.value = true
        delay(4000)
        _isLoading.value = false
    }

}
