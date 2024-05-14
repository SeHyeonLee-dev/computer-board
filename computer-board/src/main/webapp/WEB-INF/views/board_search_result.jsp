<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="vo.PageInfo"%>
<%@page import="vo.BoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>


<!DOCTYPE html>
<html>
<head>
    <title>컴퓨터공학과 게시판</title>
    <style type="text/css">
    
    	<link rel="preconnect" href="https://fonts.googleapis.com">
		<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
		<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&family=Roboto&display=swap" rel="stylesheet">
		
        * {
            font-family: 'Noto Sans KR', sans-serif;
			font-family: 'Roboto', sans-serif;
            background-color: #f4f4f4;
        }

        #resultTable {
            margin: auto;
            width: 80%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        #resultTable th, #resultTable td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 12px;
        }

        #resultTable th {
            background-color: #f2f2f2;
        }

        h2 {
            text-align: center;
            color: #333;
        }
    </style>
</head>
<body>
    <div>
        <h2>게시글 검색 결과</h2>
    </div>

    <table id="resultTable">
        <thead>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>날짜</th>
                <th>조회수</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="board" items="${searchResult}">
                <tr>
                    <td>${board.BOARD_NUM}</td>
                    <td>
                        <a href="boardDetail.bo?board_num=${board.BOARD_NUM}&page=${nowPage}">
                            ${board.BOARD_SUBJECT}
                        </a>
                    </td>
                    <td>${board.BOARD_NAME}</td>
                    <td>${board.BOARD_DATE}</td>
                    <td>${board.BOARD_READCOUNT}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>

