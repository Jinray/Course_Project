/**
 * Created by Alex on 16.02.2016.
 */
angular.module('myApp')
    .directive('commentDirective', function(){
        return {
            template: "<div  id=\'markpost\'  class=\'f-infoblock-with-icon__info_text b-infoblock-with-icon__info_text c-primary b-blog-listing__text\'></div>",
            restrict: "E",
            link: function(scope, elem, attrs) {
                var div = document.createElement('div');
                div.innerHTML = scope.comment.text;
                elem.append(div);
            }
        }
    });