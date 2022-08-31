package io.namoosori.travelclub.web.store.jpastore.repository;

import io.namoosori.travelclub.web.store.jpastore.jpo.TravelClubJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// <Entity로 정의한 class, 식별자(PK)로 설정한 값의 타입>
public interface ClubRepository extends JpaRepository<TravelClubJpo, String> {
    List<TravelClubJpo> findAllByName(String name);
}
