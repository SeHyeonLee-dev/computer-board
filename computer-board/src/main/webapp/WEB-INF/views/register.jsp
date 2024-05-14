<!-- register.jsp -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <!-- 필요한 스타일 및 외부 라이브러리 등 추가 -->
</head>
<body>
    <h2>회원가입</h2>
    
    <form action="RegisterServlet" method="post">
        <!-- 회원가입 입력 폼 -->
        <!-- 필요한 입력 항목을 추가하세요 -->
        <label for="name">이름:</label>
        <input type="text" name="name" required><br>
        
        <label for="id">아이디:</label>
        <input type="text" name="id" required><br>
        
        <label for="password">비밀번호:</label>
        <input type="password" name="password" required><br>
        
        <!-- 추가 입력 항목 및 버튼 등 -->
        
        <input type="submit" value="가입">
    </form>
</body>
</html>
