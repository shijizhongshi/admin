<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="试题错误信息反馈"/>
<link rel="stylesheet" href="/styles/admin.css" />
<link rel="stylesheet" href="/styles/management.css" />
<script src="/scripts/admin.js"></script>

<@b.body menu="sidebarmenu-questionBank" submenu="sidebarmenu-questionBank-feedback">

<div >
	<div class="details" style="width: 100%;">
		<div class="details-nav">
			<ul>
				<li><img src="/images/sjk-home.png" style="color: red;" />我的主页</li>
				<li>/</li>
				<li>题库资源管理</li>
				<li>/</li>
				<li>试题错误信息反馈</li>
			</ul>
		</div>
		<div class="details-frame">
			<div class="details-frame-content">

				<div class="select-3" style="width: 15%;">
					<span>学员</span>
					<form id="search">
						<input type="text"  placeholder="请输入学员名称" />
					</form>
				</div>
				<div class="select-3" style="width: 15%;">
					<span>专业</span>
					<form id="search">
						<input type="text"  placeholder="请输入专业名称" />
					</form>
				</div>
				<div class="select-3" style="width: 15%;">
					<span>课程章</span>
					<form id="search">
						<input type="text"  placeholder="请输入课程章名称" />
					</form>
				</div>
				<div class="select-3" style="width: 15%;">
					<span>试题</span>
					<form id="search">
						<input type="text"  placeholder="" />
					</form>
				</div>
				<div>
					<input type="button" class="btn-lg im-key" value="立即检索"
						ng-click="teacherBases()" />
				</div>
			</div>
			<div class="manage">
				<ul class="show">
					<li onclick="showDiv3()" style="background: #F9CD33;">
					<span class="glyphicon glyphicon-pencil"></span>&nbsp;修改试题</li>
					<li  style="background: #F9CD33;">
					<span class="glyphicon glyphicon-pencil"></span>&nbsp;查看信息</li>
					<li ng-click="deleteTeacher()" style="background: #F86846;"><span
						class="glyphicon glyphicon-trash"></span>&nbsp;删除信息</li>
					<li style="float: right; margin-right: 20px; background: none;"><img
						src="/images/sjk-f5.png" name="changyi" ng-click="reset()" /></li>
				</ul>
				<div class="admin-table">

					<table>
						<tr>
							<th>学员</th>
							<th>专业</th>
							<th>课程章</th>
							<th>试题</th>
							<th>错误分类</th>
							<th>反馈留言</th>
							<th>创建日期</th>
						</tr>
						<tr>
							<th>学员</th>
							<th>专业</th>
							<th>课程章</th>
							<th>试题</th>
							<th>错误分类</th>
							<th>反馈留言</th>
							<th>创建日期</th>
						</tr>
					</table>
					</div>
					<div class="col-sm-6"></div>
					<div class="col-sm-6">
						<ul uib-pagination boundary-links="true" total-items="total"
							ng-model="current" items-per-page="pageSize" max-size="5"
							class="pagination-sm" previous-text="&lsaquo;"
							next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
							ng-click="teacherBases()">
						</ul>
					</div>
				

<!-- 修改试题 -->
<div id="resource" class="resource" style="width:600px;min-width:600px;left:20%;">
	<form id="myform3" class="ng-pristine ng-valid">
	<h3 style="margin-bottom:0;">修改试题</h3>
	<p style="border-bottom:1px solid #999;padding-bottom:5px;"><span>试题所属节：{}</span><span style="margin-left:10%;">试题类型：{单选题}</span></p>
	<div>
	<div style="float:left;width:49%;">
	<div class="grade-text">
	<span>试题标题</span>
	<textarea ng-model="courseNofree.describes" class="ng-pristine ng-untouched ng-valid ng-empty"></textarea>
	</div>
	
	<div class="select-2">
		<span>解析视频ID<i class="bitian">*</i></span>
<input type="text" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="视频ID" >
	</div>
	<div class="select-2">
		<span>试题考点ID<i class="bitian">*</i></span>
<input type="text" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="考点ID" >
	</div></div>
	<div style="width:49%;float:right;">
	<div class="shiti">
	<ul><li style="background:#F5F6F8">试题1</li><li>试题2</li><li>试题3</li></ul>
	<p>标题</p>
	<div class="xiugaibt">
	<textarea></textarea>
	</div>
	</div>


<!-- 答案项  。试题类型是公共选项的时候调用，单选题不调用 -->
<div class="daan">
<p style="margin:10px 0px 0px 3px;"><span>答案项</span><span style="float:right;margin-right:8px;">是否正确</span></p>
<table>
<tbody>
<tr>
<td><input type="text"/></td>
<td>
<div class="dw">
<img src="/images/sjk-xl.png"><select class="ng-pristine ng-untouched ng-valid ng-empty">
			<option value="">正确</option>
			<option value="">错误</option>
		</select></div>
		</td>
