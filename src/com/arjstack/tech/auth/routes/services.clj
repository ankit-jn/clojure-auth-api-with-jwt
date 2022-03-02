(ns com.arjstack.tech.auth.routes.services
  (:require [schema.core :as s]
            [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [ring.swagger.schema :as rs]
			[com.arjstack.tech.auth.services.handlers :as handler]
			[com.arjstack.tech.auth.models.login :as login-model]))

;; Routes

(def auth-routes
  (routes
    (context "/api" []
	  :tags ["authentication"]

    (context "/users" []
  		(POST "/" []
  		  :summary "Create User"
  		  :body [user login-model/User]
            (ok (handler/create-user user))))

	  (context "/login" []
  		(POST "/" []
  		  :summary "Authenticate"
  		  :body [login login-model/Login]
            (ok (handler/auth-user login)))
  		(POST "/refresh-auth-token" []
  		  :summary "Refersh Token"
  		  :body [refresh-token login-model/RefereshToken]
            (ok (handler/refresh-auth-token refresh-token)))
  		(POST "/invalidate-refresh-token" []
  		  :summary "Invalidate Refresh Token"
  		  :body [refresh-token login-model/RefereshToken]
            (ok (handler/invalidate-refresh-token refresh-token))))

    (context "/logout" []
      (POST "/" []
        :summary "Logout"
        :body [delete-token login-model/DeleteToken]
            (ok (handler/delete-auth-token delete-token))))

            )))
