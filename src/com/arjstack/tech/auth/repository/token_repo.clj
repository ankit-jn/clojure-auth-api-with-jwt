(ns com.arjstack.tech.auth.repository.token-repo
  (:require [korma.core :refer :all]
			[com.arjstack.tech.auth.repository.db :refer [db]]
      [com.arjstack.tech.auth.models.entities :as entity]))

;; This function is used to insert a record for token pair in database
(defn add-token [user_id token]
	(insert entity/tokens
          (values {:user_id user_id
				   :authToken (:auth-token token)
				   :refreshToken (:refresh-token token)
				   :issued_at (:issued-at token)
				   :status (str "A")})))

;; This function is used to get the token details
(defn get-token
  ;; This function is used to get the token details based on user email and issued time
  ([email issued-at]
	   (first (select entity/tokens
              (where {:users.email email :issued_at issued-at})
              (with entity/users))))
  ;; This function is used to get the token details based on user email
  ([email]
    (first (select entity/tokens
             (where {:users.email email :status (str "A")})
             (with entity/users)))))

;; This function is used to invalidate the refresh token
(defn invalidate-token
  ;; This function is used to invalidate the refresh token based on user id and token issued time
	([user_id issued-at]
		(update entity/tokens
			(set-fields {:status (str "I")})
			(where {:user_id user_id :issued_at issued-at})
      )
	)
  ;; This function is used to invalidate the refresh token based on token record id
	([id]
		(update entity/tokens
			(set-fields {:status (str "I")})
			(where {:id id}))
	))

;; This function is used to delete the token
(defn delete-token [id]
	(delete entity/tokens
				(where {:id id})))
