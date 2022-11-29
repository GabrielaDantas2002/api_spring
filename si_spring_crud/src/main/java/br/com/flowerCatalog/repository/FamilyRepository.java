package br.com.flowerCatalog.repository;

import br.com.flowerCatalog.model.FamilyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FamilyRepository extends JpaRepository<FamilyModel, Long> {

    //..a optional return
    Optional<FamilyModel> findById(long id);

    //..JPA derived query to retrive a list by name
    List<FamilyModel> findByFamilyNameContainsIgnoreCase(String familyName);

}
