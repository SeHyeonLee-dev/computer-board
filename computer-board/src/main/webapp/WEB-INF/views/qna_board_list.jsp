<%@page import="vo.PageInfo"%>
<%@page import="vo.BoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
 
<%
	ArrayList<BoardBean> boardList=(ArrayList<BoardBean>)request.getAttribute("boardList");
    PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	int listCount=pageInfo.getListCount();
	int nowPage=pageInfo.getPage();
	int maxPage=pageInfo.getMaxPage();
	int startPage=pageInfo.getStartPage();
	int endPage=pageInfo.getEndPage();
%>
 
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>컴퓨터공학과 게시판</title>
    <style>
    	<link rel="preconnect" href="https://fonts.googleapis.com">
		<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
		<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&family=Roboto&display=swap" rel="stylesheet">
		
        * {
            font-family: 'Noto Sans KR', sans-serif;
			font-family: 'Roboto', sans-serif;
            background-color: #f4f4f4;
        }
        
        body {
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        header {
            background-color: #333;
            color: white;
            text-align: center;
            padding: 1em;
        }

        section {
            margin: auto;
            width: 80%;
            background-color: white;
            padding: 20px;
            margin-top: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            color: #333;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        #pageList {
            margin-top: 20px;
            text-align: center;
        }

        #pageList a {
            padding: 8px 16px;
            text-decoration: none;
            color: #333;
            background-color: #f2f2f2;
            border: 1px solid #ddd;
            margin: 0 4px;
            border-radius: 4px;
        }

        #pageList a:hover {
            background-color: #ddd;
        }

        #emptyArea {
            text-align: center;
            color: #666;
            margin-top: 20px;
        }

        .search-container {
            margin-top: 20px;
            text-align: right;
        }

        .search-container select,
        .search-container input {
            padding: 8px;
            margin-right: 8px;
        }

        .search-container button {
            padding: 8px 16px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .search-container button:hover {
            background-color: #45a049;
        }

        .actions {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .actions a {
            text-decoration: none;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border-radius: 4px;
        }

        .actions a:hover {
            background-color: #45a049;
        }
       

	.user-actions {
		text-align: center;
	}
	
    .user-actions a {
        margin-left: 10px; /* 각 링크 사이의 간격 조절 */
        color: black; /* 링크 색상을 흰색 또는 원하는 색상으로 조절 */
    }
    </style>
</head>

<body>
    <header>
        <h1>컴퓨터공학과 게시판</h1>
    </header>

	<div class="user-actions">

		<a href="LoginForm.bo">로그인</a> <a href="RegisterForm.bo">회원가입</a>

	</div>

	<section>
        <div class="actions">
            <h2>글 목록</h2>
            <a href="boardWriteForm.bo">게시판글쓰기</a>
        </div>
        <table>
            <% if (boardList != null && listCount > 0) { %>
            <tr id="tr_top">
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>날짜</th>
                <th>조회수</th>
            </tr>
            <% for (int i = 0; i < boardList.size(); i++) { %>
            <tr>
                <td><%=boardList.get(i).getBOARD_NUM() %></td>
                <td>
                    <% if (boardList.get(i).getBOARD_RE_LEV() != 0) { %>
                    <% for (int a = 0; a <= boardList.get(i).getBOARD_RE_LEV() * 2; a++) { %>
                    &nbsp;
                    <% } %> re:
                    <% } %>
                    <a href="boardDetail.bo?board_num=<%=boardList.get(i).getBOARD_NUM() %>&page=<%=nowPage%>">
                        <%=boardList.get(i).getBOARD_SUBJECT() %>
                    </a>
                </td>
                <td><%=boardList.get(i).getBOARD_NAME() %></td>
                <td><%=boardList.get(i).getBOARD_DATE() %></td>
                <td><%=boardList.get(i).getBOARD_READCOUNT() %></td>
            </tr>
            <% } %>
            </table>
        </section>

        <section id="pageList">
            <% if (nowPage > 1) { %>
            <a href="boardList.bo?page=<%=nowPage-1 %>">이전</a>
            <% } %>
            <% for (int a = startPage; a <= endPage; a++) { %>
            <% if (a == nowPage) { %>
            [<%=a %>]
            <% } else { %>
            <a href="boardList.bo?page=<%=a %>">[<%=a %>]</a>
            <% } %>
            <% } %>
            <% if (nowPage < maxPage) { %>
            <a href="boardList.bo?page=<%=nowPage+1 %>">다음</a>
            <% } %>
        </section>

        <% } else { %>
        <section id="emptyArea">등록된 글이 없습니다.</section>
        <% } %>

        <div class="search-container">
            <div class="row">
                <form method="post" name="search" action="boardSearch.bo">
                    <select class="form-control" name="searchField">
                        <option value="BOARD_SUBJECT">제목</option>
                        <option value="BOARD_NAME">작성자</option>
                    <input type="text" class="form-control" placeholder="검색어 입력" name="searchText" maxlength="100">
                    <button type="submit" class="btn btn-success">검색</button>
                </form>
            </div>
        </div>
    </body>
</html>   
                        
