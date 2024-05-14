<%@page import="vo.BoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	BoardBean board = (BoardBean)request.getAttribute("board");
    String nowPage = (String)request.getAttribute("page");
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8" />
    <title>게시글 답글 작성</title>
    <style type="text/css">
        body {
            font-family: NotoSansKR, Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        #writeForm {
            width: 500px;
            margin: auto;
            border: 1px solid red;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-top: 20px;
            padding: 20px;
            box-sizing: border-box;
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        table {
            margin: auto;
            width: 100%;
        }

        .td_left {
            width: 30%;
            text-align: right;
            font-weight: bold;
            background-color: #ddd;
            padding: 10px;
            box-sizing: border-box;
        }

        .td_right {
            width: 70%;
            background-color: #ddd;
            padding: 10px;
            box-sizing: border-box;
        }

        #commandCell {
            text-align: center;
            margin-top: 20px;
        }

        input[type="text"],
        input[type="password"],
        input[type="submit"],
        input[type="reset"],
        textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            box-sizing: border-box;
        }

        input[type="submit"],
        input[type="reset"] {
            background-color: #d9534f;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            padding: 12px;
            transition: background-color 0.3s;
        }

        input[type="submit"]:hover,
        input[type="reset"]:hover {
            background-color: #c9302c;
        }
    </style>
</head>

<body>
    <!-- 게시글 답글 -->
    <section id="writeForm">
        <h2>게시글 답글 등록</h2>
        <form action="boardReplyPro.bo" method="post" name="boardform">
            <input type="hidden" name="page" value="<%=nowPage %>" />
            <input type="hidden" name="BOARD_NUM" value="<%=board.getBOARD_NUM() %>">
            <input type="hidden" name="BOARD_RE_REF" value="<%=board.getBOARD_RE_REF() %>">
            <input type="hidden" name="BOARD_RE_LEV" value="<%=board.getBOARD_RE_LEV() %>">
            <input type="hidden" name="BOARD_RE_SEQ" value="<%=board.getBOARD_RE_SEQ() %>">
            <table>
                <tr>
                    <td class="td_left"><label for="BOARD_NAME">글쓴이</label></td>
                    <td class="td_right"><input type="text" name="BOARD_NAME" id="BOARD_NAME" required /></td>
                </tr>
                <tr>
                    <td class="td_left"><label for="BOARD_PASS">비밀번호</label></td>
                    <td class="td_right"><input name="BOARD_PASS" type="password" id="BOARD_PASS" required /></td>
                </tr>
                <tr>
                    <td class="td_left"><label for="BOARD_SUBJECT">제 목</label></td>
                    <td class="td_right"><input name="BOARD_SUBJECT" type="text" id="BOARD_SUBJECT" required /></td>
                </tr>
                <tr>
                    <td class="td_left"><label for="BOARD_CONTENT">내 용</label></td>
                    <td><textarea id="BOARD_CONTENT" name="BOARD_CONTENT" cols="40" rows="15" required></textarea></td>
                </tr>
            </table>
            <section id="commandCell">
                <input type="submit" value="답변글 등록" />&nbsp;&nbsp;
                <input type="reset" value="다시 작성" />
            </section>
        </form>
    </section>
</body>

</html>
