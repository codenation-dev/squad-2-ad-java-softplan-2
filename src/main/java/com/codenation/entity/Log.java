package com.codenation.entity;

import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

@Entity
public class Log {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String level;
	private String detail;

	@CreationTimestamp
	private Date createdAt;

	private String origin;

	@Column(length = 350)
	private String token;

	private String generatedBy;
	private String env;
	private Boolean stored;
	private Integer freq;


	public Log(String title, String level, String detail, Date createdAt, String origin, String generatedBy, String token, String env) {
		this.title = title;
		this.level = level;
		this.detail = detail;
		this.createdAt = createdAt;
		this.origin = origin;
		this.generatedBy = generatedBy;
		this.env = env;
		this.token = token;
		this.stored = false;
		this.freq = 0;
	}

	public Log (){}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getFreq() {
		return freq;
	}

	public void setFreq(Integer freq) {
		this.freq = freq;
	}

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	public Boolean getStored() {
		return stored;
	}

	public void setStored(Boolean stored) {
		this.stored = stored;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	/**
	 * @return the detail
	 */
	public String getDetail() {
		return detail;
	}
	/**
	 * @param detail the detail to set
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}
	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	/**
	 * @return the origin
	 */
	public String getOrigin() {
		return origin;
	}
	/**
	 * @param origin the origin to set
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	/**
	 * @return the generatedBy
	 */
	public String getGeneratedBy() {
		return generatedBy;
	}
	/**
	 * @param generatedBy the generatedBy to set
	 */
	public void setGeneratedBy(String generatedBy) {
		this.generatedBy = generatedBy;
	}
	@Override
	public int hashCode() {
		return Objects.hash(createdAt, detail, generatedBy, id, level, origin, title);
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
				&& Objects.equals(level, other.level) && Objects.equals(origin, other.origin)
				&& Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Log{" +
						"id=" + id +
						", title='" + title + '\'' +
						", level='" + level + '\'' +
						", detail='" + detail + '\'' +
						", createdAt=" + createdAt +
						", origin='" + origin + '\'' +
						", generatedBy='" + generatedBy + '\'' +
						", env='" + env + '\'' +
						", stored=" + stored +
						", freq=" + freq +
						'}';
	}
}
