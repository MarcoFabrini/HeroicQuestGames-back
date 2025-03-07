package com.hqc.beck.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.hqc.beck.dto.AuthorsDTO;
import com.hqc.beck.dto.CartsDTO;
import com.hqc.beck.dto.CategoriesDTO;
import com.hqc.beck.dto.DetailsCartDTO;
import com.hqc.beck.dto.DetailsOrderDTO;
import com.hqc.beck.dto.DetailsShippingDTO;
import com.hqc.beck.dto.EditorsDTO;
import com.hqc.beck.dto.OrdersDTO;
import com.hqc.beck.dto.PayCardsDTO;
import com.hqc.beck.dto.ProductDTO;
import com.hqc.beck.dto.ReviewsDTO;
import com.hqc.beck.dto.UsersDTO;
import com.hqc.beck.model.Authors;
import com.hqc.beck.model.Carts;
import com.hqc.beck.model.Categories;
import com.hqc.beck.model.DetailsCart;
import com.hqc.beck.model.DetailsOrder;
import com.hqc.beck.model.DetailsShipping;
import com.hqc.beck.model.Editors;
import com.hqc.beck.model.Orders;
import com.hqc.beck.model.PayCards;
import com.hqc.beck.model.Product;
import com.hqc.beck.model.Reviews;
import com.hqc.beck.model.Users;

public class Utilities {
  private final static String PATTERN_DATE = "yyyy-MM-dd";

  public static Date convertStringToDate(String dataString) throws ParseException {
    SimpleDateFormat formatter = new SimpleDateFormat(PATTERN_DATE, Locale.ITALY);
    return formatter.parse(dataString);
  }

  public static String convertDateToString(Date date) throws ParseException {
    DateFormat formatter = new SimpleDateFormat(PATTERN_DATE, Locale.ITALY);
    return formatter.format(date);
  }

  public static AuthorsDTO builAuthorsDTO(Authors authors) {
    return new AuthorsDTO(
        authors.getId(),
        authors.getBiography(),
        authors.getCountry(),
        authors.getLastname(),
        authors.getName(),
        authors.getActive());
  }

  public static List<AuthorsDTO> buildAuthorsDTO(List<Authors> authorsList) {
    return authorsList.stream()
        .map(a -> new AuthorsDTO(
            a.getId(),
            a.getBiography(),
            a.getCountry(),
            a.getLastname(),
            a.getName(),
            a.getActive()))
        .collect(Collectors.toList());
  }

  public static List<CartsDTO> buildCartsDTO(List<Carts> c) {
    return c.stream()
        .map(cart -> new CartsDTO(
            cart.getId(),
            cart.getCreatedAt(),
            cart.getUpdatedAt(),
            buildDetailsCartsDTO(cart.getListDetailsCart())))
        .collect(Collectors.toList());
  }

  public static CartsDTO buildCartsDTO(Carts c) {
    return new CartsDTO(
        c.getId(),
        c.getCreatedAt(),
        c.getUpdatedAt(),
        buildDetailsCartsDTO(c.getListDetailsCart()));
  }

  public static CategoriesDTO buildCategoriesDTO(Categories c) {
    return new CategoriesDTO(
        c.getId(),
        c.getName(),
        c.getActive());
  }

  public static List<CategoriesDTO> buildCategoriesDTO(List<Categories> ca) {
    return ca.stream()
        .map(c -> new CategoriesDTO(
            c.getId(),
            c.getName(),
            c.getActive()))
        .collect(Collectors.toList());
  }

  public static List<DetailsCartDTO> buildDetailsCartsDTO(List<DetailsCart> dC) {
    return dC.stream()
        .map(dCart -> new DetailsCartDTO(
            dCart.getId(),
            dCart.getQuantity(),
            buildProductDTOid(dCart.getProduct())))
        .collect(Collectors.toList());
  }

  public static DetailsCartDTO buildDetailsCartsDTO(DetailsCart dC) {
    return new DetailsCartDTO(
        dC.getId(),
        dC.getQuantity(),
        buildProductDTOid(dC.getProduct()));
  }

  // builder per il singolo DetailsOrderDTO
  public final static DetailsOrderDTO buildDetailsOrderDTO(DetailsOrder d) {
    return new DetailsOrderDTO(
        d.getId(),
        d.getQuantity(),
        d.getPriceAtTime(),
        buildProductDTOid(d.getProduct()));
  }

  // builder per farsi restituire la lista DetailsOrderDTO
  public final static List<DetailsOrderDTO> buildDetailsOrderDTO(List<DetailsOrder> listDetailsOrder) {
    return listDetailsOrder.stream()
        .map(detail -> new DetailsOrderDTO(
            detail.getId(),
            detail.getQuantity(),
            detail.getPriceAtTime(),
            buildProductDTOid(detail.getProduct())))
        .collect(Collectors.toList());
  }

  public static EditorsDTO buildEditorsDTO(Editors e) {
    return new EditorsDTO(
        e.getId(),
        e.getName(),
        e.getWebsite(),
        e.getActive());
  }// buildEditorsDTO

  public static List<EditorsDTO> buildEditorsDTO(List<Editors> e) {
    return e.stream()
        .map(ed -> new EditorsDTO(
            ed.getId(),
            ed.getName(),
            ed.getWebsite(),
            ed.getActive()))
        .collect(Collectors.toList());
  }// buildEditorsDTO

