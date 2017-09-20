// JavaScript Document
(function(jQuery){
	jQuery.fn.framPanel = function(options){
		var tabinconfig = {
			toggltCls:".toggle",
			leftsize:200,
			rightsize:0,
			bottomsize:0,
			lespace: 0,
			rispace: 0
			
        }
		var opts = jQuery.extend(tabinconfig, options);
		return this.each(function(){
			var ts = jQuery(this);
			ts.css({'position':'relative'});    
			function ReSize() {
				var bodyw=jQuery(window).width();
				var bodyh=jQuery(window).height();
				var layoutw=ts.width(jQuery(window).width());
				var layouth=ts.height(jQuery(window).height());
				var top=ts.find("[posbox=top]"),
					left=ts.find("[posbox=left]"),
					right=ts.find("[posbox=right]"),
					content=ts.find("[posbox=content]"),
					bottom=ts.find("[posbox=bottom]");
				   
					if(top.length > 0){
					   top.css({width:bodyw,height:top.height(),top:0,left:0,'position':'absolute'});
					}
					if(left.length > 0){
					   left.css({width:opts.leftsize+opts.lespace,height:bodyh-top.outerHeight(true)-opts.bottomsize,top:top.outerHeight(true),left:0,'position':'absolute'});
					}
					if(right.length > 0){
					   right.css({width:opts.rightsize+opts.rispace,height:bodyh-top.outerHeight(true)-opts.bottomsize,top:top.outerHeight(true),right:0,'position':'absolute'});
					}
					if(content.length > 0){
					   content.css({width:bodyw-left.outerWidth(true)-right.outerWidth(true),height:bodyh-top.outerHeight(true)-opts.bottomsize,top:top.outerHeight(true),right:opts.rightsize,'position':'absolute'});
					}
					if(bottom.length > 0){
					   bottom.css({width:bodyw,height:opts.bottomsize,top:top.outerHeight(true)+left.height(),left:0,'position':'absolute'});
					}
				};
				ReSize();
				$(window).resize(function(){
					ReSize();
				});
				
	   })	
	}
})(jQuery);

