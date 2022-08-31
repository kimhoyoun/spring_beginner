package io.namoosori.travelclub.web.store.jpastore.jpo;

import io.namoosori.travelclub.web.aggregate.club.TravelClub;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// jpa entity 객체
// 실제 데이터베이스에 저장할 데이터를 정의한 클래스
@Entity
@Getter
@Setter
@NoArgsConstructor
// table 이 자동 생성되고 그 테이블의 이름을 직접 지정할 수 있다.
@Table(name="TRAVEL_CLUB")
public class TravelClubJpo {

    @Id
    private String id;
    private String name;
    private String intro;
    private long foundationTime;

    // Entity(Domain 객체) -> JPO 객체 변환
    public TravelClubJpo(TravelClub travelClub){
//        this.id = travelClub.getId();
//        this.name = travelClub.getName();
//        this.intro = travelClub.getIntro();
//        this.foundationTime = travelClub.getFoundationTime();

        // travelClub 객체에서 필드를 분석해서 그 내용을 TravelClubJpo 가 가지고 있는 필드와 맵핑하여 간판하게 데이터를 넣을 수 있다.
        BeanUtils.copyProperties(travelClub, this);
    }

    // JPO -> Entity(Domain 객체) 객체 변환
    public TravelClub toDomain(){
        TravelClub travelClub = new TravelClub(this.name, this.intro);
        travelClub.setId(this.id);
        travelClub.setFoundationTime(this.foundationTime);

        return travelClub;
    }
}
