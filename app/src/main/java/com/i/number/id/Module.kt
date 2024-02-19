package com.i.number.id

import android.content.Context
import androidx.room.Room
import com.i.db.HistoryDataBase
import com.i.db.HistoryRepositoryDB
import com.i.db.NumberEntity
import com.i.number.model.ModelNumberInfo
import com.i.number.reqest.MyRetrofit
import com.i.number.reqest.requestInfo.RepositoryNumInfo
import com.i.number.reqest.request_random_info.RepositoryRandomInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Module {
    @Provides
    @Singleton
    fun getRetrofit() = MyRetrofit()

    @Provides
    @Singleton
    fun getRepositoryNumInfo(myRetrofit: MyRetrofit) = RepositoryNumInfo(myRetrofit = myRetrofit)

    @Provides
    @Singleton
    fun getRepositoryRandomInfo(myRetrofit: MyRetrofit) =
        RepositoryRandomInfo(myRetrofit = myRetrofit)


    @Provides
    @Singleton
    fun gerHistoryDBRepository(dataBase: HistoryDataBase) = HistoryRepositoryDB(database = dataBase)

}

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    fun getDataBase(@ApplicationContext context: Context): HistoryDataBase {
        return Room.databaseBuilder(
            context = context,
            klass = HistoryDataBase::class.java,
            name = "history_database"
        ).build()
    }


}


