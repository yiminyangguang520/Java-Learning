"use strict";

var paginationModule = angular.module('simplePagination', []);

paginationModule.factory('Pagination', function() {

    var pagination = {};

    pagination.getNew = function(perPage, numPages) {

        perPage = perPage === undefined ? 5 : perPage;
        numPages = numPages === undefined ? 1 : numPages;

        var paginator = {
            numPages: numPages,
            perPage: perPage,
            page: 0
        };

        paginator.firstPage = function(){
            paginator.page = 0;
        };

        paginator.lastPage = function () {
            paginator.page = paginator.numPages - 1;
        };
                
        paginator.prevPage = function() {
            if (paginator.page > 0) {
                paginator.page -= 1;
            }
        };
        
        paginator.isFirstPage = function(){
            if(paginator.page == 0){
            	return true;
            }
        };
        
        paginator.isLastPage = function(){
            if(paginator.page+1 == paginator.numPages ){
            	return true;
            }
        };

        paginator.nextPage = function() {
            if (paginator.page < paginator.numPages - 1) {
                paginator.page += 1;
            }
        };

        paginator.toPageId = function(id) {
            if (id >= 0 && id <= paginator.numPages - 1) {
                paginator.page = id;
            }
        };

        return paginator;
    };

    return pagination;
});

paginationModule.filter('startFrom', function() {
    return function(input, start) {
        if (input === undefined) {
            return input;
        } else {
            return input.slice(+start);
        }
    };
});

paginationModule.filter('range', function() {
    return function(input, total) {
        total = parseInt(total);
        for (var i = 0; i < total; i++) {
            input.push(i);
        }
        return input;
    };
});
