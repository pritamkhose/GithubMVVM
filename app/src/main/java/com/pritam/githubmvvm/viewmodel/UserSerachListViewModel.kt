package com.pritam.githubmvvm.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.pritam.githubmvvm.database.DatabaseCache
import com.pritam.githubmvvm.service.model.UserSerachResponse
import com.pritam.githubmvvm.service.repository.GitRepository

class UserSerachListViewModel(application: Application) : AndroidViewModel(application) {
    /**
     * Expose the LiveData Facts query so the UI can observe it.
     */
    fun getFactListObservable(context: Context, provideCache: DatabaseCache): LiveData<UserSerachResponse> {
        //return factListObservable
        return GitRepository.getInstance().getUserSerachList(context, provideCache)
    }
}