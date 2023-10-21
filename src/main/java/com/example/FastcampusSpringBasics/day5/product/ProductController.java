package com.example.FastcampusSpringBasics.day5.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    // 생성자 주입방법이 불변인 이유?
    // 생성자 호출은 객체 생성때만 이루어지기 때문에, 그런거 아님..?
    ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * 클라이언트에게 데이터를 전달받는 방법 3가지
     * 쿼리 스트링 : URL 값을 들고 들어오겠다 ex. 특정 상품 상세 조회 : Get /products?id= , /products/{id}
     * http 메세지 바디 : #JSON <-> #직렬화/역직렬화 <-> 자바 객체
     */


    // DB interface : JDBC, JDBC template, hibernate, ibatis, mybatis, jpa, spring data jpa



    // 상품 전체 조회
//    @RequestMapping(value = "/products", method = RequestMethod.GET)
    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        return productService.findAllProducts();
    }

    // 상품 개별 등록
    @PostMapping("/products")
    public ResponseEntity saveProduct(@RequestBody ProductDto productDto) {
        productService.saveProduct();
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/products")
    public ResponseEntity saveAllProducts(@RequestBody List<ProductDto> productDtoList) {
        productService.saveAllProducts();
        return new ResponseEntity(HttpStatus.CREATED);
    }


    /**
     * <URL, method>
     * 상품 여러개 등록 : POST /products
     *                REQ - List<ProductDto>, RES - 201, + redirect 전체조회
     * 상품 개별 등록 : POST /products
     *               REQ - ProductDto(상품 이름, 내용, 가격 ...) RES - 201, + redirect ...
     * 상품 전체 조회 : GET /products
     *               REQ X RES - List<ProductDto>
     * 상품 개별 조회 : GET /products/{id}
     *               REQ - id , RES - ProductDto
     * 상품 (개별) 수정 : PUT, PATCH /products/{id}
     *                 REQ - id, ProductDto(ProductUpdateRequest) , RES - (수정된) ProductDto, 200, id
     *                 cf. 리다이렉트, 개별조회
     *          호출 : 1) 수정 페이지 들어갈때 2) 수정 다하고 [저장(수정)] 버튼 누를 때
     * 상품 전체 삭제 : DELETE /products
     *               REQ X / RES - 리다이렉트, 전체 조회, 200, ...
     * 상품 개별 삭제 : DELETE /products/{id}
     *               REQ - id, RES - 리다이렉트, 전체 조회, 200 + 삭제된 상품 id ...
     */
}
