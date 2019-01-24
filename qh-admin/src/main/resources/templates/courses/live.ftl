<#import "/layout/header.ftl" as h/> 
<#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="直播管理"/>
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/course/liveshow.js"></script>
<script src="/scripts/admin.js"></script>

<@b.body menu="sidebarmenu-course" submenu="sidebarmenu-course-live">
<div ng-controller="liveShowController">
<div class="classify">
	<ul class="menu">
	
   <li class="list" ng-click="typeList(1)" >医师资格 
      <ul class="items" ng-class="{'active':active==1}">
         <li ng-repeat="sub in courseTypeSubclass" ng-click="typeSub('医师资格',sub)">{{sub.courseTypeSubclassName}}</li>
        
      </ul>
   </li>
   <li class="list" ng-click="typeList(2)">药师资格
      <ul class="items" ng-class="{'active':active==2}">
         <li ng-repeat="sub in courseTypeSubclass" ng-click="typeSub('药师资格',sub)">{{sub.courseTypeSubclassName}}</li>
        
      </ul>
      </li>
       <li class="list" ng-click="typeList(3)" >中医基础理论
      <ul class="items" ng-class="{'active':active==3}">
         <li ng-repeat="sub in courseTypeSubclass" ng-click="typeSub('中医基础理论',sub)">{{sub.courseTypeSubclassName}}</li>
      </ul>
      </li>
       <li class="list" ng-click="typeList(4)">卫生资格 
      <ul class="items" ng-class="{'active':active==4}">
        <li ng-repeat="sub in courseTypeSubclass" ng-click="typeSub('卫生资格',sub)">{{sub.courseTypeSubclassName}}</li>
        
      </ul>
      </li>
      <li class="list"><a href="#">健康管理师</a> 
      <ul class="items" ng-class="{'active':active==5}">
         <li ng-repeat="sub in courseTypeSubclass" ng-click="typeSub('健康管理师',sub)">{{sub.courseTypeSubclassName}}</li>
      </ul>
      </li>
      
      
      
      </ul>
      </div>
<div class="details">
	<div class="details-nav">
		<ul>
			<li><img src="/images/sjk-home.png" style="color: red;"/>我的主页</li>
			<li>/</li>
		<li>直播管理</li>
		</ul>
	</div>
<div class="details-frame" >
	<div class="details-frame-content" style="height:110px;padding-bottom:0;">

	<div class="select-3" style="width:15%;">
	<span>直播名称</span>
	<form id="">
	<input type="text" ng-model="liveName"/></form>
	</div>
		<div>
					<input type="button" class="btn-lg im-key"
						value="检索" ng-click="liveBases()" />
				</div>
	</div>
<div class="manage">
	<ul class="show">

					<li ng-click="add()"
						style="background: #9DE879;"><span
						class="glyphicon glyphicon-plus"></span>&nbsp;添加</li>
					<li ng-click="update()" style="background: #F9CD33;"><span
						class="glyphicon glyphicon-pencil"></span>&nbsp;修改</li>
					<li ng-click="deleteLive()" style="background: #F86846;"><span
						class="glyphicon glyphicon-trash"></span>&nbsp;删除</li>
					<li ng-click="liveBases()" style="float: right; margin-right:20px; background: none;"><img
						src="/images/sjk-f5.png" name="changyi" /></li>
				</ul>
				<div class="admin-table">

					<table>
						<tr>
							<th>直播名称</th>
							<th>直播图片</th>
							<th>是否可见</th>
							<th>创建时间</th>

						</tr>

						<tr ng-repeat="l in livelist" ng-click="checkedlive(l)" ng-class="{'selected':selected==l}">
							<th>{{l.liveName}}</th>
							<th><img ng-src="{{l.imgUrl}}"/></th>
							<th ng-show="l.isshow==1">是</th>
							<th ng-show="l.isshow==0">否</th>
							<th>{{l.showtime}}</th>

						</tr>
					</table>

				</div>

				<div class="col-sm-6">
					<ul uib-pagination boundary-links="true" total-items="total"
						ng-model="page" items-per-page="pageSize" max-size="5"
						class="pagination-sm" previous-text="&lsaquo;"
						next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
						ng-change="liveBases()">
					</ul>
				</div>


				<!--弹窗-->
				<div class="poop" id="add">
					<form id="myform">
						<h3>直播管理</h3>
						<div class="template-add">
						
							<div class="select-2">
								<span>直播名称<i class="bitian">*</i></span> 
								<input type="text" ng-model="live.liveName" class="ng-pristine ng-untouched ng-valid ng-empty">
							</div>
							<div class="add-jie-radio" >
							<span>是否可见</span> <span>
						<input type="radio" ng-model="live.isshow" ng-value="1" />是
						&nbsp;<input type="radio" ng-model="live.isshow" ng-value="0" />否</span>
							</div>
							<div class="add-jie-radio" >
							<span>是否推荐</span> <span>
						<input type="radio" ng-model="live.isremmend" ng-value="1" />是
							&nbsp;<input type="radio" ng-model="live.isremmend" ng-value="0"/>否</span>
							</div>
					
								<div class="select-2">
								<span>直播链接</span> 
								<input type="text" ng-model="live.outLinks">
							</div>
							<div class="costs-uploadfile-div">
										课程图片<input type="file" name="file" value="上传课程图片" onchange="angular.element(this).scope().uploadmainimage(this)">
										<div class="costs-img">
											<img ng-src="{{live.imgUrl}}">
										</div>
									</div>
						</div>
					</form>
					<div class="end">
						<input name="git" type="submit" value="提交" style="background: #5ED8A9;" ng-show="liveId==null" ng-click="addLive()"> 
							<input name="git" type="submit" value="修改" style="background: #5ED8A9;" ng-show="liveId!=null" ng-click="addLive()">
							<input name="esc" type="reset" value="取消" onclick="CloseDiv();formReset()" class="esc">
					</div>
				</div>
</div>
					</div>
		</div>

 
<style type="text/css">
.poop{width: 400px;height:490px;}
.poop .select-2{width:90%;}
 .poop .select-2 input ,  .poop .select-2  select {border-radius:0;}

div.costs-uploadfile-div {
	position: relative;
}

div.costs-uploadfile-div #textfield {
	width: 40%;
	height: 30px;
}

div.costs-uploadfile-div #fileField {
	width: 100%;
	height: 30px;
	position: absolute;
	top: 0;
	left: 0;
	filter: alpha(opacity : 0);
	opacity: 0;
}

div.costs-uploadfile-div .allBtn {
	padding: 0;
	margin: 0;
	height: 30px;
	line-height: 30px;
	width: 35%;
	background-color: #18b3cf;
	border: none;
	color: #fff;
}
</style>
</@b.body>
</div>  
</div>
</html>