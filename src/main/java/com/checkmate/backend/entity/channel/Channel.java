package com.checkmate.backend.entity.channel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.checkmate.backend.entity.board.Board;
import com.checkmate.backend.entity.team.Team;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CHANNEL")
public class Channel {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "CHANNEL_SEQ")
	private Long channelSeq;

	@Column(name = "CHANNEL_NAME")
	private String channelName;

	@Column(name = "CREATED_AT")
	@NotNull
	private LocalDateTime createdAt;

	@Column(name = "MODIFIED_AT")
	@NotNull
	private LocalDateTime modifiedAt;

	@OneToMany(mappedBy = "channel", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Board> boards = new ArrayList<>();

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TEAM_ID")
	private Team team;

	public Channel(
		@NotNull String channelName,
		@NotNull Team team,
		@NotNull LocalDateTime createdAt,
		@NotNull LocalDateTime modifiedAt
	) {
		this.channelName = channelName;
		this.team=team;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}

	public void setChannelName(String channelName){
		this.channelName=channelName;
	}
}
