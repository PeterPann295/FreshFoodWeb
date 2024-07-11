
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="categoryDAO" class="database.CategoryDao" scope="page" />
<jsp:useBean id="parentCategoryDAO" class="database.ParentCategoryDao"
             scope="page" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>

</head>
<body>
<div class="col-lg-3">
    <div class="container" style="padding-left: 30px">
        <div class="accordion" id="filterAccordion">
            <div class="card" style="background-color: #efffcf">
                <div class="card-header" id="">
                    <h5 class="mb-0 text-success">Danh Mục Sản Phẩm</h5>
                </div>

                <div id="categoryCollapse" class="collapse show"
                     aria-labelledby="categoryHeading" data-parent="#filterAccordion" >
                    <div class="card-body" >
                            <c:forEach var="p" items="${parentCategoryDAO.selectAll()}"
                                       varStatus="loop">
                                <div class="accordion accordion-flush" id="accordionFlushExample">

                                <div class="accordion-item">
                                    <h2 class="accordion-header">

                                        <button style="background: #efffcf" class="accordion-button collapsed" type="button"
                                                data-bs-toggle="collapse"
                                                data-bs-target="#flush-collapse-${loop.index}"
                                                aria-expanded="false"
                                                aria-controls="flush-collapse-${loop.index}">
                                                ${p.name}</button>
                                    </h2>
                                    <div id="flush-collapse-${loop.index}"
                                         class="accordion-collapse collapse"
                                         data-bs-parent="#accordionFlushExample">
                                        <div class="accordion-body">
                                            <ul class="list-unstyled">
                                            <c:forEach var="c"
                                                       items="${categoryDAO.selectByParentId(p.id)}"
                                                       varStatus="innerLoop">
                                                <div class="form-check">
                                                    <input class="form-check-input" type="checkbox" id="category${innerLoop.index}" name="category" value="${c.id}">
                                                    <label class="form-check-label" for="category${innerLoop.index}">
                                                            ${c.name}
                                                    </label>
                                                </div>

                                            </c:forEach>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                        </div>
                            </c:forEach>

                    </div>
                </div>
            </div>
        </div>
        <div class="card mt-4" style="background-color: #efffcf">
            <div class="card-header" id="categoryHeading">
                <h5 class="mb-0 text-success">Chọn Mức Giá</h5>
            </div>
            <div class="card-body">
                    <div class="form-check mt-1 mb-1">
                        <input class="form-check-input" type="radio" id="under10000"
                               name="price" value="price < 10000"> <label
                            class="form-check-label" for="under10000"> Dưới 10,000đ
                    </label>
                    </div>
                    <div class="form-check mt-1 mb-1">
                        <input class="form-check-input" type="radio" id="10000to50000"
                               name="price" value="price Between 10000 and 50000">
                        <label class="form-check-label" for="10000to50000"> Từ
                            10,000đ - 50,000đ </label>
                    </div>

                    <div class="form-check mt-1 mb-1">
                        <input class="form-check-input" type="radio" id="50000to100000"
                               name="price" value="price Between 50000 and 100000">
                        <label class="form-check-label" for="50000to100000"> Từ
                            50,000đ - 100,000đ </label>
                    </div>

                    <div class="form-check mt-1 mb-1">
                        <input class="form-check-input" type="radio" id="100000to200000"
                               name="price" value="price Between 100000 and 200000">
                        <label class="form-check-label" for="100000to200000"> Từ
                            100,000đ - 200,000đ </label>
                    </div>
                    <div class="form-check mt-1 mb-1">
                        <input class="form-check-input" type="radio" id="200000to300000"
                               name="price" value="price Between 200000 and 300000">
                        <label class="form-check-label" for="200000to300000"> Từ
                            200,000đ - 300,000đ </label>
                    </div>
                    <div class="form-check mt-1 mb-1">
                        <input class="form-check-input" type="radio" id="300000to500000"
                               name="price" value="price Between 300000 and 500000">
                        <label class="form-check-label" for="300000to500000"> Từ
                            300,000đ - 500,000đ </label>
                    </div>
                    <div class="form-check mt-1 mb-1">
                        <input class="form-check-input" type="radio" id="500000to1000000"
                               name="price" value="price Between 500000 and 1000000">
                        <label class="form-check-label" for="500000to1000000"> Từ
                            500,000đ - 1000,000đ </label>
                    </div>
                    <div class="form-check mt-1 mb-1">
                        <input class="form-check-input" type="radio" id="above1000000"
                               name="price" value="price > 1000000"> <label
                            class="form-check-label" for="above1000000"> Trên
                        1,000,000đ </label>
                    </div>
                    <hr>
                    <div class="form-check mt-1 mb-1">
                        <input class="form-check-input" type="checkbox" id="discount10"
                               name="discount" value="hasDiscount">
                        <label class="form-check-label" for="discount10"> Giảm
                            giá </label>
                    </div>

            </div>
        </div>

    </div>

</div>


</body>
</html>
