package com.codenation.entity;

import com.codenation.enums.Environment;
import com.codenation.enums.Level;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Log {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Level level;

	@Column(nullable = false, columnDefinition="TEXT")
	private String detail;

	@CreationTimestamp
	private Date createdAt;

	@Column(nullable = false)
	private String origin;

	@Column(length = 350)
	private String token;

	@Column(nullable = false)
	private String generatedBy;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Environment environment;

	private Boolean stored = false;

	private HashMap<Date, String> events;

	public Log(String title, Level level, String detail, Date createdAt, String origin, String token, String generatedBy, Environment environment, Boolean stored) {
		this.title = title;
		this.level = level;
		this.detail = detail;
		this.createdAt = createdAt;
		this.origin = origin;
		this.token = token;
		this.generatedBy = generatedBy;
		this.environment = environment;
		this.stored = stored;
		this.events = new HashMap<>();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Log)) {
			return false;
		}
		Log other = (Log) obj;
		return Objects.equals(detail, other.detail)
						&& Objects.equals(generatedBy, other.generatedBy)
						&& Objects.equals(level, other.level)
						&& Objects.equals(origin, other.origin)
						&& Objects.equals(title, other.title);
	}

	public void addEvent(String email, Date data){
		events.put(data, email);
	}

	public Integer getEventOccurrences(){
		return events.size();
	}

	public int hashCode() {
		return Objects.hash(id, title, level, detail, createdAt, origin, generatedBy, environment, stored, events);
	}

	public boolean isValid(){
		return !StringUtils.isEmpty(this.detail)
						&& !StringUtils.isEmpty(this.title)
						&& !StringUtils.isEmpty(this.level)
						&& !StringUtils.isEmpty(this.environment);
	}
}
