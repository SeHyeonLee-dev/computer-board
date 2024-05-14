<%@ page language="java" contentType="text/html; charset=UTF-8"%>
 
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>게시판</title>
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
            height: 610px;
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

        input {
            width: 300px;
            font-weight: bold;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
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

        input[type="submit"],
        input[type="reset"] {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"]:hover,
        input[type="reset"]:hover {
            background-color: #45a049;
        }
    </style>
</head>

<body>

    <!-- 새로운 글 작성 -->

    <section id="writeForm">
        <h2>새로운 글 작성</h2>
        <!-- enctype="multipart/form-data" 부분이 있어야 파일업로드 기능 처리 가능하다. -->
        <form action="boardWritePro.bo" method="post" enctype="multipart/form-data" name="boardform">
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
                    <td class="td_right"><textarea id="BOARD_CONTENT" name="BOARD_CONTENT" cols="40" rows="15"
                            style="width:300px" required></textarea></td>
                </tr>
                <tr>
                    <td class="td_left"><label for="BOARD_FILE"> 파일 첨부 </label></td>
                    <td class="td_right"><input name="BOARD_FILE" type="file" id="BOARD_FILE" required /></td>
                </tr>
            </table>
            <section id="commandCell">
                <input type="submit" value="등록"> &nbsp;&nbsp;
                <input type="reset" value="다시쓰기" />
            </section>
        </form>
    </section>
</body>

</html>
