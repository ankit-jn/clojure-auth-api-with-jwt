(ns com.arjstack.tech.auth.models.entities
  (:use korma.core
        com.arjstack.tech.auth.repository.db))

(declare users tokens roles)

(defentity roles
  (pk :id)
  (table :roles))

(defentity users
  (pk :id)
  (table :users)
  (many-to-many roles :user_roles {:lfk :user_id :rfk :role_id}))

;; This is to define mapping of tokens table
(defentity tokens
  (pk :id)
  (table :tokens)
  (belongs-to users {:fk :user_id}))
