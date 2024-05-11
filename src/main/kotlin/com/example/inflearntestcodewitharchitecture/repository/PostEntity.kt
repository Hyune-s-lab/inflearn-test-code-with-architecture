package com.example.inflearntestcodewitharchitecture.repository

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "posts")
class PostEntity(
    @Column(name = "content")
    var content: String,

    @Column(name = "created_at")
    val createdAt: Long,

    @Column(name = "modified_at")
    var modifiedAt: Long? = null,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val writer: UserEntity,
): BaseEntity()
