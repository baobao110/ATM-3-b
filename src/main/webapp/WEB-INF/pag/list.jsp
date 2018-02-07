<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
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
									<div class="widget-title am-fl">流水操作</div>
									<div class="widget-function am-fr">
										<a href="javascript:;" class="am-icon-cog"></a>
									</div>
								</div>
								<div class="widget-body am-fr">

									<form class="am-form tpl-form-line-form">


										<div class="am-form-group">
											<label for="user-phone" class="am-u-sm-3 am-form-label">银行卡
												<span class="tpl-form-line-small-title"></span>
											</label>
											<div class="am-u-sm-9">
												<select data-am-selected="{searchBox: 1}"
													style="display: none;">
													<option value="no">请选择银行卡</option>
													<option value="b">622***009</option>
													<option value="o">622***007</option>
												</select>
												<button type="button"
													class="am-btn am-btn-default am-radius"
													onclick="loadFlow();">查询</button>
													
												<button type="button"
													class="am-btn am-btn-default am-radius"
													onclick="flow();">打印</button>
											</div>

										</div>



										<div class="am-form-group">
											<label for="user-name" class="am-u-sm-3 am-form-label">密码
												<span class="tpl-form-line-small-title"></span>
											</label>
											<div class="am-u-sm-9">
												<input type="password" class="tpl-form-input" id="password"
													placeholder="请输入6位银行卡密码"> <small></small>
											</div>
										</div>


									</form>


									<div class="widget am-cf">
										<div class="widget-head am-cf">
											<div class="widget-title am-fl"></div>
											<div class="widget-function am-fr"></div>
										</div>
										<div class="widget-body  widget-body-lg am-fr">

											<table width="100%"
												class="am-table am-table-compact am-table-striped tpl-table-black "
												id="appFlow">
												<thead>
													<tr>
														<th>卡号</th>
														<th>金额</th>
														<th>备注</th>
														<th>时间</th>
													</tr>
												</thead>
												<tbody>
													<tr class="gradeX" v-for="flow in flowList"> <!-- 注意这里的for each循环 flow -->
														<td>{{flow.number}}</td>
														<td>{{flow.money}}</td>
														<td>{{flow.description}}</td>
														<td>{{flow.createtime}}</td>

													</tr>

													<!-- more data -->
												</tbody>
											</table>

											<ul class="am-pagination">
												<li><a href="###" onclick="fist();">首页 &raquo;</a></li>
												<li><a href="###" onclick="pre();">&laquo; 上一页</a></li>
												<li><a href="###" onclick="next();">下一页 &raquo;</a></li>
												<li><a href="###" onclick="tail();">尾页 &raquo;</a></li>
												<li id="fenyeInfo">1/20</li>
											</ul>

										</div>
									</div>



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
			<div class="am-modal-bd" id="successAlet">存款成功</div>
			<div class="am-modal-footer">
				<span class="am-modal-btn">确定</span>
			</div>
		</div>
	</div>
	

	<div class="am-modal am-modal-no-btn" tabindex="-1" id="your-modal">
		<div class="am-modal-dialog">
			<div class="am-modal-hd">
				<a href="javascript: void(0)" class="am-close am-close-spin"
					data-am-modal-close>&times;</a>
			</div>
			<div class="am-modal-bd" id="errorAlert">操作失败</div>
		</div>
	</div>

	<jsp:include page="common/foot.jsp"></jsp:include>
	<script src="/js/vue.js"></script>

	<script type="text/javascript">
		var currentPage = 1;
		var totalPage= 0;/*  注意这里的初始化非常重要  */

		var app = {};	/* vue的定义 */

		$(document).ready(function() {
			$('ul li a').removeClass('active');
			$('#flowHref').addClass("active");

			app = new Vue({
				el : '#appFlow',
				data : {
					flowList : []/*vue的使用数据以数组的形式保存   */
				}
			})

			loadBankCard();
			$('#my-alert').on('close.modal.amui', function() {
				window.location.href = "/user/toUserCenter.do";
			});
		});

		function loadFlow() {
			var param={
					number:$('select').val(),
	    			password:$('#password').val(),
	    			currentPage:currentPage
			};
			$.post('/card/List.do',param,callback);
		}
		
		function callback(ajaxDAO,status){
			alert("点击");
			if(ajaxDAO.success){
				var result = ajaxDAO.data;
				totalPage= ajaxDAO.data.totalPage;
				alert(totalPage);
				$('#fenyeInfo').html(currentPage + '/' + totalPage);

				app.flowList = result.object; /*注意这里的 result要点到哪里 怎么知道 DAO的信息,在这里在浏览器的 source上打断电 ,就可以看这里的信息 ,其实和ajax差不多   */
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
				$('#errorAlert').html(ajaxDAO.message);
	    		$('#your-modal').modal('toggle');
				}
			}
		
		function flow(){
			var number=$('select').val();
			var password=$('#password').val();
			window.location.href='/card/down.do?number='+number+'&&password='+password;/* 注意这里的传参数方式  */
		}

		function next() {
			if (currentPage == totalPage) {
				return false;
			}

			currentPage = parseInt(currentPage) + 1;
			loadFlow();
		}

		function pre() {

			if (currentPage == 1) {
				return false;
			}

			currentPage = parseInt(currentPage) - 1;
			loadFlow();
		}

		function fist() {
			currentPage = 1;
			loadFlow();
		}

		function tail() {
			currentPage = totalPage;
			loadFlow();
		}
	</script>


</body>

</html>
