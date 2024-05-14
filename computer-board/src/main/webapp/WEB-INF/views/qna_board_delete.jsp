<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	int board_num=(Integer)request.getAttribute("board_num");
    String nowPage = (String)request.getAttribute("page");
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
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
            margin: 0;
            padding: 0;
        }

        #passForm {
            width: 400px;
            margin: auto;
            border: 1px solid red;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-top: 20px;
            padding: 20px;
            box-sizing: border-box;
        }

        #passForm label {
            display: block;
            font-weight: bold;
            margin-bottom: 10px;
        }

        #passForm input {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            box-sizing: border-box;
        }

        #passForm input[type="submit"],
        #passForm input[type="button"] {
            width: 45%;
            padding: 10px 15px;
            background-color: #d9534f;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        #passForm input[type="submit"]:hover,
        #passForm input[type="button"]:hover {
            background-color: #c9302c;
        }
    </style>
</head>

<body>
    <section id="passForm">
        <form name="deleteForm" action="boardDeletePro.bo?board_num=<%=board_num %>" method="post">
            <input type="hidden" name="page" value="<%=nowPage %>">
            <label>글 비밀번호 :</label>
            <input name="BOARD_PASS" type="password" required>
            <input type="submit" value="삭제">
            <input type="button" value="돌아가기" onClick="javascript:history.go(-1)">
        </form>
    </section>
</body>

</html>
