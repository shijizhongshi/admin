<#import "/layout/header.ftl" as h/>
<#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="活动推荐"/>
<link rel="stylesheet" href="/styles/admin.css" />
<link rel="stylesheet" href="/styles/management.css" />
<link rel="stylesheet" href="/styles/recommended.css" />
<script src="/scripts/admin.js"></script>
<@b.body menu="sidebarmenu-system" submenu="sidebarmenu-system-recommended">
<div class="class="details" style="width:100%;">
<div class="details-nav">
			<ul>
				<li><img src="/images/sjk-home.png" style="color: red;">我的主页</li>
				<li>/</li>
				<li>公共资源管理</li>
				<li>/</li>
				<li>活动推荐</li>
			</ul>
		</div>
<div class="details-frame">

<div style="background: white;padding:20px;overflow: hidden;min-width:990px;">
<div><p class="tit">广播推送</p>
<p class="tit">组播推送</p></div>
<div class="tsgs" style="float:left;padding-bottom:80px;">
<p class="tstitle">广播推送</p>
<div class="select-2">
<span>标题<i class="bitian">*</i></span>
<input type="text" placeholder="请输入标题"/>
</div>
<div class="grade-text">
<span>内容<i class="bitian">*</i></span>
<textarea ></textarea>
</div>
<div class="select-2">
<span>跳转连接</span>
<input type="text" placeholder="不默认打开应用"/>
</div>
<div class="select-3">
<span>加盟商注册时间</span>
<input type="date">
</div>
<div class="select-3" style="font-size: 1.6rem;width: 1%;text-align: center;">
&nbsp;<span class="glyphicon glyphicon-hand-right"></span>
</div>
<div class="select-3">
<span>&nbsp;</span>
<input type="date" >
</div>
<div class="end">
<input name="git" type="submit" value="发送"style="background: #5ED8A9;">
</div>
</div>
<!-- 组播推送 -->
<div class="tsgs" style="float:right">

<p class="tstitle">组播推送</p>
<div class=" select-2" style="width:30%;">
<span>专业：</span> 
<i style="position: absolute; right: 10px; top: 40px; display: inherit; cursor: pointer;" class="glyphicon glyphicon-search" onclick="showDiv()"> </i> <input type="text" ng-model="teacher" disabled="disabled" >
</div>
<div class="select-2">
<span>标题<i class="bitian">*</i></span>
<input type="text" placeholder="请输入标题"/>
</div>
<div class="grade-text">
<span>内容<i class="bitian">*</i></span>
<textarea ></textarea>
</div>
<div class="select-2">
<span>跳转连接</span>
<input type="text" placeholder="不默认打开应用"/>
</div>
<div class="select-3">
<span>加盟商注册时间</span>
<input type="date">
</div>
<div class="select-3" style="font-size: 1.6rem;width: 1%;text-align: center;">
&nbsp;<span class="glyphicon glyphicon-hand-right"></span>
</div>
<div class="select-3">
<span>&nbsp;</span>
<input type="date" >
</div>
<div class="end">
<input name="git" type="submit" value="发送"style="background: #5ED8A9;">
</div>
</div>

<!-- 弹窗 -->
<div class="poop"  id="add">
<p class="close" onclick="CloseDiv()">X</p>
<p>选择专业</p>
	<div class="classify" style="width:100%;">
		<ul class="menu" style="box-shadow:none;">

			<li class="list" ng-click="typeList('医师资格',1)">医师资格
				<ul class="items" ng-class="{'active':active==1}">
					<li ng-repeat="sub in courseTypeSubclass"
						ng-click="courseSub('医师资格',sub,$event)"
						ng-class="{'selected':typeSelected==sub.courseTypeSubclassName}">{{sub.courseTypeSubclassName}}</li>

				</ul>
			</li>
			<li class="list" ng-click="typeList('药师资格',2)">药师资格
				<ul class="items" ng-class="{'active':active==2}">
					<li ng-repeat="sub in courseTypeSubclass"
						ng-click="courseSub('药师资格',sub,$event)"
						ng-class="{'selected':typeSelected==sub.courseTypeSubclassName}">{{sub.courseTypeSubclassName}}</li>

				</ul>
			</li>
			<li class="list" ng-click="typeList('中医基础理论',3)">中医基础理论
				<ul class="items" ng-class="{'active':active==3}">
					<li ng-repeat="sub in courseTypeSubclass"
						ng-click="courseSub('中医基础理论',sub,$event)"
						ng-class="{'selected':typeSelected==sub.courseTypeSubclassName}">{{sub.courseTypeSubclassName}}</li>
				</ul>
			</li>
			<li class="list" ng-click="typeList('卫生资格 ',4)">卫生资格
				<ul class="items" ng-class="{'active':active==4}">
					<li ng-repeat="sub in courseTypeSubclass"
						ng-click="courseSub('卫生资格 ',sub,$event)"
						ng-class="{'selected':typeSelected==sub.courseTypeSubclassName}">{{sub.courseTypeSubclassName}}</li>

				</ul>
			</li>
			<li class="list" ng-click="typeList('健康管理师',5)">健康管理师
				<ul class="items" ng-class="{'active':active==5}">
					<li ng-repeat="sub in courseTypeSubclass"
						ng-click="courseSub('健康管理师',sub,$event)"
						ng-class="{'selected':typeSelected==sub.courseTypeSubclassName}">{{sub.courseTypeSubclassName}}</li>
				</ul>
			</li>
			<li class="list" ng-click="typeList('建筑资格',7)">建筑资格
				<ul class="items" ng-class="{'active':active==7}">
					<li ng-repeat="sub in courseTypeSubclass"
						ng-click="courseSub('建筑资格',sub,$event)"
						ng-class="{'selected':typeSelected==sub.courseTypeSubclassName}">{{sub.courseTypeSubclassName}}</li>
				</ul>
			</li>


		</ul>
	</div>
</div>

</div>
</div>
</div>
</@b.body>  
</html>