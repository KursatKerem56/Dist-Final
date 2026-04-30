package com.publishment_management.backend.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.publishment_management.backend.dtos.DtoBook;
import com.publishment_management.backend.dtos.DtoBookIU;
import com.publishment_management.backend.exceptions.BaseException;
import com.publishment_management.backend.exceptions.ErrorMessage;
import com.publishment_management.backend.exceptions.MessageType;
import com.publishment_management.backend.mappers.BookMapper;
import com.publishment_management.backend.models.Book;
import com.publishment_management.backend.repositories.BookRepository;
import com.publishment_management.backend.services.IBookService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService {
	
	private final BookRepository bookRepository; 
	private final BookMapper bookMapper;

	@Override
	public List<DtoBook> getBookList() {
		return bookMapper.toDtoList(bookRepository.findAll());
	}

	@Override
	public DtoBook getBookById(Integer id) {
		return bookMapper.toDto(bookRepository.findById(id)
				.orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, null))));
	}

	@Override
	public DtoBook saveBook(DtoBookIU book) {
		return bookMapper.toDto(bookRepository.save(bookMapper.toEntity(book)));
	}

	@Override
	public void deleteBook(Integer id) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, null)));
		
		bookRepository.delete(book);
	}

	@Override
	public DtoBook changeBook(Integer id, DtoBookIU book) {
		Book existingBook = bookRepository.findById(id)
				.orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, null)));
		
		bookMapper.copyBookFromDtoIU(book, existingBook);
		
		return bookMapper.toDto(bookRepository.save(existingBook));
	}
	

}
