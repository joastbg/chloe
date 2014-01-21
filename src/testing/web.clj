; Attempt to port Raptor to Clojure
; (C) Johan Astborg, 2013

(ns testing.web
  (:use compojure.core)
  (:use ring.middleware.json-params)
  (:use ring.middleware.static)
  (:use testing.gen)
  (:use ring.middleware.params)
  (:use ring.middleware.file)
  (:require [clj-json.core :as json])
  (:require [testing.elem :as elem])
  (:require [clojure.tools.nrepl :as repl]))

(defn json-response [data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/json"}
   :body (json/generate-string data)})

; UUID generator
(defn uuid [] (str (java.util.UUID/randomUUID)))

; pages
(def indexpage (str
    (html
      (head
        (title "HTML-gen using Clojure")
      )
    (body
      (h1 "Welcome to HTML-gen using Clojure"))
      (p "Very nice page")
      (ahref "/elems" "My page")
      (footer
        (hr)
        (small "Start page")
      )
    )
  )
)

(defn nrepl-eval [expr] (with-open [conn (repl/connect :port 31234 )]
     (-> (repl/client conn 1000)    ; message receive timeout required
       (repl/message {:op "eval" :code expr})
       repl/response-values)))

; handlers
(comment
(defroutes handler
  (GET "/elems" []
    (json-response (elem/list)))

  (GET "/elems/:id" [id]
    (json-response (elem/get id)))

  (PUT "/elems/:id" [id attrs]
    (json-response (elem/put id attrs)))

  (DELETE "/elems/:id" [id]
    (json-response (elem/delete id)))

  (GET "/johan" []
    (json-response {"hello" "johan"}))

  (GET "/html" []
    indexpage))

(defroutes handler
  (POST "/eval" [expr]
        (do (println expr))
    (json-response {"eval" (nrepl-eval expr)})))

(defroutes hello
  (POST "/eval" {body :body} (slurp body)))
)

(defn handler [{params :params}]
  (do (println (params "body")))
  {:status  200
   :headers {"Content-Type" "text/plain"}
   :body    (str (first (nrepl-eval (params "body"))))})

;(def app
;  (wrap-params handler)
;  (wrap-resource "public"))



(def app
  (-> (wrap-file handler "/home/gecemmo/Development/clojure-nrepl-rest/public/") (wrap-params handler)))

;(def app
;  (handler/site app-routes))

; main
(comment
(def app
  (-> hello
      wrap-json-params
      (wrap-resource "public")))
)
