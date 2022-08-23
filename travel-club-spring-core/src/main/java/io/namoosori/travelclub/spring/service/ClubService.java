package io.namoosori.travelclub.spring.service;

import io.namoosori.travelclub.spring.aggregate.club.TravelClub;
import io.namoosori.travelclub.spring.service.sdo.TravelClubCdo;
import io.namoosori.travelclub.spring.shared.NameValueList;

import java.util.List;

// 각 Service 인터페이스를 만들어서 결합도를 줄임.
// 구현체를 logic 패키지 내 구현
public interface ClubService {
	//
	String registerClub(TravelClubCdo club);
	TravelClub findClubById(String id);
	List<TravelClub> findClubsByName(String name);
	void modify(String clubId, NameValueList nameValues);
	void remove(String clubId);
}
