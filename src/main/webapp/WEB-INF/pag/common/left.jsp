<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

 <div class="left-sidebar">
            <!-- 用户信息 -->
            <div class="tpl-sidebar-user-panel">
                <div class="tpl-user-panel-slide-toggleable">
                    

                    <div class="am-form-group am-form-file">
                        <div class="tpl-user-panel-profile-picture">
	                       <img src="/user/showPicture.do" alt="" width="66px" height="66px" onerror="javascript:this.src='/images/dayuan.jpg';" id='picture'>
	                    </div>
                         <form id="avatarForm" method="POST" enctype="multipart/form-data" action="/user/load.do" target="avatarFrame"><!--注意这里的target -->
                        	<input id="doc-form-file" type="file" name="upfile" onchange="upload();">
                        </form>
                    </div>
                    
                    <a href="javascript:;" class="tpl-user-panel-action-link"> <span class="am-icon-pencil"></span> 账号设置</a>
                </div>
            </div>

            <!-- 菜单 -->
            <ul class="sidebar-nav">
                
                <li class="sidebar-nav-link">
                    <a href="/user/toUsercenter.do" class="active">
                        <i class="am-icon-home sidebar-nav-link-logo"></i> 首页
                    </a>
                </li>
                <li class="sidebar-nav-link">
                    <a href="/card/toOpenAccount.do">
                        <i class="am-icon-wpforms sidebar-nav-link-logo"></i> 开户
                    </a>
                </li>
                <li class="sidebar-nav-link">
                    <a href="/card//toCheck.do">
                        <i class="am-icon-wpforms sidebar-nav-link-logo"></i> 取款
                    </a>
                </li>
                <li class="sidebar-nav-link">
                    <a href="/card/toSave.do">
                        <i class="am-icon-wpforms sidebar-nav-link-logo"></i> 存款

                    </a>
                </li>
                <li class="sidebar-nav-link">
                    <a href="/card/toTransfer.do">
                        <i class="am-icon-wpforms sidebar-nav-link-logo"></i> 转账

                    </a>
                </li>

                <li class="sidebar-nav-link">
                    <a href="/card/toList.do">
                        <i class="am-icon-bar-chart sidebar-nav-link-logo"></i> 流水
                    </a>
                </li>
                
                <li class="sidebar-nav-link">
                    <a href="/card/toChangePassword.do">
                        <i class="am-icon-bar-chart sidebar-nav-link-logo"></i> 修改密码
                    </a>
                </li>
                
                    <li class="sidebar-nav-link">
                    <a href="/card/todelete.do">
                        <i class="am-icon-bar-chart sidebar-nav-link-logo"></i> 销户
                    </a>
                </li>
                

            </ul>
        </div>
        