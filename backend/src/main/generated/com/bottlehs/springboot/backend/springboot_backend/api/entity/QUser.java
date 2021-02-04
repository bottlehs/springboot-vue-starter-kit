package com.bottlehs.springboot.backend.springboot_backend.api.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -705143819L;

    public static final QUser user = new QUser("user");

    public final com.bottlehs.springboot.backend.springboot_backend.api.module.common.base.entity.QBaseEntity _super = new com.bottlehs.springboot.backend.springboot_backend.api.module.common.base.entity.QBaseEntity(this);

    public final NumberPath<Integer> bookmark_card_cnt = createNumber("bookmark_card_cnt", Integer.class);

    public final StringPath city = createString("city");

    public final StringPath country = createString("country");

    public final NumberPath<Integer> create_card_cnt = createNumber("create_card_cnt", Integer.class);

    public final NumberPath<Integer> create_humananswer_cnt = createNumber("create_humananswer_cnt", Integer.class);

    public final StringPath email = createString("email");

    public final StringPath first_name = createString("first_name");

    public final NumberPath<Integer> follower_cnt = createNumber("follower_cnt", Integer.class);

    public final NumberPath<Integer> following_cnt = createNumber("following_cnt", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath img_avatar = createString("img_avatar");

    public final StringPath img_profile = createString("img_profile");

    public final StringPath last_name = createString("last_name");

    public final DatePath<java.sql.Date> latest_dt = createDate("latest_dt", java.sql.Date.class);

    public final NumberPath<Integer> like_human_answer_cnt = createNumber("like_human_answer_cnt", Integer.class);

    public final NumberPath<Integer> like_receive_cnt = createNumber("like_receive_cnt", Integer.class);

    //inherited
    public final DateTimePath<java.util.Date> mod_dt = _super.mod_dt;

    public final StringPath password = createString("password");

    public final StringPath premium_yn = createString("premium_yn");

    public final StringPath profit_yn = createString("profit_yn");

    //inherited
    public final DateTimePath<java.util.Date> reg_dt = _super.reg_dt;

    public final StringPath region = createString("region");

    public final ListPath<String, StringPath> roles = this.<String, StringPath>createList("roles", String.class, StringPath.class, PathInits.DIRECT2);

    public final StringPath state_yn = createString("state_yn");

    public final StringPath status_desc = createString("status_desc");

    public final StringPath super_yn = createString("super_yn");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

