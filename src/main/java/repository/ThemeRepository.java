package repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Theme;
@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {

	Optional<Theme> findById(Long id);
}
