
    alter table authors_product 
       drop 
       foreign key FKpfy0gd49txbiesg5teibyxh2q;

    alter table authors_product 
       drop 
       foreign key FK5fryu8ah0m4d4tcvmirncbpc9;

    alter table carts 
       drop 
       foreign key FKjgsx53mn11qckeaot8tedfo5q;

    alter table category_product 
       drop 
       foreign key FKhx1j207rgvkswhw4f4cgaqu4q;

    alter table category_product 
       drop 
       foreign key FKe237utwkkgbrtq2wag1loclw5;

    alter table details_cart 
       drop 
       foreign key FKe5au5adhpsien8khafffev9co;

    alter table details_cart 
       drop 
       foreign key FK68gv5wesfx9fielsatrb1023l;

    alter table details_order 
       drop 
       foreign key FKbvxfkfdne3bp5je3swrwrlfrn;

    alter table details_order 
       drop 
       foreign key FKekfvvdnpjermsh5sjmghp7raa;

    alter table details_shipping 
       drop 
       foreign key FKlor977jsas5s5r376wsp8mt7d;

    alter table orders 
       drop 
       foreign key FKrlv1mu9c3hmlvlxmfeu1gg57t;

    alter table orders 
       drop 
       foreign key FKhur7mikdwaqa7j9rnu62s4bp;

    alter table pay_cards 
       drop 
       foreign key FK1w26uiumo01kcx0hnck9ykary;

    alter table product 
       drop 
       foreign key FKgnohgmhoyhdmlytmb818rwsua;

    alter table reviews 
       drop 
       foreign key FKgryyrfy0wo18oi1eraw3tl23a;

    alter table reviews 
       drop 
       foreign key FKe2j03wgp6woisp89mc6ws1bgn;

    drop table if exists authors;

    drop table if exists authors_product;

    drop table if exists carts;

    drop table if exists categories;

    drop table if exists category_product;

    drop table if exists details_cart;

    drop table if exists details_order;

    drop table if exists details_shipping;

    drop table if exists editors;

    drop table if exists orders;

    drop table if exists pay_cards;

    drop table if exists product;

    drop table if exists reviews;

    drop table if exists users;
