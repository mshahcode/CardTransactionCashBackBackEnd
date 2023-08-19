package az.pashabank.cardzone.dao.repository;

import az.pashabank.cardzone.dao.entity.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CardRepository extends JpaRepository<Card,Long> {

    Page<Card> findAllBy(Pageable pageable);

    @Query(value = "SELECT c from Card c where c.balance BETWEEN :minBalance AND :maxBalance ")
    Page<Card> findAllByFiltered(@Param("minBalance") Double minBalance, @Param("maxBalance") Double maxBalance, Pageable pageable);

}
