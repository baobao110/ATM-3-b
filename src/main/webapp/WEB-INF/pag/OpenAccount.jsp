<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- <!DOCTYPE html>
<html>

<jsp:include page="common/head.jsp"></jsp:include>

<body>
<h1>ATM--开户</h1>

<form action="/card/openAccount.do" method="post">	
	密码：<input type="password" name="password">
	<input type="submit" value="开户">
</form>

</body>

<a href="/card/toUsercenter.do">返回个人中心</a>

<jsp:include page="common/foot.jsp"></jsp:include>
</html> --%>

<html lang="en">
<body data-type="index" class="theme-white">
	
    <div class="am-g tpl-g">
        <!-- 头部 -->
        <jsp:include page="common/head.jsp"></jsp:include>
        <!-- 风格切换 -->
        
        <!-- 侧边导航栏 -->
        <jsp:include page="common/left.jsp"></jsp:include>

        <!-- 内容区域 -->
        <div class="tpl-content-wrapper">

            <div class="row-content am-cf">
                <div class="row  am-cf">
                    
                    <div class="row">

                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <div class="widget am-cf">
                            <div class="widget-head am-cf">
                                <div class="widget-title am-fl">银行卡开户</div>
                                <div class="widget-function am-fr">
                                    <a href="javascript:;" class="am-icon-cog"></a>
                                </div>
                            </div>
                            <div class="widget-body am-fr">

                                <form class="am-form tpl-form-line-form">
                                    <div class="am-form-group">
                                        <label for="user-name" class="am-u-sm-3 am-form-label">密码 <span class="tpl-form-line-small-title"></span></label>
                                        <div class="am-u-sm-9">
                                            <input type="password" class="tpl-form-input" id="password" placeholder="请输入6位银行卡密码">
                                            <small></small>
                                        </div>
                                    </div>

                                    <div class="am-form-group">
                                        <label for="user-name" class="am-u-sm-3 am-form-label">确认密码 <span class="tpl-form-line-small-title"></span></label>
                                        <div class="am-u-sm-9">
                                            <input type="password" class="tpl-form-input" id="confirmPwd" placeholder="请输入6位银行卡密码">
                                            <small></small>
                                        </div>
                                    </div>

                                    

                                    <div class="am-form-group">
                                        <div class="am-u-sm-9 am-u-sm-push-3">
                                            <button type="button" class="am-btn am-btn-primary tpl-btn-bg-color-success " onclick="openaccount();">开户</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                    
                </div>
 
                </div>

            </div>
        </div>
        
         <div class="am-modal am-modal-alert" tabindex="-1" id="my-alert">
		  <div class="am-modal-dialog">
		    <div class="am-modal-hd">开户</div>
		    <div class="am-modal-bd" id="successAlet">
		      	卡号
		    </div>
		    <div class="am-modal-footer">
		      <span class="am-modal-btn">确定</span>
		    </div>
		  </div>
		</div>
        
        <div class="am-modal am-modal-no-btn" tabindex="-1" id="your-modal">
		  <div class="am-modal-dialog">
		    <div class="am-modal-hd">
		      <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
		    </div>
		    <div class="am-modal-bd" id="errorAlert">
		      	操作成功
		    </div>
		  </div>
		</div>
    
    <jsp:include page="common/foot.jsp"></jsp:include>
    
    <script type="text/javascript">
    
    	$(document).ready(function() {
    		$('ul li a').removeClass('active');
    		$('#openaccountHref').addClass("active");
    		$('#my-alert').on('close.modal.amui', function() {
    			window.location.href="/card/toUsercenter.do";
    		});
    	});
    	
    	function upload() {
    		$('#avatarForm').submit();
    	}
    	
    	function openaccount() {
    		alert("开始");
    		var param={
        			password:$('#password').val(),
    				confirmPwd:$('#confirmPwd').val()
			};
    		$.post('/card/openAccount.do',param,callback);
    	}
    	function callback(data,status){
			alert("点击");
			var ajaxDAO=data;
			if(ajaxDAO.success){
				$('#successAlet').html(ajaxDAO.data.number);
				$('#my-alert').modal('toggle');
				}
			else{
				$('#errorAlert').html(data.message);
        		$('#your-modal').modal('toggle');
				}
			}
    	
    	function loadAvatar() {
			$('#picture').attr('src',"/resources/show/${user.username}?"+new Date().getTime());	
    	}
    	</script>

    	</body>
	<iframe name="avatarFrame" style="display: none;">
	</iframe>
    	</html>
    	