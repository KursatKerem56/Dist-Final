package com.publishment_management.backend.dtos;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoPublishesIU {
	
	@NotNull(message = "Book ID cannot be null")
	@Positive(message = "Book ID must be a positive number")
	private Integer bookId;
	
	@NotNull(message = "Author ID cannot be null")
    @Positive(message = "Author ID must be a positive number")
    private Integer authorId;

    @Min(value = 1, message = "Edition must be at least 1")
    private int edition;
}
