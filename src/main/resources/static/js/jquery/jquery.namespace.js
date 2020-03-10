(function($) {
	if ({}.__proto__) {
		// mozilla & webkit expose the prototype chain directly
		$.namespace = function(name) {
			$.fn[name] = function namespace() { // insert this function in the
				// prototype chain
				this.__proto__ = arguments.callee;
				return this;
			};
			$.fn[name].__proto__ = $.fn;
		};
		$.fn.$ = function() {
			this.__proto__ = $.fn;
			return this;
		};
	} else {
		// every other browser; need to copy methods
		$.namespace = function(name) {
			$.fn[name] = function namespace() {
				return this.extend(arguments.callee);
			};
		};
		$.fn.$ = function() { // slow but restores the default namespace
			var len = this.length;
			this.extend($.fn);
			this.length = len; // $.fn has length = 0, which messes everything
			// up
			return this;
		};
	}
})(jQuery);