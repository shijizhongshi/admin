<#import "/layout/header.ftl" as h/>
<#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="面授课管理"/>
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/course/face-teache.js"></script>
<script src="/scripts/admin.js"></script>

<@b.body menu="sidebarmenu-course" submenu="sidebarmenu-face-teache">
<div ng-controller="faceTeacheController">
	<div class="classify">
		<ul class="menu">

			<li ng-repeat="ctl in courseTypeList" class="list" ng-click="typeList(ctl.courseTypeName,ctl.id)">{{ctl.courseTypeName}}
				<ul class="items" ng-class="{'active':active==ctl.id}">
					<li ng-repeat="sub in courseTypeSubclass"
						ng-click="typeSub(ctl.courseTypeName,sub,$event)"
						ng-class="{'selected':typeSelected==sub.courseTypeSubclassName}">{{sub.courseTypeSubclassName}}</li>

				</ul>
			</li>
			

		</ul>
	</div>
	<div class="details">
		<div class="details-nav">
			<ul>
				<li><img src="/images/sjk-home.png" style="color: red;" />我的主页</li>
				<li>/</li>
				<li>网课资源管理</li>
				<li>/</li>
				<li>面授课管理</li>
			</ul>
		</div>
		<div class="details-frame">
			<div class="details-frame-content"
				style="height: 110px; padding-bottom: 0;">

				<div class="select-3" style="width: 10%;margin-right:15px">
					<span>面授课名称</span>
					<input type="text" placeholder="请输入面授课名称" ng-model="courseName" />
				</div>
				<div class="select-3" style="width: 10%;margin-right:15px">
					<span>教师名称</span>
					<input type="text" placeholder="请输入教师名称" ng-model="teacherNames" />
				</div>
				<div>
					<input type="button" class="btn-lg im-key" value="立即检索"
						ng-click="FaceTeacheList()" />
				</div>
			</div>
			<div class="manage">
				<ul class="show">

					<li ng-click="add()" style="background: #9DE879;"><span
						class="glyphicon glyphicon-plus"></span>&nbsp;添加面授课</li>
					<li ng-click="update()" style="background: #F9CD33;"><span
						class="glyphicon glyphicon-pencil"></span>&nbsp;修改面授课</li>
					<li ng-click="deleteFaceTeache()" style="background: #F86846;"><span
						class="glyphicon glyphicon-trash"></span>&nbsp;删除</li>
					<li ng-click="refresh()"
						style="float: right; margin-right: 20px; background: none;"><img
						src="/images/sjk-f5.png" name="changyi" /></li>
				</ul>
				<div class="admin-table">

					<table>
						<tr>
							<th>面授课名称</th>
							<th>主讲人</th>
							<th>主讲老师图片</th>
							<th>详细地址</th>
							<th>省市</th>
							<th>具体开始时间</th>
							<th>面授时间</th>
							
						</tr>

						<tr ng-repeat="ft in faceTeachelist" ng-click="checkedFaceTeache(ft)"
							ng-class="{'selected':selected==ft}">
							<th>{{ft.courseName}}</th>
							<th>{{ft.teacherName}}</th>
							<th><img ng-src="{{ft.firstImg}}" /></th>
							<th>{{ft.detailAddress}}</th>
							<th>{{ft.probablyAddress}}</th>
							<th>{{ft.startTime}}</th>
							<th>{{ft.times}}</th>
						</tr>
					</table>
					<div class="col-sm-6"></div>
					<div class="col-sm-6">
						<ul uib-pagination boundary-links="true" total-items="total"
							ng-model="page" items-per-page="pageSize" max-size="5"
							class="pagination-sm" previous-text="&lsaquo;"
							next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
							ng-click="FaceTeacheList()">
						</ul>
					</div>
				</div>




				<!--弹窗-->
				<div class="poop" id="add">
					
						<h3>面授课管理</h3>
						<div class="template-add">

							<div class="select-2">
								<span>面授课名称<i class="bitian">*</i></span> <input type="text" placeholder="输入面授课名称"
									ng-model="faceTeache.courseName"
									class="ng-pristine ng-untouched ng-valid ng-empty">
							</div>
							<div style="width: 100%; clear: both;">
									<div class=" select-2">
										<span>老师：</span> <i
											ng-click="showteacher()"
											style="position: absolute; right: 10px; top: 40px; display: inherit; cursor: pointer;"
											class="glyphicon glyphicon-search"> </i> <input type="text"
											ng-model="teacherName" disabled="disabled" />

									</div>
								</div>
							<div class="select-2" >
								<span>详细地址<i class="bitian">*</i></span> <input type="text" placeholder="输入详细地址"
									ng-model="faceTeache.detailAddress"
									class="ng-pristine ng-untouched ng-valid ng-empty">
							</div>
							<div class="select-2" >
								<span>省市<i class="bitian">*</i></span> <input type="text" placeholder="输入省市"
									ng-model="faceTeache.probablyAddress"
									class="ng-pristine ng-untouched ng-valid ng-empty">
							</div>
							<div class="select-2">
								<span>面授时间<i class="bitian">*</i></span> <input type="text" placeholder="输入面授时间"
									ng-model="faceTeache.times"
									class="ng-pristine ng-untouched ng-valid ng-empty">
							</div>
							<div class="select-2">
								<span>具体开始时间<i class="bitian">*</i></span> 
								<input type="datetime-local" ng-model="startTime" name="search" class="ng-pristine ng-untouched ng-valid ng-empty">
							</div>
							
							
						</div>
					
					<div class="end">
						<input name="git" type="submit" value="提交"
							style="background: #5ED8A9;" ng-show="id==null"
							ng-click="addFaceTeache()"> <input name="git" type="submit"
							value="修改" style="background: #5ED8A9;" ng-show="id!=null"
							ng-click="updateFaceTeache()"> <input name="esc" type="reset"
							value="取消" ng-click="cancel()" class="esc">
					</div>
				</div>
				
				
				<div id="revise" class="resource" style="width: 720px;">
					
						<div class="select-2" style="width: 100%;">
							<span>搜索老师名称</span> <input type="text" ng-model="teacherName" />
							<p ng-click="teacherList()"
								style="position: absolute; right: 10px; top: 40px; cursor: pointer;">
								<i class="glyphicon glyphicon-search"></i>搜索
							</p>
						</div>

						<div class="admin-table">
							<table>
								<tr>
									<th>教师名称</th>
									<th>教师图片</th>
									<th>所属专业</th>

								</tr>
								<tr ng-repeat="t in teacherlist"
									ng-class="{'selected':selected1==t}" ng-click="checkteacher(t)">
									<th>{{t.name}}</th>
									<th><img ng-src="{{t.imgUrl}}"
										style="width: 50px; height: 30px;" /></th>
									<th>{{t.courseTypeSubclassNames}}</th>
								</tr>
							</table>
						</div>
						<div class="col-sm-6">
							<ul uib-pagination boundary-links="true" total-items="total1"
								ng-model="page1" items-per-page="pageSize1" max-size="5"
								class="pagination-sm" previous-text="&lsaquo;"
								next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
								ng-click="teacherList()">
							</ul>
						</div>

					
					<div class="end" style="clear: both;">
						<input name="git" type="submit" value="提交" ng-click="addteacher()"
							style="background: #5ED8A9;" /> <input name="esc" type="reset"
							value="取消" ng-click="reset()" class="esc" />
					</div>

				</div>
			</div>
		</div>
	</div>

	 
	<style type="text/css">
.poop {
	width: 400px;
	height: 490px;
}

.poop .select-2 {
	width: 90%;
}

.poop .select-2 input, .poop .select-2  select {
	border-radius: 0;
}

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
