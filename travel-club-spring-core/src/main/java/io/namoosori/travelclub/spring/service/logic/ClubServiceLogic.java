package io.namoosori.travelclub.spring.service.logic;

import io.namoosori.travelclub.spring.aggregate.club.TravelClub;
import io.namoosori.travelclub.spring.service.ClubService;
import io.namoosori.travelclub.spring.service.sdo.TravelClubCdo;
import io.namoosori.travelclub.spring.shared.NameValueList;
import io.namoosori.travelclub.spring.store.ClubStore;
import io.namoosori.travelclub.spring.store.mapstore.ClubMapStore;
import io.namoosori.travelclub.spring.util.exception.NoSuchClubException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("clubService")
public class ClubServiceLogic implements ClubService {

    private ClubStore clubStore;

    /*
    public ClubServiceLogic(){
        // ClubServiceLogic과 ClubMapStore 의 관계가 타이트해짐 (타이트 커플링)
        // ClubMapStore가 사라지고 새로운 Store로 변경되면 모든 코드를 수정해야 한다.
        // 보완 : Spring의 IoC(제어의 역전) 컨테이너에 의해 new 즉, 객체 생성을 IoC 컨테이너가 수행하고 관계를 정해준다.
        // IoC 객체 제어 방식
        // bean의 개념을 사용해 ClubServiceLogic (bean A)과 ClubMapStore (bean B)를 각각 bean으로 등록하고
        // ClubServiceLogic 에서 ClubMapStore를 참조할 때 IoC컨테이너가 ClubMapStore객체를 자동 생성하여
        // ClubServiceLogic 에서 사용하는 ClubMapStore 객체에 의존관계를 주입한다. (DI : Dependency Injection)
        this.clubStore = new ClubMapStore();
    }
    */

    // resources 폴터 내 applicationContext.xml 에 ClubServiceLogic 과 ClubMapStore를 bean으로 등록했으므로 다음과 같이 생성자를 변경 가능
    // 실제 ClubServiceLogic 클래스가 사용되는 시점 (생성되는 시점)에 생성자를 호출하면서 ClubStore를 new (객체 생성)해서
    // 생성자의 파라미터로 전달함.
    // ClubServiceLogic 의 입장에서 ClubStore 인터페이스가 참조하고 있는 객체가 어떤 ClubStore인지 상관이 없다.
    // (IoC 컨테이너가 설정 파일의 정보를 읽고 알아서 주입 됨.)
    // 생성자를 사용하지 않고 @Autowired 어노테이션을 사용할 수 있다.
   public ClubServiceLogic(ClubStore clubStore){
        this.clubStore = clubStore;
    }

    @Override
    public String registerClub(TravelClubCdo club) {
        TravelClub newClub = new TravelClub(club.getName(), club.getIntro());
        // checkValidation() : club의 이름이 3글자 미만이거나, club 소개가 10글자 미만이면 club 생성을 하지 않게 하는 메서드
        // TravelClub class에 정의됨.
        newClub.checkValidation();
        return clubStore.create(newClub);
    }

    @Override
    public TravelClub findClubById(String id) {
        return clubStore.retrieve(id);
    }

    @Override
    public List<TravelClub> findClubsByName(String name) {
        return clubStore.retrieveByName(name);
    }

    @Override
    public void modify(String clubId, NameValueList nameValues) {
        TravelClub foundedClub = clubStore.retrieve(clubId);
        if(foundedClub == null){
            throw new NoSuchClubException("No such club with id : " + clubId);
        }
        foundedClub.modifyValues(nameValues);
        clubStore.update(foundedClub);
    }

    @Override
    public void remove(String clubId) {
        if(!clubStore.exists(clubId)){
            throw new NoSuchClubException("No such club with id : " + clubId);
        }

        clubStore.delete(clubId);
    }
}
