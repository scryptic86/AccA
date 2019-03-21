package mattecarra.accapp.utils

import android.app.Application
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import mattecarra.accapp.database.AccaRoomDatabase
import mattecarra.accapp.database.ProfileDao
import mattecarra.accapp.models.ProfileEntity

class DataRepository(application: Application) {

    private val mProfileDao: ProfileDao

    // Live Data
    private val mProfileListLiveData: LiveData<List<ProfileEntity>>

    init {

        val accaDatabase = AccaRoomDatabase.getDatabase(application)
        mProfileDao = accaDatabase.profileDao()
        mProfileListLiveData = mProfileDao.getAllProfiles()
    }

    fun getAllProfiles(): LiveData<List<ProfileEntity>> {
        return mProfileListLiveData
    }

    @WorkerThread
    suspend fun insertProfile(profile: ProfileEntity) {
        mProfileDao.insert(profile)
    }



}