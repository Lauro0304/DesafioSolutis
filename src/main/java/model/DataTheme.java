package model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@MappedSuperclass
@Data
public abstract class DataTheme {
	@CreationTimestamp
	@Column(name = "CRIACAO", nullable = false, updatable = false)
	private Instant criacao;

	@UpdateTimestamp
	@Column(name = "ALTERACAO", nullable = false)
	private Instant alteracao;
}
