<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="考官提问管理"/>
<link rel="stylesheet" href="/styles/admin.css" />
<link rel="stylesheet" href="/styles/management.css" />
<script src="/scripts/admin.js"></script>
<script src="/scripts/student/management.js"></script>
<script src="/scripts/questionBank/examinerQuestion.js"></script>
<@b.body menu="sidebarmenu-questionBank"
submenu="sidebarmenu-questionBank-examinerQuestion">
<div ng-controller="examinerQuestionController">

	<div class="classify">
		<ul class="menu">

			<li ng-repeat="ctl in courseTypeList" class="list" ng-click="typeList(ctl.courseTypeName,ctl.id)">{{ctl.courseTypeName}}
				<ul class="items" ng-class="{'active':active==ctl.id}">
					<li ng-repeat="sub in courseTypeSubclass"
						ng-click="questionSub(ctl.courseTypeName,sub,$event)"
						ng-class="{'selected':typeSelected==sub.courseTypeSubclassName}">{{sub.courseTypeSubclassName}}</li>

				</ul>
				</li>

</ul>
	</div>


	<div class="details">
		<div class="details-nav">
			<ul>
				<li><img src="/images/sjk-home.png" />我的主页</li>
				<li>/</li>
				<li>题库管理</li>
				<li>/</li>
				<li>考官提问管理</li>
			</ul>
		</div>
		<div class="details-frame">

			<div class="manage">
				<ul class="show">

					<li style="background: #9DE879;" ng-click="add()"><span
						class="glyphicon glyphicon-plus"></span>&nbsp;添加问题</li>
					<li style="background: #F9CD33;" ng-click="update()"><span
						class="glyphicon glyphicon-pencil"></span>&nbsp;修改问题</li>
					<li style="background: #F86846;" ng-click="deletequestion()"><span
						class="glyphicon glyphicon-trash"></span>&nbsp;删除问题</li>
					<li ng-click="tojie()"><span
						class="glyphicon glyphicon-briefcase"></span>&nbsp;节内容管理</li>

				</ul>
				<div class="admin-table">

					<table id="tableExcel">

						<tbody>
							<tr>
								<th>问题</th>
								<th>答案</th>

								<th>创建时间</th>
								<th>修改时间</th>
							</tr>

							<tr ng-repeat="qc in questionlist"
								ng-click="checkquestioncate(qc)"
								ng-class="{'selected':selected==qc}">

								<th>{{qc.addtime | date:'yyyy.MM.dd HH:mm:ss'}}</th>
								<th>{{qc.updatetime | date:'yyyy.MM.dd HH:mm:ss'}}</th>
							</tr>



						</tbody>
					</table>
				</div>
				<div class="col-sm-6"></div>
				<div class="col-sm-6">
					<ul uib-pagination boundary-links="true" total-items="total"
						ng-model="page" items-per-page="pageSize" max-size="5"
						class="pagination-sm" previous-text="&lsaquo;"
						next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
						ng-click="questioncate()">
					</ul>
				</div>
				
				<!--添加修改章-->
				<div class="poop" id="add">
					<form id="myform" class="ng-pristine ng-valid">
						<h3>{{}}章</h3>
						<p style="padding: 10px 0;">专业类型：&nbsp;{{courseTypeSubclassName}}</p>
						<div class="select-2">
							<span>章名称<i class="bitian">*</i></span> <input type="text" ng-model="questionCategory.name"
								class="ng-pristine ng-untouched ng-valid ng-empty"
								placeholder="请输入章名称">
						</div>
						
						<div class="select-2">
							<span>是否显示<i class="bitian">*</i></span> <img
								src="/images/sjk-xl.png"> <select ng-model="questionCategory.isshow"
								class="ng-pristine ng-untouched ng-valid ng-empty">
								<option value="1">显示</option>
								<option value="0">不显示</option>
							</select>
						</div>

						<div class="end">
							<input name="git" type="submit" value="提交" ng-show="cateId==null" ng-click="questioncateadd()"
								style="background: #5ED8A9;"> 
							<input name="git" type="submit" value="修改" ng-show="cateId!=null"
								ng-click="updateCategory()" style="background: #5ED8A9;"
								class="ng-hide"> 
							<input name="esc" type="reset" value="取消" ng-click="reset()" class="esc">
						</div>
					</form>
				</div>

			</div>

		</div>

	</div>
</div>
</div>


</@b.body>

</html>