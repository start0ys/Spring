package com.oracle.oBootJpa02.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.oracle.oBootJpa02.domain.Member;
import com.oracle.oBootJpa02.domain.Team;

public class JpaMemberRepository implements MemberRepository {
    private final EntityManager em;
    
    public JpaMemberRepository(EntityManager em) {
    	this.em = em;
    }
    
	@Override
	public Member save(Member member) {
		//팀 저장
		 Team team = new Team();
		 team.setName(member.getTeamname());
		 em.persist(team);
		 //회원 저장
		 member.setTeam(team); //단방향 연관관계 설정, 참조 저장
		 em.persist(member);		
		 return member;	}

	@Override
	public List<Member> findAll() {
		List<Member> memberList = em.createQuery("select m from Member m", Member.class)
                                    .getResultList();

		return memberList;
	}
	// SELECT * FROM Member WHERE id = id;
	// Optional 객체를 사용하면 예상치 못한 NullPointerException 예외를 제공되는 메소드로 간단히 회피.
	// 즉, 복잡한 조건문 없이도 널(null) 값으로 인해 발생하는 예외를 처리
	@Override
	public Optional<Member> findByMember(Long id) {
		// TODO Auto-generated method stub
		Member member = em.find(Member.class, id);
		return Optional.ofNullable(member);
	}

	@Override
	public int updateByMember(Member member) {
		int result = 0;
	    Member member3 = em.find(Member.class, member.getId());
	    //  member3.ifPresent(object);
	    if( member3 != null) {
	    	   // 팀 저장
	    	  Team team = em.find(Team.class, member.getTeamid());
	    	  if (team != null) {
	 	  		 team.setName(member.getTeamname());
	 	  		 em.persist(team);
	    	  }
	  		 //회원 저장
		     member3.setTeam(team); //단방향 연관관계 설정, 참조 저장
		     member3.setName(member.getName()); //단방향 연관관계 설정, 참조 저장
	    //	 member3.setTeam(member.getTeam());
	  		 em.persist(member3);	
	  		 System.out.println("JpaMemberRepository updateByMember member.getName()->"+member.getName());
	  		 result = 1;  
	    } else {
	    	 result = 0;
	  		 System.out.println("JpaMemberRepository updateByMember No Exist..");
	    }
		return result;
	}

	@Override
	public List<Member> findByNames(String name) {
		String pname = name + '%';
		System.out.println("JpaMemberRepository findByNames name->"+name);

		List<Member> memberList = em.createQuery("select m from Member m where name Like :name", Member.class)
				                 .setParameter("name",pname)
				                 .getResultList();
		System.out.println("JpaMemberRepository memberList.size()->"+memberList.size());
		return memberList;
	}	

}
