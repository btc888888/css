var table, form, currentLayer;
var gridArgs = {
	deleteUrl : null,
	updateUrl : null,
	detailUrl : null,
	addUrl : null,
	/** 重置密码 **/
	resetUrl : null,
	/** 数据主键* */
	dataId : "id",
	/** 表格标题* */
	title : null,
	/**特殊的ajax请求**/
	ajaxUrl:null,
	/**提示信息**/
	resetTitle:null

};
/**
 * 表格初始化
 * 
 * <p>
 * 使用例子，searchForm:'searchForm'，为查询表单form的属性lay-filter='searchForm',edit2对应layer-event
 * </p>
 * gridArgs_.title = '消息类别'; gridArgs_.dataId = 'id'; gridArgs_.deleteUrl = '<@path/>/msg/msgType/del.do';
 * gridArgs_.updateUrl = '<@path/>/msg/msgType/updateView.do?id=';
 * gridArgs_.detailUrl = '<@path/>/msg/msgType/detailView.do?id=';
 * gridArgs_.addUrl = '<@path/>/msg/msgType/addView.do'; gridArgs_.listUrl = '<@path/>/msg/msgType/list.do';
 * initGrid({cols : [ [{ field : 'id', title : '主键', width : 100, checkbox :
 * true }, { field : 'name', title : '名称', width : 200, sort : true }, { width :
 * 250, align : 'center', toolbar : '#barDemo' } // 这里的toolbar值是模板元素的选择器
 * ]],page:false,searchForm:'searchForm'}, {edit2:function(obj){ log(obj);
 * alert(234); }});
 * 
 * @param options
 * @param events
 *            时间表，如果要添加例如审批的事件
 * @param initGridArgs
 *            gridArgs参数，如果为空则用外面定义的全局变量gridArgs
 * @returns
 */
