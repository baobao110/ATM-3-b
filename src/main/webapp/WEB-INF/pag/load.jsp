<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<jsp:include page="common/head.jsp"></jsp:include>
<h1>上传头像</h1>
<form method="POST" enctype="multipart/form-data" action="/user/load.do">
<input type="file" name="file"><br/>
 <input type="submit" value="上传">
 </form>
 <a href="/card/toUsercenter.do">返回个人中心</a>
<jsp:include page="common/foot.jsp"></jsp:include>
</html>