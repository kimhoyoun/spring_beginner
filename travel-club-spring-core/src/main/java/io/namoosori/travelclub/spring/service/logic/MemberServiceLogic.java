package io.namoosori.travelclub.spring.service.logic;

import io.namoosori.travelclub.spring.aggregate.club.CommunityMember;
import io.namoosori.travelclub.spring.service.MemberService;
import io.namoosori.travelclub.spring.service.sdo.MemberCdo;
import io.namoosori.travelclub.spring.shared.NameValueList;
import io.namoosori.travelclub.spring.store.MemberStore;
import io.namoosori.travelclub.spring.util.exception.MemberDuplicationException;
import io.namoosori.travelclub.spring.util.exception.NoSuchMemberException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("memberService")
public class MemberServiceLogic implements MemberService {

    private MemberStore memberStore;

    public MemberServiceLogic(MemberStore memberStore){
        // MemberStore 를 implements 하고 있는 class 가 MemberMapStore 뿐이라서 spring IoC 컨테이너가 자동으로 MemberMapStore를 등록한다.
        // 만약 MemberStore 를 implements 하고 있는 class 가 여러개라면 @qualifier 어노테이션을 사용하여 bean의 이름을 통해 어떤 class 를 주입하라고 할 수 있지만
        // 설계 관점에서 주입할 인터페이스를 여러 클래스에서 implements 하지 않는다.
        this.memberStore = memberStore;
    }

    @Override
    public String registerMember(MemberCdo member) {
        String email = member.getEmail();
        CommunityMember foundedMember = memberStore.retrieveByEmail(email);

        if(foundedMember != null){
            throw new MemberDuplicationException("Member already exists with email : "+ email);
        }

        foundedMember = new CommunityMember(
                member.getEmail(), member.getName(), member.getPhoneNumber());

        foundedMember.setNickName(member.getNickName());
        foundedMember.setBirthDay(member.getBirthDay());

        foundedMember.checkValidation();

        String memberId = memberStore.create(foundedMember);
        return memberId;
    }

    @Override
    public CommunityMember findMemberById(String memberId) {
        return memberStore.retrieve(memberId);
    }

    @Override
    public CommunityMember findMemberByEmail(String memberEmail) {
        return memberStore.retrieveByEmail(memberEmail);
    }

    @Override
    public List<CommunityMember> findMembersByName(String name) {
        return memberStore.retrieveByName(name);
    }

    @Override
    public void modifyMember(String memberId, NameValueList nameValueList) {
        CommunityMember targetMember = memberStore.retrieve(memberId);

        if(targetMember == null){
            throw new NoSuchMemberException("No such member with id : " + memberId);
        }

        targetMember.modifyValues(nameValueList);

        memberStore.update(targetMember);
    }

    @Override
    public void removeMember(String memberId) {
        if(memberStore.exists(memberId)){
            throw new NoSuchMemberException("No such member with id : "+memberId);
        }

        memberStore.delete(memberId);
    }
}
