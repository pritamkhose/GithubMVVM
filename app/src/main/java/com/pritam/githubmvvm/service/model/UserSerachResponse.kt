package com.pritam.githubmvvm.service.model

data class UserSerachResponse(
    var total_count: Int,
    var incomplete_results: Boolean,
    var items: MutableList<Item>
)