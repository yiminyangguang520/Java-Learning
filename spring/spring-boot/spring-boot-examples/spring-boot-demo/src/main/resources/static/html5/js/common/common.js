/**
 * 基础路径
 */
var BASE_API_URL = getRootPath();

var loginUrl =BASE_API_URL+"/index.html";
var registeredUrl =BASE_API_URL+"/page/registered.html";
var searchUserUrl =BASE_API_URL+"/page/search_user.html";
/**
 * 获取当前路径
 * @returns
 */

function getRootPath(){
	var strFullPath=window.document.location.href;
	var strPath=window.document.location.pathname;
	var pos=strFullPath.indexOf(strPath);
	var prePath=strFullPath.substring(0,pos);
	var postPath=strPath.substring(0,strPath.substr(1).indexOf('/')+1);
	return(prePath+postPath);
}

function getCurrentPath() {
	var currentUrl = document.location.toString();
	var index = currentUrl.lastIndexOf("/");
	return currentUrl.substring(0, index)+"/";
}

/**
 * 方法:Array.remove(dx) 功能:根据元素位置值删除数组元素. 参数:元素值 返回:在原数组上修改数组
 */
Array.prototype.remove = function(dx) {
	if (isNaN(dx) || dx > this.length) {
		return false;
	}
	for ( var i = 0, n = 0; i < this.length; i++) {
		if (this[i] != this[dx]) {
			this[n++] = this[i];
		}
	}
	this.length -= 1;
};

/**
 * 用来处理时间戳
 */
Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	};
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
};


function getType(o)
{
    var _t;
    return ((_t = typeof(o)) == "object" ? o==null && "null" || Object.prototype.toString.call(o).slice(8,-1):_t).toLowerCase();
}

/**
 * 多维数组深克隆
 * @param destination 目标数组
 * @param source 原数组
 * @returns {Array} 克隆后的新数组
 */
function arrayDeepcopy(destination,source)
{
    for(var p in source)
    {
        if(getType(source[p])=="array"||getType(source[p])=="object")
        {
            destination[p]=getType(source[p])=="array"?[]:{};
            arguments.callee(destination[p],source[p]);
        }
        else
        {
            destination[p]=source[p];
        }
    }
}

/**
 * 打印控制台日志
 * @param obj 打印信息
 */
function consolePrint(obj) {
    if (window.console) {
        window.console.log(obj);
    }
}
/**
 * 判断数组是否包含某参数
 * @param array 目标数组
 * @param param 目标参数
 */
function contains(array,param){
	for(var i=0;i<array.length;i++)	{
		if(array[i] == param)
			return true;
	}
	return false;
}


