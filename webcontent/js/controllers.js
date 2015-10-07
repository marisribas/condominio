(function ($app) {
    angular.module('custom.controllers', []);

    app.controller('LoginController', ['$scope', '$http', '$location', '$rootScope', '$window', '$state', function ($scope, $http, $location, $rootScope, $window, $state) {

        $scope.message = {};

        $scope.login = function () {

            $scope.message.error = undefined;

            var user = { username: username.value, password: password.value };

            $http({
                method: 'POST',
                url: 'auth',
                data: $.param(user),
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
            }).success(handleSuccess).error(handleError);
        }
        
        $rootScope.infiniteReached = function() {
          alert("wow!");
        }

        function handleSuccess(data, status, headers, config) {
            // Store data response on session storage
            // The session storage will be cleaned when the browser window is closed
            if(typeof(Storage) !== "undefined") {
                // save the user data on localStorage
                sessionStorage.setItem("_u",JSON.stringify(data));
            } else {
                // Sorry! No Web Storage support.
                // The home page may not work if it depends
                // on the logged user data
            }
            
            // Redirect to home page
            $state.go("home");
        }

        function handleError(data, status, headers, config) {
            var error = status == 401 ? "Username or passoword invalid!" : data;
            $scope.message.error = error;
        }
    }]);

    app.controller('HomeController', ['$scope', '$http', '$rootScope', '$state', function ($scope, $http, $rootScope, $state) {
        
        $scope.selecionado = {
          valor : 1
        }
        
        // When access home page we have to check
        // if the user is authenticated and the userData
        // was saved on the browser's sessionStorage
        $rootScope.session = (sessionStorage._u) ? JSON.parse(sessionStorage._u) : null;
        if(!$rootScope.session) {
          // If there isn't a user registered on the sessionStorage
          // we must send back to login page
          $state.go("login");
        }
        
        $rootScope.logout = function logout() {

            $http({
                method: 'GET',
                url: 'logout',
            }).then(handleSuccess, handleError)

            $rootScope.session = {};
        }

        function handleSuccess(data) {
            // Before redirect to login page we 
            // have to clean the user data from the 
            // session storage
            if(typeof(Storage) !== "undefined") {
                // save the user data on localStorage
                sessionStorage.removeItem("_u");
            } else {
                // It's not working with sessionStorage
            }
            
            $state.go("login");
        }


        function handleError(error) {
            $rootScope.session.error = error;
        }
    }]);
} (app));
