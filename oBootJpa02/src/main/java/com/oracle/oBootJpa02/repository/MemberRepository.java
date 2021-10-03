package com.oracle.oBootJpa02.repository;

import java.util.List;
import java.util.Optional;

import com.oracle.oBootJpa02.domain.Member;

public interface MemberRepository {
	Member           save(Member member);
	List<Member>     findAll();
	Optional<Member> findByMember(Long id);
	int              updateByMember(Member member);
	List<Member>     findByNames(String name);
}
