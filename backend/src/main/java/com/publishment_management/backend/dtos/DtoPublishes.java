package com.publishment_management.backend.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoPublishes {
	
	private Integer id; 
    
    private Integer authorId;
    private String authorName;
    
    private Integer bookId;
    private String bookTitle;
    
    private LocalDate addedDate;
    private int edition;
}
