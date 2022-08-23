package io.namoosori.travelclub.spring.store.mapstore;

import io.namoosori.travelclub.spring.aggregate.club.TravelClub;
import io.namoosori.travelclub.spring.store.ClubStore;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("clubStore")
public class ClubMapStore implements ClubStore {

    // Map으로 데이터 저장
    private Map<String, TravelClub> clubMap;

    public ClubMapStore(){
        this.clubMap = new LinkedHashMap<>();
    }

    // 저장
    @Override
    public String create(TravelClub club) {
        clubMap.put(club.getId(), club);
        return club.getId();
    }

    // 하나의 클럽 찾기
    @Override
    public TravelClub retrieve(String clubId) {
        return clubMap.get(clubId);
    }

    // 동일한 이름의 클럽을 모두 반환
    @Override
    public List<TravelClub> retrieveByName(String name) {
        // for문과 iterator, stream을 사용가능
        return clubMap.values().stream()                            // clubMap의 value들에 대한 컬렉션을 얻어서 다시 스트림을 받아옴
                .filter( club ->club.getName().equals(name))        // filter 메서드를 사용해서 람다식 즉, 클럽의 이름으로 필터링하는 람다식을 제공함.
                .collect(Collectors.toList());                      // 그 결과를 collect해서 List로 변환한 컬랙팅으로 반환한 형태
    }

    // 수정
    @Override
    public void update(TravelClub club) {
        clubMap.put(club.getId(), club);
    }

    // 삭제
    @Override
    public void delete(String clubId) {
        clubMap.remove(clubId);
    }

    // 해당 클럽 아이디를 가지고 있는지 확인하는 메서드
    @Override
    public boolean exists(String clubId) {
        // 동일한 결과
//        return clubMap.containsKey(clubId);
        return Optional.ofNullable(clubMap.get(clubId)).isPresent();
    }
}
