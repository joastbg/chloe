(defproject rest-testing-one "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-ring "0.8.6"]]
  :dependencies
  	[[org.clojure/clojure "1.4.0"]
  	[org.clojure/clojure-contrib "1.2.0"]
  	[ring/ring-jetty-adapter "0.2.5"]
    [ring-json-params "0.1.0"]
    [compojure "0.4.0"]
    [org.clojure/tools.nrepl "0.2.3"]
    [clj-json "0.2.0"]]
  :main testing.core
  :dev-dependencies [[lein-ring "0.8.6"]]
  :main testing.core
;  :ring {:handler testing.core/core}
  )
