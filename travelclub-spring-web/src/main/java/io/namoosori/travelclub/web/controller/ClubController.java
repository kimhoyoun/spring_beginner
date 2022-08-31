package io.namoosori.travelclub.web.controller;

import io.namoosori.travelclub.web.aggregate.club.TravelClub;
import io.namoosori.travelclub.web.service.ClubService;
import io.namoosori.travelclub.web.service.sdo.TravelClubCdo;
import io.namoosori.travelclub.web.shared.NameValueList;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @Controller 어노테이션은 view page 가 있을때 사용함
// @RestController 어노테이션은 JSON 타입으로 데이터만 전달하기 위해 사용.
@RestController
@RequestMapping("/club")    // ClubController 내 메서드에 http 메서드 경로 앞에 /club을 자동으로 붙일 수 있다.
public class ClubController {
    // web page에서 /test 입력 시 return값을 반환함.
//    @GetMapping("/test")
//    public String test(){
//        return "Hello Spring MVC~~";
//    }
    private ClubService clubService;

    // Spring IoC 컨테이너에 의해 생성자를 통한 주입을 받음.
    public ClubController(ClubService clubService){
        this.clubService = clubService;
    }

    @PostMapping // localhost:8090/club
    // HTTP 프로토콜 중 Post 메서드를 사용하며 Body에 Json 타입으로 데이터를 전달할 것이므로
    // @RequestBody 라는 어노테이션을 사용하여 데이터를 받는다.
    public String register(@RequestBody TravelClubCdo travelClubCdo){
        return clubService.registerClub(travelClubCdo);
    }

    @GetMapping("/all")
    public List<TravelClub> findAll(){
        return clubService.findAll();
    }

    // Get 방식은 Body에 데이터를 전달할 수 없으므로 URL에 데이터를 전달한다.
    // @GetMapping 어노테이션 내 경로 설정할 때 { } 로 묶은 부분의 name 을 설정하고
    // 메서드 내 파라미터에 @PathVariable 어노테이션을 추가하면 { }에 name과 일치하는 파라미터에 값이 자동 주입된다.
    // 직접 지정할 수 있지만 그렇게 하지 않음
    @GetMapping("/{clubId}")
    public TravelClub find(@PathVariable String clubId){
        return clubService.findClubById(clubId);
    }

    // 에러 발생
    // @GetMapping("club/{clubId}") 와 동일한 URL로 인식하여 에러 발생한다.

//    @GetMapping("club/{name}")
//    public List<TravelClub> findByName(@PathVariable String name){
//        return clubService.findClubsByName(name);
//    }

    // 보완
    // {clubId}인지 {name}인지 알 수 없으므로 URL을 변경해서 구분해 줘야 한다.
    // localhost:8090/club?name=JavaClub 처럼 parameter 전달 방식으로 구분함.
    @GetMapping // localhost:8090/club?name=JavaClub
    public List<TravelClub> findByName(@RequestParam String name){
        return clubService.findClubsByName(name);
    }

    // body에 변경될 값 전달
    @PutMapping("/{clubId}")
    public void modify(@PathVariable String clubId, @RequestBody NameValueList nameValueList){
        clubService.modify(clubId, nameValueList);
    }

    @DeleteMapping("/{clubId}")
    public void delete(@PathVariable String clubId){
        clubService.remove(clubId);
    }
}
