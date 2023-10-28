package org.sopt.Seminar.domain.post.repository;

import java.util.List;
import org.sopt.Seminar.domain.member.model.Member;
import org.sopt.Seminar.domain.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByMember(Member member);
}
