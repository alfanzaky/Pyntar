package com.alfanro.pyntar.data.mapper

import com.alfanro.pyntar.core.database.entity.UserEntity
import com.alfanro.pyntar.domain.model.User

fun UserEntity.toDomain(): User = User(
    id = id,
    username = username,
    email = email,
    avatarUrl = avatarUrl,
    joinedAt = joinedAt
)
