(ns com.arjstack.tech.auth.security.token-security
  (:require [clojure.java.io :as io]
			[buddy.core.keys :as ks]
			[buddy.sign.jwt :as jwt]
			[clj-time.core :as t]
			[clj-time.coerce :as coerce]))

(defn- priv-key [auth-conf]
  (ks/private-key
   (io/resource (:privkey auth-conf))
   (:passphrase auth-conf)))

(defn- pub-key [auth-conf]
  (ks/public-key
   (io/resource (:pubkey auth-conf))))

(defn- create-auth-token [auth-conf user]
  (let [exp (-> (t/plus (t/now) (t/minutes 30)) (coerce/to-long))]
    (jwt/sign {:user (dissoc user :password :id)}
              (priv-key auth-conf)
              {:alg :rs256 :exp exp})))

(defn- create-refresh-token [auth-conf user]
  (let [issued-at (-> ( t/now) (coerce/to-long))
        token (jwt/sign {:email (:email user)}
                        (priv-key auth-conf)
                        {:alg :rs256 :iat issued-at :exp (-> (t/plus (t/now) (t/days 3)) (coerce/to-long))})]
    {:issued-at issued-at :token token}))


(defn create-token [user]
	(let [auth-conf {:privkey "auth_privkey.pem" :passphrase "<YOUR PASSPHRASE>"}]
		(let [auth-token (create-auth-token auth-conf user)
			 refresh-token-data (create-refresh-token auth-conf user)]
		{:auth-token auth-token
				 :refresh-token (:token refresh-token-data)
				 :issued-at (:issued-at refresh-token-data)})))


(defn unsign-token [token]
	(let [auth-conf {:pubkey "auth_pubkey.pem"}]
		(jwt/unsign token (pub-key auth-conf) {:alg :rs256})))
