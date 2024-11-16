package shop.uz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import shop.uz.entity.AttachEntity;

import java.util.Optional;

public interface AttachRepository extends CrudRepository<AttachEntity,String> {


    @Query("from AttachEntity where id = ?1 and visible = true")
    Optional<AttachEntity> findByIdAndVisibleTrue(String id);


    @Query("from AttachEntity where visible = true")
    Page<AttachEntity> getAll(Pageable pageable);
}
