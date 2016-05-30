<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<footer class="panel-footer">
    <div class="row">
        <div class="col-sm-8 ">
            <small class="text-muted inline m-t-sm m-b-sm">
               共<i class="blue">${pageInfoList.totalrows}</i>条，第<i class="blue">${pageInfoList.pageno}/${pageInfoList.total}</i>页展示，
                    每页<i class="blue">${pageInfoList.rowsize}</i>条

            </small>
        </div>
        <div class="col-sm-4 text-right text-center-xs">
            <ul class="pagination pagination-sm m-t-none m-b-none">
                <li><a  <c:if test="${pageInfoList.pageno>1}"> ui-sref="${pageInfoList.simpleDomain}List({pageNo:'${pageInfoList.pageno-1}'})" </c:if>><i class="fa fa-chevron-left"></i></a></li>
                <c:forEach begin="1" end="${pageInfoList.total}" step="1" var="num" >
                    <c:choose>
                        <c:when test="${num==1}">
                            <li ><a <c:if test="${pageInfoList.pageno==num}"> style="background-color: #dee5e7;" </c:if> ui-sref="${pageInfoList.simpleDomain}List({pageNo:'${num}'})">${num}</a></li>
                            <c:if test="${pageInfoList.pageno-2>2}">
                                <li ><a >...</a></li>
                            </c:if>
                        </c:when>
                        <c:when test="${(pageInfoList.pageno-num>=0 && pageInfoList.pageno-num<=2) || (num-pageInfoList.pageno>=0 && num-pageInfoList.pageno<=2 && num<pageInfoList.total)}">
                            <li ><a <c:if test="${pageInfoList.pageno==num}"> style="background-color: #dee5e7;" </c:if> ui-sref="${pageInfoList.simpleDomain}List({pageNo:'${num}'})">${num}</a></li>
                            <c:if test="${num-pageInfoList.pageno==2 && num+1<pageInfoList.total}">
                                <li ><a >...</a></li>
                            </c:if>
                        </c:when>
                        <c:when test="${ num==pageInfoList.total}">
                            <li ><a <c:if test="${pageInfoList.pageno==num}"> style="background-color: #dee5e7;" </c:if> ui-sref="${pageInfoList.simpleDomain}List({pageNo:'${num}'})">${num}</a></li>
                        </c:when>
                    </c:choose>
                </c:forEach>
                <li><a <c:if test="${pageInfoList.pageno<pageInfoList.total}"> ui-sref="${pageInfoList.simpleDomain}List({pageNo:'${pageInfoList.pageno+1}'})" </c:if>><i class="fa fa-chevron-right"></i></a></li>
            </ul>
        </div>
    </div>
</footer>