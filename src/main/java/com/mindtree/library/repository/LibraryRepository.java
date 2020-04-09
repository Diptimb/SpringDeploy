package com.mindtree.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.library.entity.Library;

public interface LibraryRepository extends JpaRepository<Library, Integer>{

}
