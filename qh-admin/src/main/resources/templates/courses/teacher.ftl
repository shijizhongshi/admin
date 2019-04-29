<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="课程管理页面"/>
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/course/teacher.js"></script>
<script src="/scripts/admin.js"></script>

<@b.body menu="sidebarmenu-course" submenu="sidebarmenu-course-teacher">

<div ng-controller="teacherController">
	<div class="details" style="width: 100%;">
		<div class="details-nav">
			<ul>
				<li><img src="/images/sjk-home.png" style="color: red;" />我的主页</li>
				<li>/</li>
				<li>网课资源管理</li>
				<li>/</li>
				<li>教师管理</li>
			</ul>
		</div>
		<div class="details-frame">
			<div class="details-frame-content">

				<div class="select-3" style="width: 15%;margin-right:0%">
					<span>教师名称</span>
					<form id="search">
						<input type="text" ng-model="teacherName" placeholder="请输入教师名称" />
					</form>
				</div>
				<div>
					<input type="button" class="btn-lg im-key" value="立即检索"
						ng-click="teacherBases()" />
				</div>
			</div>
			<div class="manage">
				<ul class="show">

					<li ng-click="add()" style="background: #9DE879;"><span
						class="glyphicon glyphicon-plus"></span>&nbsp;添加教师</li>
					<li ng-click="update()" style="background: #F9CD33;"><span
						class="glyphicon glyphicon-pencil"></span>&nbsp;修改教师</li>
					<li ng-click="deleteTeacher()" style="background: #F86846;"><span
						class="glyphicon glyphicon-trash"></span>&nbsp;删除教师</li>
					<li ng-click="Knowledgepmove(1)"><span class="glyphicon glyphicon-sort" class="move-up"></span>&nbsp;上移</li>
					<li ng-click="Knowledgepmove(2)"><span class="glyphicon glyphicon-sort-by-attributes"
						class="move-down"  ></span>&nbsp;下移</li>
					<li style="float: right; margin-right: 20px; background: none;"><img
						src="/images/sjk-f5.png" name="changyi" ng-click="reset()" /></li>
				</ul>
				<div class="admin-table">

					<table>
						<tr>
							<th>教师图片</th>
							<th>教师名称</th>
							<th>是否显示</th>
						</tr>
						<tr ng-repeat="t in teacherlist" ng-click="checkedTeacher(t)"
							ng-class="{'selected':selected==t}">
							<th><img ng-src="{{t.imgUrl}}"
								style="width: 50px; height: 30px;"></th>
							<th>{{t.name}}</th>

							<th ng-show="{{t.isshow==1}}">是</th>
							<th ng-show="{{t.isshow==0}}">否</th>
						</tr>
					</table>
					<div class="col-sm-6"></div>
					<div class="col-sm-6">
						<ul uib-pagination boundary-links="true" total-items="total"
							ng-model="page" items-per-page="pageSize" max-size="5"
							class="pagination-sm" previous-text="&lsaquo;"
							next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
							ng-click="teacherBases()">
						</ul>
					</div>
				</div>


				<!--弹窗-->
				<div class="poop" id="add">
					<form id="myform">
						<h3>添加教师</h3>
						<div class="template-add">
							<div class="template-left" style="padding-right: 5%;">
								<div class=" select-2">
									<span>教师姓名：</span> <input type="text" ng-model="teacher.name"
										class="" placeholder="请输入教师名称" style="width: 230px;" />
								</div>
								<div class="select-radio ">
									<ul>
										<li>是否显示</li>
										<li><input type="radio" ng-model="teacher.isshow"
											ng-value="1" /> 显示</li>
										<li><input type="radio" ng-model="teacher.isshow"
											ng-value="0" />不显示</li>
									</ul>
								</div>
								<div class="select-radio ">
									<ul>
										<li>是否推荐</li>
										<li><input type="radio" ng-model="teacher.isremmend"
											ng-value="1" /> 是</li>
										<li><input type="radio" ng-model="teacher.isremmend"
											ng-value="0" />否</li>
									</ul>
								</div>
								<div class="costs-uploadfile-div">
									<input type="file" name="file" id="fileField"
										onchange="angular.element(this).scope().uploadmainimage(this)"
										accept="image/*" />

									<button class="allBtn costs-marl15">老师图片</button>
									<div style="height: 130px; width: 35%; margin-top: 3px;">
										<img src="{{imgUrl}}" />
									</div>
								</div>
								<b style="margin: 5px 0; font-size: 1.5rem;">讲授课程</b>
								<!--医师药师复选框-->
								<div class="xuanzekuang">
									<dl ng-repeat="ctl in courseTypeList">
										<dt>
											<span ng-click="showCourseTypeSubclassList(ctl.id)">{{ctl.courseTypeName}}</span>
										</dt>
										<dd id="items0" ng-show="ddshow==ctl.id">
											<ul ng-model="teacher.typename">

												<li ng-repeat="sub in courseTypeSubclass"><span><input
														type="checkbox"
														ng-checked="isSelected(sub.courseTypeSubclassName)"
														ng-click="updateSelection($event,ctl.courseTypeName,sub.courseTypeSubclassName)" /></span>{{sub.courseTypeSubclassName}}</li>
											</ul>
										</dd>
									</dl>

									
								</div>


							</div>


							<div class="template-right">
								<div class="grade-text">
									<span>获奖状况</span>
									<textarea ng-model="teacher.prizes"></textarea>
								</div>
								<div class="grade-text">
									<span>讲课特点</span>
									<textarea ng-model="teacher.features"></textarea>
								</div>
								<div class="grade-text">
									<span>老师描述</span>
									<textarea ng-model="teacher.describes"></textarea>
								</div>
								<div class="grade-text">
									<span>详细介绍</span>
									<textarea ng-model="teacher.details"></textarea>
								</div>
							</div>
					</form>

					<div class="end">
						<input name="git" type="submit" ng-show="teacherId==null"
							ng-click="addteacher()" value="提交" style="background: #5ED8A9;" />
						<input name="git" type="submit" ng-show="teacherId!=null"
							ng-click="addteacher()" value="修改" style="background: #5ED8A9;" />
						<input name="esc" type="reset" value="取消" ng-click="cancel()"
							class="esc" />
					</div>









				</div>

			</div>

		</div>
	</div>
