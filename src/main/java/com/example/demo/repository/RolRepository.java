package com.example.demo.repository;

import com.example.demo.entity.Rol;
import com.example.demo.util.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author marco-romero
 */
@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

    Optional<Rol> findByName(RolNombre name);

    boolean existsByName(RolNombre name);

}
