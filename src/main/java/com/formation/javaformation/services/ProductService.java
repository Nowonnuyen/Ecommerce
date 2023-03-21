package com.formation.javaformation.services;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.formation.javaformation.dto.CategoryDTO;
import com.formation.javaformation.dto.ProductDTO;
import com.formation.javaformation.entities.Category;
import com.formation.javaformation.entities.Product;
import com.formation.javaformation.exceptions.CategoryNotFoundException;
import com.formation.javaformation.repositories.ProductRepository;




@Service
public class ProductService {
	private final ProductRepository productRepository;
	private final CategoryService categoryService;
	private static Product PRODUCT_NOT_FOUND = null; //?? pk variable static?

	public ProductService(ProductRepository productRepository,CategoryService categoryService) {
		this.productRepository = productRepository;
		this.categoryService =  categoryService;
	}


	public ProductDTO create (ProductDTO productDTO) throws CategoryNotFoundException {
		this.checkCategoryData(productDTO);
		Product product = mappingProductDTOToProduct(productDTO);
		Category category = categoryService.findById(productDTO.getCategoryDTO().getId());
		if (category == null) {
			throw new CategoryNotFoundException("catégorie non trouvé");
		}
		product.setCategory(category); 
		if(productDTO.getId() > 0) {
			
			product.setId(productDTO.getId());
			
		}
		Product productCreated = this.productRepository.save(product);
		
		productDTO.setId(productCreated.getId());



		return productDTO;

	}

	public ProductDTO findById(Long id) {

		return productRepository.findById(id).map(product->{
			ProductDTO productDTO = new ProductDTO(); productDTO.setId(product.getId());
			productDTO.setId(product.getId());
			productDTO.setLabel(product.getLabel());
			productDTO.setDescription(product.getDescription());
			productDTO.setPrice(product.getPrice());
			if(product.getCategory() != null) {
				CategoryDTO categoryDTO = this.categoryService.mappingCategoryToCategoryDTO(product.getCategory());
				productDTO.setCategoryDTO(categoryDTO);
				
				
			}
			
			return productDTO;

		}).orElse(null);

	}
	

	public void delete(Long id) {
		
         //supprime directement l'entité et donc le DTO n'existe plus 
		 productRepository.deleteById(id);
				
						

	}





	public List<ProductDTO> findall() { 
		List<Product> products = productRepository.findAll();
		return products.stream().map(product->{
			ProductDTO productDTO = new ProductDTO(); productDTO.setId(product.getId());
			productDTO.setId(product.getId());
			productDTO.setLabel(product.getLabel());
			productDTO.setDescription(product.getDescription());
			productDTO.setPrice(product.getPrice());
			//productDTO.setCategory(product.getCategory());
			CategoryDTO categoryDTO = Optional.ofNullable(product.getCategory())
					.map(category -> {
						CategoryDTO ctDTO = new CategoryDTO();
						ctDTO.setDescription(category.getDescription());
						ctDTO.setLabel(category.getLabel());
						return ctDTO;
					}).orElse(null);
			productDTO.setCategoryDTO(categoryDTO);
			return productDTO;


		}).collect(Collectors.toList());
	}





	private void checkCategoryData(ProductDTO productDTO) {
		if(!StringUtils.hasText(productDTO.getLabel()));
	}

	private Product mappingProductDTOToProduct(ProductDTO productDTO) {
		Product product = new Product();

		product.setLabel(productDTO.getLabel().toLowerCase());
		product.setDescription(productDTO.getDescription());
		product.setPrice(productDTO.getPrice());

		return product;
	}




}