</div>
</div>
 
<style type="text/css">
.poop {
	overflow-y: scroll;
	width: 60%;
	height: 600px;
	position: absolute;
	left: 15%;
	top: 5%;
}

@media screen and (max-width: 1501px) {
	.poop {
		width: 750px;
		left: 0%;
		top: 5%
	}
}

.poop span {
	font-size: 1.5rem;
}

.template-add {
	width: 100%;
	border-top: 1px solid #F5F6F8;
	height: 80%;
	padding-top: 10px;
	margin-top: 10px;
}

.template-left, .template-right {
	width: 50%;
	float: left;
	height: auto;
	overflow: auto;
}

.template-right .grade-text {
	width: 80%;
}

.template-right .grade-text textarea {
	width: 100%;
	height: 110px;
	font-size: 1.5rem
}

position
:relative
;
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

.xuanzekuang {
	width: 60%;
	height: auto;
	text-align: center;
	background: #f8fbffba;
}

.xuanzekuang dt, .xuanzekuang ul li {
	font-size: 1.0em;
	height: 30px;
	text-align: left;
	width: 100%;
	cursor: pointer;;
}

.xuanzekuang ul li {
	font-size: 1.5rem;
}

.xuanzekuang input {text-align：left;
	width: 15px;
	height: 15px;
}

.xuanzekuang span {
	height: 30px;
	float: left;
}
</style>


</@b.body>  
</html>
