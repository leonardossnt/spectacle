package tech.ada.leosan.spectacle

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed class MusicLibraryDataState {
    class Success(val data: MutableList<Song>) : MusicLibraryDataState()
    class Failure(val message: String) : MusicLibraryDataState()
    object Loading : MusicLibraryDataState()
    object Empty : MusicLibraryDataState()
}

class MusicLibraryViewModel : ViewModel() {
    private val mutableState = MutableStateFlow<MusicLibraryDataState>(MusicLibraryDataState.Empty)

    val state = mutableState.asStateFlow()

    init {
        fetchDataFromFirebase()
    }

    private fun fetchDataFromFirebase() {
        mutableState.value = MusicLibraryDataState.Loading

        val uid = Firebase.auth.uid

        FirebaseDatabase.getInstance().getReference("$uid/musiclist/")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.childrenCount <= 0) {
                        mutableState.value = MusicLibraryDataState.Empty
                    } else {
                        val list = mutableListOf<Song>()
                        for (data in snapshot.children) {
                            println(data)
                            println(data.value)
                            val song = data.getValue(Song::class.java)
                            if (song != null) {
                                list.add(song)
                            }
                        }
                        mutableState.value = MusicLibraryDataState.Success(list)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    mutableState.value = MusicLibraryDataState.Failure(error.message)
                }
            })
    }
}