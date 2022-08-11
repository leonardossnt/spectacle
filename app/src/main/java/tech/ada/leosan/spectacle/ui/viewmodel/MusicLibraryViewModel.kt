package tech.ada.leosan.spectacle.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import tech.ada.leosan.spectacle.model.Track
import tech.ada.leosan.spectacle.ui.states.MusicLibraryDataState

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
                        val list = mutableListOf<Track>()
                        for (data in snapshot.children) {
                            val track = data.getValue(Track::class.java)
                            if (track != null) {
                                list.add(track)
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