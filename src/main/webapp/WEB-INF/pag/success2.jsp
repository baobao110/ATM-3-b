<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<jsp:include page="common/head.jsp"></jsp:include>

<body>
<h1>ATM系统-业务办理成功</h1>
卡号 ${card1.getNumber()} <br>
金额   ${card1.getMoney()}  <br>
汇款账户 ${card2.getNumber()}

<a href="/card/toUsercenter.do">返回个人中心</a>
 
</body>


<jsp:include page="common/foot.jsp"></jsp:include>
</html>