package io.namoosori.travelclub.spring;

import io.namoosori.travelclub.spring.aggregate.club.CommunityMember;
import io.namoosori.travelclub.spring.aggregate.club.TravelClub;
import io.namoosori.travelclub.spring.service.ClubService;
import io.namoosori.travelclub.spring.service.MemberService;
import io.namoosori.travelclub.spring.service.sdo.MemberCdo;
import io.namoosori.travelclub.spring.service.sdo.TravelClubCdo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.Date;

public class TravelClubApp {
    public static void main(String[] args) {
        // spring 컨테이너 에게 설정 정보는 같은 contextPath 안에 applicationContext.xml 파일에 있다를 알려줌.
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 등록된 bean 확인
        // 출력 : [clubStore, clubService]
        String [] beanNames = context.getBeanDefinitionNames();
//        System.out.println(Arrays.toString(beanNames));

        MemberService memberService = context.getBean("memberService", MemberService.class);

        String memberId = memberService.registerMember(
                new MemberCdo("test@nextree.io",
                        "Kim",
                        "TestMember",
                        "010-0000-0000",
                        "2020.10.10"));

        CommunityMember foundedMember = memberService.findMemberById(memberId);
        System.out.println(foundedMember.toString());

//        TravelClubCdo clubCdo = new TravelClubCdo("TravelClub", "Test TravelClub");
//        // bean 찾기
//        ClubService clubService = context.getBean("clubService", ClubService.class);
//
//        String clubId = clubService.registerClub(clubCdo);
//
//        TravelClub foundedClub = clubService.findClubById(clubId);
//
//        System.out.println("Club name : " + foundedClub.getName());
//        System.out.println("Club intro : " + foundedClub.getIntro());
//        System.out.println("Club foundationTime : " + new Date(foundedClub.getFoundationTime()));
    }
}