function initGrid(options, events, initGridArgs) {
	var gridArgs_ = null;
	if (typeof initGridArgs == 'undefined') {
		gridArgs_ = gridArgs;
	} else {
		gridArgs_ = initGridArgs;
		if(gridArgs_.dataId==null){
			gridArgs_.dataId="id";
		}
	}
	var searchFormHeight=42;
	if(options.searchForm){
		searchFormHeight=42+$('#'+options.searchForm).height();
	}
	var heightDiff=gridArgs_.heightDiff||searchFormHeight;

	var addBtn=gridArgs_.addButton||'#addBtn_';
	var addBtn2=gridArgs_.addButton2||'#addBtn_2';
	var refreshBtn=gridArgs_.refreshButton||'#refreshBtn_';
	var deleteBtn=gridArgs_.deleteButton||'#deleteBtn_';
	var ajaxBtn=gridArgs_.ajaxButton||'#ajaxBtn_';
	var resetBtn=gridArgs_.resetButton||'#resetBtn_';

	layui.use([ 'table', 'form', 'jquery' ], function() {
		table = layui.table;
		var form = layui.form;
		var layer = layui.layer;
		var $ = layui.$;
		var tableOptions = $.extend({
			id : 'maingrid',
			elem : '#maingrid' // 指定原始表格元素选择器（推荐id选择器）
			,
			height : 'full-'+heightDiff // 容器高度
			,
			request : {
				pageName : 'pageNumber' // 页码的参数名称，默认：page
				,
				limitName : 'pageSize' // 每页数据量的参数名，默认：limit
			},
			response : {
				countName : 'totalRow' // 数据总数的字段名称，默认：count
				,
				dataName : 'list' // 数据列表的字段名称，默认：data
			},
			url : gridArgs_.listUrl,
			limit:20,
			page : true
		}, options)

		// 执行渲染
		table.render(tableOptions);
		// 监听工具条
		table.on('tool(' + options.id + ')', function(obj) { // 注：tool是工具条事件名，operations是table原始容器的属性,这里设置为跟ID一样
			// lay-filter="对应的值"
			var data = obj.data; // 获得当前行数据
			var layEvent = obj.event; // 获得 lay-event 对应的值
			var tr = obj.tr; // 获得当前行 tr 的DOM对象
			if (layEvent === 'detail') { // 查看
				var id = data[gridArgs_.dataId];
				currentLayer=openDialog(gridArgs_.title + '详情', gridArgs_.detailUrl + id, false, null, null,null);
			} else if (layEvent === 'detail_') {
				currentLayer=openDialog(gridArgs_.title + '详情', gridArgs_.detailUrl + id, false, 650, 400,null);
			} else if (layEvent === 'del') { // 删除			
				layer.confirm('确认删除？', {icon: 3},function(index) {
					jQuery.post(gridArgs_.deleteUrl, {
						'ids[0]' : data[gridArgs_.dataId]
					}, function(feedback) {
						if (feedback.success) {
							obj.del(); // 删除对应行（tr）的DOM结构
						} else {
							layer.alert(feedback.msg,{icon: 2});
						}
					});
					layer.close(index);
					// 向服务端发送删除指令
				});
			} else if (layEvent === 'edit') { // 编辑
				currentLayer=openDialog('编辑' + gridArgs_.title, gridArgs_.updateUrl + data[gridArgs_.dataId], false, null, null, {
					end : function() {
						refreshData();//刷新当前页数据
					}
				});
			} else if (layEvent === 'edit_') { // 编辑
				currentLayer=openDialog('编辑' + gridArgs_.title, gridArgs_.updateUrl + data[gridArgs_.dataId], false, 650, 400, {
					end : function() {
						refreshData();//刷新当前页数据
					}
				});
			} else {
				if (typeof events != 'undefined') {

					for (evt in events) {
						var func = events[evt];
						if (layEvent == evt) {
							func.call(this, obj);
						}
					}
				}
			}
		});
		// 排序
		table.on('sort(' + tableOptions.id + ')', function(obj) { // 注：tool是工具条事件名，test是table原始容器的属性
			// 尽管我们的 table 自带排序功能，但并没有请求服务端。
			// 有些时候，你可能需要根据当前排序的字段，重新向服务端发送请求，如：
			table.reload(options.id, {
				initSort : obj // 记录初始排序，如果不设的话，将无法标记表头的排序状态。 layui 2.1.1 新增参数
				,
				where : { // 请求参数
					sortname : obj.field // 排序字段
					,
					sortorder : obj.type
				// 排序方式
				}
			});
		});
		// 查询提交
		form.on('submit(' + tableOptions.searchForm + ')', function(data) {
			var data = jQuery(data.elem).formToArray();
			var params = {};
			for ( var k in data) {
				if (data[k]) {
					params[data[k].name] = data[k].value;
				} else {
					params[data[k].name] = null;
					delete params[data[k].name];
				}
			}
			table.reload(tableOptions.id, {
				where : params
			});
			event.preventDefault();
			event.stopPropagation();
			return false;
		});
		$('#' + tableOptions.searchForm).bind('keydown', function(event) {
			if (event.keyCode == 13) {
				var params = {};
				var data = $(this).formToArray(true);
				var data = new Array();
				for ( var k in data) {
					if (data[k]) {
						params[data[k].name] = data[k].value;
					} else {
						params[data[k].name] = null;
						delete params[data[k].name];
					}
				}
				table.reload(tableOptions.id, {
					where : params
				});
				event.preventDefault();
				event.stopPropagation();
				return false;
			}
		});
		$('button[search]').click(function() {
			var searchId = $(this).attr('search');
			log(searchId);
			var keyDownEvt = $.Event('keydown', {
				keyCode : 13
			});
			$('#' + searchId).trigger(keyDownEvt);
		});
		// 添加
		$(addBtn).click(function() {
			currentLayer=openDialog('添加' + gridArgs_.title, gridArgs_.addUrl, false, null, null, {
				end : function() {
					var button = $(".layui-laypage-btn");
					if (button.length > 0) {
						refreshData();
					} else {
						table.reload(options.id, {});
					}
				}
			});
		});
		
		$(addBtn2).off('click');
		$(addBtn2).click(function() {			
			currentLayer=openDialog('添加' + gridArgs_.title, gridArgs_.addUrl, false, 650, 400, {
				end : function() {
					var button = $(".layui-laypage-btn");
					if (button.length > 0) {
						refreshData();
					} else {
						table.reload(options.id, {});
					}
				}
			});
		});
//		$('#'+tableOptions.searchForm).removeAttr('action');
		// 刷新
		$(refreshBtn).click(function() {
			var keyDownEvt = $.Event('keydown', {
				keyCode : 13
			});
				
			var button = $(".layui-laypage-btn");
			if (button.length > 0) {
				refreshData();
			} 	
			$('#' + tableOptions.searchForm).trigger(keyDownEvt);			
		});
		// 删除多条
		$(deleteBtn).click(function() {
			var checkStatus = table.checkStatus(options.id); // test即为基础参数id对应的值

			if(checkStatus.data.length>0){
				layer.confirm('确认删除？', {icon: 3}, function(index) {
					var ids = [];
					for (var i = 0; i < checkStatus.data.length; i++) {
						ids[i] = checkStatus.data[i][gridArgs_.dataId];
					}
					// 向服务端发送删除指令
					jQuery.post(gridArgs_.deleteUrl, param({
						'ids' : ids
					}), function(feedback) {
						if (feedback.success) {
							refreshData();
						} else {
							layer.alert(feedback.msg,{icon: 2});
						}
					});
					layer.close(index);
				});
			}else{
				layer.alert('请选择行',{icon: 7});
			}
		});
		// 重置密码
		$(resetBtn).click(function() {
			var checkStatus = table.checkStatus(options.id); // test即为基础参数id对应的值

			if(checkStatus.data.length>0){
				layer.confirm(gridArgs_.resetTitle||'确认重置密码？', {icon: 3, title:'提示'}, function(index) {
					var ids = [];
					for (var i = 0; i < checkStatus.data.length; i++) {
						ids[i] = checkStatus.data[i][gridArgs_.dataId];
					}
					// 向服务端发送删除指令
					jQuery.post(gridArgs_.resetUrl, param({
						'ids' : ids
					}), function(feedback) {
						if (feedback.success) {
							refreshData();
							layer.msg(feedback.msg);
						} else {
							layer.alert(feedback.msg,{icon: 2});
						}
					});
					layer.close(index);
				});			
			}else{
				layer.alert('请选择行',{icon: 7});
			}
		});
		
		// 特殊的ajax请求
		$(ajaxBtn).click(function() {
			var checkStatus = table.checkStatus(options.id); // test即为基础参数id对应的值

			if(checkStatus.data.length>0){
				var ids = [];
				for (var i = 0; i < checkStatus.data.length; i++) {
					ids[i] = checkStatus.data[i][gridArgs_.dataId];
				}
				// 向服务端发送ajax指令
				jQuery.post(gridArgs_.ajaxUrl, param({
					'ids' : ids
				}), function(feedback) {
					if (feedback.success) {
						refreshData();
					} else {
						layer.alert(feedback.msg,{icon: 2});
					}
				});	
			}else{
				layer.alert('请选择行',{icon: 7});
			}
		});
		
		//时间戳的处理
		layui.laytpl.toDateString = formateDate;
		 
		//数字前置补零
		layui.laytpl.digit = digit;
	});
}
// end initGrid
// 关闭当前弹出框
function closeCurrentLayer() {
	if (currentLayer) {
		layer.close(currentLayer);
	}
}
// 重新渲染表格
function loadGrid() {
	table.reload('maingrid', {});
}
//刷新当前页数据
function refreshData(){
	$(".layui-laypage-btn").click();
}
var digit = function(num, length, end){
	  var str = '';
	  num = String(num);
	  length = length || 2;
	  for(var i = num.length; i < length; i++){
	    str += '0';
	  }
	  return num < Math.pow(10, length) ? str + (num|0) : num;
	};
function formateDate(d, format){
	  if(d ==undefined)return "";
	  var date = new Date(d || new Date())
	  ,ymd = [
	   digit(date.getFullYear(), 4),
	    digit(date.getMonth() + 1),
	    digit(date.getDate())
	  ]
	  ,hms = [
	    digit(date.getHours())
	    ,digit(date.getMinutes())
	    ,digit(date.getSeconds())
	  ];

	  format = format || 'yyyy-MM-dd HH:mm:ss';

	  return format.replace(/yyyy/g, ymd[0])
	  .replace(/MM/g, ymd[1])
	  .replace(/dd/g, ymd[2])
	  .replace(/HH/g, hms[0])
	  .replace(/mm/g, hms[1])
	  .replace(/ss/g, hms[2]);
	};
	 