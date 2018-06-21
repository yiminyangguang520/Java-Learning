/*!
 *@作者：  赵玉
 *@邮箱：  sailiy@126.com
 *@公司：  彩虹世纪文化传媒有限公司
 *@项目：  jquery.barrager.js
 */
(function ($) {
    function Barrager(dom) {
        this.canvas = dom.get(0);
        this.ctx = this.canvas.getContext("2d");
        this.msgs = new Array(300);
        this.width = screen.availWidth;
        this.height = screen.availHeight;
        this.canvas.width=this.width;
        this.canvas.height=this.height;
        this.font = "30px 黑体";
        this.ctx.font=this.font;
        this.colorArr=["Olive","OliveDrab","Orange","OrangeRed","Orchid","PaleGoldenRod","PaleGreen","PaleTurquoise","PaleVioletRed","PapayaWhip","PeachPuff","Peru","Pink","Plum","PowderBlue","Purple","Red","RosyBrown","RoyalBlue","SaddleBrown","Salmon","SandyBrown","SeaGreen","SeaShell","Sienna","Silver","SkyBlue"];
        this.interval = "";
        this.draw = function () {
            if (this.interval != "")return;
            var _this=this;
            this.interval = setInterval(function () {
                _this.ctx.clearRect(0, 0, _this.width, _this.height);
                _this.ctx.save();
                for (var i = 0; i < _this.msgs.length; i++) {
                    if (!(_this.msgs[i] == null || _this.msgs[i] == "" || typeof(_this.msgs[i]) == "undefined")) {
                        if(_this.msgs[i].L==null || typeof(_this.msgs[i].L)=="undefined"){
                            _this.msgs[i].L=_this.width;
                            _this.msgs[i].T=parseInt(Math.random() * 700);
                            _this.msgs[i].S=parseInt(Math.random() * (10 - 4) + 4);
                            _this.msgs[i].C=_this.colorArr[Math.floor(Math.random() * _this.colorArr.length)];
                        }else{
                            if(_this.msgs[i].L<-200){
                                _this.msgs[i]=null;
                            }else {
                                _this.msgs[i].L=parseInt(_this.msgs[i].L-_this.msgs[i].S);
                                _this.ctx.fillStyle =_this.msgs[i].C;
                                _this.ctx.fillText(_this.msgs[i].msg,_this.msgs[i].L,_this.msgs[i].T);
                                _this.ctx.restore();
                            }
                        }
                    }
                }
            }, 50);
        };
        this.putMsg = function (datas) {
            for (var j = 0; j < datas.length; j++) {
                for (var i = 0; i < this.msgs.length; i++) {
                    if (this.msgs[i] == null || this.msgs[i] == "" || typeof(this.msgs[i]) == "undefined") {
                        this.msgs[i] = datas[j];
                        break;
                    }
                }
            }
            this.draw();
        };
        this.clear = function () {
            clearInterval(this.interval);
            this.interval="";
            this.ctx.clearRect(0, 0, this.width, this.height);
            this.ctx.save();
            for(var i=0;i<this.msgs.length;i++){
                this.msgs[i]=null;
            }
        };
    }

    $.fn.barrager = function (para) {
        if (typeof(para)=="string") {
            try{
                var api = $(this).data('barrager_api');
                api[para].apply(api);
            }catch (e){}
        } else if (typeof para == 'object' || !para) {
            $this = $(this);
            if ($this.data('barrager_api') != null && $this.data('barrager_api') != ''){
                var api = $this.data('barrager_api');
                api.putMsg(para);
            }else{
                var api = new Barrager($this);
                $this.data('barrager_api', api);
                api.putMsg(para);
            }
        } else {
            $.error('Method ' + method + ' does not exist on jQuery.barrager');
        }
        return this;
    }
})(jQuery);