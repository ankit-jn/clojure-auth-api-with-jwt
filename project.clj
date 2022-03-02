(defproject auth-service "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [metosin/compojure-api "2.0.0-alpha30"]
                 [http-kit "2.3.0"]
                 [reloaded.repl "0.2.4"]
				 [korma "0.4.3"]
				 [mysql/mysql-connector-java "8.0.12"]
				 [buddy/buddy-sign "3.1.0" :exclusions [[org.clojure/clojure] [buddy/buddy-core]]]
				 [buddy/buddy-hashers "1.4.0" :exclusions [[org.clojure/clojure] [buddy/buddy-core]]]
				 [buddy/buddy-core "1.6.0" :exclusions [org.clojure/clojure]]
				]
  :ring {:handler com.arjstack.tech.auth.core/app
         :port 8080}
  :main com.arjstack.tech.auth.server
  :uberjar-name "auth-service.jar"
  :profiles {:dev {:dependencies [[javax.servlet/javax.servlet-api "3.1.0"]]
                  :plugins [[lein-ring "0.12.5"]]}})
