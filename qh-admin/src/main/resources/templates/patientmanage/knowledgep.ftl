<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="知识点视频管理"/>
<link rel="stylesheet" href="/styles/admin.css" />
<link rel="stylesheet" href="/styles/management.css" />
<script src="/scripts/admin.js"></script>
<script src="/scripts/system/super_admin.js"></script>
<style type="text/css">


</style>
<@b.body menu="sidebarmenu-patientmanage" submenu="sidebarmenu-patientmanage-knowledgep">
<div ng-app="app" ng-controller="superAdminController">
<div class="details" style="width: 100%;">
		<div class="details-nav">
			<ul>
				<li><img src="/images/sjk-home.png" style="color: red;" />我的主页</li>
				<li>/</li>
				<li>发布管理</li>
				<li>/</li>
				<li>知识点视频管理</li>
			</ul>
		</div>
		<div class="details-frame">
			<div class="details-frame-content">

				<div class="select-3" style="width: 15%;">
					<span>姓名</span>
					
						<input type="text"  ng-model="username" placeholder="输入姓名" />
					
				</div>
			
				
				<div class="select-3" style="width: 15%;">
					<img src="/images/sjk-xl.png" /> <span>属性</span>
			<select ng-model="categorys">
							<option  value="">查看全部</option>
						</select>
					
				</div>
						<div class="select-3" style="width: 15%;">
					<img src="/images/sjk-xl.png" /> <span>专业</span>
			<select ng-model="categorys">
							<option  value=""></option>
						</select>
					
				</div>
				<div>
					<input type="button" class="btn-lg im-key" value="立即检索" />
				</div>			
				
			</div>
			<div class="manage">
				<ul class="show">
				<li style="background:#9DE879;"><span class="glyphicon glyphicon-plus"></span>&nbsp;添加</li>
					<li ng-click="update()" style="background: #F9CD33;">
					<span class="glyphicon glyphicon-pencil"></span>&nbsp;修改</li>
					
					<li  style="background: #F86846;"><span
						class="glyphicon glyphicon-trash"></span>&nbsp;删除</li>
					
					<li style="float: right; margin-right: 20px; background: none;"><img
						src="/images/sjk-f5.png" name="changyi" ng-click="refresh()" /></li>
						
				</ul>
				<div class="admin-table">

					<table>
						<tr>
							<th>视频标题</th>
							<th>封面图</th>
							<th>专业</th>
							<th>发布人</th>
							<th>是否置顶</th>
							<th>是否同步短视频</th>
							
						</tr>
						<tr>
						<th>视频标题</th>
							<th>封面图</th>
							<th>专业</th>
							<th>发布人</th>
							<th>是否置顶</th>
							<th>是否同步短视频</th>
						</tr>
					</table>
					</div>
					<div class="col-sm-6"></div>
					<div class="col-sm-6">
						<ul uib-pagination boundary-links="true" total-items="total"
							ng-model="page" items-per-page="pageSize" max-size="5"
							class="pagination-sm" previous-text="&lsaquo;"
							next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
							ng-click="userRoleList()">
						</ul>
					</div>
					
					<!-- 添加修改账号 -->
				<div id="resource" class="resource" style="display: block;">
	<form id="myform3" class="ng-pristine ng-valid">
	<h3 style="margin-bottom:0;">{{html}}账号</h3>
	<div>
	<div style="float:left;width:49%;">
	<div class="select-2">
		<span>视频链接<i class="bitian">*</i></span>
<input type="text" placeholder="请输入链接" >
	</div>
	<div class="select-2">
		<span>视频标题<i class="bitian">*</i></span>
<input type="text" placeholder="请输入链接" >
	</div>
<div class="select-2">
		<span>发布人<i class="bitian">*</i></span>
<input type="text" placeholder="请输入链接" >
	</div>
	<div class="costs-uploadfile-div">
	<input type="file" name="file" id="fileField" onchange="angular.element(this).scope().uploadmainimage(this)" accept="image/*">
<button class="allBtn costs-marl15">封面图片</button>
		<div style="height: 130px; width: 35%; margin-top: 3px;">
		<img src="">
</div>
<p style="color:red">(图片尺寸：750*422&nbsp;图片大小1500kb以下)</p>
	</div>
	<div class="add-jie-radio">
	<span>是否置顶</span> <span> <input type="radio" value="1">是 &nbsp;<input type="radio"  value="0">否</span>
	</div>
		<div class="add-jie-radio">
	<span>是否同步短视频</span> <span> <input type="radio" value="1">是 &nbsp;<input type="radio"  value="0">否</span>
	</div>
	</div>
	<div style="width:49%;float:right;">
<div class="qx">
<p>所属专业<i class="bitian">*</i></p>

<div class="qxul">
	<ul ng-repeat="menu in menus">
	<p class="qxtit" ng-click="unfolf(menu.id)" ng-class="{'fuhao':fuhao==menu.id}">
	<span class="jian">-</span>
	<span class="jia">+</span>
	<input type="checkbox" ng-checked="isSelected(menu.names)" ng-click="updateSelection($event,menu)" style="margin-left:5px;margin-right:20px;"/>{{menu.names}}</p>
		<div class="sj" ng-show="sj==menu.id">
			<li ng-repeat="sub in menu.adminSubMenus">
			<input type="checkbox" ng-checked="isSubSelected(sub.names)" ng-click="updateSubSelection($event,sub,menu)"/>{{sub.names}}
			</li>
			<p style="width: 60%;height: 3px;border: 1px solid #e6e2e2;"></p>
		</div>
	</ul>
	</div>
</div>
	</div>
	
</div>


	<div class="end" style="margin-top:10px;">
			<input id="addbutton" name="git" type="submit" ng-show="userRoleId==null" value="添加" ng-click="insertquestionbank()"  style="background:#5ED8A9;">
			<input id="updatebutton" name="git" type="submit" ng-show="userRoleId!=null" value="修改" ng-click="updatequestionbank()" style="background:#5ED8A9;">
			<input id="esc" name="esc" type="reset" value="取消" onclick="CloseDiv3()"  class="esc">
			
		</div>



</div>




</div>

					
			</div>
</div>
</div>
</div>



<style>
.resource{width:50%;left:10%;min-width:600px;}
.resource .select-2 , .resource .select-3{width:95%;}
.qx .qxul{width:100%;background:#F6F6F6;padding:15px;border-radius:10px;border:1px solid #e3e2e2;}
.qx .qxul .qxtit{cursor: pointer;font-size:1.6rem;margin:0;}

.qx ul li{margin:5px 0;font-size:1.5rem;text-indent:4em;}
.qx ul li input{margin-right:8px;}
.poop p{font-size:1.8rem;font-weight:bold;}
.poop ul{display: flex; justify-content: space-between; flex-wrap: wrap;}
.poop ul li{padding: 3px 12px;background:#F8F8F8;border:1px solid #F3F4F4;border-radius: 20px;display: inline-block;margin-right:8px;margin-bottom:8px;}
.qxtit .jia , .qxtit .jian{float:left;display: contents;}
.fuhao{display: inline-block;padding:0 10px;font-weight: bold;font-size:1.3rem}
.qxtit .jian{display: none;font-size: 3rem;}
.qxtit .jia{font-size: 2.3rem;}
.fuhao .jia{display: none;}
.fuhao .jian{display:contents;}
.selected {
	background-color: #c1ddec
}
.end{clear: both;}
</style>

</@b.body>  
</html>