$.fn.extend({
	createDesktop : function(option) {
		var that = $(this);
		var dop = {
			url : '',
			data : null,
			imgbasepath : '',
			btnprevId : 'btnPagePrev',
			btnnextId : 'btnPageNext',
			pagelistId : 'pagelist',
			clickfunc : null
		};

		$.extend(dop, option);
		that.data('option', dop);
		that.laodpage();
	},
	laodpage : function() {
		var that = $(this);
		var dop = that.data('option');
		$.ajax({
			url : dop.url,
			async : true,
			type : 'POST',
			data : dop.data,
			dataType : 'json',
			success : function(data) {
				that.empty();
				var basepath = dop.imgbasepath;

				$.each(data, function(index, item) {
					$('#menucontainer').append('<li class="mnuitem" id="menuitem_' + index + '"><a href="javascript:void(0);" iconid="' + item.id + '" page="' + item.indexUrl + '" systype="' + item.sysType + '" class="iconmnu" iconname="' + item.name + '" icontitle="' + item.title + '" usernameField="' + item.usernameField + '" loginpage=" ' + item.sysUrl + '" passwdField="' + item.passwdField + '"><img src="' + basepath + item.iconPath + '"><div>' + item.title + '</div></a></li>');
				});
				$('.iconmnu').click(function() {
					if (dop.clickfunc) {
						dop.clickfunc($(this));
					}
				});
				that.attr('mnuitemcount', data.length);
				that.renderpage();
			}
		});
	},
	renderpage : function() {
		var that = $(this);
		var option = that.data('option');
		var docwidth = $(window).width();
		var docHeight = $(window).height();
		
		var sizeperpage = 1;
		var mainwidth = docwidth - 400;
		var mainheight = docHeight - parseInt(that.css('top')) - 10;
		var itemwidth = $('#menuitem_0').outerWidth(true);
		var itemheight = $('#menuitem_0').outerHeight(true);		
		var cols = Math.floor(mainwidth / itemwidth);
		var rows = Math.floor(mainheight / itemheight);		 
		
		var total = parseInt(that.attr('mnuitemcount'));
		sizeperpage = cols * rows;
		if(total < sizeperpage){
			if((total % cols)==0){
				rows = total / cols; 
			}else{
				rows = Math.floor(total / cols)+1;
			}
		}		
		var w = itemwidth * cols;
		var h = itemheight * rows ;
		if(that.width() > (docwidth*0.7)){
			that.css('width', w);
			that.css('left',Math.floor((docwidth - that.width())/2));
		}else{
			that.css('left',Math.floor((docwidth - that.width())/2));
		}
		if(that.hasClass('ones')){
			rows = 1;
			sizeperpage = cols * rows;
		}else{
			that.css('height', h);
			that.css('top',Math.floor(docHeight / 2) - Math.floor(h/2));
		}
//		that.css('width', w);
//		that.css('height', h);
//		that.css('left',Math.floor(docwidth / 2) - Math.floor(w/2));
//		that.css('top',Math.floor(docHeight / 2) - Math.floor(h/2));		
		
		var pages = 1;
		that.attr('currentpage', 1);
		that.attr('sizeperpage', sizeperpage);

		if (total % sizeperpage == 0) {
			pages = parseInt(total / sizeperpage);
		} else {
			pages = parseInt(total / sizeperpage) + 1;
		}

		that.attr('pages', pages);
		if (pages > 1) {
			$('#' + option.pagelistId).empty();
			for (var i = 0; i < pages; i++) {
				$('#' + option.pagelistId).append('<li><div class="pageitem" id="pageitem_' + (i + 1) + '"></div></li>')
			}
			$('#pageitem_1').addClass('cur');
			$('#' + option.pagelistId).parent().show();
			$('#' + option.btnprevId).show();
			$('#' + option.btnnextId).show();

			$('#' + option.btnprevId).unbind('click').bind('click', function() {
				var curpage = parseInt(that.attr('currentpage'));
				var pages = parseInt(that.attr('pages'));
				if (curpage == 1) {
					curpage = pages;
				} else {
					curpage--;
				}
				showPageContent(curpage);
			});

			$('#' + option.btnnextId).unbind('click').bind('click', function() {
				var curpage = parseInt(that.attr('currentpage'));
				var pages = parseInt(that.attr('pages'));
				if (curpage == pages) {
					curpage = 1;
				} else {
					curpage++;
				}
				showPageContent(curpage);
			});

			showPageContent(1);

			function showPageContent(page) {
				$('.pageitem').removeClass('cur');
				$('#pageitem_' + page).addClass('cur');
				var pagesize = parseInt(that.attr('sizeperpage'));
				that.attr('currentpage', page);
				$('.mnuitem').hide();
				for (var i = (page - 1) * pagesize; i < page * pagesize; i++) {
					$('#menuitem_' + i).show();
				}
			}
		} else {
			$('#' + option.pagelistId).parent().hide();
			$('#' + option.btnprevId).hide();
			$('#' + option.btnnextId).hide();
		}
	}
});