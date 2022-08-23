package io.namoosori.travelclub.spring.aggregate.club;

import io.namoosori.travelclub.spring.aggregate.Entity;
import io.namoosori.travelclub.spring.shared.NameValue;
import io.namoosori.travelclub.spring.shared.NameValueList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor	// Lombok을 사용해 No argument 생성자 생성 (디폴트 생성자 자동 생성)
public class TravelClub extends Entity { // Entity 클래스를 상속받아 ID 필드값을 갖고있음.
	// 최소 글자 제한을 위한 사용자 정의 상수 정의
	private static final int MINIMUM_NAME_LENGTH =  3;
	private static final int MINIMUM_INTRO_LENGTH =  10;


	private String name;	// 클럽 이름
	private String intro;	// 클럽 소개
	private long foundationTime;	// 클럽 생성 날짜

	public TravelClub(String id) {
		//
		super(id);
	}

	public TravelClub(String name, String intro) {
		//
		super();
		this.name = name;
		this.intro = intro;
		this.foundationTime = System.currentTimeMillis();
	}

	@Override
	public String toString() {
		//
		StringBuilder builder = new StringBuilder();

		builder.append("Travel Club Id:").append(id);
		builder.append(", name:").append(name);
		builder.append(", intro:").append(intro);
		builder.append(", foundation day:").append(foundationTime);

		return builder.toString();
	}

	public void checkValidation() {
		//
		checkNameValidation(name);
		checkIntroValidation(intro);
	}

	private void checkNameValidation(String name) {
		//
		if (name.length() < TravelClub.MINIMUM_NAME_LENGTH) {
			throw new IllegalArgumentException("\t > Name should be longer than " + TravelClub.MINIMUM_NAME_LENGTH);
		}
	}

	private void checkIntroValidation(String intro) {
		//
		if (intro.length() < TravelClub.MINIMUM_INTRO_LENGTH) {
			throw new IllegalArgumentException("\t > Intro should be longer than " + TravelClub.MINIMUM_INTRO_LENGTH);
		}
	}

	public void modifyValues(NameValueList nameValues) {
		//
		for (NameValue nameValue : nameValues.getNameValues()) {
			String value = nameValue.getValue();
			switch (nameValue.getName()) {
				case "name":
					checkNameValidation(value);
					this.name = value;
					break;
				case "intro":
					checkIntroValidation(value);
					this.intro = value;
					break;
			}
		}
	}

	// 샘플 데이터를 만들 수 있는 static 메서드 구현
	public static TravelClub sample() {
		//
		String name = "JavaTravelClub";
		String intro = "Travel club to the Java island.";

		return new TravelClub(name, intro);
	}

	// 샘플 데이터를 확인하기 위한 main 메서드
	public static void main(String[] args) {
		//
		System.out.println(sample().toString());
	}
}
