package com.publishment_management.backend.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Publishes")
@NoArgsConstructor
@AllArgsConstructor
public class Publishes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
	
	@ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
	
	@Column(name = "added_date")
    private LocalDate addedDate = LocalDate.now();
	
	@Column(name = "edition")
	private int edition;
}