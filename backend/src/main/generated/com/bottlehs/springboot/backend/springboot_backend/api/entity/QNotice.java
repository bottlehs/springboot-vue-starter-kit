package com.bottlehs.springboot.backend.springboot_backend.api.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QNotice is a Querydsl query type for Notice
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QNotice extends EntityPathBase<Notice> {

    private static final long serialVersionUID = 757965954L;

    public static final QNotice notice = new QNotice("notice");

    public final com.bottlehs.springboot.backend.springboot_backend.api.module.common.base.entity.QBaseEntity _super = new com.bottlehs.springboot.backend.springboot_backend.api.module.common.base.entity.QBaseEntity(this);

    public final StringPath blind_yn = createString("blind_yn");

    public final StringPath cont = createString("cont");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    //inherited
    public final DateTimePath<java.util.Date> mod_dt = _super.mod_dt;

    //inherited
    public final DateTimePath<java.util.Date> reg_dt = _super.reg_dt;

    public final StringPath title = createString("title");

    public final StringPath tp = createString("tp");

    public QNotice(String variable) {
        super(Notice.class, forVariable(variable));
    }

    public QNotice(Path<? extends Notice> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNotice(PathMetadata metadata) {
        super(Notice.class, metadata);
    }

}

