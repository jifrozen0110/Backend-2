package com.checkmate.backend.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.checkmate.backend.entity.team.Team;
import com.checkmate.backend.entity.team.TeamParticipant;

public interface TeamRepository extends JpaRepository<Team, Long> {

	Team findByTeamSeq(Long teamId);

}
