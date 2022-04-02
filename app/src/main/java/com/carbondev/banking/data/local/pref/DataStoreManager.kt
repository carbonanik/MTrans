package com.carbondev.banking.data.local.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.carbondev.banking.data.model.User
import com.carbondev.banking.util.fromJson
import com.carbondev.banking.util.toJson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.*
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DataStoreManager.USER_DATA_PREFERENCES)

@Singleton
class DataStoreManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val dataStore = context.dataStore

    companion object {
        const val USER_DATA_PREFERENCES = "user_data"

        private val TOKEN = stringPreferencesKey(name = "token")
        private val USER = stringPreferencesKey(name = "user")
        private val BALANCE = intPreferencesKey(name = "balance")


    }

    val savedToken: Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences())
            else throw exception
        }.map { preferences ->
            preferences[TOKEN] ?: NO_TOKEN
        }

    val savedUser: Flow<User?> = dataStore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences())
            else throw exception
        }.map { preference ->
            preference[USER]?.fromJson()
        }

    val savedBalance: Flow<Int?> = dataStore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences())
            else throw exception
        }.map { preference ->
            preference[BALANCE]
        }

    suspend fun storeToken(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN] = token
        }
    }

    suspend fun storeUser(user: User) {
        dataStore.edit { preference ->
            preference[USER] = user.toJson() ?: ""
        }
    }

    suspend fun storeBalance(balance: Int){
        dataStore.edit { preference ->
            preference[BALANCE] = balance
        }
    }

    // delete all data from preference data store
    suspend fun clear() {
        dataStore.edit { it.clear() }
    }
}

const val NO_TOKEN = "no-Token"