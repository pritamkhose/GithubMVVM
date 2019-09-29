package com.pritam.githubmvvm.service.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// Fact Data Class
@Entity
class Fact(@PrimaryKey(autoGenerate = true)
           val id: Int,
           var title: String? = null,
           var description: String? = null,
           var imageHref: String? = null
)