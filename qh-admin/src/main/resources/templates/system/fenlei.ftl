<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="分类管理"/>
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/course/grade-template.js"></script>
<script src="/scripts/admin.js"></script>

<@b.body menu="sidebarmenu-system-fenlei"
submenu="/web/system/fenlei">
<div >
	<div class="details" style="width: 100%">
		<div class="details-nav">
			<ul>
				<li><img src="/images/sjk-home.png" style="color: red;" />我的主页</li>
				<li>/</li>
				<li>公共资源管理</li>
				<li>/</li>
				<li>分类管理</li>
			</ul>
		</div>
		<div class="details-frame">
			<div class="details-frame-content" id="details-frame-content">
<ul><li>热卖专区</li>
	<li>医疗器械</li>
	<li>母婴专场</li>
	<li>美妆个护</li>
	<li>海外精选</li>
	<li>成人用品</li>
	<li>中药养生</li>
	<li>营养保健</li>
	<li>实用百货</li>

		<ul class="add-fenlei" onclick="showDiv()"><span class="glyphicon glyphicon-plus"></span>&nbsp;添加分类</ul>	
</ul>
		
	
			</div>
			<div class="manage">
				<ul style="height: 80px;" class="show">
                     <li style="background: none;color: black;"><b>食品百货管理</b></li>
					<li onclick="showDiv()"
						style=" background: #9DE879;"><span
						class="glyphicon glyphicon-plus"></span>&nbsp;添加</li>
					<li ng-click="update()" style="background: #F9CD33;"><span
						class="glyphicon glyphicon-pencil"></span>&nbsp;修改</li>
					<li ng-click="deletetemplate()" style="background: #F86846;"><span
						class="glyphicon glyphicon-trash"></span>&nbsp;删除</li>
					<li><span class="glyphicon glyphicon-sort" class="move-up"></span>&nbsp;上移</li>
					<li><span class="glyphicon glyphicon-sort-by-attributes"
						class="move-down"></span>&nbsp;下移</li>
					<li style="float: right; margin-right: 100px; background: none;"><img
						src="/images/sjk-f5.png" name="changyi" /></li>
				</ul>
				<div class="admin-table">

					<table>
						<tr>
							<th><input type="checkbox" /></th>
							<th>电饭煲</th>
							<th>上移</th>
							<th>下移</th>
							<th>编辑</th>
							<th>删除</th>

						</tr>

						<tr>
							<th><input type="checkbox" /></th>
							<th>电饭煲</th>
							<th>上移</th>
							<th>下移</th>
							<th>编辑</th>
							<th>删除</th>
						</tr>
						<tr>
							<th><input type="checkbox" /></th>
							<th>电饭煲</th>
							<th>上移</th>
							<th>下移</th>
							<th>编辑</th>
							<th>删除</th>
						</tr>
					</table>

				</div>
				<div class="col-sm-6"></div>
				<div class="col-sm-6">
					<ul uib-pagination boundary-links="true" total-items="total"
						ng-model="current" items-per-page="pageSize" max-size="5"
						class="pagination-sm" previous-text="&lsaquo;"
						next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
						ng-change="courseBases()">
					</ul>
				</div>


				<!--弹窗-->
				<div class="poop" id="add">
					<form id="myform">
						<h3>添加分类</h3>
						<div class="template-add">
							<div class="select-2">
								<span>分类名称</span>
								<input type="text" />
							</div>
							<div class="costs-uploadfile-div">   
	 <input type="file" name="file" id="fileField"  onchange="document.getElementById('textfield').value=this.value"  accept="image/*" /> 
 <button class="allBtn costs-marl15">封面图片</button> <input type='text' id="textfield"  /> 
    <div style="height:130px;width:40%;margin-top:3px;">
    <img style="height:130px;" /></div>
        </div>	
							</div>
					</form>
					<div class="end">
						<input name="git" type="submit" value="提交"
							style="background: #5ED8A9;" /> <input name="esc" type="reset"
							value="取消" onclick="CloseDiv();formReset()" class="esc" />
					</div>
				</div>
			</div>
		</div>



<style type="text/css">
.poop {
	overflow-y: auto;
	width: 25%; height: 400px; position: absolute; left: 25%; top: 10%; display:none;
}

.poop span {
	font-size: 1.5rem;
}

.details-frame-content{height:160px;overflow-y: scroll;padding-bottom: 0;}
.details-frame-content ul li{float: left;width:10%;height: 45px;text-align: center;line-height: 45px;margin-right: 2%;background:#F3F3F3;margin-bottom: 8px;font-size: 1.5rem;cursor: pointer;}
.details-frame-content .add-fenlei{float: left;border-radius: 20px;background:#F3F3F3; height: 30px;line-height: 30px; margin-top: 10px;font-size:1.2rem;padding:0 15px;cursor: pointer; }
.admin-table tr:nth-child(2n-1){background:#F3F4F6 !important;}

.admin-table tr:nth-child(2n){background:#FFFFFF;}
.admin-table tr th:last-child{color: red;}
.admin-table tr th{cursor:pointer;}
</style>
<script type="text/javascript">
window.onload=function(){
    var lis=document.getElementById("details-frame-content").getElementsByTagName("li");
    for(var i=0;i<lis.length;i++){
        lis[i].setAttribute("index",i);
        lis[i].onclick=function(){
            for(var i=0;i<lis.length;i++){
                if(this.getAttribute("index")==i){
                    lis[i].style.color="#fff";
                    lis[i].style.backgroundColor="#666";
                }else{
                    lis[i].style.color="";
                    lis[i].style.backgroundColor="";
                }
            }
        }
    }
}
</script>
</@b.body>

</html>
