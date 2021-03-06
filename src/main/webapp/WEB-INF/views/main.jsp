<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!--
date : 19-01-10
내용 : 뷰단 초기 설정 진행
date : 19-01-19
내용 : 버튼 정렬 수
date : 19-01-24
내용 : css 누가 깨놓은 거 고침
작성자 : 고은아
-->
<header>
	<div id="carouselExampleIndicators" class="carousel slide"
		data-ride="carousel">
		<ol class="carousel-indicators">
			<li data-target="#carouselExampleIndicators" data-slide-to="0"
				class="active"></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
		</ol>
		<div class="carousel-inner" role="listbox">
			<!-- Slide One - Set the background image for this slide in the line below -->
			<div class="carousel-item active"
				style="background-image: url('https://cdn.pixabay.com/photo/2017/05/24/12/08/broken-triangle-2340393__340.jpg')">
				<div class="carousel-caption d-none d-md-block">
					<h3>당신의 불만을 기다립니다</h3>
					<p>자유로운 불만 공유 사이트</p>
				</div>
			</div>
			<!-- Slide Two - Set the background image for this slide in the line below -->
			<div class="carousel-item"
				style="background-image: url('https://png.pngtree.com/thumb_back/fw800/back_pic/03/61/31/0557a7322b6c277.jpg')">
				<div class="carousel-caption d-none d-md-block">
					<h3>시장 제품의 문제점에 대해 알아보세요</h3>
					<p>불만글을 토대로 새로운 아이디어를 창출해보세요</p>
				</div>
			</div>
			<!-- Slide Three - Set the background image for this slide in the line below -->
			<div class="carousel-item"
				style="background-image: url('https://png.pngtree.com/thumb_back/fw800/back_pic/03/62/14/2357a9a0f6e00b5.jpg')">
				<div class="carousel-caption d-none d-md-block">
					<h3>The world is an infinitely better place precisely because
						you weren't</h3>
					<p>The Imitation Game, 2014</p>
				</div>
			</div>
		</div>
		<a class="carousel-control-prev" href="#carouselExampleIndicators"
			role="button" data-slide="prev"> <span
			class="carousel-control-prev-icon" aria-hidden="true"></span> <span
			class="sr-only">Previous</span>
		</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
			role="button" data-slide="next"> <span
			class="carousel-control-next-icon" aria-hidden="true"></span> <span
			class="sr-only">Next</span>
		</a>
	</div>
</header>

<!-- Page Content -->
<div class="container">

<br>
       <!-- 최근 게시글 시작 -->
      <hr>
      <h2>최근 게시글</h2>
        <div class="row">
      <c:forEach items="${boardList}" var="boardList">
      <c:if test="${boardList.thumbNail != null }">
      <br>
      <br>
        <div class="col-lg-4 col-sm-6 portfolio-item">
          <div class="card h-100">
          	<c:choose>
          		<c:when test="${empty boardList.thumbNail}">
          			<a href="selectOneBoard.do?bno=${boardList.bno}"> <img
                     src="http://192.168.0.85:8888/img/boardThumbNail/thumbnail.png"
                     class="card-img-top" alt=""></a>
          		</c:when>
          		<c:otherwise>
          			<a href="selectOneBoard.do?bno=${boardList.bno}"> <img
                     src="http://192.168.0.85:8888/img/boardThumbNail/${boardList.thumbNail }"
                     class="card-img-top" alt=""></a>
          		</c:otherwise>
          	</c:choose>
            <div class="card-body">
              <h4 class="card-title">
              	<c:choose>
              		<c:when test="${fn:length(boardList.title) >= 12}">
                		<a href="selectOneBoard.do?bno=${boardList.bno}"><c:out value="${fn:substring(boardList.title,0,12)}"/>..</a>              		
              		</c:when>
              		<c:otherwise>
              			<a href="selectOneBoard.do?bno=${boardList.bno}">${boardList.title}</a>
              		</c:otherwise>
              	</c:choose>

              </h4>
              <p class="card-text">${boardList.mid}</p>
              <p class="card-text">${boardList.readCount}</p>
            </div>
          </div>
       </div>
       </c:if>
    </c:forEach>
        </div>
      </div>
        <div class="rightOutDiv">
	        <div class="rightInDiv">
	          	<a class="btn btn-secondary" href="boardForm.do">더보기</a>
	      	</div>
        </div>
      <!-- 최근 게시글 끝 -->
<br>
<br>