  public static ProductDTO buildProductDTOid(Product p) {
    return new ProductDTO(
        p.getId(),
        p.getName(),
        p.getDate(),
        p.getMinGameTime(),
        p.getMaxGameTime(),
        p.getMinPlayerNumber(),
        p.getMaxPlayerNumber(),
        p.getMinAge(),
        p.getDescription(),
        p.getStockQuantity(),
        p.getPrice(),
        p.getActive(),
        buildEditorsDTO(p.getEditor()),
        buildAuthorsDTO(p.getListAuthors()),
        buildCategoriesDTO(p.getListCategory()),
        buildReviewsDTO(p.getListReviews()));
  }// buildProductDTO

  public static List<ProductDTO> buildProductDTO(List<Product> pr) {
    return pr.stream()
        .map(p -> new ProductDTO(
            p.getId(),
            p.getName(),
            p.getMinAge(),
            p.getPrice()))
        .collect(Collectors.toList());
  }// List buildProductDTO

  // builder per farsi restituire un OrdersDTO
  public final static OrdersDTO buildOrdersDTO(Orders o) {
    return new OrdersDTO(
        o.getId(),
        o.getTotalAmmount(),
        o.getOrderStatus(),
        o.getCreatedAt(),
        o.getUpdatedAt(),
        buildDetailsOrderDTO(o.getListDetailsOrder()),
        buildPayCardsDTO(o.getPayCard()));
  }

  // builder per farsi restituire la lista di OrdersDTO
  public final static List<OrdersDTO> buildOrdersDTO(List<Orders> listOrders) {
    return listOrders.stream()
        .map(order -> new OrdersDTO(
            order.getId(),
            order.getTotalAmmount(),
            order.getOrderStatus(),
            order.getCreatedAt(),
            order.getUpdatedAt(),
            buildDetailsOrderDTO(order.getListDetailsOrder()),
            buildPayCardsDTO(order.getPayCard())))
        .collect(Collectors.toList());
  }

  public static PayCardsDTO buildPayCardsDTO(PayCards p) {
    return new PayCardsDTO(
        p.getId(),
        p.getCardNumber(),
        p.getCardHolderName(),
        p.getExpirationDate(),
        p.getActive());
  }// PayCardsDTO

  public static List<PayCardsDTO> buildPayCardsDTO(List<PayCards> p) {
    return p.stream()
        .map(ps -> new PayCardsDTO(
            ps.getId(),
            ps.getCardNumber(),
            ps.getCardHolderName(),
            ps.getExpirationDate(),
            ps.getActive()))
        .collect(Collectors.toList());
  }// List buildPayCardsDTO

  public static ReviewsDTO buildReviewsDTO(Reviews r) {
    return new ReviewsDTO(
        r.getId(),
        r.getScore(),
        r.getDescription(),
        r.getCreatedAt(),
        buildProductDTOid(r.getProduct()),
        buildOrdersDTO(r.getOrder()),
        r.getActive());
  }// buildReviewsDTO

  public static List<ReviewsDTO> buildReviewsDTO(List<Reviews> r) {
    return r.stream()
        .map(re -> new ReviewsDTO(
            re.getId(),
            re.getScore(),
            re.getDescription(),
            re.getCreatedAt(),
            buildProductDTOid(re.getProduct()),
            buildOrdersDTO(re.getOrder()),
            re.getActive()))
        .collect(Collectors.toList());
  }// buildReviewsDTO

  public final static DetailsShippingDTO buildDetailsShippingDTO(DetailsShipping d) {
    return new DetailsShippingDTO(
        d.getId(),
        d.getName(),
        d.getLastname(),
        d.getCountry(),
        d.getStateRegion(),
        d.getCap(),
        d.getCity(),
        d.getAddress(),
        d.getActive());
  }// buildDetailsShippingDTO

  public final static List<DetailsShippingDTO> buildDetailsShippingDTO(List<DetailsShipping> listD) {
    return listD.stream()
        .map(d -> new DetailsShippingDTO(
            d.getId(),
            d.getName(),
            d.getLastname(),
            d.getCountry(),
            d.getStateRegion(),
            d.getCap(),
            d.getCity(),
            d.getAddress(),
            d.getActive()))
        .collect(Collectors.toList());
  }// buildDetailsShippingDTO

  public static UsersDTO buildUsersDTO(Users u) {
    return new UsersDTO(
        u.getId(),
        u.getFullName(),
        u.getEmail(),
        u.getRoles(),
        buildOrdersDTO(u.getListOrders()),
        buildCartsDTO(u.getCart()),
        buildPayCardsDTO(u.getListPayCards()),
        buildDetailsShippingDTO(u.getListDetailsShippings()),
        u.getActive());
  }// buildUsersDTO

  public static List<UsersDTO> buildUsersDTO(List<Users> u) {
    return u.stream()
        .map(us -> new UsersDTO(
            us.getId(),
            us.getFullName(),
            us.getEmail(),
            us.getRoles(),
            buildOrdersDTO(us.getListOrders()),
            buildCartsDTO(us.getCart()),
            buildPayCardsDTO(us.getListPayCards()),
            buildDetailsShippingDTO(us.getListDetailsShippings()),
            us.getActive()))
        .collect(Collectors.toList());
  }// buildUsersDTO

}// class
