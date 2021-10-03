package com.oracle.oBootJpa02.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator( name = "team_seq_gen",
					sequenceName = "team_seq_generator", //매핑할 DB 시퀀스 이름
					initialValue = 1,
					allocationSize = 1
                  )
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_seq_gen")
	private Long team_id;
	@Column(name = "teamname")
	private String name;
	
	public Long getTeam_id() {
		return team_id;
	}
	public void setTeam_id(Long team_id) {
		this.team_id = team_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	
	
}
