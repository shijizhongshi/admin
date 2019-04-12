<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>
<!DOCTYPE html>
<html lang="en">
<@h.header title="课程管理页面"/>
<script src="/scripts/admin.js"></script>
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/course/course.js"></script>
<link rel="stylesheet" href="/styles/management.css" />
<@b.body menu="sidebarmenu-course" submenu="sidebarmenu-course-course">
<div ng-controller="CourseController">

	<div class="classify">
		<ul class="menu">

			<li ng-repeat="ctl in courseTypeList" class="list" ng-click="typeList(ctl.courseTypeName,ctl.id)">{{ctl.courseTypeName}}
				<ul class="items" ng-class="{'active':active==ctl.id}">
					<li ng-repeat="sub in courseTypeSubclass"
						ng-click="courseSub(ctl.courseTypeName,sub,$event)"
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
				<li>课程管理</li>


			</ul>
		</div>
		<div class="details-frame">
			<div class="details-frame-content">

				<div class="select-3" style="width: 15%;margin-right:0%">
					<span>搜索课程</span>
					<form id="search">
						<input type="text" placeholder="请输入课程名称" ng-model="courseName" />
					</form>
				</div>
				<div>
					<input type="button" class="btn-lg im-key" ng-click="courseBases()"
						value="立即检索" />
				</div>
			</div>
			<div class="manage">
				<ul class="show">

					<li ng-click="add()" style="background: #9DE879;"><span
						class="glyphicon glyphicon-plus"></span>&nbsp;添加课程</li>
					<li ng-click="updateCourse()" style="background: #F9CD33;"><span
						class="glyphicon glyphicon-pencil"></span>&nbsp;修改课程</li>
					<li ng-click="deleteCourse()" style="background: #F86846;"><span
						class="glyphicon glyphicon-trash"></span>&nbsp;删除课程</li>
						<li style="width: 90px;" ng-click="coursemove(1)"><span class="glyphicon glyphicon-sort"></span>&nbsp;上移</li>
				<li style="width: 90px;" ng-click="coursemove(2)"><span
					class="glyphicon glyphicon-sort-by-attributes"></span>&nbsp;下移</li>
					<li ng-click="chapter()"><span
						class="glyphicon glyphicon-briefcase"></span>&nbsp;资源章节管理</li>
					<li ng-click="goBuyCourse()"><span class="glyphicon glyphicon-sort-by-attributes"
						class="move-down"></span>&nbsp;学员列表</li>
					<li ng-click="removeStudent()"><span class="glyphicon glyphicon-sort-by-attributes"
						class="move-down"></span>&nbsp;移除所有学员</li>
					<li style="float: right; margin-right: 20px; background: none;"><img
						src="/images/sjk-f5.png" name="changyi" ng-click="reset()" /></li>
				</ul>
				<div class="admin-table">

					<table>

						<tr>
							<th>课程名称</th>

							<th>课程总数</th>
							<th>课程价格</th>
							<th>课程折扣</th>
							<th>是否显示</th>
						</tr>


						<tr ng-repeat="c in courselist" ng-click="checkedCourse(c)"
							ng-class="{'selected':selected==c}">
							<th>{{c.courseName}}</th>
							<th>{{c.courseChapterSize}}</th>
							<th>{{c.coursePrice}}</th>
							<th>{{c.courseDiscountPrice}}</th>
							<th ng-show="{{c.courseShow==1}}">否</th>
							<th ng-show="{{c.courseShow==0}}">是</th>

						</tr>
					</table>
					<div class="col-sm-6"></div>
					<div class="col-sm-6">
						<ul uib-pagination boundary-links="true" total-items="total"
							ng-model="page" items-per-page="pageSize" max-size="5"
							class="pagination-sm" previous-text="&lsaquo;"
							next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
							ng-change="courseBases()">
						</ul>
					</div>
				</div>

				<!--弹窗-->
				<div class="poop" id="add">
					<form id="myform">
						<h3>添加课程</h3>
						<div class="flex">
                         <div style="width:20%;">
						<div class=" select-2">
						<span>课程名称</span>
							<input type="text" ng-model="course.courseName"
								placeholder="请输入课程名称" /> 
						</div>
						<div class=" select-2">
						<span>课程年份</span>
						 <input type="text" placeholder="输入课程年份" ng-model="course.courseYear" />
						</div>
						
							<div class="select-2" >
							<span>课程价格</span>
								<input type="text" ng-model="course.coursePrice" placeholder="输入课程价格" /> 
							</div>
								<div class="select-2" >
							<span>折扣价格</span>
								 <input type="text" ng-model="course.courseDiscountPrice"
									placeholder="输入折扣价格" />
							</div>

							<div class="select-radio">
							<ul>
								<li>是否展示</li> 
								<li><input type="radio" ng-model="course.courseShow" value="0" />是</li>
								<li><input type="radio" ng-model="course.courseShow" value="1" />否</li>
								<!-- <select
									ng-model="course.courseShow">
									<option ng-selected="course.courseShow==0" value="0">是</option>
									<option ng-selected="course.courseShow==1" value="1">否</option> </select> -->
								</ul>
							</div>
							<div class="select-radio">
							<ul>
								<li>是否精品</li> 
								<li><input type="radio" ng-model="course.courseExcellent" value="1" />是</li>
								<li><input type="radio" ng-model="course.courseExcellent" value="0" />否</li>
								<!--<select
									ng-model="course.courseExcellent">
									<option ng-selected="course.courseExcellent==1" value="1">是</option>
									<option ng-selected="course.courseExcellent==0" value="0">否</option>
								</select>  -->
							</ul></div>
						

						<div class="select-2">
							<img src="/images/sjk-xl.png" /> <span>选择类别</span> <select
								ng-model="course.courseResourceType">

								<option ng-selected="course.courseResourceType=='基础课' "
									value="基础课">基础课</option>
								<option ng-selected="course.courseResourceType=='实践技能' "
									value="实践技能">实践技能</option>
								<option ng-selected="course.courseResourceType=='应试技巧' "
									value="应试技巧">应试技巧</option>
								<option ng-selected="course.courseResourceType=='冲刺课' "
									value="冲刺课">冲刺课</option>
								<option ng-selected="course.courseResourceType=='习题精讲' "
									value="习题精讲">习题精讲</option>
								<option ng-selected="course.courseResourceType=='包过套餐' "
									value="包过套餐">包过套餐</option>
							</select>
						</div>

						<div class="select-2" >
							<img src="/images/sjk-xl.png" /> <span>用途</span> <select
								ng-model="course.courseUseDifference">

								<option ng-selected="course.courseUseDifference=='自用课程' "
									value="自用课程">自用课程</option>
								<option ng-selected="course.courseUseDifference=='部门共用' "
									value="部门共用">部门共用</option>
								<option ng-selected="course.courseUseDifference=='公司共用' "
									value="公司共用">公司共用</option>
								<option ng-selected="course.courseUseDifference=='他人共用' "
									value="他人共用">他人共用</option>
							</select>
						</div>

						<div class="costs-uploadfile-div">
							上传课程图片<input type="file" name="file" accept="image/*"
								value="上传课程图片"
								onchange="angular.element(this).scope().uploadmainimage(this)" />

							<div class="costs-img">
								<img src="{{courseImg}}" />
							</div>
						</div>
