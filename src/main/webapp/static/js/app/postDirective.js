angular.module('myApp')
.directive('posttextDirective', function(){
    return {
        template: "<div  id=\'markpost\'  class=\'f-infoblock-with-icon__info_text b-infoblock-with-icon__info_text c-primary b-blog-listing__text\'></div>",
        restrict: "E",
        link: function(scope, elem, attrs) {
            var div = document.createElement('div');
            div.innerHTML = scope.post.text;
            elem.append(div);
        }
    }
});