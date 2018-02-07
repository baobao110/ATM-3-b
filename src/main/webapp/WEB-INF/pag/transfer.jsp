<%-- <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>


<jsp:include page="common/head.jsp"></jsp:include>
<body>
<h1>ATM系统-交易</h1>

<form action="/card/transfer.do" method="post">
	卡号:${number}
	<input type="hidden" name="number" value="${number}"> <br>
	密码：<input type="password" name="password">	<br>
	汇款账户；<input type="text" name="InNumber"> <br>
	金额；<input type="text" name="money"> <br>
	<input type="submit" value="汇款">
</form>

</body>


<jsp:include page="common/foot.jsp"></jsp:include>
</html> --%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<body data-type="index">
    <div class="am-g tpl-g">
        <!-- 头部 -->
       <jsp:include page="common/head.jsp"></jsp:include>
        <!-- 风格切换 -->
        
       	<jsp:include page="common/left.jsp"></jsp:include>
       
        <!-- 内容区域 -->
        <div class="tpl-content-wrapper">

            <div class="row-content am-cf">
                <div class="row  am-cf">
                    
                    <div class="row">

                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <div class="widget am-cf">
                            <div class="widget-head am-cf">
                                <div class="widget-title am-fl">转账操作</div>
                                <div class="widget-function am-fr">
                                    <a href="javascript:;" class="am-icon-cog"></a>
                                </div>
                            </div>
                            <div class="widget-body am-fr">

                                <form class="am-form tpl-form-line-form">


                                <div class="am-form-group">
                                        <label for="user-phone" class="am-u-sm-3 am-form-label">银行卡 <span class="tpl-form-line-small-title"></span></label>
                                        <div class="am-u-sm-9">
                                            <select data-am-selected="{searchBox: 1}" style="display: none;" id="cardNum">
											  <option value="no">请选择银行卡</option>
											  <option value="b">622***009</option>
											  <option value="o">622***007</option>
											</select>

                                        </div>
                                    </div>

                                    <div class="am-form-group">
                                        <label for="user-name" class="am-u-sm-3 am-form-label">转入卡号 <span class="tpl-form-line-small-title"></span></label>
                                        <div class="am-u-sm-9">
                                            <input type="text" class="tpl-form-input" id="InNumber" placeholder="请输入收款卡号">
                                            <small></small>
                                        </div>
                                    </div>
                                    
                                    	<div class="am-form-group">
											<label for="user-name" class="am-u-sm-3 am-form-label">金额
												<span class="tpl-form-line-small-title"></span>
											</label>
											<div class="am-u-sm-9">
												<input type="text" class="tpl-form-input" id="amount"
													placeholder="转账金额"> <small></small>
											</div>
										</div>

                                    <div class="am-form-group">
                                        <label for="user-name" class="am-u-sm-3 am-form-label">密码 <span class="tpl-form-line-small-title"></span></label>
                                        <div class="am-u-sm-9">
                                            <input type="password" class="tpl-form-input" id="password" placeholder="请输入6位银行卡密码">
                                            <small></small>
                                        </div>
                                    </div>

                                    

                                    

                                    <div class="am-form-group">
                                        <div class="am-u-sm-9 am-u-sm-push-3">
                                            <button type="button" class="am-btn am-btn-primary tpl-btn-bg-color-success " onclick=' transfer()'>转账</button>
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
		    <div class="am-modal-hd"></div>
		    <div class="am-modal-bd" id="successAlet">
		      	转账成功
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
		      	操作失败
		    </div>
		  </div>
		</div>
   	<jsp:include page="common/foot.jsp"></jsp:include>
	<script src="/js/vue.js"></script>

	<script type="text/javascript">
	$(document).ready(function() {
		$('ul li a').removeClass('active');
		$('#depositHref').addClass("active");
		
		loadBankCard();
		$('#my-alert').on('close.modal.amui', function() {
			window.location.href="/card/toUsercenter.do";
		});
	});
	
	function transfer() {
		var param={
				number:$('#cardNum').val(),
    			password:$('#password').val(),
				money:$('#amount').val(),
				InNumber:$('#InNumber').val()
		};
		$.post('/card/transfer.do',param,callback);
	}
	
	function callback(ajaxDAO,status){
		alert("点击");
		if(ajaxDAO.success){
			$('#my-alert').modal('toggle');
		}
		else{
			$('#errorAlert').html(ajaxDAO.message);
    		$('#your-modal').modal('toggle');
			}
		}
	
	function loadBankCard() {
		$.post('/card/flow.do',{},call);
	}

	function call(ajaxDAO,status){
		alert("点击");
		var msg = '<option value="no">请选择银行卡</option>';
		var result = ajaxDAO.data;
		if(ajaxDAO.success){
			for (var i = 0; i < result.length; i++) {
				msg += '<option value="'+result[i].number+'">'+result[i].number+'</option>';
			}

			$('select').html(msg);
		}
		else{
			$('#errorAlert').html(data.message);
    		$('#your-modal').modal('toggle');
			}
		}

	</script>

	</body>

	</html>