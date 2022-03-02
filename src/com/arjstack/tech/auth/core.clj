(ns com.arjstack.tech.auth.core
  (:require [ring.util.http-response :refer :all]
            [compojure.api.sweet :refer :all]
            [compojure.api.upload :refer :all]
            [schema.core :as s]
            ring.swagger.json-schema-dirty
            ring.middleware.multipart-params.byte-array
            [com.arjstack.tech.auth.routes.services :refer [auth-routes]]
            [clojure.java.io :as io])

  (:import (org.joda.time DateTime)
           (java.io File)))

;; Routes
;;

(def app
  (api
    {:swagger
     {:ui "/"
      :spec "/swagger.json"
      :data {:info {:version "0.0.1-RELEASE"
                    :title "Clojure Authentication API backed by JWT"
                    :description "This is an authentication service written in clojure backed by JWT mechanism"
                    :contact {:name "Ankit Jain"
                              :email "ankii.jain@gmail.com"
                              :url "https://www.arjstack.com/#/ankit"}}
             :tags [
                    {:name "auth", :description "Authentication API"}
                  ]}}}

    auth-routes))
