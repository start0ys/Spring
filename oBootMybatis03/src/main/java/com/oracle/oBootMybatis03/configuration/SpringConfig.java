package com.oracle.oBootMybatis03.configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.oracle.oBootMybatis03.dao.MemberJpaRepository;
import com.oracle.oBootMybatis03.dao.MemberJpaRepositoryImpl;
import com.oracle.oBootMybatis03.domain.Member;
import com.oracle.oBootMybatis03.service.MemberJpaService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SpringConfig {
	private final DataSource dataSource;
	private final EntityManager em;
	
	@Bean
	public MemberJpaService memberJpaService() {
		return new MemberJpaService(memberJpaRepository());
	}
	
	@Bean
	public MemberJpaRepository memberJpaRepository(){
		return new MemberJpaRepositoryImpl(em);
	}
}
