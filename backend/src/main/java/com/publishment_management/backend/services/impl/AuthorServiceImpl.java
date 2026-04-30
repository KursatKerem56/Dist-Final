package com.publishment_management.backend.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.publishment_management.backend.dtos.DtoAuthor;
import com.publishment_management.backend.dtos.DtoAuthorIU;
import com.publishment_management.backend.exceptions.BaseException;
import com.publishment_management.backend.exceptions.ErrorMessage;
import com.publishment_management.backend.exceptions.MessageType;
import com.publishment_management.backend.mappers.AuthorMapper;
import com.publishment_management.backend.models.Author;
import com.publishment_management.backend.repositories.AuthorRepository;
import com.publishment_management.backend.services.IAuthorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements IAuthorService {
	
	private final AuthorRepository authorRepository;
	private final AuthorMapper authorMapper;

	@Override
	public List<DtoAuthor> getAuthorList() {
		return authorMapper.toDtoList(authorRepository.findAll());
	}

	@Override
	public DtoAuthor getAuthorById(Integer id) {
		return authorMapper.toDto(authorRepository.findById(id)
				.orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, null))));
	}

	@Override
	public DtoAuthor saveAuthor(DtoAuthorIU author) {
		return authorMapper.toDto(authorRepository.save(authorMapper.toEntity(author)));
	}

	@Override
	public void deleteAuthor(Integer id) {
	    Author author = authorRepository.findById(id)
	        .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, null)));

	    authorRepository.delete(author);
	}

	@Override
	public DtoAuthor changeAuthor(Integer id, DtoAuthorIU author) {
	    Author existingAuthor = authorRepository.findById(id)
	        .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, null)));

	    authorMapper.copyAuthorFromDtoIU(author, existingAuthor);

	    Author saved = authorRepository.save(existingAuthor);
	    return authorMapper.toDto(saved);
	}
}

	

	

