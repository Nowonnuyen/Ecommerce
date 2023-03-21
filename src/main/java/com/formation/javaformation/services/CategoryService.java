package com.formation.javaformation.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.formation.javaformation.dto.CategoryDTO;
import com.formation.javaformation.entities.Category;
import com.formation.javaformation.repositories.CategoryRepository;

@Component
public class CategoryService {
	private final CategoryRepository categoryRepository;
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}


	public CategoryDTO create (CategoryDTO categoryDTO) {
		this.checkCategoryData(categoryDTO);
		Category category = mappingCategoryDTOToCategory(categoryDTO);
		Category categoryCreated = this.categoryRepository.save(category);
		categoryDTO.setId(categoryCreated.getId());
		return categoryDTO;
		
		
		
	}

	public List<CategoryDTO> findall() {
		List<Category> categories = categoryRepository.findAll();
		return categories.stream().map(category->{
			return this.mappingCategoryToCategoryDTO(category);
		}).collect(Collectors.toList());


	}
	
	public Category findById(Long id) {
		
		return categoryRepository.findById(id).orElse(null);
		
	}

	private void checkCategoryData(CategoryDTO categoryDTO) {
		if(!StringUtils.hasText(categoryDTO.getLabel()));
	}

	public Category mappingCategoryDTOToCategory(CategoryDTO categoryDTO) {
		Category category = new Category();

		category.setLabel(categoryDTO.getLabel().toLowerCase());
		category.setDescription(categoryDTO.getDescription());
		return category;
	}
	
	public CategoryDTO mappingCategoryToCategoryDTO(Category category) {
		

		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(category.getId());
		categoryDTO.setLabel(category.getLabel());
		categoryDTO.setDescription(category.getDescription());
		return categoryDTO;
	}




}






