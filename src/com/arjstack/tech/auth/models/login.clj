(ns com.arjstack.tech.auth.models.login
  (:require [schema.core :as s]
			[com.arjstack.tech.auth.validators.validator-util :as v]))

(defn valid-value? [value]
	(v/non-blank? value))

(s/defschema User {:email
                				(s/constrained s/Str
                				 valid-value? (str "Email is missing"))
                	 :password
                	 		  (s/constrained s/Str
                				 valid-value? (str "Password is missing"))})

(s/defschema Login {:email
              				(s/constrained s/Str
              				 valid-value? (str "Email is missing"))
              			:password
              				(s/constrained s/Str
              				 valid-value? (str "Password is missing"))})

(s/defschema RefereshToken {:token
                      				(s/constrained s/Str
                      				 valid-value? (str "Refresh token is missing"))})

(s/defschema DeleteToken {:email
                             (s/constrained s/Str
                              valid-value? (str "Email is missing"))
                          :token
                      				(s/constrained s/Str
                      				 valid-value? (str "Auth token is missing"))})
