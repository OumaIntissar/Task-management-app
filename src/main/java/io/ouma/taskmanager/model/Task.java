package io.ouma.taskmanager.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "task")

@ApiModel(description = "Details about task entity.")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name="to_do_date_time", nullable = true)
	private LocalDateTime toDoDateTime;
	
	@ManyToOne 
	@JoinColumn(name="user_id", nullable = false)
	private User user;
	
	@ManyToOne 
	@JoinColumn(name="statut_id")
	private Statut statut;

	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", toDoDateTime=" + toDoDateTime + ", userID=" + user.getId() + ", statutID="
				+ statut.getId() + "]";
	}
	
	
}