</div>
<!-- 新加的属性 -->
<div class="classify" style="width: 20%;">
								<p class="xiaobiaoti">选择专业</p>
								<ul class="menu" style="box-shadow: none;">

									<li ng-repeat="ctl in courseTypeList" class="list" ng-click="typeList(ctl.id)">{{ctl.courseTypeName}}
										<ul class="items" ng-class="{'active':active==ctl.id}">
									<li ng-repeat="sub in courseTypeSubclass"
										ng-click="typeSub(ctl.courseTypeName,sub)"
											ng-class="{'selected':selected==sub}">{{sub.courseTypeSubclassName}}</li>

									</ul>
									</li>


								</ul>
							</div>
							<div style="width:30%;">
								<p class="xiaobiaoti">选择章</p>
								<div class="cltab" >
									<table>

										<tbody>
											<tr>
												<th>选择</th>
												<th>章名称</th>
                                                </tr>
											<tr>
												<th><input type="checkbox"></th>
												<th>章名称</th>
                                                </tr>
										</tbody>
									</table>
								</div>
							</div>
							<div style="width:25%;">
							<p>已选章</p>
							<div style="weight: 100; height: 455px; border: 1px solid #EEEEEE; border-radius: 10px; overflow: hidden; padding: 20px 15px;">

								</div>
							</div>

</div>
						<div class="end">
							<input name="git" type="submit" value="提交"
								ng-show="courseId==null" ng-click="addCourse()"
								style="background: #5ED8A9;" /> <input name="git" type="submit"
								value="修改" ng-show="courseId!=null" ng-click="addCourse()"
								style="background: #5ED8A9;" /> <input name="esc" type="reset"
								value="取消" ng-click="cancel()" class="esc" />
						</div>
					</form>
				</div>

			</div>

		</div>
	</div>
</div>
<style type="text/css">
.poop{width:1100px;}
.details-frame-content .select-2 {
	float: left;
	margin-right: 15px;
	width: 18%;
}

@media screen and (max-width: 901px) {
	.details-frame-content .select-2 {
		width: 90%;
	}
}
.flex{display: flex; justify-content: space-between;}
.flex .select-2{width:100%;}
.cltab{weight: 100; height:auto; border: 1px solid #EEEEEE; border-radius: 10px; overflow: hidden;}
.poop table{width:100%;}
.poop table tr:nth-child(2n){background:#F3F4F6;}
.poop table tr:nth-child(2n-1){background:#FFFFFF;}
.poop table tr:nth-child(1){background:#A3AAB0;color:white;border-radius:5px;}
.poop table th{text-align: center;}
.poop table tr th:nth-child(1){border-right: 1px solid #EEEEEE;}
</style>

</@b.body>    
</html>
