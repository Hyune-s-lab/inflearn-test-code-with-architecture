package com.example.inflearntestcodewitharchitecture.user.infrastructure

import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository: JpaRepository<PostEntity, Long>
