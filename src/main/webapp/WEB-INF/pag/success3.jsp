<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<jsp:include page="common/head.jsp"></jsp:include>

<body>
<h1>ATM系统-查询结果</h1>
卡号 	${card.getNumber()} <br>
金额   ${card.getMoney() }  <br>

<a href="/card/toUsercenter.do">返回个人中心</a>
</body>

<jsp:include page="common/foot.jsp"></jsp:include>

</html>