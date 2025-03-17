
    create table accessory (
        battery_life integer,
        id integer not null auto_increment,
        wireless bit,
        color varchar(255),
        compatible_with varchar(255),
        extra_features TEXT,
        original_or_thirdparty varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table authors (
        active bit not null,
        id integer not null auto_increment,
        biography TEXT,
        country varchar(255),
        lastname varchar(255),
        name varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table authors_product (
        id_authors integer not null,
        id_product integer not null
    ) engine=InnoDB;

    create table board_game (
        id integer not null auto_increment,
        max_game_time integer,
        max_player_number integer,
        min_age integer,
        min_game_time integer,
        min_player_number integer,
        primary key (id)
    ) engine=InnoDB;

    create table carts (
        id integer not null auto_increment,
        id_users integer,
        created_at datetime(6),
        updated_at datetime(6),
        primary key (id)
    ) engine=InnoDB;

    create table categories (
        active bit not null,
        id integer not null auto_increment,
        name varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table category_product (
        id_category integer not null,
        id_product integer not null
    ) engine=InnoDB;

    create table collectible_card (
        grade_score integer,
        graded bit,
        holographic bit,
        id integer not null auto_increment,
        card_set varchar(255),
        condition_card TEXT,
        edition varchar(255),
        grade_authority varchar(255),
        language varchar(255),
        rarity varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table console (
        id integer not null auto_increment,
        storage_capacity integer,
        warranty_months integer,
        brand varchar(255),
        condition_console varchar(255),
        included_accessories TEXT,
        model varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table details_cart (
        id integer not null auto_increment,
        id_carts integer,
        id_product integer,
        quantity integer not null,
        primary key (id)
    ) engine=InnoDB;

    create table details_order (
        id integer not null auto_increment,
        id_orders integer,
        id_product integer,
        price_at_time float(53),
        quantity integer,
        primary key (id)
    ) engine=InnoDB;

    create table details_shipping (
        active bit,
        id integer not null auto_increment,
        id_users integer not null,
        address varchar(255) not null,
        cap varchar(255) not null,
        city varchar(255) not null,
        country varchar(255) not null,
        lastname varchar(255) not null,
        name varchar(255) not null,
        state_region varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table editors (
        active bit,
        id integer not null auto_increment,
        name varchar(255) not null,
        website varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table orders (
        id integer not null auto_increment,
        id_pay_cards integer,
        id_users integer,
        total_ammount float(53),
        created_at datetime(6),
        updated_at datetime(6),
        order_status varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table pay_cards (
        active bit not null,
        id integer not null auto_increment,
        id_users integer,
        created_at datetime(6),
        expiration_date datetime(6),
        updated_at datetime(6),
        card_holder_name varchar(255),
        card_number varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table product (
        active bit,
        id integer not null auto_increment,
        id_accessory integer,
        id_board_game integer,
        id_collectible_card integer,
        id_console integer,
        id_editors integer,
        price float(53) not null,
        stock_quantity integer not null,
        pubblication_date datetime(6),
        description TEXT,
        image varchar(255),
        name varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table reviews (
        active bit not null,
        id integer not null auto_increment,
        id_order integer,
        id_product integer,
        score integer,
        created_at datetime(6),
        updated_at datetime(6),
        description TEXT,
        primary key (id)
    ) engine=InnoDB;

    create table users (
        active bit not null,
        id integer not null auto_increment,
        created_at datetime(6),
        updated_at datetime(6),
        email varchar(100) not null,
        full_name varchar(255) not null,
        password varchar(255) not null,
        roles enum ('ROLE_ADMIN','ROLE_MODERATOR','ROLE_USER') not null,
        primary key (id)
    ) engine=InnoDB;

    alter table carts 
       add constraint UK1eyx4jkn3tmk5cc9itor35q84 unique (id_users);

    alter table product 
       add constraint UK1s9a78qkdtoyf9yt11wn8f78d unique (id_accessory);

    alter table product 
       add constraint UKbpk04inba8dycw7mknmqp2ins unique (id_board_game);

    alter table product 
       add constraint UK4jtlbsokhey5eaaanjg7fretx unique (id_collectible_card);

    alter table product 
       add constraint UKoxcgmka19hylk8fw6gfiajxig unique (id_console);

    alter table users 
       add constraint UK6dotkott2kjsp8vw4d0m25fb7 unique (email);

    alter table authors_product 
       add constraint FKpfy0gd49txbiesg5teibyxh2q 
       foreign key (id_authors) 
       references authors (id);

    alter table authors_product 
       add constraint FK5fryu8ah0m4d4tcvmirncbpc9 
       foreign key (id_product) 
       references product (id);

    alter table carts 
       add constraint FKjgsx53mn11qckeaot8tedfo5q 
       foreign key (id_users) 
       references users (id);

    alter table category_product 
       add constraint FKhx1j207rgvkswhw4f4cgaqu4q 
       foreign key (id_category) 
       references categories (id);

    alter table category_product 
       add constraint FKe237utwkkgbrtq2wag1loclw5 
       foreign key (id_product) 
       references product (id);

    alter table details_cart 
       add constraint FKe5au5adhpsien8khafffev9co 
       foreign key (id_carts) 
       references carts (id);

    alter table details_cart 
       add constraint FK68gv5wesfx9fielsatrb1023l 
       foreign key (id_product) 
       references product (id);

    alter table details_order 
       add constraint FKbvxfkfdne3bp5je3swrwrlfrn 
       foreign key (id_orders) 
       references orders (id);

    alter table details_order 
       add constraint FKekfvvdnpjermsh5sjmghp7raa 
       foreign key (id_product) 
       references product (id);

    alter table details_shipping 
       add constraint FKlor977jsas5s5r376wsp8mt7d 
       foreign key (id_users) 
       references users (id);

    alter table orders 
       add constraint FKrlv1mu9c3hmlvlxmfeu1gg57t 
       foreign key (id_pay_cards) 
       references pay_cards (id);

    alter table orders 
       add constraint FKhur7mikdwaqa7j9rnu62s4bp 
       foreign key (id_users) 
       references users (id);

    alter table pay_cards 
       add constraint FK1w26uiumo01kcx0hnck9ykary 
       foreign key (id_users) 
       references users (id);

    alter table product 
       add constraint FKclh2rq5h86pynb0tax0lf4dvl 
       foreign key (id_accessory) 
       references accessory (id);

    alter table product 
       add constraint FK3wmlt3r8c0hafn2lgnv9ibww9 
       foreign key (id_board_game) 
       references board_game (id);

    alter table product 
       add constraint FKbue345tjf0dphgum1dutolv8q 
       foreign key (id_collectible_card) 
       references collectible_card (id);

    alter table product 
       add constraint FK2r6py0ovg8h91ph6j9cgx13f8 
       foreign key (id_console) 
       references console (id);

    alter table product 
       add constraint FKgnohgmhoyhdmlytmb818rwsua 
       foreign key (id_editors) 
       references editors (id);

    alter table reviews 
       add constraint FKgryyrfy0wo18oi1eraw3tl23a 
       foreign key (id_order) 
       references orders (id);

    alter table reviews 
       add constraint FKe2j03wgp6woisp89mc6ws1bgn 
       foreign key (id_product) 
       references product (id);
