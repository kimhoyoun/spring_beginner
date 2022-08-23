package io.namoosori.travelclub.spring.service.sdo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TravelClubCdo implements Serializable {
    // sdo : service domain object,  cdo : creative domain object
    // TravelClub 필드는 name, intro, foundationTime 와 Entity로 부터 상속받은 id 이지만
    // TravelClub 이 생성될 때(완성될 때) foundationTime과 id의 data는 자동으로 저장되기 때문에 실제 필요한 정보는 name과 intro 뿐이다.
    // 즉, TravelClub 객체가 생성될 때 (creative) 필요한 데이터 들을 별도의 domain object로 나눠둔 것이 cdo의 역할이다.
    // 따라서 TravelClubCdo 필드는 name 과 intro 뿐이다.
    private String name;
    private String intro;
}
