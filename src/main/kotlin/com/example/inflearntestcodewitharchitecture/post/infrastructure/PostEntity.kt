package com.example.inflearntestcodewitharchitecture.post.infrastructure

import com.example.inflearntestcodewitharchitecture.common.infrastructure.BaseEntity
import com.example.inflearntestcodewitharchitecture.post.domain.Post
import com.example.inflearntestcodewitharchitecture.user.infrastructure.UserEntity
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

    override val id: Long? = null,
): BaseEntity() {

    constructor(post: Post): this(
        id = post.id,
        content = post.content,
        createdAt = post.createdAt,
        modifiedAt = post.modifiedAt,
        writer = UserEntity(post.writer),
    )

    fun toModel(): Post {
        return Post(
            id = this.id,
            content = this.content,
            createdAt = this.createdAt,
            modifiedAt = this.modifiedAt,
            writer = this.writer.toModel(),
        )
    }
}
