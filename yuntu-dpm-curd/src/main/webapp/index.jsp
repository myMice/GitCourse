<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//将path放到页作用域中,方便使用el表达式调用
	pageContext.setAttribute("app_path", path);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 导入前端框架(jquery的引入在bootstrap之前) -->
<link rel="stylesheet" type="text/css"
	href="${app_path}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script type="text/javascript"
	src="${app_path}/static/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript"
	src="${app_path}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

</head>
<body>




	<!-- 用于修改的模态框 -->
	<div class="modal fade" id="projectInfoUpdateModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">项目编辑</h4>
				</div>
				<div class="modal-body">
					<!-- 模态框中的主题,表单 -->
					<form class="form-horizontal">
						<!-- 项目名称 -->
						<div class="form-group">
							<label for="projectName_update_static" class="col-sm-2 control-label">ProjectName</label>
							<div class="col-sm-10">
								<p class="form-control-static" id="projectName_update_static"></p>
							</div>
						</div>
						<!-- 起始时间 -->
						<div class="form-group">
							<label for="startDate_update_input" class="col-sm-2 control-label">StartDate</label>
							<div class="col-sm-10">
								<input type="date" name="piStartdate" class="form-control"
									id="startDate_update_input" placeholder="StartDate">
							</div>
						</div>
						<!-- 结束时间 -->
						<div class="form-group">
							<label for="endDate_update_input" class="col-sm-2 control-label">EndDate</label>
							<div class="col-sm-10">
								<input type="date" name="piEnddate" class="form-control"
									id="endDate_update_input" placeholder="EndDate">
							</div>
						</div>
						<!-- 申报状态 -->
						<div class="form-group">
							<label for="status_update_input" class="col-sm-2 control-label">Status</label>
							<div class="col-sm-6">
								<!-- 状态的显示和提交的值是不一样的 -->
								<select class="form-control" name="piStatus">
									<option value="0">已申报</option>
									<option value="1">审核中</option>
									<option value="2">已审核</option>
								</select>
							</div>
						</div>
						<!-- 申报人列表 -->
						<div class="form-group">
							<label for="acid_update_input" class="col-sm-2 control-label">Applicant</label>
							<div class="col-sm-6">
								<!-- 内容是查询出来的名字，提交的值是申报人的acid的编号 -->
								<select class="form-control" name="acid">
								</select>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						id="projectInfo_update_btn">保存</button>
				</div>
			</div>
		</div>
	</div>

	

	<!-- 用于添加的模态框 -->
	<div class="modal fade" id="projectInfoAddModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">项目申报</h4>
				</div>
				<div class="modal-body">
					<!-- 模态框中的主题,表单 -->
					<form class="form-horizontal">
						<!-- 项目名称 -->
						<div class="form-group">
							<label for="projectName_add_input" class="col-sm-2 control-label">ProjectName</label>
							<div class="col-sm-10">
								<input type="text" name="piProjectname" class="form-control"
									id="projectName_add_input" placeholder="ProjectName">
								<!-- 显示错误提示 -->
								<span class="help-block"></span>
							</div>
						</div>
						<!-- 起始时间 -->
						<div class="form-group">
							<label for="startDate_add_input" class="col-sm-2 control-label">StartDate</label>
							<div class="col-sm-10">
								<input type="date" name="piStartdate" class="form-control"
									id="startDate_add_input" placeholder="StartDate">
							</div>
						</div>
						<!-- 结束时间 -->
						<div class="form-group">
							<label for="endDate_add_input" class="col-sm-2 control-label">EndDate</label>
							<div class="col-sm-10">
								<input type="date" name="piEnddate" class="form-control"
									id="endDate_add_input" placeholder="EndDate">
							</div>
						</div>
						<!-- 申报状态 -->
						<div class="form-group">
							<label for="status_add_input" class="col-sm-2 control-label">Status</label>
							<div class="col-sm-6">
								<!-- 状态的显示和提交的值是不一样的 -->
								<select class="form-control" name="piStatus">
									<option value="0">已申报</option>
									<option value="1">审核中</option>
									<option value="2">已审核</option>
								</select>
							</div>
						</div>
						<!-- 申报人列表 -->
						<div class="form-group">
							<label class="col-sm-2 control-label">Applicant</label>
							<div class="col-sm-6">
								<!-- 内容是查询出来的名字，提交的值是申报人的acid的编号 -->
								<select class="form-control" name="acid">
								</select>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						id="projectInfo_save_btn">保存</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 主页内容 -->
	<div class="container">
		<!-- 标题行 -->
		<div class="row">
			<div class="col-md-12">
				<h1>YUNTU_DPM_CURD</h1>
			</div>
		</div>
		<!-- 按钮行 -->
		<div class="row">
			<!-- col-md-2：站两格 col-md-offset-10：向右偏移10个格子的位置 -->
			<div class="col-md-2 col-md-offset-10" style="margin-bottom: 10px">
				<button type="button" class="btn btn-success"
					id="projectInfo_add_btn">新增</button>
				<button type="button" class="btn btn-danger"
					id="projectInfo_delete_allbtn">删除</button>
			</div>
		</div>
		<!-- 显示表格数据 -->
		<div class="row">
			<div class="col-md-12">
				<!-- 用表格展示出数据 -->
				<table class="table table-striped table-hover"
					id="projectInfos_table">
					<!-- 列标题 -->
					<thead>
						<tr>
							<td><input type="checkbox" id="check_all" /></td>
							<td>项目编号(#)</td>
							<td>项目名称</td>
							<td>开始日期</td>
							<td>结束日期</td>
							<td>申报状态</td>
							<td>申报人</td>
							<td>性别</td>
							<td>工作年限</td>
							<td>操作</td>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
		</div>
		<!-- 显示分页信息 -->
		<div class="row">
			<!-- 文字信息 -->
			<div class="col-md-4 col-md-offset-2" id="page_info_area"></div>
			<!-- 分页导航条 -->
			<div class="col-md-6" id="page_nav_area"></div>
		</div>
	</div>

</body>

<script type="text/javascript">
	//全局变量
	var totalCount,currentPage;
	
	/* 1.页面加载完成后直接发送ajax请求，拿到分页数据 */
	$(function() {
		//页面进入直接访问第一页的数据
		to_page(1);
	});
	function to_page(pn) {
		$.ajax({
			url : "${app_path}/projectInfos",
			data : "pn=" + pn,
			type : "GET",
			success : function(result) {
				console.log(result);
				//1,解析json数据并显示员工信息
				build_projectInfo_table(result);
				//2.解析并显示分页文字信息
				build_page_info(result);
				//3.解析并显示分页文字导航条
				build_page_nav(result);
			}
		});
	}

	function build_projectInfo_table(result) {
		//0.构建表格之前,将表格中的数据全部清空，防止出现多个表格数据
		$("#projectInfos_table tbody").empty();
		//1.解析json数据,拿到其中的extend值中的pageInfo中的list集合
		var projectInfos = result.extend.pageinfo.list;
		//2.遍历
		$.each(projectInfos,function(index, item) {
			//复选框
			var checkBoxTd = $("<td><input type='checkbox' class='check_item'/></td>");
			//获取item中的每一个属性信息
			var piIDTd = $("<td></td>").append(item.piId);
			var piProjectNameTd = $("<td></td>").append(item.piProjectname);
			var piStartdateTd = $("<td></td>").append(dateFarmat("yyyy-MM-dd",new Date(item.piStartdate)));
			var piEnddateTd = $("<td></td>").append(dateFarmat("yyyy-MM-dd",new Date(item.piEnddate)));
			var piStatusTd = $("<td></td>").append(item.piStatus);
			var acNameTd = $("<td></td>").append(item.applicant.acName);
			var acSexTd = $("<td></td>").append(item.applicant.acSex == 1 ? '男' : '女');
			var workinglifeTd = $("<td></td>").append(item.applicant.workinglife);

			//编辑按钮
			var editBtn = $("<button></button>").addClass("btn btn-primary btn_sm edit_btn")
				.append($("<span></span>")).addClass("glyphicon glyphicon-pencil")
				.append("编辑");
			//为编辑按钮添加一个自定义属性,表示当前操作项的id
			editBtn.attr("edit-id",item.piId);
							
			//删除按钮
			var delBtn = $("<button></button>").addClass("btn btn-danger btn_sm delete_btn")
				.append($("<span></span>")).addClass("glyphicon glyphicon-trash")
				.append("删除");
			//为编辑按钮添加一个自定义属性,表示当前操作项的id
			delBtn.attr("del-id",item.piId);
							
			//将两个按钮拼接在一起
			var btnTd=$("<td></td>").append(editBtn).append(" ").append(delBtn);
							
			//组合
			$("<tr></tr>").append(checkBoxTd)
				.append(piIDTd)
				.append(piProjectNameTd)
				.append(piStartdateTd)
				.append(piEnddateTd)
				.append(piStatusTd)
				.append(acNameTd)
				.append(acSexTd)
				.append(workinglifeTd)
				.append(btnTd)
				.appendTo("#projectInfos_table tbody");
			});
	}
	
	//日期格式化操作
	//dateFarmat("yyyy-MM-dd hh:mm:ss",new Date(item.piStartdate))
	function dateFarmat(fmt,date)   
	{    
	var o = {   
	  "M+" : date.getMonth()+1,                 //月份   
	  "d+" : date.getDate(),                    //日   
	  "h+" : date.getHours(),                   //小时   
	  "m+" : date.getMinutes(),                 //分   
	  "s+" : date.getSeconds(),                 //秒   
	  "q+" : Math.floor((date.getMonth()+3) / 3), //季度   
	  "S"  : date.getMilliseconds()             //毫秒   
	}
	if(/(y+)/.test(fmt))   
	  fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));   
	for(var k in o)   
	  if(new RegExp("("+ k +")").test(fmt))   
	fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
	return fmt;   
	} 
	
	//2.
	function build_page_info(result) {
		//构建分页之前,进行分页元素清空
		$("#page_info_area").empty();
		//构建分页信息数据
		$("#page_info_area").append(
			"当前第"+result.extend.pageinfo.pageNum+"页，总共"+result.extend.pageinfo.pages+
			"页，共"+result.extend.pageinfo.total+"条记录"		
		);
		totalCount = result.extend.pageinfo.pages;
		currentPage = result.extend.pageinfo.pageNum;
	}
	//3.
	function build_page_nav(result) {
		//构建分页导航之前,进行分页导航元素清空
		$("#page_nav_area").empty();
		//构建ul元素
		var ul = $("<ul></ul>").addClass("pagination");
		//首页元素
		var firstPageLi=$("<li></li>").addClass("page-item").append($("<a></a>").addClass("page-link").append("首页").attr("href","#"));
		//上一页元素
		var prePageLi=$("<li></li>").addClass("page-item").append($("<a></a>").addClass("page-link").append("&laquo;").attr("href","#"));
		//判断是否还有上一页元素
		if(result.extend.pageinfo.hasPreviousPage==false){
			//给首页元素添加类样式
			firstPageLi.addClass("disabled");
			//给上一页元素添加类样式
			prePageLi.addClass("disabled");
		}else{
			firstPageLi.click(function() {
				to_page(1);
			});
			prePageLi.click(function() {
				to_page(result.extend.pageinfo.pageNum-1);
			});
		}
		//下一页元素
		var nextPageLi=$("<li></li>").addClass("page-item").append($("<a></ a>").addClass("page-link").append("&raquo;").attr("href","#"));
		//末页元素
		var lastPageLi=$("<li></li>").addClass("page-item").append($("<a></ a>").addClass("page-link").append("末页").attr("href","#"));
		//判断是否还有下一页数据
		if (result.extend.pageinfo.hasNextPage == false) {//没有上一页
			//禁用下一页和末页按钮，给首页元素添加类样式
			nextPageLi.addClass("disabled");
			lastPageLi.addClass("disabled");
		} else {
			//为下一页添加点击翻页事件
			nextPageLi.click(function() {
				to_page(result.extend.pageinfo.pageNum+1);
			});
			//为末页点击翻页事件
			lastPageLi.click(function() {
				to_page(result.extend.pageinfo.pages);
			});
		}
		
		//添加首页和上一页
		ul.append(firstPageLi).append(prePageLi);
		
		
		//遍历中间的数字
		$.each(result.extend.pageinfo.navigatepageNums,function(index,item){
			//构建li的元素对象
			var numLi = $("<li></li>").addClass("page-item").append($("<a></a>").addClass("page-link").append(item).attr("href","#"));
			//判断数字是否是当前显示的页码,如果是,添加bootstrap的选中样式效果
			if (result.extend.pageinfo.pageNum == item) {
				numLi.addClass("active")
			}
			//每一个li的点击事件,都是通过to_page函数进行ajax访问
			numLi.click(function() {
				to_page(item);
			});
			
			//循环将每一个li添加进ul中
			ul.append(numLi);
		});
		//添加下一页和末页
		ul.append(nextPageLi).append(lastPageLi);
		//将ul添加到nva导航条中
		var navElw=$("<nav></nav>").append(ul);
		//显示
		navElw.appendTo("#page_nav_area");
	}
	
	
	//添加按钮的单机事件,弹出模态框
	$("#projectInfo_add_btn").click(function() {
		//表单内容的重置(包括表单数据,表单样式)
		clear_add_from("#projectInfoAddModal form");
		//发送ajax请求,查询申报人信息,显示在项目申报的下拉列表中
		getApplicants("#projectInfoAddModal select[name=acid]");
		//模态框的弹出
		$('#projectInfoAddModal').modal({
			backdrop:'static'
		})
	})
	//表单清空
	function clear_add_from(ele) {
		//重置表单
		$(ele)[0].reset();
		//清空样式
		$(ele).find("*").removeClass("has-success has-error");
		//清空表达附加的提示信息
		$(ele).find(".help-block").text("");
	}
	//获取人	
	function getApplicants(ele) {
		//先清除下拉列表中原本的元素对象
		$(ele).empty();
		//发送ajax请求,获取所有部门
		$.ajax({
			url : "${app_path}/applicants",
			type : "GET",
			success : function (result) {
				console.log(result);
				//显示申报人信息在下拉列表中
				$.each(result.extend.applicants,function(){
					//构建列表项的内容
					var optionEle = $("<option></option>").append(this.acName).attr("value",this.acId);
					//将构建的内容列表添加在select中
					optionEle.appendTo(ele);
				});
			}
		});
	}
	
	//显示验证之后的提示信息
	function show_validate_msg(ele,status,msg) {//选择器,状态信息,展示信息
		//每次显示验证信息时,都将之前的类样式清除掉
		$(ele).parent().removeClass("has-success has-error");
		//判断状态,确定状态对应的样式
		if ("success"==status) {
			//正确,添加正确样式
			$(ele).parent().addClass("has-success");
		}else if ("error"==status){
			//错误,添加错误样式
			$(ele).parent().addClass("has-error");
		}
		//展示正确或者错误的文字信息
		$(ele).next("span").text(msg);
	}
	
	//数据校验(添加表单,前端校验)
	function validate_add_from() {
		//主要校验项目名称
		//1.拿到校验数据,使用正则表达式
		var projectName=$("#projectName_add_input").val();
		var regName=/(^[a-zA-Z0-9_-]{6,64}$)|(^[\u2E80-\u9FFF]{2,32}$)/;
		//验证
		if(!regName.test(projectName)){
			//调用验证信息的显示函数
			show_validate_msg("#projectName_add_input","error","论文名为2~32位中文,或者6~64位大小写字母,数字,下划线,横线");
			return false;
		}else{
			//调用验证信息的显示函数
			show_validate_msg("#projectName_add_input","success","");
		}
		return true;
	}
	
	//点击模态框中的保存,动态添加项目申报数据
	$("#projectInfo_save_btn").click(function() {
		//0.现要对提交给服务器的的数据进行数据校验(前端校验)
		if(!validate_add_from()){//如果校验不成功,点击保存的事件就不再执行
			return false;
		}
		//1.通过Ajax请求用户校验是否可用;如果没有才能继续保存
		if($(this).attr("ajax_valiadate_projectName")=="error"){
			return false;
		}
		//将模态框中的数据交给应用服务器,进行数据库服务器保存
		//发送Ajax请求,保存员工
		/* 
			通过jquery提供的表单序列化进行表单数据提交(这样就不用通过id选择器一个一个获取里面的值)
			而是用serialize()函数得到信息
		*/
		//console.log($("#projectInfoAddModal form").serialize());
		$.ajax({
			url : "${app_path}/projectInfo",
			type : "POST",
			data : $("#projectInfoAddModal form").serialize(),
			success : function(result){
				console.log(result)
				if(result.code == 200){//成功
					//申报项目保存
					//1.关闭模态框
					$('#projectInfoAddModal').modal('hide')
					//2.来到最后一页,显示最后一页的数据
					to_page(totalCount);
				}else{//失败
					//判断是哪一个字段失败,(假如失败的值为undefined,证明代码有错误信息)
					if(result.extend.errorFields.piProjecname!=undefined){
						show_validate_msg("#projectName_add_input","error",result.extend.errorFields.piProjecname);
					}
				}
			}
		});
	})
	//进行项目名重复验证,ajax以不请求
	$("#projectName_add_input").change(function(){
		//获取输入框的值
		var projectName = this.value;
		//如果校验的方法换回不成功,用户名重复验证就不再执行
		if(!validate_add_from()){//如果校验不成功,点击保存的事件就不再执行
			return false;
		}
		//发送ajax请求校验用户是否可用
		$.ajax({
			url : "${app_path}/checkProjectInfo",
			data : "projectName="+projectName,
			type : "POST",
			success : function(result){
				//判断返回的Msg对象中的状态码
				if(result.code == 200){//成功
					show_validate_msg("#projectName_add_input" ,"success","√ 可用");
					//通过jq对按钮进行携带属性信息(保证验证项目名重复时,点击保存不能提交)
					$("#projectInfo_save_btn").attr("ajax_valiadate_projectName","success");
				}else{
					show_validate_msg("#projectName_add_input" ,"error",result.extend.validate_msg);
					$("#projectInfo_save_btn").attr("ajax_valiadate_projectName","error");
				}
			}
		});
	});
	
	//编辑按钮的单机事件
	/* 
		加载dom元素时,此时还没有编辑按钮,直接绑定click函数无法绑定
		决解方案：动态绑定
		live()函数(已过期) on()函数(正常使用)
		on()函数绑定,绑定过在整个文档中
	*/
	$(document).on("click",".edit_btn",function(){
		//1.查询项目信息
		getProjectInfo($(this).attr("edit-id"));
		//2.查询申报人的信息
		getApplicants("#projectInfoUpdateModal select[name=acid]");
		//3.将需要更新的员工id从编辑按钮上携带给模态框用来修改时使用;然后在弹出模态框
		$("#projectInfo_update_btn").attr("edit-id",$(this).attr("edit-id"));
		
		$('#projectInfoUpdateModal').modal({
			backdrop:'static'
		})
	})
	
	//1.查询单个项目信息
	function getProjectInfo(id) {
		$.ajax({
			url:"${app_path}/projectinfo/"+id,
			type:"GET",
			success:function(result){
				console.log(result);
				//获取员工对象
				var projectInfo = result.extend.projectInfo;
				//将员工对象放到模态框中对应的位置
				$("#projectInfoUpdateModal select[name=acid]").val([parseInt(projectInfo.acid)]);
				$("#projectName_update_static").text(projectInfo.piProjectname);
				$("#startDate_update_input").val(dateFarmat("yyyy-MM-dd",new Date(projectInfo.piStartdate)));
				$("#endDate_update_input").val(dateFarmat("yyyy-MM-dd",new Date(projectInfo.piEnddate)));
				$("#projectInfoUpdateModal select[name=piStatus]").val([parseInt(projectInfo.piStatus)]);
			}
		})
	}
	
	//修改模态框中保存按钮的点击事件
	$("#projectInfo_update_btn").click(function(){
		//1.验证数据是否合法,数据校验(根据需求)
		//2.发送Ajax请求，进行员工的数据更新(以put方式请求)
		$.ajax({
			url : "${app_path}/projectinfo/"+$(this).attr("edit-id"),
			type : "PUT",
			data : $("#projectInfoUpdateModal form").serialize(),
			success : function(result){
				console.log(result);
				//返回当前页,隐藏模态框
				$("#projectInfoUpdateModal").modal("hide");
				to_page(currentPage);
			}
		})
	})
	
	//全选和全不选
	$("#check_all").click(function() {
		//将当前操作复选框的checked的属性值，作为参数
		//设置为所有子项的复选框的checked的属性值
		$(".check_item").prop("checked",$(this).prop("checked"));
	})
	//每个选中,全选按钮也选中
	$(document).on("click",".check_item",function(){
		//获取选中的状态(选中的数量是否等于复选框的总数量)
		var flag = $(".check_item:checked").length==$(".check_item").length;
		$("#check_all").prop("checked",flag);
	})
	
	//删除单个按钮的动态绑定的点击事件
	$(document).on("click",".delete_btn",function(){
		//获取删除项的项目名字
		var projectInfoName = $(this).parents("tr").find("td:eq(2)").text();
		//获取按钮上的del-id
		var delId = $(this).attr("del-id");
		//循环判断
		if(confirm("确定是否删除"+projectInfoName)){
			//发送ajax请求
			$.ajax({
				url : "${app_path}/projectinfo/"+delId,
				type : "DELETE",
				success:function(result){
					to_page(currentPage);
				}
			})
		}
	})
	
	//点击删除所选项
	$("#projectInfo_delete_allbtn").click(function(){
		//创建用于存放删除的名字
		var projectInfoNames = "";
		//创建用于存放用户项目id的字符串
		var del_ids_str="";
		//循环遍历
		$.each($(".check_item:checked"),function(){
			//将遍历的项目名称拼接到projectinfoName中,并且用“，”隔开
			projectInfoNames+=$(this).parents("tr").find("td:eq(2)").text()+",";
			//将遍历的id拼接到del_ids_str中，用户“-”隔开
			del_ids_str+=$(this).parents("tr").find("td:eq(1)").text()+"-";
		})
		//去掉最后一个“,”和“-”
		projectInfoNames=projectInfoNames.substring(0,projectInfoNames.length-1);
		del_ids_str=del_ids_str.substring(0,del_ids_str.length-1);
		//循环判断
		if(confirm("确定是否删除"+projectInfoNames)){
			//发送ajax请求
			$.ajax({
				url : "${app_path}/projectinfo/"+del_ids_str,
				type : "DELETE",
				success:function(result){
					to_page(currentPage);
				}
			})
		}
	})
	
</script>

</html>