<%@page import="vo.BoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	BoardBean board = (BoardBean)request.getAttribute("board");
%>
 
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>게시글 수정</title>
    <script type="text/javascript">
        function modifyboard() {
            modifyform.submit();
        }
    </script>
    <style type="text/css">
    	<link rel="preconnect" href="https://fonts.googleapis.com">
		<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
		<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&family=Roboto&display=swap" rel="stylesheet">
		
        * {
            font-family: 'Noto Sans KR', sans-serif;
			font-family: 'Roboto', sans-serif;
            background-color: #f4f4f4;
        }
        
    

        #registForm {
            width: 500px;
            height: 600px;
            border: 1px solid red;
            margin: auto;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-top: 20px;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        table {
            margin: auto;
            width: 400px;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .td_left {
            width: 100px;
            text-align: right;
            font-weight: bold;
            background-color: #ddd;
            padding: 10px;
        }

        .td_right {
            width: 300px;
            background-color: #ddd;
            padding: 10px;
        }

        #commandCell {
            text-align: center;
            margin-top: 20px;
        }

        a {
            text-decoration: none;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: #fff;
            border-radius: 5px;
        }

        a:hover {
            background-color: #45a049;
        }
    </style>
</head>

<body>

    <!-- 게시판 글 수정 -->
    <section id="writeForm">
        <h2>게시판 글 수정</h2>
        <form action="boardModifyPro.bo" method="post" name="modifyform">
            <input type="hidden" name="BOARD_NUM" value="<%=board.getBOARD_NUM()%>" />
            <table>
                <tr>
                    <td class="td_left">
                        <label for="BOARD_NAME">글쓴이</label>
                    </td>
                    <td class="td_right">
                        <input type="text" name="BOARD_NAME" id="BOARD_NAME" value="<%=board.getBOARD_NAME()%>" />
                    </td>
                </tr>
                <tr>
                    <td class="td_left">
                        <label for="BOARD_PASS">비밀번호</label>
                    </td>
                    <td class="td_right">
                        <input name="BOARD_PASS" type="password" id="BOARD_PASS" />
                    </td>
                </tr>
                <tr>
                    <td class="td_left">
                        <label for="BOARD_SUBJECT">제 목</label>
                    </td>
                    <td class="td_right">
                        <input name="BOARD_SUBJECT" type="text" id="BOARD_SUBJECT" value="<%=board.getBOARD_SUBJECT()%>" />
                    </td>
                </tr>
                <tr>
                    <td class="td_left">
                        <label for="BOARD_CONTENT">내 용</label>
                    </td>
                    <td>
                        <textarea id="BOARD_CONTENT" name="BOARD_CONTENT" cols="40" rows="15"><%=board.getBOARD_CONTENT()%></textarea>
                    </td>
                </tr>
            </table>
            <section id="commandCell">
                <a href="javascript:modifyboard()">수정</a>&nbsp;&nbsp;
                <a href="javascript:history.go(-1)">뒤로</a>
            </section>
        </form>
    </section>
</body>

</html>
