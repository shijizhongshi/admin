<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="课程管理页面"/>
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/course/audition.js"></script>
<script src="/scripts/admin.js"></script>
<@b.body menu="sidebarmenu-course" submenu="sidebarmenu-audition">
<div ng-controller="CourseNofreeController">

	<div>
		<div class="classify">
			<ul class="menu">

				<li ng-repeat="ctl in courseTypeList" class="list"
					ng-click="typeList(ctl.courseTypeName,ctl.id)">{{ctl.courseTypeName}}
					<ul class="items" ng-class="{'active':active==ctl.id}">
						<li ng-repeat="sub in courseTypeSubclass"
							ng-click="auditionSub(ctl.courseTypeName,sub,$event)"
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
					<li>试听课程</li>
				</ul>
			</div>
			<div class="details-frame">
				<div class="details-frame-content">

					<div class="select-3" style="width: 15%; margin-right: 0%">
						<span>搜索课程</span>
						<form id="search">
							<input ng-model="courseName" placeholder="请输入课程名称" type="text"
								style="text-indent: 2em;" />
						</form>
					</div>
					<div class="select-3"
						style="width: 15%; margin-right: 0%; margin-left: 20px">
						<span>搜索教师</span>
						<form id="">
							<input ng-model="teachers" placeholder="请输入教师名称" type="text"
								style="text-indent: 2em;" />
						</form>
					</div>
					<div>
						<input type="button" class="btn-lg im-key"
							ng-click="auditionBases()" value="立即检索" />
					</div>
				</div>
				<div class="manage">
					<ul class="show">

						<li ng-click="add()" style="background: #9DE879;"><span
							class="glyphicon glyphicon-plus"></span>&nbsp;添加试听课程</li>
						<li ng-click="update()" style="background: #F9CD33;"><span
							class="glyphicon glyphicon-pencil"></span>&nbsp;修改试听课程</li>
						<li ng-click="deleteAudition()" style="background: #F86846;"><span
							class="glyphicon glyphicon-trash"></span>&nbsp;删除试听课程</li>

						<li ng-click="refresh()"
							style="float: right; margin-right: 20px; background: none;"><img
							src="/images/sjk-f5.png" name="changyi" /></li>
					</ul>
					<div class="admin-table">

						<table>
							<tr>
								<th>试听分类</th>
								<th>课程图片</th>
								<th>课程名称</th>
								<th>课程老师</th>
								<th>课程时长</th>
								<th>视频的ID</th>

								<th>是否推荐</th>
								<th>课程用途</th>
								<th>创建日期</th>
							</tr>

							<tr ng-repeat="a in auditionlist" ng-click="checkedAudition(a)"
								ng-class="{'selected':selected==a}">
								<th>{{a.courseResourceType}}</th>
								<th><img src="{{a.imgUrl}}" /></th>
								<th>{{a.courseName}}</th>
								<th>{{a.teachers}}</th>
								<th>{{a.palyTime}}</th>

								<th>{{a.videoId}}</th>
								<th>{{a.remmend}}</th>
								<th>{{a.courseUseDifference}}</th>
								<th>{{a.addtime | date:'yyyy.MM.dd HH:mm:ss'}}</th>
							</tr>
						</table>
						<div class="col-sm-6"></div>
						<div class="col-sm-6">
							<ul uib-pagination boundary-links="true" total-items="total"
								ng-model="page" items-per-page="pageSize" max-size="5"
								class="pagination-sm" previous-text="&lsaquo;"
								next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
								ng-change="auditionBases()">
							</ul>
						</div>
					</div>

				</div>
				<!--弹窗-->
				<div class="poop" id="add">
					<form id="myform">
						<h3>添加试听课程</h3>
						<span style="margin-right: 20px;">已选专业类型:</span> <span>{{courseTypeSubclassName}}</span>
						<div class="grade-add">
							<div class="grade-left" style="padding-right: 5%;">

								<div class=" select-2" style="width: 100%;">
									<span>课程名称：</span> <input type="text"
										ng-model="courseNofree.courseName" placeholder="请输入班级名称" />
								</div>

								<div style="width: 100%; height: 90px; clear: both;">
									<div class=" select-2" style="float: left;">
										<img src="/images/sjk-xl.png" /> <span>分类：</span> <select
											ng-model="courseNofree.courseResourceType">
											<option ng-selected="courseNofree.courseResourceType=='基础课' "
												value="基础课">基础课</option>
											<option
												ng-selected="courseNofree.courseResourceType=='实践技能' "
												value="实践技能">实践技能</option>
											<option
												ng-selected="courseNofree.courseResourceType=='应试技巧' "
												value="应试技巧">应试技巧</option>
											<option ng-selected="courseNofree.courseResourceType=='冲刺课' "
												value="冲刺课">冲刺课</option>
											<option
												ng-selected="courseNofree.courseResourceType=='习题精讲' "
												value="习题精讲">习题精讲</option>
											<option
												ng-selected="courseNofree.courseResourceType=='包过套餐' "
												value="包过套餐">包过套餐</option>

										</select>
									</div>
									<div class=" select-2" style="float: right;">
										<img src="/images/sjk-xl.png" /> <span>课程用途：</span> <select
											ng-model="courseNofree.courseUseDifference">
											<option
												ng-selected="courseNofree.courseUseDifference=='自用课程' "
												value="自用课程">自用课程</option>
											<option
												ng-selected="courseNofree.courseUseDifference=='部门共用' "
												value="部门共用">部门共用</option>
											<option
												ng-selected="courseNofree.courseUseDifference=='公司共用' "
												value="公司共用">公司共用</option>
											<option
												ng-selected="courseNofree.courseUseDifference=='他人共用' "
												value="他人共用">他人共用</option>

										</select>
									</div>
								</div>
								<div style="width: 100%; clear: both;">
									<div class=" select-2">

										<span>播放时长（分）：</span> <input type="text"
											ng-model="courseNofree.palyTime" placeholder="请输入时长" />
									</div>
								</div>
								<div style="width: 100%; clear: both;">
									<div class=" select-2">
										<span>老师：</span> <i
											ng-click="showteacher(courseTypeSubclassName)"
											style="position: absolute; right: 10px; top: 40px; display: inherit; cursor: pointer;"
											class="glyphicon glyphicon-search"> </i> <input type="text"
											ng-model="teacher" disabled="disabled" />

									</div>
								</div>
								<ul>
									<li>是否推荐</li>
									<li><input type="radio" name="tuijian"
										ng-model="courseNofree.isremmend" ng-value="1" />是</li>
									<li><input type="radio" name="tuijian"
										ng-model="courseNofree.isremmend" ng-value="0" />否</li>
								</ul>


							</div>


							<div class="grade-center">
								<div class="grade-text">
									<span>描述</span>
									<textarea ng-model="courseNofree.describes"></textarea>
								</div>
								<div class="costs-uploadfile-div">
									<input type="file" value="上传试听课图片"
										onchange="angular.element(this).scope().uploadmainimage(this)">
									<div style="height: 130px; margin-top: 3px;">
										<img src="{{imgUrl}}" style="height: 130px;" />
									</div>
								</div>
							</div>
							<div style="width: 100%; clear: both;">
								<div class=" select-2">

									<span>视频的videoId:</span> <input type="text"
										ng-model="courseNofree.videoId" placeholder="视频的videoId"
										style="width: 230px; text-indent: 2em;"
										ng-keyup="ccnew(section.videoId)" />
								</div>
							</div>
							<div class="costs-uploadfile-div" ng-show="ccvideo">
								<!--<b>添加试听课程</b> <input type="file" id="file" value="上传试听课视频"
									accept=".avi, .wmv, .mp4, .mp3, .mov, .flv, .mkv, .rmvb"
									onchange="angular.element(this).scope().uploadmainimage1(this)" />-->
								<div style="margin-top: 3px;">
									<iframe id="{{scriptss2}}" src="{{trustSrc()}}" frameborder="0"
										height="490" width="600"></iframe>
								</div>
							</div>
						</div>
					</form>
					<div class="end">
						<input name="git" type="submit" value="提交"
							ng-click="addAudition()" ng-if="id==null"
							style="background: #5ED8A9;" /> <input name="git" type="submit"
							value="修改" ng-click="updateAudition()" ng-if="id!=null"
							style="background: #5ED8A9;" /> <input name="esc" type="reset"
							value="取消" ng-click="reset()" class="esc" />
					</div>


				</div>



				<div id="revise" class="resource" style="width: 720px;">
					<form id="myform2">

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
									ng-class="{'selected':selected==t}" ng-click="checkteacher(t)">
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
								ng-change="courseBases()">
							</ul>
						</div>

					</form>
					<div class="end" style="clear: both;">
						<input name="git" type="submit" value="提交" ng-click="addteacher()"
							style="background: #5ED8A9;" /> <input name="esc" type="reset"
							value="取消" ng-click="reset()" class="esc" />
					</div>

				</div>




			</div>

		</div>
	</div>
	</body>
	 
	<style type="text/css">
