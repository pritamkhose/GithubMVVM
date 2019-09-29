package com.pritam.githubmvvm.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.pritam.githubmvvm.database.DatabaseCache
import com.pritam.githubmvvm.service.model.FactResponse
import com.pritam.githubmvvm.service.repository.FactRepository

class FactListViewModel(application: Application) : AndroidViewModel(application) {
    /**
     * Expose the LiveData Facts query so the UI can observe it.
     */
    fun getFactListObservable(context: Context, provideCache: DatabaseCache): LiveData<FactResponse> {
        //return factListObservable
        return FactRepository.getInstance().getFactList(context, provideCache)
    }
}