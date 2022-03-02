(ns com.arjstack.tech.auth.services.handlers
  (:require [com.arjstack.tech.auth.validators.validator-util :as v]
			[com.arjstack.tech.auth.repository.user-repo :as user-repo]
			[com.arjstack.tech.auth.repository.token-repo :as token-repo]
			[com.arjstack.tech.auth.security.token-security :as token-security]
      [buddy.hashers :as hashers]))

;; This handler function is used to create the user
(defn create-user [user]
  (user-repo/create-user user))

;; This handler function is used to authenticate the user and generate the auth tokan and refresh token
(defn auth-user [login]
  (let [user (user-repo/get-user-by-email (:email login))
        unauthed {:message "Invalid username or password"}]
    (if user
		    (let [match (hashers/check (:password login) (:password user))]
          (if match
        			(let [token (token-security/create-token user)]
        			   (token-repo/add-token (:id user) token)
        			   {:token token})
			     unauthed))
		unauthed )))

;; This handler function is used to generate new token pair based on refresh token
(defn refresh-auth-token [refresh-token]
	(let [unsigned (token-security/unsign-token (:token refresh-token))]
		(if unsigned
			(do
				(let [db-token-rec (token-repo/get-token (:email unsigned) (:iat unsigned))
					  user (user-repo/get-user-by-email (:email unsigned))]
					(cond (= (str "A") (:status db-token-rec))
						(let [a (token-repo/invalidate-token (:id db-token-rec))
							  token (token-security/create-token user)]
								(token-repo/add-token (:id user) token)
								{:token token})
						:else {:message "Invalid or expired refresh token is provided"})))
		{:message "Invalid or expired refresh token provided"})))

;; This handler function is used to invalidate the refresh token
(defn invalidate-refresh-token [refresh-token]
	(let [unsigned (token-security/unsign-token (:token refresh-token))]
		(if unsigned
			(do
        (let [user (user-repo/get-user-by-email (:email unsigned))]
            (token-repo/invalidate-token (:id user) (:iat unsigned))
    				[true {:message "Token Invalidated Successfully"}]))
		[false {:message "Invalid or expired refresh token provided"}])))

;; This handler function is used to delete the token pair (logout feature)
(defn delete-auth-token [delete-token]
	(let [db-token-rec (token-repo/get-token (:email delete-token))]
			(cond (= (:token delete-token) (:authToken db-token-rec))
				(let [a (token-repo/delete-token (:id db-token-rec))]
						{:message "Logged out"})
				:else {:message "Invalid Auth token is provided"})))
