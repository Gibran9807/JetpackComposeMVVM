package mx.edu.delasalle.mvvmlogin.app.data

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providerFirestoreInstance() = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun providerComputerList(
        firestore: FirebaseFirestore
    ) = firestore.collection("computers")

}