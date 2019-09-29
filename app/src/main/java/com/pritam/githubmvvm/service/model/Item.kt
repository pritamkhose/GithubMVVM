package com.pritam.githubmvvm.service.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// Data Class
@Entity
class Item(@PrimaryKey(autoGenerate = true)
           val id: Int,
           var login: String? = null,
           var score: Float? = null,
           var avatar_url: String? = null
)