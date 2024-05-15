package com.example.inflearntestcodewitharchitecture.post.domain

import org.junit.jupiter.api.Test

class PostTest {
    @Test
    fun `PostCreate으로 게시물을 만들 수 있다`() {
        // given
        //        val postCreate: PostCreate = PostCreate.builder()
        //            .writerId(1)
        //            .content("helloworld")
        //            .build()
        //        val writer: User = User.builder()
        //            .id(1L)
        //            .email("kok202@naver.com")
        //            .nickname("kok202")
        //            .address("Seoul")
        //            .status(UserStatus.ACTIVE)
        //            .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab")
        //            .build()
        //
        //        // when
        //        val post: Post = Post.from(writer, postCreate, TestClockHolder(1679530673958L))
        //
        //        // then
        //        AssertionsForClassTypes.assertThat(post.content).isEqualTo("helloworld")
        //        AssertionsForClassTypes.assertThat(post.createdAt).isEqualTo(1679530673958L)
        //        AssertionsForClassTypes.assertThat(post.writer.email).isEqualTo("kok202@naver.com")
        //        AssertionsForClassTypes.assertThat(post.writer.nickname).isEqualTo("kok202")
        //        AssertionsForClassTypes.assertThat(post.writer.address).isEqualTo("Seoul")
        //        AssertionsForClassTypes.assertThat<UserStatus>(post.writer.status).isEqualTo(UserStatus.ACTIVE)
        //        AssertionsForClassTypes.assertThat(post.writer.certificationCode)
        //            .isEqualTo("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab")
    }

    @Test
    fun `PostUpdate로 게시물을 수정할 수 있다`() {
        // given
        //        val postUpdate: PostUpdate = PostUpdate.builder()
        //            .content("foobar")
        //            .build()
        //        val writer: User = User.builder()
        //            .id(1L)
        //            .email("kok202@naver.com")
        //            .nickname("kok202")
        //            .address("Seoul")
        //            .status(UserStatus.ACTIVE)
        //            .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab")
        //            .build()
        //        var post: Post = Post.builder()
        //            .id(1L)
        //            .content("helloworld")
        //            .createdAt(1678530673958L)
        //            .modifiedAt(0L)
        //            .writer(writer)
        //            .build()
        //
        //        // when
        //        post = post.update(postUpdate, TestClockHolder(1679530673958L))
        //
        //        // then
        //        AssertionsForClassTypes.assertThat(post.content).isEqualTo("foobar")
        //        AssertionsForClassTypes.assertThat(post.modifiedAt).isEqualTo(1679530673958L)
    }
}
