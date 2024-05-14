<%@page import="vo.BoardBean"%>
<%@page language="java" contentType="text/html; charset=UTF-8"%>
 
<%
	BoardBean board = (BoardBean)request.getAttribute("board");
    String nowPage = (String)request.getAttribute("page");
%>
 
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>게시글</title>
    <style type="text/css">
    
    	<link rel="preconnect" href="https://fonts.googleapis.com">
		<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
		<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&family=Roboto&display=swap" rel="stylesheet">
		
        * {
            font-family: 'Noto Sans KR', sans-serif;
			font-family: 'Roboto', sans-serif;
            background-color: #f4f4f4;
        }
        
        body {
            margin: 0;
            padding: 0;
        }

        #container {
            width: 100%;
            max-width: 800px;
            margin: auto;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-top: 20px;
            padding: 20px;
            box-sizing: border-box;
        }

        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        #basicInfoArea {
            height: 40px;
            text-align: center;
            padding: 10px;
            background-color: #f2f2f2;
            border-bottom: 1px solid #ddd;
        }

        #boardContentArea {
            background: #ddd;
            margin-top: 20px;
            padding: 20px;
            text-align: left;
            overflow: auto;
        }

        #commandList {
            text-align: center;
            margin-top: 20px;
        }

        #commandList a {
            display: inline-block;
            margin: 0 10px;
            padding: 8px 15px;
            text-decoration: none;
            color: #fff;
            background-color: #4CAF50;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        #commandList a:hover {
            background-color: #45a049;
        }
    </style>
</head>

<body>
    <!-- 게시판 수정 -->
    <div id="container">
        <h2>글 내용 상세보기</h2>
        <div id="basicInfoArea">
            제 목 : <%=board.getBOARD_SUBJECT()%><br> 첨부파일 :
            <%if(!(board.getBOARD_FILE()==null)){ %>
            <a href="file_down?downFile=<%=board.getBOARD_FILE()%>"> <%=board.getBOARD_FILE() %>
            </a>
            <%} %>
        </div>
        <div id="boardContentArea">
            <%=board.getBOARD_CONTENT() %>
        </div>
        <div id="commandList">
            <a href="boardReplyForm.bo?board_num=<%=board.getBOARD_NUM() %>&page=<%=nowPage%>">[답변]</a>
            <a href="boardModifyForm.bo?board_num=<%=board.getBOARD_NUM() %>">[수정]</a>
            <a href="boardDeleteForm.bo?board_num=<%=board.getBOARD_NUM() %>&page=<%=nowPage%>">[삭제]</a>
            <a href="boardList.bo?page=<%=nowPage%>">[목록]</a>
        </div>
    </div>
</body>

</html>
