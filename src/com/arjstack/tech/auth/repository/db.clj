(ns com.arjstack.tech.auth.repository.db
  (:require [korma.db :as korma]))

(def db-connection-info 
	(korma/mysql                         
		{:classname "com.mysql.cj.jdbc.Driver"
		 :subprotocol "mysql"
		 :user "root"
		 :password "root"
		 :subname "//localhost:3306/db_auth"}))
		 
; set up korma
(korma/defdb db db-connection-info)
