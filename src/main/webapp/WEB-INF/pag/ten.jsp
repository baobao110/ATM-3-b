<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>

<jsp:include page="common/head.jsp"></jsp:include>

<body>
用户名：${username}"

<table id="table">
	
</table>

<jsp:include page="common/foot.jsp"></jsp:include>
</body>

<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/js/json2.js"></script>
<script type="text/javascript">

	function getNowFormatDate(day)
	{
	//var day = new Date();
	var Year = 0;
	var Month = 0;
	var Day = 0;
	var CurrentDate = "";
	//初始化时间
	//Year= day.getYear();//有火狐下2008年显示108的bug
	Year= day.getFullYear();//ie火狐下都可以
	Month= day.getMonth()+1;
	Day = day.getDate();
	//Hour = day.getHours();
	// Minute = day.getMinutes();
	// Second = day.getSeconds();
	CurrentDate += Year + "-";
	if (Month >= 10 )
	{
	CurrentDate += Month + "-";
	}
	else
	{
	CurrentDate += "0" + Month + "-";
	}
	if (Day >= 10 )
	{
	CurrentDate += Day ;
	}
	else
	{
	CurrentDate += "0" + Day ;
	}
	return CurrentDate;
	}

	function flow(){
			var param={
				username:${username};
			};
			$.post('/card/ten.do',param,callback)
		}
		
		function callback(data,status){
			alert("点击");
			var ajaxDAO=data;
			var result=ajaxDAO.data.object;
			var msg="";
			for (var i=0; i<result.length;i++) {
				msg+='<tr>';
				msg+='<td>'+result[i].number+'</td>';
				msg+='<td>'+ getNowFormatDate(new Date(result[i].createtime))+'</td>';
				msg+='<td>'+result[i].money+'</td>';
				msg+='</tr>';
				}
				$('#table').html(msg);
			}
		$(document).ready(function(){
		flow();
		});