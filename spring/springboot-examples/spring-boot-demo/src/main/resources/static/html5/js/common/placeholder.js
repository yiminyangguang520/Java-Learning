
function readyplace(element, type, handler){
	if (element.addEventListener){
		element.addEventListener(type, handler, false);
	} else if (element.attachEvent){
		element.attachEvent("on" + type, handler);
	} else {
		element["on" + type] = handler;
	}
}
(function (window, undefined) {

var hasClass = function(elem,sClass)
{
    var sOldName=elem.className;
    if(sOldName)
    {
        sOldName=sOldName.split(' ');
        for(var i=0;i<sOldName.length;i++)
        {
            if(sOldName[i]==sClass)
            {
                return true;
            }
        }
    }
}

var addClass = function(elem,sClass)
{
    if(!hasClass(elem,sClass))
    {
        if(elem.className)
        {
            var sOldName=elem.className, blank = (sOldName != '') ? ' ' : '';
            elem.className=sOldName + blank + sClass;
        }
        else
        {
            elem.className=sClass;
        }
    }
}

var removeClass = function(elem,sClass)
{
    var sOldName=elem.className.split(' ');
    var sNewClass='';
    for(var i=0;i<sOldName.length;i++)
    {
        if(sOldName[i] && sOldName[i]!=sClass)
        {
            sNewClass+=sOldName[i]+' ';
        }
    }
    elem.className=sNewClass.replace(/(^\s+)|(\s+$)/g, '');
}

	var Place = (function() {
		return function(set){
			this.tagName    = set.tagName || "input" ;  
			this.placeattr  = set.placeattr || "placeholder" ;     
			this.isempty    = set.isempty ? true : false;
			this.focusCls   = set.focusCls || "finps";
			this.onblurCls  = set.onblurCls || "oinps";
			this.init();
		} 
    })();
	Place.prototype = {
		init : function() {	
			var elemTagName = document.getElementsByTagName(this.tagName);
			for(var i=0;i<elemTagName.length;i++){
				this.create(elemTagName[i],this.isempty);
			}		
		},
		create:function(obj,isShow){
			if (!obj.getAttribute(this.placeattr)) return;
			var getStyle = function(obj, prop) {
				if (obj.currentStyle) { //IE浏览器
					return obj.currentStyle[prop];
				} else if (window.getComputedStyle) { //W3C标准浏览器
					return document.defaultView.getComputedStyle(obj, null)[prop];
				}
				return null;
			};

			var defaultValue = obj.getAttribute(this.placeattr);
			var placeHolderCont = document.createTextNode(defaultValue);
			var REPX=/px|em|rem/;
			var pat=parseInt(getStyle(obj,"paddingTop").replace(REPX,'')) || 0;
			var par=parseInt(getStyle(obj,"paddingRight").replace(REPX,'')) || 0;
			var pab=parseInt(getStyle(obj,"paddingBottom").replace(REPX,'')) || 0;
			var pal=parseInt(getStyle(obj,"paddingLeft").replace(REPX,'')) || 0;
			
			var mat=parseInt(getStyle(obj,"marginTop").replace(REPX,'')) || 0;
			var mar=parseInt(getStyle(obj,"marginRight").replace(REPX,'')) || 0;
			var mab=parseInt(getStyle(obj,"marginBottom").replace(REPX,'')) || 0;
			var mal=parseInt(getStyle(obj,"marginLeft").replace(REPX,'')) || 0;
			
			var fontSize = getStyle(obj, 'fontSize');
			if (!obj.getAttribute("id")) {
				var idFor = "place" +Math.floor(Math.random().toString().substr(2,8));
			}else{
				var idFor =obj.getAttribute("id");
			}
			obj.setAttribute("id", idFor);
            var LabelWrap = document.createElement('label');
			    LabelWrap.setAttribute("for", idFor);
			    LabelWrap.className = 'placewrap';
                LabelWrap.style.cssText = 'font-size:'+fontSize+';position:absolute;cursor:text;text-indent:2px;z-index:5;top:'+mat+'px;left:'+mal+'px;padding:'+pat+'px '+par+'px '+pab+'px '+pal+'px;color:#A9A9A9;';
				LabelWrap.style.width = obj.offsetWidth - (parseInt(getStyle(obj, 'marginLeft'), 10)||0) + 'px';
				LabelWrap.style.width=LabelWrap.style.width=='0px'?'auto':LabelWrap.style.width;
				LabelWrap.style.lineHeight = obj.nodeName.toLowerCase() == 'textarea' ? '': ((obj.offsetHeight-pat-pab)<0?20:(obj.offsetHeight-pat-pab)) + 'px';
			var placebox=document.createElement("div");
				placebox.className="labelwraps";
				placebox.style.cssText='position:relative;visibility:visible;z-index:1;display:inline-block;';
				obj.parentNode.insertBefore(placebox,null);
				LabelWrap.appendChild(placeHolderCont);
				placebox.appendChild(LabelWrap); 
				placebox.appendChild(obj); 
				obj.removeAttribute(this.placeattr);
            LabelWrap.onclick = function() {
                obj.focus();
            };
			LabelWrap.style.display = obj.value == '' ? 'block': 'none';
			var	changeHandler = function() {
				LabelWrap.style.display = obj.value != '' ? 'none': 'block';
			};
            var that=this;
			if(!isShow){
				obj.onclick = function() {
					if(obj.value == defaultValue && obj.value != ''){
						LabelWrap.style.display ='block';
						removeClass(obj,that.onblurCls);
                        addClass(obj,that.focusCls);
					}else {
						LabelWrap.style.display ='none';
						removeClass(obj,that.onblurCls);
                        addClass(obj,that.focusCls);
					}

				}
				obj.oninput = function() {
					if(obj.value != ''){
						LabelWrap.style.display ='none';
					}else{
						LabelWrap.style.display ='block';
					}	
				}
				obj.onblur = function() {
					if(obj.value != ''){
						LabelWrap.style.display ='none';
						removeClass(obj,that.focusCls);
						addClass(obj,that.onblurCls);
					}else{
						LabelWrap.style.display ='block';
						removeClass(obj,that.focusCls);
						addClass(obj,that.onblurCls);
					}
				}
				//obj.onblur();
			}else{
				obj.onclick = function() {
					if(obj.value == defaultValue && obj.value != ''){
						removeClass(obj,that.onblurCls);
                        addClass(obj,that.focusCls);
					}else {
						removeClass(obj,that.onblurCls);
                        addClass(obj,that.focusCls);
					}

				};
				obj.onblur = function() {
					if(obj.value != ''){
						removeClass(obj,that.focusCls);
						addClass(obj,that.onblurCls);
					}else{
						removeClass(obj,that.focusCls);
						addClass(obj,that.onblurCls);
					}
				};
				if (typeof(obj.oninput) == 'object') {
					obj.addEventListener("input", changeHandler, false);
				} else {
					obj.onpropertychange = changeHandler;
				}
			};
			return this;
		}
	}
	var placeholder = function(options) { new Place(options); }
	window.placeholder = placeholder;
	return placeholder;
})(window);

readyplace(document, 'readystatechange', function(){
	if(navigator.userAgent.indexOf('MSIE')>0){
	    placeholder({focusCls:'finps',onblurCls:'oinps',isempty:true});
		placeholder({tagName:'textarea',focusCls:'ftext',onblurCls:'otext',isempty:true});
	}
})