</tr>


</tbody>
</table>
	</div>
	<div class="grade-text">
	<span>答案解析</span>
	<textarea ng-model="courseNofree.describes" class="ng-pristine ng-untouched ng-valid ng-empty"></textarea>
	</div>
</div>
</div>

	<div class="end" style="margin-top:10px;">
			<input name="git" type="submit" value="修改" ng-show="courseId==null" ng-click="addCourse()" style="background:#5ED8A9;">
			<input name="esc" type="reset" value="取消" onclick="CloseDiv3()" class="esc">
		</div>
</form>

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

#resource .grade-text , #resource .select-2 {width:auto;}
#resource .grade-text textarea{height:50px;}
.shiti ul {display:flex;justify-content: left;margin-bottom: 0;}
.shiti ul li{margin-right: 3px;border-radius: 7px 7px 0px 0;background:#CBD2D8;text-align: center;padding: 0 5px;}
.shiti p{padding-top: 7px;width:100%;padding-left: 2%;background: #F5F6F8;margin: 0;font-size: 1.3rem;padding-bottom: 5px;}
.xiugaibt{width:100%;background: #F5F6F8;padding: 0 2%;height: 60px;}
.xiugaibt textarea{width:100%;background:#FFFFFF;height: 53px;}
.daan table{overflow:hidden;border-radius:10px; border-collapse: collapse;text-align: center;}
.daan table tr{background:#F7F8FC;font-size:1.3rem;}
.daan table tr:nth-child(1){background:#F7F8FC;color:black;}
.daan table tr td:nth-child(1){border-right:1px solid #ebe1e1;}
.daan table tr:nth-child(1) td{}
.dw{position: relative;width: 65px;}
.dw img{position: absolute;right:0;top: 11px;width: 12px;}
.daan table tr .dw select{margin-left:0; border:none;}
.daan table tr:nth-child(2n-1){background:#F7F8FC;}
</style>

<script type="text/javascript">
	function getObject(objectId) {
		if (document.getElementById && document.getElementById(objectId)) {
			return document.getElementById(objectId);
		} else if (document.all && document.all(objectId)) {
			return document.all(objectId);
		} else if (document.layers && document.layers[objectId]) {
			return document.layers[objectId];
		} else {
			return false;
		}
	}

	function showHide(e, objname) {
		var obj = getObject(objname);
		if (obj.style.display == "none") {
			obj.style.display = "block";
			e.className = "minus";
		} else {
			obj.style.display = "none";
			e.className = "plus";
		}
	}
	$(function() {
		//实现全选反选
		$(".yi").on(
				'click',
				function() {
					$("#items0 input:checkbox").prop("checked",
							$(this).prop('checked'));
				})
		$("#items0 input:checkbox")
				.on(
						'click',
						function() {
							//当选中的长度等于checkbox的长度的时候,就让控制全选反选的checkbox设置为选中,否则就为未选中
							if ($("#items0 input:checkbox").length === $("#items0 input:checked").length) {
								$(".yi").prop("checked", true);
							} else {
								$(".yi").prop("checked", false);
							}
						})
	})
	$(function() {
		$(".yao").on(
				'click',
				function() {
					$("#items1 input:checkbox").prop("checked",
							$(this).prop('checked'));
				})
		$("#items1 input:checkbox")
				.on(
						'click',
						function() {
							if ($("#items1 input:checkbox").length === $("#items1 input:checked").length) {
								$(".yao").prop("checked", true);
							} else {
								$(".yao").prop("checked", false);
							}
						})
	})
	$(function() {
		$(".zhong").on(
				'click',
				function() {
					$("#items2 input:checkbox").prop("checked",
							$(this).prop('checked'));
				})
		$("#items2 input:checkbox")
				.on(
						'click',
						function() {
							if ($("#items2 input:checkbox").length === $("#items2 input:checked").length) {
								$(".zhong").prop("checked", true);
							} else {
								$(".zhong").prop("checked", false);
							}
						})
	})
	$(function() {
		$(".wei").on(
				'click',
				function() {
					$("#items3 input:checkbox").prop("checked",
							$(this).prop('checked'));
				})
		$("#items3 input:checkbox")
				.on(
						'click',
						function() {
							if ($("#items3 input:checkbox").length === $("#items3 input:checked").length) {
								$(".wei").prop("checked", true);
							} else {
								$(".wei").prop("checked", false);
							}
						})
	})
	$(function() {
		$(".jian").on(
				'click',
				function() {
					$("#items4 input:checkbox").prop("checked",
							$(this).prop('checked'));
				})
		$("#items4 input:checkbox")
				.on(
						'click',
						function() {
							if ($("#items4 input:checkbox").length === $("#items4 input:checked").length) {
								$(".jian").prop("checked", true);
							} else {
								$(".jian").prop("checked", false);
							}
						})
	})
</script>
</@b.body>  
</html>
