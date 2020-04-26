package com.juaka.osworks.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juaka.osworks.api.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

}
