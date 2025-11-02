package com.vizu.eventmanager.domain.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="title",length = 100, nullable = false)
    private String title;

    @Column(name="description", length = 1000)
    private String description;

    @Column(name="date_time", nullable = false)
    private Timestamp eventTime;

    @Column(name="location", length = 200)
    private String location;

    @Column(name="deleted", nullable = false)
    private boolean deleted;

    @Column(name="created_at")
    private Timestamp createdAt;

    @Column(name="updated_at")
    private Timestamp updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
        this.updatedAt = Timestamp.valueOf(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = Timestamp.valueOf(LocalDateTime.now());
    }

    public Event() {
    }

    public Event(String title, String description, Timestamp eventTime, String location) {
        this.title = title;
        this.description = description;
        this.eventTime = eventTime;
        this.location = location;
    }

    public Event(String title, String description, Timestamp eventTime, String location, boolean deleted) {
        this.title = title;
        this.description = description;
        this.eventTime = eventTime;
        this.location = location;
        this.deleted = deleted;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getEventTime() {
        return eventTime;
    }

    public String getLocation() {
        return location;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void setEventTime(Timestamp dateTime) {
        this.eventTime = dateTime;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
