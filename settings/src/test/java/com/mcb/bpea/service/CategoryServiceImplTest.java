package com.mcb.bpea.service;

import com.mcb.bpea.entities.Category;
import com.mcb.bpea.exception.ResourceNotFoundException;
import com.mcb.bpea.repository.CategoryRepository;
import com.mcb.bpea.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl subject;


    @Test
    void getAllCategories() {
        // Arrange
        List<Category> categories = Arrays.asList(
                new Category(1L, "Category1"),
                new Category(2L, "Category2")
        );
        when(categoryRepository.findAll()).thenReturn(categories);

        // Act
        List<Category> result = subject.getAllCategories();

        // Assert
        assertEquals(categories, result);
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void getCategoryById() {
        // Arrange
        Long categoryId = 1L;
        Category category = new Category(categoryId, "Category1");
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        // Act
        Category result = subject.getCategoryById(categoryId);

        // Assert
        assertEquals(category, result);
        verify(categoryRepository, times(1)).findById(categoryId);
    }

    @Test
    void getCategoryById_NotFound() {
        // Arrange
        Long categoryId = 1L;
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> subject.getCategoryById(categoryId));
        verify(categoryRepository, times(1)).findById(categoryId);
    }

    @Test
    void createCategory() {
        // Arrange
        Category categoryToCreate = new Category(null, "NewCategory");
        Category createdCategory = new Category(1L, "NewCategory");
        when(categoryRepository.save(categoryToCreate)).thenReturn(createdCategory);

        // Act
        Category result = subject.createCategory(categoryToCreate);

        // Assert
        assertEquals(createdCategory, result);
        verify(categoryRepository, times(1)).save(categoryToCreate);
    }

    @Test
    void updateCategory() {
        // Arrange
        Long categoryId = 1L;
        Category existingCategory = new Category(categoryId, "ExistingCategory");
        Category updatedCategory = new Category(categoryId, "UpdatedCategory");

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(existingCategory));
        when(categoryRepository.save(updatedCategory)).thenReturn(updatedCategory);

        // Act
        Category result = subject.updateCategory(categoryId, updatedCategory);

        // Assert
        assertEquals(updatedCategory, result);
        verify(categoryRepository, times(1)).findById(categoryId);
        verify(categoryRepository, times(1)).save(updatedCategory);
    }

    @Test
    void updateCategory_NotFound() {
        // Arrange
        Long categoryId = 1L;
        Category updatedCategory = new Category(categoryId, "UpdatedCategory");

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> subject.updateCategory(categoryId, updatedCategory));
        verify(categoryRepository, times(1)).findById(categoryId);
        verify(categoryRepository, never()).save(updatedCategory);
    }

    @Test
    void deleteCategory() {
        // Arrange
        Long categoryId = 1L;

        // Act
        subject.deleteCategory(categoryId);

        // Assert
        verify(categoryRepository, times(1)).deleteById(categoryId);
    }
}