.poop, .resource {
	overflow-y: auto;
	position: absolute;
	left: 25%;
	top: 15%;
	width: auto;
	height: 600px;
}

@media screen and (max-width: 1401px) {
	.poop, .resource {
		width: 700px;
		left: 0%;
		top: 5%
	}
}

.poop span {
	font-size: 1.5rem;
}

.poop span {
	font-size: 1.5rem;
}

.grade-add {
	width: 100%;
	border-top: 1px solid #F5F6F8;
	height: 80%;
	padding-top: 10px;
	margin-top: 10px;
}

.grade-left, .grade-center {
	width: 330px;
	float: left;
	height: auto;
	overflow: auto;
}

.grade-left ul {
	height: 50px;
	line-height: 50px;
	font-size: 2rem;
}

.grade-left ul li {
	float: left;
	margin-right: 5px;
}

.grade-left ul li:nth-child(1) {
	margin-right: 10px;
}

.grade-center .grade-text {
	width: 80%;
}

.grade-center .grade-text textarea {
	width: 100%;
	height: 160px;
	font-size: 1.5rem
}

.grade-add-bottom {
	width: 100%;
	clear: both;
}

.grade-add-bottom textarea {
	width: 100%;
	height: 250px;
	background: #EDEEF0;
	border-radius: 20px;
	text-indent: 2em;
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

</div>
</@b.body>  
</html>