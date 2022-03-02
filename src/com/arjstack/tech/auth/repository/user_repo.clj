(ns com.arjstack.tech.auth.repository.user-repo
  (:require [korma.core :refer :all]
      			[com.arjstack.tech.auth.repository.db :refer [db]]
            [com.arjstack.tech.auth.models.entities :as entity]
            [buddy.hashers :as hashers]))

(defentity users)

(defn create-user [user]
  (let [{:keys [email password]} user]
	   (insert entity/users
          (values {:email email
				           :password (hashers/encrypt password)}))))

(defn get-user-by-email [email]
  (first (select entity/users
            (with entity/roles)
            (where {:email email}))))
