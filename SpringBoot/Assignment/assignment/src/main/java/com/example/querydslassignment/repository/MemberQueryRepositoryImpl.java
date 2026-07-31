package com.example.querydslassignment.repository;

import com.example.querydslassignment.dto.MemberResponse;
import com.example.querydslassignment.dto.MemberSearchRequest;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.querydslassignment.entity.QMember.member;
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
public class MemberQueryRepositoryImpl implements MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<MemberResponse> search(MemberSearchRequest request) {
        return queryFactory
                .select(Projections.constructor(MemberResponse.class, member.id, member.name, member.age))
                .from(member)
                .where(
                        nameContains(request.getName()),
                        ageGoe(request.getAgeGoe()),
                        ageLoe(request.getAgeLoe()),
                        invalidAgeRange(request)
                )
                .orderBy(primaryOrder(request.getSort()), member.id.asc())
                .fetch();
    }

    private BooleanExpression nameContains(String name) {
        return hasText(name) ? member.name.containsIgnoreCase(name) : null;
    }

    private BooleanExpression ageGoe(Integer ageGoe) {
        return ageGoe != null ? member.age.goe(ageGoe) : null;
    }

    private BooleanExpression ageLoe(Integer ageLoe) {
        return ageLoe != null ? member.age.loe(ageLoe) : null;
    }

    private BooleanExpression invalidAgeRange(MemberSearchRequest request) {
        Integer ageGoe = request.getAgeGoe();
        Integer ageLoe = request.getAgeLoe();
        return ageGoe != null && ageLoe != null && ageGoe > ageLoe ? member.id.isNull() : null;
    }

    private OrderSpecifier<?> primaryOrder(String sort) {
        if ("ageDesc".equals(sort)) {
            return member.age.desc();
        }
        if ("nameAsc".equals(sort)) {
            return member.name.asc();
        }
        return member.age.asc();
    }
}
