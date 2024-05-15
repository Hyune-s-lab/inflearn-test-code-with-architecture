package com.example.inflearntestcodewitharchitecture.post.infrastructure

import com.example.inflearntestcodewitharchitecture.user.infrastructure.PostEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PostJpaRepository: JpaRepository<PostEntity, Long>
