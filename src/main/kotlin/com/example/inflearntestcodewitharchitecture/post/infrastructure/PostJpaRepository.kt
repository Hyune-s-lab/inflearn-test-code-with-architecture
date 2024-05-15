package com.example.inflearntestcodewitharchitecture.post.infrastructure

import org.springframework.data.jpa.repository.JpaRepository

interface PostJpaRepository: JpaRepository<PostEntity, Long>
