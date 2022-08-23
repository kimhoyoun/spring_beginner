package io.namoosori.travelclub.spring.aggregate;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

// 추상 클래스, ID를 갖는 추상 클래스
// 인스턴스 object 만들어질 수 없음. 부모클래스의 역할만 가능
// Entity 클래스(CommunityMember, Membership, TravelClub) 모두 고유의 ID를 갖기 때문에 해당 클래스를 상속받아 사용하도록 정의함.

// Lombok 라이브러리의 @Getter, @Setter를 사용하여 getter, setter를 자동으로 생성하도록 함.
@Getter
@Setter
public abstract class Entity {
	//
	protected String id;

	protected Entity() {
		// UUID 클래스의 randomUUID() 메서드를 사용하여 랜덤한 아이디를 생성할 수 있도록 함.
		this.id = UUID.randomUUID().toString();
	}

	protected Entity(String id) {
		//
		this.id = id;
	}
}
