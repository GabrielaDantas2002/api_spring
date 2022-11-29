package br.com.flowerCatalog.repository;

import br.com.flowerCatalog.model.FlowerModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlowerRepository extends JpaRepository<FlowerModel, Long> {

    public Optional<FlowerModel> findById(long id);

    List<FlowerModel> findByNameContainsIgnoreCaseOrderByName(String name);

    Page<FlowerModel> findAll(Pageable pageable);

    List<FlowerModel> findAll();


}
