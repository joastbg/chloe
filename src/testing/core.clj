(ns testing.core
  (:use ring.adapter.jetty)
  (:use [clojure.tools.nrepl.server :only (start-server stop-server)])
  (:require [testing.web :as web]))

(def nrepl-port 31234)
(def server-port 8080)

(defn start-nrepl-server []
  (defonce server (start-server :port nrepl-port)))

(defn -main [& args]
  (start-nrepl-server)
  (println "Welcome to Chloe" args)
  (run-jetty #'web/app {:port server-port}))
